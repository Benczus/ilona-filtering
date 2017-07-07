package uni.miskolc.ips.ilona.client.mobile.filtering.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.net.wifi.ScanResult;
/**
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 */
public class WiFiRSSIObservation {

	private long timestamp;
	private Map<String, Double> observation;

	public WiFiRSSIObservation() {
		super();
	}
/**
 * @param timestamp The timestamp of the scan.
 * @param observation Map of String-Doubles of the SSID-RSSI values
 */
	public WiFiRSSIObservation(long timestamp, Map<String, Double> observation) {
		super();
		this.timestamp = timestamp;
		this.observation = observation;
	}
/**
 * @param timestamp The timestamp of the scan.
 * @param scanresults The scan results(SSID,RSSI values) of the scan.
 * TODO NEEDS TEST
 */
	public WiFiRSSIObservation(long timestamp, Collection<ScanResult> scanresults) {
		super();
		this.timestamp = timestamp;
		Map<String, Double> scanObservation=new HashMap<String, Double>();
		for (ScanResult scanresult : scanresults) {
			
			scanObservation.put(scanresult.SSID, (double) scanresult.level);
		}
		this.observation=scanObservation;

	}

	public long getTimestamp() {
		return timestamp;
	}

	public Map<String, Double> getObservation() {
		return observation;
	}

	public int getSize() {
		return observation.size();
	}

	public void put(String ssid, double filteredValue) {
		observation.put(ssid, filteredValue);

	}

}
