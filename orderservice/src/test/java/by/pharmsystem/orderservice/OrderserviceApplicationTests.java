package by.pharmsystem.orderservice;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(/*MockitoJUnitRunner.class*/ SpringRunner.class)
@SpringBootTest
public class OrderserviceApplicationTests {

	/*@Mock
    private OrderRepository orderRepository;

	@InjectMocks
    private OrderServiceImpl orderService;
*/

	@Autowired
	private OrderService orderService;

	@Test
	public void contextLoads() {
		Map<Long, Integer> medicaments = new HashMap<>();
		medicaments.put(1L, 1);
		medicaments.put(2L, 1);
		medicaments.put(3L, 2);

		Order order = new Order();
		order.setClientId(1);
		order.setPharmacistId(2);
		order.setMedicaments(medicaments);
		order.setCost(20);
		order.setRequestDate(new Date());
		order.setStatus("sent");

		orderService.createOrder(order);
	}

	@Test
	public void test() {

	}

}
