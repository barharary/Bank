package com.bh.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.bh.Utils.ArtUtils;
import com.bh.Utils.BankUtils;
import com.bh.Utils.ClientUtils;
import com.bh.Utils.StatisticsUtils;
import com.bh.beans.people.Client;
import com.bh.beans.people.RegularClient;
import com.bh.beans.people.VipClient;
import com.bh.exceptions.ClientException;
import com.bh.exceptions.ClientException.Errors;
import com.bh.exceptions.DeleteException;
import com.bh.exceptions.WithdrawException;

public class BankSystem {

	private String name;
	private InterestTask task = new InterestTask();
	private TreeSet<Client> clients = new TreeSet<>();
	private static Scanner scanner;
	private static BankSystem instance;

	private BankSystem() {
	}

	public static BankSystem getInstance() {
		if (instance == null) {
			synchronized (BankSystem.class) {
				if (instance == null) {
					BankSystem.instance = new BankSystem();
				}
			}
		}
		return instance;
	}

	public static Scanner getScanner() {
		return scanner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeSet<Client> getClients() {
		return clients;
	}

	public Client getClientById(int id) {
		return this.clients.stream().filter(c -> c.getId() == id)//
				.collect(Collectors.toList()).get(0);

	}

	public void setClients(TreeSet<Client> clients) {
		this.clients = clients;
	}

	public void startSystem(String name) throws InterruptedException {
		scanner = new Scanner(System.in);
		this.setName(name);
		BankUtils.welcomeMessage(name);
		new Thread(this.task).start();
		BankSystem.getScanner().nextLine();
		BankUtils.showMenu();
		menuDecisionHandeling();
	}

	public void menuDecisionHandeling() throws InterruptedException {
		try {
			menuDecision();
		} catch (ClientException | DeleteException | WithdrawException | ClassCastException e) {
			System.out.println(e.getMessage());
		} finally {
			BankUtils.backToMenu();
		}
	}

	public void menuDecision()
			throws ClientException, InterruptedException, DeleteException, WithdrawException {
		int menuDecision = scanner.nextInt();
		System.out.println();
		switch (menuDecision) {
		case 1:
			ClientUtils.addClientMessage();
			addClient();
			break;
		case 2:
			ClientUtils.deleteClientMessage();
			int id = scanner.nextInt();
			if (isClientExists(id)) {
				deleteClient(id);
			} else {
				throw new ClientException(Errors.NOT_EXISTS);
			}
			break;
		case 3:
			Map<Integer, Double> mapWithdraw = ClientUtils.chooseIdAndAmount("withdraw");
			int idToWithdraw = (int) mapWithdraw.keySet().toArray()[0];
			double amountToWithraw = (double) mapWithdraw.get(idToWithdraw);
			withdraw(idToWithdraw, amountToWithraw);
			break;
		case 4:
			Map<Integer, Double> mapDeposit = ClientUtils.chooseIdAndAmount("Deposit");
			int idDeposit = (int) mapDeposit.keySet().toArray()[0];
			double amountDeposit = (double) mapDeposit.get(idDeposit);
			deposite(idDeposit, amountDeposit);
			break;
		case 5:
			printAll();
			break;
		case 6:
			StatisticsUtils.printReachestClient();
			break;
		case 7:
			StatisticsUtils.printPoorestClient();
			break;
		case 8:
			StatisticsUtils.printTotalBalanceAndNumOfClients();
			break;
		case 9:
			ClientUtils.addSeveralClients();
			System.out.println(ArtUtils.TAB + "All clients register sucssfully.");
			break;
		case 10:
			scanner.close();
			System.out.println(ArtUtils.TAB + "You choose to exit." //
					+ ArtUtils.NEW_LINE + ArtUtils.TAB + "Bye bye..");
			System.exit(0);
			break;
		default:
			break;
		}
	}

	public boolean isClientExists(int id) {
		return (!this.clients.stream().filter(c -> c.getId() == id) //
				.collect(Collectors.toList()).isEmpty());

	}

	public void addClient() throws InterruptedException {
		Client client = null;
		String clientType = scanner.next();
		if (clientType.toUpperCase().startsWith("R")) {
			client = new RegularClient();
		} else if (clientType.toUpperCase().startsWith("V")) {
			client = new VipClient();
		}
		this.clients.add(client);
		BankUtils.confirmOperation(client.getId(), ArtUtils.NEW_LINE + ArtUtils.TAB + "name: " + client.getName()
				+ ArtUtils.NEW_LINE + ArtUtils.TAB + "add");

	}

	public void deleteClient(int id) throws DeleteException, InterruptedException {
		Client client = getClientById(id);
		if (ClientUtils.isClientInOverDraft(client)) {
			String message = ArtUtils.TAB + "It is not possible to delete client because the account is in overdraft.";
			throw new DeleteException(client.getId(), client.getName(), client.getAccount().getBalance(), message,
					new Date());
		}
		this.clients.remove(client);
		BankUtils.confirmOperation(id, " delete");
	}

	public void withdraw(int id, double amount) throws WithdrawException, InterruptedException {
		Client client = getClientById(id);
		if (ClientUtils.isClientInOverDraft(client)) {
			String message = ArtUtils.TAB + "It is not possible to withdraw money because the account is in overdraft.";
			throw new WithdrawException(client.getId(), client.getName(), client.getAccount().getBalance(), message,
					new Date());
		}
		double balance = client.getAccount().getBalance();
		balance -= amount;
		client.getAccount().setBalance(balance);
		BankUtils.confirmOperation(id, " " + client.getName() + " withdraw " + "$" + amount);
	}

	public void deposite(int id, double amount) throws InterruptedException {
		Client client = getClientById(id);
		double balance = client.getAccount().getBalance();
		client = this.clients.stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
		balance += amount;
		client.getAccount().setBalance(balance);
		BankUtils.confirmOperation(id, " " + client.getName() + " deposit " + "$" + amount);
		System.out.println(ArtUtils.TAB + "the balance for client Id:" + client.getId() + " is " + "$"
				+ String.format("%.2f", client.getAccount().getBalance()));

	}

	public void printAll() {
		if (this.clients.isEmpty()) {
			System.out.println(ArtUtils.NEW_LINE + ArtUtils.TAB + "The bank has no clients.");
		} else {
			System.out.println(instance);
		}

	}

	public static List<Client> getSortedClientsByBalance() {
		List<Client> compertorByBalance = BankSystem.getInstance().getClients().stream()
				.sorted((Client o1, Client o2) -> //
				(int) (o1.getAccount().getBalance() - o2.getAccount().getBalance())).collect(Collectors.toList());
		return compertorByBalance;
	}

	@Override
	public String toString() {
		return "\"BANK " + name.toUpperCase() + "\"" + "\t" + ArtUtils.NEW_LINE + ArtUtils.HEADLINE + ArtUtils.NEW_LINE
				+ ArtUtils.VERTICAL_LINE + getSortedClientsByBalance() + ArtUtils.NEW_LINE;
	}

//	public void printAll() {
//		if (this.clients.isEmpty()) {
//			System.out.println(ArtUtils.NEW_LINE + ArtUtils.TAB + "The bank has no clients.");
//		} else {
//			System.out.println(instance);
//			// ClientUtils.sortClientsByBalance();
////			System.out.println(instance);
//			// if it was List and no TreeSet
//			// final String id = "ID";
//			// final String balance = "BALANCE";
//			// final String sortedByHeadline = "======SORTED BY
//			// %s:=====================================================================================================|";
//			// ClientUtils.printClientsBy(sortedByHeadline, id); //
//			// ClientUtils.printClientsBy(sortedByHeadline, balance);
//		}
//
//	}

}
