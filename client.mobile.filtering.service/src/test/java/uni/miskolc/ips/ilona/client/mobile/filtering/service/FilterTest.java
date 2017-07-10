package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import org.junit.Before;
import org.junit.Test;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

import java.util.*;

public class FilterTest {
	
	Map<String,Double> map= new HashMap<String, Double>();
	long timestamp;
	// TODO observations list!!
	List<WiFiRSSIObservation> observations = new ArrayList<WiFiRSSIObservation>() ;
	private int memsize = 5;
	private double threshold = 5;
	WiFiRSSIFilter filter = new StaticTimeWindowFilter(memsize, threshold);
	
	@Before
	public void setUp() throws Exception {
		Random rand = new Random();
		int n = 0;
		for (int i = 0; i < 5; i++) {
			n = -1 * (rand.nextInt(50) + 1);
			map.put("api1", (double) n);
		}
		
	}

	@Test
	public void testWithOneObservation() {
		System.out.println("testWithOneObservations");
		WiFiRSSIObservation observation= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation);
		
		WiFiRSSIObservation result= filter.filter(observations);
		System.out.println("\n"+observations);
		System.out.println("\n---------------------NEXT TEST----------------------\n");
		System.out.println(observations.get(0));
		System.out.println(observations.get(0).getObservation());
		System.out.println(result);
		System.out.println(result.getObservation());
	}
	@Test
	public void testWithMultipleObservations() {
		System.out.println("testWithMultipleObservations");
		WiFiRSSIObservation observation1= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation2= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation3= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation4= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation1);
		observations.add(observation2);
		observations.add(observation3);
		observations.add(observation4);
		WiFiRSSIObservation result= filter.filter(observations);System.out.println(observations);
		System.out.println("\n"+observations);
		System.out.println("\n---------------------NEXT TEST----------------------\n");
		System.out.println(observations.get(0));
		System.out.println(observations.get(0).getObservation());
		System.out.println(result);
		System.out.println(result.getObservation());
		
	}
	@Test
	public void testWithfiveObservations() {
		System.out.println("testWithfiveObservations");
		WiFiRSSIObservation observation1= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation2= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation3= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation4= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation5= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation1);
		observations.add(observation2);
		observations.add(observation3);
		observations.add(observation4);
		observations.add(observation5);
		WiFiRSSIObservation result= filter.filter(observations);
		System.out.println("\n"+observations);
		System.out.println("\n---------------------NEXT TEST----------------------\n");
		System.out.println(observations.get(0));
		System.out.println(observations.get(0).getObservation());
		System.out.println(result);
		System.out.println(result.getObservation());
	}
	@Test
	public void testWithMoreThanfiveObservations() {
		System.out.println("testWithMoreThanfiveObservations");
		WiFiRSSIObservation observation1= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation2= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation3= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation4= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation5= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation6= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation1);
		observations.add(observation2);
		observations.add(observation3);
		observations.add(observation4);
		observations.add(observation5);
		observations.add(observation6);
		WiFiRSSIObservation result = filter.filter(observations);
			result=	filter.filter(observations);
			System.out.println("\n"+observations);
			System.out.println("\n---------------------NEXT TEST----------------------\n");
			System.out.println(observations.get(0));
			System.out.println(observations.get(0).getObservation());
			System.out.println(result);
			System.out.println(result.getObservation());
	}


	@Test
	public void testWithOutlyingObservation() {
		System.out.println("testWithOutlyingObservation");
		WiFiRSSIObservation observation1= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation2= new WiFiRSSIObservation(timestamp, map);
		Map<String, Double> map2 = new HashMap<String, Double>();
		int n = -60;
		for (int i = 0; i < 5; i++) {
			n = n - 20;
			map2.put("api1", (double) n);
		}
		WiFiRSSIObservation observation3 = new WiFiRSSIObservation(timestamp, map2);
		WiFiRSSIObservation observation4= new WiFiRSSIObservation(timestamp, map);
		WiFiRSSIObservation observation5= new WiFiRSSIObservation(timestamp, map);
		observations.add(observation1);
		observations.add(observation2);
		observations.add(observation3);
		observations.add(observation4);
		observations.add(observation5);
		WiFiRSSIObservation result= filter.filter(observations);
		System.out.println("\n"+observations);
		System.out.println("\n---------------------NEXT TEST----------------------\n");
		System.out.println(observations.get(0));
		System.out.println(observations.get(0).getObservation());
		System.out.println(result);
		System.out.println(result.getObservation());
	}
	
	
	//TODO  test esetek: kiesik adat, outlying adat. -> egyelnő-e a kézzel számoltal?

}
