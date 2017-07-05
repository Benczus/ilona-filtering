/**
 * 
 */
package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import java.util.ArrayList;
import java.util.LinkedList;

import org.client.mobile.filtering.core.service.WiFiRSSIFilter;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.Statistics;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

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
	    public WiFiRSSIObservation filter(LinkedList<WiFiRSSIObservation> observation) {
	        if (observation.size() < memsize) {
	            return observation.getFirst();
	        }
	        double filteredValue;
	        WiFiRSSIObservation result= new WiFiRSSIObservation();
	        ArrayList<Double> rssiValues=new ArrayList<Double>();
	        for (String ssid : getKeys(observation)) {
	            rssiValues = getWiFiRSSIVector(ssid, observation);
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
