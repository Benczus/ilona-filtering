package uni.miskolc.ips.ilona.client.mobile.filtering.core.service;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract filtering Strategy class.
 * Implemented by Horus, Static Time Window, and Dynamic Time Window filters.
 *
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 */
public abstract class WiFiRSSIFilter {
    public abstract WiFiRSSIObservation filter(List<WiFiRSSIObservation> observations);

    /**
     * Extracts a set of SSID keys from an observation object
     *
     * @param observation The list of observations containing the SSID keys.
     * @return a string set of SSID keys.
     */
    protected Set<String> getKeys(List<WiFiRSSIObservation> observation, int memsize) {
        Set<String> result = new HashSet<String>();
        for (int i = 0; i < memsize - 1; i++) {
            result.addAll(observation.get(i).getObservation().keySet());
        }
        return result;
    }

    /**
     * Extracts an array of RSSI values of the SSID WiFi beacon.
     *
     * @param ssid         The SSID key value of the WiFi beacon
     * @param observations List of observations that the algorithm extracts the RSSI values from.
     * @return an ArrayList of RSSI values of the SSID WiFi beacon.
     */

    protected ArrayList<Double> getWiFiRSSIVector(String ssid, List<WiFiRSSIObservation> observations, int memsize) {
        ArrayList<Double> result = new ArrayList<Double>();
        //System.out.println("(GetWiFiRSSIVector)observations size : "+ observations.size() +"memory size: "+memsize);
        for (int i = observations.size() - 1; i >= observations.size() - memsize; i--) {
            //System.out.println("getWiFiRSSIVector i: "+i);
            if (observations.get(i).getObservation().get(ssid) != null) {
                result.add(observations.get(i).getObservation().get(ssid));
            }
        }
        return result;
    }

    /**
     * @param rssiValues
     * @param memsize
     * @return
     */
    protected double filterSum(ArrayList<Double> rssiValues, int memsize) {
        // //System.out.println("(filterSum)"+rssiValues + " "+ memsize);
        double sum = 0;
        for (int i = memsize - 1; i >= rssiValues.size() - memsize; i--) {
            sum += rssiValues.get(i);
        }
        return sum / memsize;
    }

}
