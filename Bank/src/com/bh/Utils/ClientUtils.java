package com.bh.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bh.beans.BankSystem;
import com.bh.beans.people.Client;
import com.bh.beans.people.RegularClient;
import com.bh.beans.people.VipClient;
import com.bh.exceptions.ClientException;
import com.bh.exceptions.ClientException.Errors;

public class ClientUtils {

	private static final double MIN_AGE = 16;
	private static final double MAX_AGE = 99;

	private static float regularIntrestRate = 0.05f;
	private static float VipIntrestRate = 0.1f;

	public static double generateAge() {
		return Math.round(Math.random() * (MAX_AGE - MIN_AGE)) + MIN_AGE;
	}

	public static float getIntrestRate(Client client) {
		return (client instanceof RegularClient) ? regularIntrestRate : VipIntrestRate;
	}

	public static void addClientMessage() {
		System.out.println(ArtUtils.TAB + "Which type of client to you want to add?");
		System.out.print(ArtUtils.TAB + "Press 'R' For \"regular client\" or press 'V' for \"VIP client\": ");

	}

	public static void deleteClientMessage() {
		System.out.print(ArtUtils.TAB + "Please enter an Id to delete: ");

	}

	public static boolean isClientInOverDraft(Client client) {
		return (client.getAccount().getBalance() < 0);

	}

	public static Map<Integer, Double> chooseIdAndAmount(String str) throws InterruptedException, ClientException {
		Map<Integer, Double> map = new HashMap<>();
		System.out.print(ArtUtils.TAB + "Please choose Id for which you want to " + str + ": ");
		int id = BankSystem.getScanner().nextInt();
		if (!BankSystem.getInstance().isClientExists(id)) {
			throw new ClientException(Errors.NOT_EXISTS);
		}
		Thread.sleep(500);
		System.out.println(ArtUtils.TAB + "thanks.");
		System.out.print(ArtUtils.TAB + "how much money do you want to " + str + " for Client Id " + id + "? ");
		double amount = BankSystem.getScanner().nextDouble();
		map.put(id, amount);
		return map;
	}

	public static void addSeveralClients() throws InterruptedException {
		System.out.print(ArtUtils.TAB + "How meny clients do you want to add? ");
		int numOfClients = BankSystem.getScanner().nextInt();
		List<Client> clients = generateClients(numOfClients);
		assignClientsToBank(clients);
		String str = ", register to the bank";
		clients.forEach(c -> {
			try {
				BankUtils.confirmOperation(c.getId(), ", Name: " + c.getName() + str);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}

	public static List<Client> generateClients(int num) {
		Client client = null;
		List<Client> clients = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			if (Math.random() < 0.5) {
				client = new RegularClient();
			} else {
				client = new VipClient();
			}
			client.getAccount().setBalance(Math.round(-500 + Math.random() * 1001));
			clients.add(client);
		}
		return clients;
	}

	public static void assignClientsToBank(List<Client> clients) {
		BankSystem.getInstance().getClients().addAll(clients);
	}

//	public static void printClientsBy(String sortedByHeadline, String str) {
//		if (str.equalsIgnoreCase("id")) {
//			sortClientsById();
//		} else if (str.toLowerCase().equals("balance")) {
//			sortClientsByBalance();
//		}
//		System.out.printf(ArtUtils.NEW_LINE + sortedByHeadline + ArtUtils.NEW_LINE, str);
//		System.out.println(BankSystem.getInstance());
//
//	}

//	public static void sortClientsByBalance() {
//		Comparator<Client> compertorClient = (Client o1, Client o2) -> //
//		(int) (o1.getAccount().getBalance() - o2.getAccount().getBalance());
//		Collections.sort((List<Client>) BankSystem.getInstance().getClients(), compertorClient);
//	}
//
//	public static void sortClientsById() {
//		Comparator<Client> compertorClient = (Client o1, Client o2) -> //
//		(int) (o1.getId() - o2.getId());
//		Collections.sort((List<Client>)BankSystem.getInstance().getClients(), compertorClient);
//	}

}
