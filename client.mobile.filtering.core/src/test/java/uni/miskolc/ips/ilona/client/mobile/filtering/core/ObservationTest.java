package uni.miskolc.ips.ilona.client.mobile.filtering.core;

import android.net.wifi.ScanResult;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ObservationTest {

    private List<ScanResult> scanresults = new ArrayList<ScanResult>();
    private long timestamp;

    @Before
    public void setUp() throws Exception {
        ScanResult scanresult1 = EasyMock.createMock(ScanResult.class);

        scanresult1.SSID = "ap1";
        scanresult1.level = -39;
        scanresult1.BSSID = "ap1";
        scanresult1.capabilities = "idk";
        scanresult1.frequency = 123;
        ScanResult scanresult2 = EasyMock.createMock(ScanResult.class);
        scanresult2.SSID = "ap2";
        scanresult2.level = -42;
        scanresult2.BSSID = "ap2";
        scanresult2.capabilities = "idk";
        scanresult2.frequency = 123;
        ScanResult scanresult3 = EasyMock.createMock(ScanResult.class);
        scanresult3.SSID = "ap3";
        scanresult3.level = -40;
        scanresult3.BSSID = "ap2";
        scanresult3.capabilities = "idk";
        scanresult3.frequency = 123;

        for (ScanResult sr : scanresults) {
            System.out.println(sr.SSID);
        }
        scanresults.add(scanresult1);
        scanresults.add(scanresult2);
        scanresults.add(scanresult3);
        System.out.println(scanresults.get(0).SSID);
        System.out.println(scanresults.get(0).level);
        WiFiRSSIObservation observation = new WiFiRSSIObservation(timestamp, scanresults);
    /*	WiFiRSSIObservation observation= new WiFiRSSIObservation();
        System.out.println(observation);
		observation.put(scanresults.get(0).SSID, scanresults.get(0).level);
		observation.put(scanresults.get(1).SSID, scanresults.get(1).level);
		observation.put(scanresults.get(2).SSID, scanresults.get(2).level);
		System.out.println("kutya\t"+observation.getObservation());*/
        EasyMock.replay(scanresult1);
        EasyMock.replay(scanresult2);
        EasyMock.replay(scanresult3);

    }

    @Test
    public void ScanResultsTest() {

        WiFiRSSIObservation observation = new WiFiRSSIObservation(timestamp, scanresults);
        Map<String, Double> expected = new HashMap<String, Double>();
        expected.put("ap1", (double) -39);
        expected.put("ap2", (double) -42);
        expected.put("ap3", (double) -40);
        System.out.println("1\t" + observation.getObservation());
        System.out.println(expected);

        assertEquals(observation.getObservation().keySet(), expected.keySet());
        assertEquals(observation.getObservation().entrySet(), expected.entrySet());

    }

    @Ignore
    @Test
    public void ObservationResultsTest() {

        WiFiRSSIObservation observation = new WiFiRSSIObservation(timestamp, scanresults);

        System.out.println("1\t" + observation);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullObservation() {
        new WiFiRSSIObservation(0, (Collection<ScanResult>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTimestamp() {
        new WiFiRSSIObservation(-1, new HashMap<String, Double>());
    }


}
