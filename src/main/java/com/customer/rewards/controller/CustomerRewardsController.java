package com.customer.rewards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.domain.CustomerRewardsResponse;
import com.customer.rewards.service.CustomerRewardsService;

@RestController
@RequestMapping("/api/customer/rewards")
public class CustomerRewardsController {
	private Logger log = LoggerFactory.getLogger(CustomerRewardsController.class);

	@Autowired
	private CustomerRewardsService customerRewardsService;

	@GetMapping("/getCustomerRewardPoints/{id}")
	public ResponseEntity<CustomerRewardsResponse> getCustomerRewardpoints(@PathVariable("id") Long customerId) {
		CustomerRewardsResponse response = customerRewardsService.getCustomerRewardPoints(customerId);
		log.debug("Customer Rewards Response {} for customer id {} received", response.toString(), customerId);
		return new ResponseEntity<CustomerRewardsResponse>(response, HttpStatus.OK);
	}

}
