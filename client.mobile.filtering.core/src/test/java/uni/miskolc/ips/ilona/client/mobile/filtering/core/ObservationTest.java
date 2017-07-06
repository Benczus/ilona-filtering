package uni.miskolc.ips.ilona.client.mobile.filtering.core;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import android.net.wifi.ScanResult;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;

public class ObservationTest {
	
	List<ScanResult> scanresults= new ArrayList<ScanResult>();
	long timestamp;
	@Before
	public void setUp() throws Exception {
		ScanResult scanresult1=EasyMock.createMock(ScanResult.class);
		
		scanresult1.SSID="Cica";
		scanresult1.level=39;
		scanresult1.BSSID="cica";
		scanresult1.capabilities="cica";
		scanresult1.frequency=123;
		ScanResult scanresult2=EasyMock.createMock(ScanResult.class);
		scanresult2.SSID="Kutya";
				scanresult2.level=42;
				scanresult2.BSSID="kutya";
				scanresult2.capabilities="kutya";
				scanresult2.frequency=123;
		ScanResult scanresult3=EasyMock.createMock(ScanResult.class);
		scanresult2.SSID="Majom";
				scanresult2.level=40;	
				scanresult2.BSSID="majom";
				scanresult2.capabilities="majom";
				scanresult2.frequency=123;
				System.out.println("1\t"+scanresult1.SSID);
		scanresults.add(scanresult1);
		scanresults.add(scanresult2);
		scanresults.add(scanresult3);	
		System.out.println(scanresults);
		WiFiRSSIObservation observation= new WiFiRSSIObservation(timestamp, scanresults);
		EasyMock.expect(observation= new WiFiRSSIObservation(timestamp, scanresults)).andReturn(observation).anyTimes();
		EasyMock.replay(observation);
		
	}
	
	@Test
	public void ScanResultsTest() {
		
		WiFiRSSIObservation observation= new WiFiRSSIObservation(timestamp, scanresults);
		
		System.out.println("1\t"+observation);
		
	}
	@Test
	public void ObservationResultsTest() {
		
		WiFiRSSIObservation observation= new WiFiRSSIObservation(timestamp, scanresults);
		
		System.out.println("1\t"+observation);
		
	}
	
}
