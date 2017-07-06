package uni.miskolc.ips.ilona.client.mobile.filtering.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

public abstract class WiFiRSSIFilter {
	private int memsize;
	
	//public abstract Map<String, Double> filter(LinkedList<Map<String, Double>> linkedList);
	public abstract WiFiRSSIObservation filter(List<WiFiRSSIObservation> observations);
	/*
	protected Set<String> getKeys(LinkedList<Map<String, Double>> linkedList) {
        Set<String> result = new HashSet<String>();
        for (int i = 0; i < linkedList.size(); i++) {
            result.addAll(linkedList.get(i).keySet());
        }
        return result;
    }

    protected ArrayList<Double> getWiFiRSSIVector(String ssid, LinkedList<Map<String, Double>> linkedList) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = 0; i < memsize; i++) {
            if (linkedList.get(i).get(ssid) != null) {
                result.add(linkedList.get(i).get(ssid));
            }
        }
        return result;
    }
*/
	protected Set<String> getKeys(List<WiFiRSSIObservation> observation) {
        Set<String> result = new HashSet<String>();
        for (int i = 0; i < observation.size(); i++) {
            result.addAll(observation.get(i).getObservation().keySet());
        }
        return result;
    }

    protected ArrayList<Double> getWiFiRSSIVector(String ssid, List<WiFiRSSIObservation> observations) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = 0; i < memsize; i++) {
            if (observations.get(i).getObservation().get(ssid) != null) {
                result.add(observations.get(i).getObservation().get(ssid));
            }
        }
        return result;
    }
    
    protected double filterSum(ArrayList<Double> m) {
        double sum = 0;
        for (int i = 0; i < m.size(); i++) {
            sum += m.get(i);
        }
        return sum / m.size();
    }
	
}
