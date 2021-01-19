package com.vasanti.web;

import com.vasanti.web.addresses.controller.AddressController;
import com.vasanti.web.addresses.respositoy.ReactiveAddressesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AddressController.class)
public class AddressesApplicationTests {


	@MockBean
	ReactiveAddressesRepository reactiveAddressesRepository;

	private final WebTestClient webTestClient;

	@Autowired
	public AddressesApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testSetUp() {
		webTestClient
				.get().uri("/test")
				.accept(MediaType.TEXT_PLAIN)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Reactive Spring Service Works");
	}

	
}
