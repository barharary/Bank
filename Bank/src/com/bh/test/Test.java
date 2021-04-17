package com.bh.test;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.bh.beans.people.Client;
import com.bh.beans.people.RegularClient;
import com.bh.beans.people.VipClient;

public class Test {

	public static void main(String[] args) {
//handle treeset...
		TreeSet<Client> clients = new TreeSet<>();
		Client client1 = new VipClient();
		client1.getAccount().setBalance(2000);
		Client client2 = new RegularClient();
		client2.getAccount().setBalance(2000);
		Client client3 = new VipClient();
		client3.getAccount().setBalance(1500);
		clients.add(client1);
		clients.add(client2);
		clients.add(client3);

		List<Client> clients1 = clients.stream().sorted((Client o1, Client o2) -> //
		(int) (o1.getAccount().getBalance() - o2.getAccount().getBalance())).collect(Collectors.toList());
		System.out.println("List" + clients1);
		System.out.println("***");
		System.out.println("Set" + clients);

	}
}
