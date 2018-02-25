package by.pharmsystem.recipeservice;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeserviceApplicationTests {

	@Test
	public void contextLoads() {
        System.out.println(new Date());
        System.out.println(new DateTime().plusYears(1).toDate());
	}

}
