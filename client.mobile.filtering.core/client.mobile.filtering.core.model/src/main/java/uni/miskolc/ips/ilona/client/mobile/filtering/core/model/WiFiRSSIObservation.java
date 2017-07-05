package uni.miskolc.ips.ilona.client.mobile.filtering.core.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;

import android.net.wifi.ScanResult;

public class WiFiRSSIObservation {
	
	private long timestamp;
	private Map<String, Double> observation;
	public WiFiRSSIObservation() {
		super();
	}
	

	public WiFiRSSIObservation(long timestamp, Map<String, Double> observation) {
		super();
		this.timestamp = timestamp;
		this.observation = observation;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public Map<String, Double> getObservation() {
		return observation;
	}

	public int getSize() {
		int size=observation.size();
		return size;
	}

	
	
}
