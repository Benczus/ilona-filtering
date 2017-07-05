package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import java.util.ArrayList;
import java.util.LinkedList;

import org.client.mobile.filtering.core.service.WiFiRSSIFilter;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
/**
 * 
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 *
 */
public class HorusFilter extends WiFiRSSIFilter {
	private final int memsize;
	
	public HorusFilter(int memsize) {
        this.memsize = memsize;
    }
	 /**
     * Horus Filtering algorithm
     * Filters the RSSI values of the observation.
     */
	@Override
	public WiFiRSSIObservation filter(LinkedList<WiFiRSSIObservation> observation) {
		 if (observation.size() < memsize) {
	            return observation.getFirst();
	        }
		 WiFiRSSIObservation result= new WiFiRSSIObservation();
	        for (String ssid : getKeys(observation)) {
	            ArrayList<Double> rssiValues = getWiFiRSSIVector(ssid, observation);
	            double filteredValue = filterSum(rssiValues);
	            result.put(ssid, filteredValue);
	        }
	        return result;
	}

}
