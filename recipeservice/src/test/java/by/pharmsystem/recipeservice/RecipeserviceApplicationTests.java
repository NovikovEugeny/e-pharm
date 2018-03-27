package by.pharmsystem.recipeservice;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeserviceApplicationTests {

	@Test
	public void contextLoads() {
		Map<Long, Boolean> map = new HashMap<>();
		/*map.put(1L, true);
		map.put(33L, false);
		map.put(12L, false);
		map.put(111L, true);*/

		map.forEach((k, v) -> {
			if (v) {
				System.out.println("has");
			}
		});

		List<Long> list = new ArrayList<>();

        System.out.println(list.size());
	}

}
