package com.customer.rewards.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.customer.rewards.domain.CustomerRewards;

@Repository
public class CustomerRewardsRepository {
	private List<CustomerRewards> customerRewardDetails = new ArrayList<>();
	
	//No data base connection to get real time data. Method provides hard coded data during application startup
	@PostConstruct
	public void initialzeData() {
		
		CustomerRewards data1 = new CustomerRewards(1L, 1L, 130, LocalDate.of(2022, 01, 26));
		CustomerRewards data2 = new CustomerRewards(2L, 1L, 60, LocalDate.of(2022, 01, 27));
		CustomerRewards data3 = new CustomerRewards(3L, 1L, 140, LocalDate.of(2022, 02, 14));
		CustomerRewards data4 = new CustomerRewards(4L, 2L, 150, LocalDate.of(2022, 02, 14));
		
		
		customerRewardDetails.add(data1);
		customerRewardDetails.add(data2);
		customerRewardDetails.add(data3);
		customerRewardDetails.add(data4);
	}

	public List<CustomerRewards> getData() {
		return customerRewardDetails;
	}

	
}
