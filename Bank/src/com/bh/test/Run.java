package com.bh.test;

import com.bh.beans.BankSystem;

public class Run {

	public static void main(String[] args) {
		try {
			BankSystem.getInstance().startSystem("Leomi");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

/**
 * all custom exception are handled inside BankUtils.toMenuMessage() for
 * stay in the menu loop;
 */