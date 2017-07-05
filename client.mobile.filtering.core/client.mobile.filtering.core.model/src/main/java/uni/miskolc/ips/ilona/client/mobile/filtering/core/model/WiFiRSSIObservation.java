package uni.miskolc.ips.ilona.client.mobile.filtering.core.model;

import java.util.Collection;
import java.util.Map;

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

	public WiFiRSSIObservation(long timestamp, Collection<ScanResult> scanresults) {
		super();
		this.timestamp = timestamp;

		for (ScanResult scanresult : scanresults) {
			observation.put(scanresult.SSID, (double) scanresult.level);
		}

	}

	public long getTimestamp() {
		return timestamp;
	}

	public Map<String, Double> getObservation() {
		return observation;
	}

	public int getSize() {
		int size = observation.size();
		return size;
	}

	public void put(String ssid, double filteredValue) {
		observation.put(ssid, filteredValue);

	}

}
