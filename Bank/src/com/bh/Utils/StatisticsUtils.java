package com.bh.Utils;

import java.util.stream.Collectors;

import com.bh.beans.BankSystem;
import com.bh.beans.people.Client;
import com.bh.beans.people.RegularClient;

public class StatisticsUtils {

	private static String str = "THE BANK HAVE A TOTAL BALANCE OF:";

	public static void printAllStatistics() {
		countMembers();
		bankBalance(str);
		printReachestClient();
		printPoorestClient();
	}

	public static void printTotalBalanceAndNumOfClients() throws InterruptedException {
		System.out.println(ArtUtils.TAB + ArtUtils.BOLD_STATISTIC_FRAME + "|");
		System.out.println(ArtUtils.NEW_LINE + ArtUtils.TAB + "Total balance and number of clients is:|");
		Thread.sleep(500);
		StatisticsUtils.bankBalance(BankSystem.getInstance().getName());
		StatisticsUtils.countMembers();
	}

	public static void countMembers() {

		int RegularClinet = (int) BankSystem.getInstance().getClients().stream().filter(c -> c instanceof RegularClient)
				.count();
		int VipClient = BankSystem.getInstance().getClients().size() - RegularClinet;
		ArtUtils.setInMediumTable(RegularClinet, VipClient);

	}

	public static void bankBalance(String str) {
		str = "BANK " + str + " TOTAL BALANCE:";
		double sumOfBalance = BankSystem.getInstance().getClients().stream()
				.collect(Collectors.summingDouble(c -> c.getAccount().getBalance()));
		ArtUtils.setInlittleTable(sumOfBalance, str);
	}

	public static void printReachestClient() {
		String message = "Richest client is:";
		Client MaxBalanceClinet = BankSystem.getInstance().getClients().stream()
				.max((o1, o2) -> (int) (o1.getAccount().getBalance() - o2.getAccount().getBalance())).get();
		ArtUtils.setInClientTable(MaxBalanceClinet, message);

	}

	public static void printPoorestClient() {
		String message = "Poorest client is:";
		Client MinBalanceClinet = BankSystem.getInstance().getClients().stream()
				.min((o1, o2) -> (int) (o1.getAccount().getBalance() - o2.getAccount().getBalance())).get();
		ArtUtils.setInClientTable(MinBalanceClinet, message);
	}

}
