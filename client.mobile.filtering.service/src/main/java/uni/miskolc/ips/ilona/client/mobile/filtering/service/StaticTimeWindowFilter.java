/**
 * 
 */
package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Static Time Window Filtering algorithm
     * Filters the RSSI values of the observation.
     */
    @Override
    public WiFiRSSIObservation filter(List<WiFiRSSIObservation> observations, long timestamp) {
        System.out.println("Memsize: " + memsize);
        if (observations.size() < memsize) {
            return observations.get(memsize - 2);
        }
        double filteredValue;
        Map observation = new HashMap();
        WiFiRSSIObservation result = new WiFiRSSIObservation();
        ArrayList<Double> rssiValues=new ArrayList<Double>();
        for (String ssid : getKeys(observations) ) {

            rssiValues = getWiFiRSSIVector(ssid, observations, memsize);

            if (rssiValues.size() > 0) {
                filteredValue = rssiValues.get(0);

                if (rssiValues.size() > 1) {
                    double difference = rssiValues.get(0) - rssiValues.get(1);

        
                    System.out.println("differnece" + difference);
                    System.out.println("ecc");
                    if ((Math.abs(difference) > threshold)) {
                        System.out.println("pecc");
                        filteredValue = filterSum(rssiValues);
                        System.out.println("Static time lófasz filteredvalue:" + filteredValue);
                    }
                }

                observation.put(ssid, filteredValue);
                result = new WiFiRSSIObservation(timestamp, observation);

            }
        }
        return result;
    }
   
}
