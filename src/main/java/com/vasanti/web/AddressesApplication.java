package com.vasanti.web;

import brave.Tracer;
import com.vasanti.web.addresses.client.AddressClient;
import com.vasanti.web.addresses.model.address;
import com.vasanti.web.addresses.service.AddressServiceQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class AddressesApplication {

	private final AddressServiceQueries addressServiceQueries;

	private final Tracer tracer;

	@Autowired
	public AddressesApplication(AddressServiceQueries addressServiceQueries,
								Tracer tracer) {
		this.addressServiceQueries = addressServiceQueries;
		this.tracer = tracer;
	}


	@Bean
	public Tracer tracer(){
		return tracer;
	}


	public static void main(String[] args) {
	    SpringApplication.run(AddressesApplication.class, args);

        AddressClient addressClient = new AddressClient();
        log.info("----{} ----",addressClient.getResult());
	}

	@SuppressWarnings("unused")
	public void run(String... args) {
		log.info("-----\nQUERY: All Addresses {}", Arrays.toString(args));
		List<address> allcities = this.addressServiceQueries
				.findAll("city", 0, 10);
		allcities.forEach(System.out::println);

	}
}
