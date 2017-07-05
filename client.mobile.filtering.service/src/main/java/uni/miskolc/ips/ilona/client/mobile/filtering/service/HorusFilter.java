package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.client.mobile.filtering.core.service.WiFiRSSIFilter;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

public class HorusFilter extends WiFiRSSIFilter {
	private final int memsize;
	
	public HorusFilter(int memsize) {
        this.memsize = memsize;
    }

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
