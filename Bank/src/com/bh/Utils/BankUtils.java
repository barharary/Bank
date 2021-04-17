package com.bh.Utils;

import com.bh.beans.BankSystem;
import com.bh.exceptions.ClientException;
import com.bh.exceptions.DeleteException;
import com.bh.exceptions.WithdrawException;

public class BankUtils {

	public static void welcomeMessage(String name) {
		ArtUtils.bankArt();
		System.out.println(ArtUtils.LONG_BOLD_LINE + ArtUtils.NEW_LINE + "");
		System.out.println("\t***WELCOME TO \"BANK " + name.toUpperCase() + "\" MANAGEMENT SYSTEM***");
	}

	public static void ToMenuMessage() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println(ArtUtils.NEW_LINE + ArtUtils.TAB + "Press enter to go to Main Menu:");
		BankSystem.getScanner().nextLine();
		BankSystem.getScanner().nextLine();

	}

	public static void backToMenu() throws InterruptedException {

		BankUtils.ToMenuMessage();
		showMenu();
		try {
			BankSystem.getInstance().menuDecision();
		} catch (ClientException | DeleteException | WithdrawException | ClassCastException e) {
			System.out.println(e.getMessage());
		} finally {
			backToMenu();
		}
	}

	public static void confirmOperation(int id, String str) throws InterruptedException {
		Thread.sleep(400);
		System.out.println(ArtUtils.TAB + "Client Id:" + id + str + " successfully.");
		System.out.println(ArtUtils.REGULAR_LINE);

	}

	public static void showMenu() {
		System.out.println(ArtUtils.TAB + ArtUtils.BOLD_STATISTIC_FRAME + "|");
		System.out.println(ArtUtils.TAB + "MAIN MENU:");
		System.out.println(ArtUtils.REGULAR_LINE);
		System.out.println(ArtUtils.TAB + "1. Add Client");
		System.out.println(ArtUtils.TAB + "2. Delete Client");
		System.out.println(ArtUtils.TAB + "3. Withdraw money for a client");
		System.out.println(ArtUtils.TAB + "4. Deposit money for a client");
		System.out.println(ArtUtils.TAB + "5. print all clients");
		System.out.println(ArtUtils.TAB + "6. Print richest client");
		System.out.println(ArtUtils.TAB + "7. Print poorest client");
		System.out.println(ArtUtils.TAB + "8. Print bank balance and number of clients ");
		System.out.println(ArtUtils.TAB + "9. Add several clients at once");
		System.out.println(ArtUtils.TAB + "10. exit");
		System.out.println(ArtUtils.REGULAR_LINE);
		System.out.print(ArtUtils.TAB + "Press a number: ");

	}

}
