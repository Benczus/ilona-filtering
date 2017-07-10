/**
 * 
 */
package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.Statistics;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 *
 */
public class DynamicTimeWindowFilter extends WiFiRSSIFilter {
	  private final int memsize;
	    private double threshold;

	    public DynamicTimeWindowFilter(int memsize, double threshold) {
	        this.threshold = threshold;
	        this.memsize = memsize;

	    }
	    /**
	     * Dynamic Time Window Filtering algorithm
	     * Filters the RSSI values of the observation.
	     */
	    @Override
        public WiFiRSSIObservation filter(List<WiFiRSSIObservation> observation, long timestamp) {
            if (observation.size() < memsize) {
	            return observation.get(0);
	        }
	        double filteredValue;
	        WiFiRSSIObservation result= new WiFiRSSIObservation();
	        ArrayList<Double> rssiValues=new ArrayList<Double>();
	        for (String ssid : getKeys(observation)) {
                rssiValues = getWiFiRSSIVector(ssid, observation, memsize);
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
	        threshold = new Statistics(rssiValues).getStdDev();
	        return result;
	    }
}
