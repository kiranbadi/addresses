package com.vasanti.web;

import brave.Tracer;
import com.vasanti.web.addresses.client.AddressClient;
import com.vasanti.web.addresses.model.address;
import com.vasanti.web.addresses.service.AddressServiceQueries;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AddressesApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressesApplication.class);

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
        LOGGER.info("----{} ----",addressClient.getResult());
	}

 // Implement commandlinerunner to run below method for testing purpose
	public void run(String... args) {
		System.out.println("-----\nQUERY: All Addresses ");
		List<address> allcities = this.addressServiceQueries
				.findAll("city", 0, 10);
		allcities.forEach(System.out::println);

		  /** commenting out below code since mongo db compass cannot create text index on field **/
		  // TODO : Create text index on field city and uncomment below code
//		LOGGER.info("-----\nQUERY: Free text search: Rome");
//		List<address> ByText = this.addressServiceQueries
//				.findByText("morrisville");
//		LOGGER.info("Search by text --\n {}",ByText.toString());
	}
}
