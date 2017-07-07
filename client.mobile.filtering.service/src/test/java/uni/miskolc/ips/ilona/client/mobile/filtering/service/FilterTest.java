package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import java.util.List;
import java.util.Map;
import org.hamcrest.*;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

public class FilterTest {
	WiFiRSSIFilter filter;
	int memsize=5;
	double treshold=5;
	Map<String,Double> map;
	long timestamp;
	WiFiRSSIObservation returnedObservation= new WiFiRSSIObservation();
	// TODO observations list!!
	List<WiFiRSSIObservation> observations;
	
	@Before
	public void setUp() throws Exception {
		filter= EasyMock.createMock(DynamicTimeWindowFilter.class);
		EasyMock.expect(filter.filter(observations)).andReturn(returnedObservation).anyTimes();
		EasyMock.replay(filter);
		
	}

	@Test
	public void testWithOneObservation() {
		WiFiRSSIObservation observation= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation);
		WiFiRSSIObservation result= filter.filter(observations);
	}
	@Test
	public void testWithMultipleObservations() {
		
		WiFiRSSIObservation observation1= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation2= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation3= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation4= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation1);
		observations.add(observation2);
		observations.add(observation3);
		observations.add(observation4);
		WiFiRSSIObservation result= filter.filter(observations);
		
	}

}
