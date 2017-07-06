/**
 * 
 */
package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import java.util.ArrayList;
import java.util.LinkedList;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

/**
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 *
 */
public class StaticTimeWindowFilter extends WiFiRSSIFilter {

	private final double threshold;
    private final int memsize;


    public StaticTimeWindowFilter(int memsize, double threshold) {
        this.threshold = threshold;
        this.memsize = memsize;
    }

    //Main filtering method
/*
    @Override
    public Map<String, Double> filter(LinkedList<Map<String, Double>> linkedList) {
        if (linkedList.size() < memsize) {
            return linkedList.getFirst();
        }
        double filteredValue;
        Map<String, Double> result = new HashMap<String, Double>();
        ArrayList<Double> rssiValues;
        for (String ssid : getKeys(linkedList)) {
            rssiValues = getWiFiRSSIVector(ssid, linkedList);
            if (rssiValues.size() > 0) {
                filteredValue = rssiValues.get(0);
                if (rssiValues.size() > 1) {
                    double difference = rssiValues.get(0) - rssiValues.get(1);
                    if ((difference > threshold)) {
                        filteredValue = filterSum(rssiValues);
                    }
                }
                result.put(ssid, filteredValue);
            }

        }
        return result;
    }*/
    /**
     * Static Time Window Filtering algorithm
     * Filters the RSSI values of the observation.
     */
    @Override
    public WiFiRSSIObservation filter(LinkedList<WiFiRSSIObservation> observations) {
        if (observations.size() < memsize) {
            return observations.getFirst();
        }
        double filteredValue;
        WiFiRSSIObservation result= new WiFiRSSIObservation();
        ArrayList<Double> rssiValues=new ArrayList<Double>();
        for (String ssid : getKeys(observations) ) {
            rssiValues = getWiFiRSSIVector(ssid, observations);
            if (rssiValues.size() > 0) {
                filteredValue = rssiValues.get(0);
                if (rssiValues.size() > 1) {
                    double difference = rssiValues.get(0) - rssiValues.get(1);
                    if ((difference > threshold)) {
                        filteredValue = filterSum(rssiValues);
                    }
                }
                result.put(ssid, filteredValue);
            }
        }
        return result;
    }
   
}
