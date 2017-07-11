package uni.miskolc.ips.ilona.client.mobile.filtering.core.service;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bence on 2017.07.11..
 */
public abstract class WiFiRSSITimeWindowFilter extends WiFiRSSIFilter {


    private double threshold;
    private int memsize;

    public WiFiRSSITimeWindowFilter(double threshold, int memsize) {
        this.threshold = threshold;
        this.memsize = memsize;
    }

    public WiFiRSSIObservation filter(List<WiFiRSSIObservation> observations) {

        if (observations.size() < memsize) {

            return observations.get(observations.size() - 1);
        }
        double filteredValue;
        Map observation = new HashMap();
        WiFiRSSIObservation result = new WiFiRSSIObservation();
        ArrayList<Double> rssiValues;

        for (String ssid : getKeys(observations, memsize)) {
            rssiValues = getWiFiRSSIVector(ssid, observations, memsize);

            if (rssiValues.size() > 0) {
                filteredValue = rssiValues.get(memsize - 1);

                if (rssiValues.size() > 1) {
                    double difference = rssiValues.get(memsize - 1) - rssiValues.get(memsize - 2);

                    if ((Math.abs(difference) > threshold)) {

                        filteredValue = filterSum(rssiValues, memsize);
                    }
                }
                observation.put(ssid, filteredValue);
                //TODO: timestamp extract from observation list
                result = new WiFiRSSIObservation(System.currentTimeMillis(), observation);
                refreshThreshold(rssiValues);
            }
        }
        return result;
    }

    public double getThreshold() {
        return threshold;
    }

    protected void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int getMemsize() {
        return memsize;
    }

    public void setMemsize(int memsize) {
        this.memsize = memsize;
    }

    protected abstract void refreshThreshold(ArrayList<Double> rssiValues);
}
