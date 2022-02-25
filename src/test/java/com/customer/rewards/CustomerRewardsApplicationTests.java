package com.customer.rewards;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.customer.rewards.domain.CustomerRewardsResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerRewardsApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void testRewardPointsForCustomer1() {
		URI getRewardPointsBuCustomerUrl = UriComponentsBuilder.fromUriString("/api/customer/rewards/getCustomerRewardPoints/1")
				.build().encode().toUri();

		ResponseEntity<CustomerRewardsResponse> response = this.restTemplate.getForEntity(getRewardPointsBuCustomerUrl,
				CustomerRewardsResponse.class);
		assertNotEquals(response, null);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getTotalPoints(), 250);
		assertEquals(response.getBody().getPointsByMonth().get("JANUARY"), 120);
		assertEquals(response.getBody().getPointsByMonth().get("FEBRUARY"), 130);
	}

}
