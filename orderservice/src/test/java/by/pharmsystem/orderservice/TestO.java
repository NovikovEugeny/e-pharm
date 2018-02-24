package by.pharmsystem.orderservice;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestO {

    @Test
    public void test() {
        Map<Long, Integer> map = new HashMap<>();
        map.put(2L, 1);
        map.put(67L, 2);
        map.put(12L, 1);
        System.out.println(map);

        List<Long> identifiers = new ArrayList<>();
        map.forEach((k, v) -> identifiers.add(k));
        System.out.println(identifiers);

        List<Double> prices = new ArrayList<>();
        prices.add(10.0);
        prices.add(20.0);
        prices.add(30.0);
        System.out.println(prices);

        double[] cost = {};
        map.forEach((k, v) -> cost[0] += v * prices.get(identifiers.indexOf(k)));

        System.out.println(cost[0]);

    }
}
