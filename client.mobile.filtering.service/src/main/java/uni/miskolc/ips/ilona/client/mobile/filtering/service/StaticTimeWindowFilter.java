
package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSITimeWindowFilter;

import java.util.ArrayList;

/**
 * @author Bence Bogdandy, bence.bogdandy@gmail.com
 */
public class StaticTimeWindowFilter extends WiFiRSSITimeWindowFilter {


    public StaticTimeWindowFilter(double threshold, int memsize) {
        super(threshold, memsize);
    }

    protected void refreshThreshold(ArrayList<Double> rssiValues) {

    }

}



