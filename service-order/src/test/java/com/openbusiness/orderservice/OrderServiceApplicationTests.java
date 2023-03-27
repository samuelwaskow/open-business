package com.openbusiness.orderservice;

import com.openbusiness.orderservice.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderServiceApplicationTests {

	@Test
	public void supplyOrder() {
		OrderServiceApplication app = new OrderServiceApplication();
		Supplier<OrderDTO> order = app.supplyOrder();
		assertNotNull(order);
		assertNotNull(order.get());
		assertNotNull(order.get().getAmount());
		assertNotNull(order.get().getName());
	}

}
