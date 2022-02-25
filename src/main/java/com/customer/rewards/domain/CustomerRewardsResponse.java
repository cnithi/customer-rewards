package com.customer.rewards.domain;

import java.util.Map;

public class CustomerRewardsResponse {
	private Map<String, Integer> pointsByMonth;
	private Integer totalPoints;
	public Map<String, Integer> getPointsByMonth() {
		return pointsByMonth;
	}
	public void setPointsByMonth(Map<String, Integer> pointsByMonth) {
		this.pointsByMonth = pointsByMonth;
	}
	public Integer getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	
}
