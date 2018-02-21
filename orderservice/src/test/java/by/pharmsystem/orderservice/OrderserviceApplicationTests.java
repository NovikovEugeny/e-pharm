package by.pharmsystem.orderservice;

import by.pharmsystem.orderservice.repository.OrderRepository;
import by.pharmsystem.orderservice.service.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderserviceApplicationTests {

	@Mock
    private OrderRepository orderRepository;

	@InjectMocks
    private OrderServiceImpl orderService;

	@Test
	public void contextLoads() {
	}

}
