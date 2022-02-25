package com.customer.rewards.domain;

import java.time.LocalDate;

public class CustomerRewards {
	private Long id;
	private Long customerId;
	private Integer purchaseAmount;
	private LocalDate purchaseDate;

	public Long getId() {
		return id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Integer getPurchaseAmount() {
		return purchaseAmount;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public CustomerRewards(Long id, Long customerId, Integer purchaseAmount, LocalDate purchaseDate) {
		this.id = id;
		this.customerId = customerId;
		this.purchaseAmount = purchaseAmount;
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "CustomerRewards [id=" + id + ", customerId=" + customerId + ", purchaseAmount=" + purchaseAmount
				+ ", purchaseDate=" + purchaseDate + "]";
	}

}
