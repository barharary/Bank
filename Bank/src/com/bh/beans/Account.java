package com.bh.beans;

public class Account {

	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "balance: $" + String.format("%.2f", balance);
	}

}
