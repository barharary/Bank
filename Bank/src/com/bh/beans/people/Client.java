package com.bh.beans.people;

import com.bh.Utils.ArtUtils;
import com.bh.beans.Account;

public abstract class Client extends Person implements Comparable<Client> {

	private Account account;
	private float interstRate;

	public Client() {
		super();
		account = new Account();
	}

	public Account getAccount() {
		return account;
	}

	public float getInterstRate() {
		return interstRate;
	}

	public void setInterstRate(float interstRate) {
		this.interstRate = interstRate;
	}

	@Override
	public String toString() {
		return ArtUtils.CLIENT_TOSTRING + ArtUtils.NEW_LINE + super.toString() + ArtUtils.TAB + " " + account
				+ ArtUtils.TAB + " interstRate: " + interstRate + "%";
	}

	@Override
	public int compareTo(Client other) {
		return (int) (this.getId() - other.getId());
	}

}
