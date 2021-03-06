package uni.miskolc.ips.ilona.client.mobile.filtering.core.model;

import android.net.wifi.ScanResult;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 */
public class WiFiRSSIObservation {

    private long timestamp;
    private Map<String, Double> observation = new HashMap<String, Double>();

    public WiFiRSSIObservation() {
        super();
    }

    /**
     * @param timestamp   The timestamp of the scan.
     * @param observation Map of String-Doubles of the SSID-RSSI values
     */
    public WiFiRSSIObservation(long timestamp, Map<String, Double> observation) throws IllegalArgumentException {
        super();
        if (observation == null || timestamp < 0) {
            throw new IllegalArgumentException();
        }
        this.timestamp = timestamp;
        this.observation = observation;
    }

    /**
     * @param timestamp   The timestamp of the scan.
     * @param scanresults The scan results(SSID,RSSI values) of the scan.
     */
    public WiFiRSSIObservation(long timestamp, Collection<ScanResult> scanresults) throws IllegalArgumentException {
        super();
        if (scanresults == null || timestamp < 0) {
            throw new IllegalArgumentException();
        }
        this.timestamp = timestamp;
        Map<String, Double> scanObservation = new HashMap<String, Double>();
        for (ScanResult scanresult : scanresults) {

            scanObservation.put(scanresult.SSID, (double) scanresult.level);
        }
        this.observation = scanObservation;

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

        System.out.println("WiFiRSSIObservation ssid: " + ssid);
        System.out.println("WiFiRSSIObservation filteredValue: " + filteredValue);
        observation.put(ssid, filteredValue);

    }

}
