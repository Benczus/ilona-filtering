package uni.miskolc.ips.ilona.client.mobile.filtering.service;

import org.junit.Before;
import org.junit.Test;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.model.WiFiRSSIObservation;
import uni.miskolc.ips.ilona.client.mobile.filtering.core.service.WiFiRSSIFilter;

import java.util.*;


public class FilterTest {

    private Map<String, Double> map = new HashMap<String, Double>();
    private long timestamp;
    // TODO observations list!!
    private List<WiFiRSSIObservation> observations = new ArrayList<WiFiRSSIObservation>();
    private int memsize = 5;
    private WiFiRSSIFilter filter = new HorusFilter(memsize);
    private double threshold = 5;

    @Before
    public void setUp() throws Exception {
        Random rand = new Random();
        int n;
        for (int i = 0; i < 5; i++) {
            n = -1 * (rand.nextInt(50) + 1);
            map.put("api1", (double) n);
        }

    }

    @Test
    public void testWithOneObservation() {
        System.out.println("testWithOneObservations");
        Random rand = new Random();
        int n;

        n = -1 * (rand.nextInt(50) + 1);
        map.put("api1", (double) n);
        observations.add(new WiFiRSSIObservation(timestamp, map));


        WiFiRSSIObservation result = filter.filter(observations);
        System.out.println("\n" + observations);

        System.out.println(observations.get(0));
        System.out.println(observations.get(0).getObservation());
        System.out.println(result);
        System.out.println(result.getObservation());
        System.out.println("\n---------------------NEXT TEST----------------------\n");
    }

    @Test
    public void testWithMultipleObservations() {


        Random rand = new Random();
        for (int i = 0; i < 4; i++) {

            int n = (-1) * (rand.nextInt(50) + 1);
            System.out.println(n);
            map = new HashMap<String, Double>();
            map.put("api1", (double) n);
            observations.add(new WiFiRSSIObservation(timestamp, map));
        }

        WiFiRSSIObservation result = filter.filter(observations);
        System.out.println(observations);
        System.out.println("\n" + observations);

        System.out.println(observations.get(3).getObservation());
        System.out.println(observations.get(2).getObservation());
        System.out.println(observations.get(1).getObservation());
        System.out.println(observations.get(0).getObservation());
        System.out.println(result);
        System.out.println(result.getObservation());
        System.out.println("\n---------------------NEXT TEST----------------------\n");
    }

    @Test
    public void testWithMemSizeObservations() {

        Random rand = new Random();
        for (int i = 0; i < memsize; i++) {

            int n = (-1) * (rand.nextInt(50) + 1);
            System.out.println(n);
            map = new HashMap<String, Double>();
            map.put("api1", (double) n);
            observations.add(new WiFiRSSIObservation(timestamp, map));
        }

        WiFiRSSIObservation result = filter.filter(observations);
        System.out.println(observations);
        System.out.println("\n" + observations);

        for (int i = 0; i < memsize; i++) {
            System.out.println(observations.get(i).getObservation());
        }
        System.out.println(result);
        System.out.println(result.getObservation());
        System.out.println("\n---------------------NEXT TEST----------------------\n");
    }

    @Test
    public void testWithMoreThanFiveObservations() {
        Random rand = new Random();
        for (int i = 0; i < memsize + 5; i++) {

            int n = (-1) * (rand.nextInt(50) + 1);
            System.out.println(n + " " + i);
            map = new HashMap<String, Double>();
            map.put("api1", (double) n);
            observations.add(new WiFiRSSIObservation(timestamp, map));
        }

        WiFiRSSIObservation result = filter.filter(observations);
        System.out.println(observations);
        System.out.println("\n" + observations);

        for (int i = 0; i < memsize + 5; i++) {
            System.out.println(observations.get(i).getObservation());
        }
        System.out.println(result);
        System.out.println(result.getObservation());
        System.out.println("\n---------------------NEXT TEST----------------------\n");
    }


    @Test
    public void testWithOutlyingObservation() {
        System.out.println("testWithOutlyingObservation");
        WiFiRSSIObservation observation1 = new WiFiRSSIObservation(timestamp, map);
        WiFiRSSIObservation observation2 = new WiFiRSSIObservation(timestamp, map);
        Map<String, Double> map2 = new HashMap<String, Double>();
        map2.put("api1", -160.0);
        WiFiRSSIObservation observation3 = new WiFiRSSIObservation(timestamp, map);
        WiFiRSSIObservation observation4 = new WiFiRSSIObservation(timestamp, map);
        WiFiRSSIObservation observation5 = new WiFiRSSIObservation(timestamp, map2);
        observations.add(observation1);
        observations.add(observation2);
        observations.add(observation3);
        observations.add(observation4);
        observations.add(observation5);
        WiFiRSSIObservation result = filter.filter(observations);
        System.out.println("\n" + observations);

        System.out.println(observations.get(0));
        System.out.println(observations.get(0).getObservation());
        System.out.println(result);
        System.out.println(result.getObservation());
        System.out.println("\n---------------------NEXT TEST----------------------\n");
    }


    @Test
    public void testAssertWithMemSizeObservations() {
        int exprected = 30;
        System.out.println("observation measurements:");
        for (int i = 0; i < memsize; i++) {

            int n = (i + 1) * (-10);
            System.out.println(n + " " + i);
            map = new HashMap<String, Double>();
            map.put("api1", (double) n);
            observations.add(new WiFiRSSIObservation(timestamp, map));
        }
        System.out.println("------------");

        WiFiRSSIObservation result = filter.filter(observations);
        System.out.println("observation result  " + result.getObservation());
        System.out.println(observations);
        System.out.println("\n" + observations);

        for (int i = 0; i < memsize; i++) {
            System.out.println(observations.get(i).getObservation());
        }
        System.out.println(result);
        System.out.println(result.getObservation());
        System.out.println("\n---------------------NEXT TEST----------------------\n");
    }


}
