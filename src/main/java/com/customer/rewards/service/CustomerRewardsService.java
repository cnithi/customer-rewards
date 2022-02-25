package com.customer.rewards.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.rewards.domain.CustomerRewards;
import com.customer.rewards.domain.CustomerRewardsResponse;
import com.customer.rewards.repository.CustomerRewardsRepository;

@Service
public class CustomerRewardsService {
	
	private Logger log = LoggerFactory.getLogger(CustomerRewardsService.class);

	@Autowired
	private CustomerRewardsRepository repository;

	public CustomerRewardsResponse getCustomerRewardPoints(Long customerId) {
		CustomerRewardsResponse rewardsResponse = new CustomerRewardsResponse();
		List<CustomerRewards> customerRewardsList = getCustomerRewardsById(customerId);
		log.debug("Received total {} records for customer id : {}", customerRewardsList.size(), customerId);
		if (CollectionUtils.isNotEmpty(customerRewardsList)) {

			Map<String, Integer> rewardPointsPerMonth = getRewardPointsPerMonth(customerRewardsList);
			rewardsResponse.setPointsByMonth(rewardPointsPerMonth);
			rewardsResponse.setTotalPoints(rewardPointsPerMonth.values().stream().reduce((a, b) -> a + b).get());
		}

		return rewardsResponse;
	}
	
	// Retrieves all data and filters based on customer id
	public List<CustomerRewards> getCustomerRewardsById(Long customerId) {
		return repository.getData().stream().filter(rewards -> customerId.equals(rewards.getCustomerId()))
				.collect(Collectors.toList());
	}
	
	//Groups all data of customer by month and calculates reward points
	private Map<String, Integer> getRewardPointsPerMonth(List<CustomerRewards> customerRewardsList) {
		Map<String, List<CustomerRewards>> purchsesPerMonth = customerRewardsList.stream().collect(Collectors.groupingBy(e -> e.getPurchaseDate().getMonth().toString()));
		Map<String, Integer> rewardPointsPerMonth = new HashMap<>();

		for (Map.Entry<String, List<CustomerRewards>> entry : purchsesPerMonth.entrySet()) {
			rewardPointsPerMonth.put(entry.getKey(), entry.getValue().stream().map(e -> {
				Integer purchaseAmount = e.getPurchaseAmount();
				if (purchaseAmount > 100) {
					return (purchaseAmount - 100) * 2 + 50 * 1;
				} else if (purchaseAmount > 50 && purchaseAmount <= 100) {
					return (purchaseAmount - 50) * 1;
				} else {
					return 0;
				}
			}).reduce((a, b) -> a + b).get());
		}
		log.debug("Recived records grouped by each month {} ", rewardPointsPerMonth.toString());
		return rewardPointsPerMonth;
	}

}
