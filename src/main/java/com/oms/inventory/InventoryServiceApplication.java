package com.oms.inventory;

import com.oms.common.web.EnableOMSCommonWeb;
import com.oms.inventory.repository.InventoryRepositoryCustom;
import com.oms.inventory.repository.InventoryRepositoryCustomImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableOMSCommonWeb
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}
