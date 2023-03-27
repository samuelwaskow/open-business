package com.openbusiness.orderservice;

import com.openbusiness.orderservice.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@SpringBootApplication
public class OrderServiceApplication {

	public static final String TOPIC = "topic_java";

	private final List<String> names = Arrays.asList("Donald", "Theresa", "Vladimir", "Angela", "Emmanuel", "Shinzō", "Jacinda", "Kim");
	private final List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public Supplier<OrderDTO> supplyOrder(){

		final Random r = new Random();

		return () -> {

			final OrderDTO loan = OrderDTO.builder()
					.uuid(UUID.randomUUID().toString())
					.name(names.get(r.nextInt(names.size())))
					.amount(amounts.get(r.nextInt(amounts.size())))
					.status("PENDING")
					.build();


			log.info("{} {} for ${} for {}", loan.getStatus(), loan.getUuid(), loan.getAmount(), loan.getName());
			return loan;
		};
	}

}
