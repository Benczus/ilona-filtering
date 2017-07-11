package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 */
public class HorusFilter extends WiFiRSSIFilter {
    private final int memsize;

    HorusFilter(int memsize) {
        this.memsize = memsize;
    }


    /**
     * Horus Filtering algorithm
     * Filters the RSSI values of the observation.
     */
    @Override
    public WiFiRSSIObservation filter(List<WiFiRSSIObservation> observation) {
        if (observation.size() < memsize) {
            return observation.get(0);
        }
        WiFiRSSIObservation result = new WiFiRSSIObservation();
        for (String ssid : getKeys(observation, memsize)) {
            ArrayList<Double> rssiValues = getWiFiRSSIVector(ssid, observation, memsize);
            double filteredValue = filterSum(rssiValues, memsize);

            System.out.println("filter ssid: " + ssid);
            System.out.println("filter filteredValue: " + filteredValue);
            result.put(ssid, filteredValue);
        }
        return result;
    }

}
