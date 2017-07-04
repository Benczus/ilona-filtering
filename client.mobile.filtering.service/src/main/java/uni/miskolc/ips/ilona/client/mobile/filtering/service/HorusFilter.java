package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.client.mobile.filtering.core.service.WiFiRSSIFilter;

public class HorusFilter extends WiFiRSSIFilter {
	private final int memsize;
	
	public HorusFilter(int memsize) {
        this.memsize = memsize;
    }

	@Override
	public Map<String, Double> filter(LinkedList<Map<String, Double>> linkedList) {
		 if (linkedList.size() < memsize) {
	            return linkedList.getFirst();
	        }
	        Map<String, Double> result = new HashMap<String, Double>();
	        for (String ssid : getKeys(linkedList)) {
	            ArrayList<Double> rssiValues = getWiFiRSSIVector(ssid, linkedList);
	            double filteredValue = filterSum(rssiValues);
	            result.put(ssid, filteredValue);
	        }
	        return result;
	}

}
