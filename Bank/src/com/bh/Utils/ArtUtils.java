package com.bh.Utils;

import com.bh.beans.people.Client;

public class ArtUtils {

	public static final String TAB = "\t|";
	public static final String NEW_LINE = "\n";

	// Clients:ToString table format
	public static final String HEADLINE = "|ID:\t| NAME:\t\t\t| AGE:\t\t| BALANCE:\t\t| INTREST RATE:\t\t| TYPE:\t\t\t| ";
	public static final String BOLD_UNDERLINE = "   |=======|================================================================================================================|";
	public static final String REGULAR_UNDERLINE = "|-------|-----------------------|---------------|-----------------------|-----------------------|-----------------------|";
	public static final String VERTICAL_LINE = "|\t|\t\t\t|\t\t|\t\t\t|\t\t\t|\t\t\t|";
	public static final String LOTS_OF_T = "\t\t\t\t\t\t\t\t\t\t\t\t\t|";
	public final static String CLIENT_TOSTRING = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|";

	// ArtUtils...
	public final static String BOLD_STATISTIC_FRAME = "=======================================";
	public final static String REGULAR_LINE = ArtUtils.TAB + "---------------------------------------|";
	public final static String LONG_BOLD_LINE = "=======================================================================================================================|";

	public static void bankArt() {
		String bankArt = //
				" /$$                           /$$     " + NEW_LINE//
						+ "| $$                           |$$      " + NEW_LINE //
						+ "| $$$$$$$   /$$$$$$   /$$$$$$$ |$$   /$$" + NEW_LINE //
						+ "| $$__  $$  |___  $$| $$__  $$| $$  /$$/" + NEW_LINE //
						+ "| $$  | $$  /$$$$$$$| $$  | $$| $$$$$$/ " + NEW_LINE//
						+ "| $$  | $$ /$$    $$| $$  | $$| $$_  $$ " + NEW_LINE//
						+ "| $$$$$$$/ | $$$$$$$| $$  | $$| $$ |  $$" + NEW_LINE//
						+ "|_______/  |_______/|__/  |__/|__/ |__/"; //
		System.out.println(bankArt);
	}

	public static void setInClientTable(Client minAccountClinet, String headline) {
		System.out.println();
		System.out.println("|" + LONG_BOLD_LINE);
		System.out.println("|" + headline + ArtUtils.LOTS_OF_T);
		System.out.println("|" + minAccountClinet);

	}

	public static void setInMediumTable(int RegularClinet, int VipClient) {
		System.out.println();
		System.out.println("\t/" + BOLD_STATISTIC_FRAME + "\\");
		System.out.println(ArtUtils.TAB + "COUNT MEMBERS: \t\t\t|");
		System.out.println(REGULAR_LINE);

		System.out.println(ArtUtils.TAB + "Regular members:\t| Vip members:  |");
		System.out.println(ArtUtils.TAB + " " + RegularClinet + "\t\t\t| " + VipClient + "\t\t|");
		System.out.println("\t\\" + BOLD_STATISTIC_FRAME + "/");
	}

	public static void setInlittleTable(double num, String str) {
		System.out.println();
		System.out.println();
		System.out.println("\t/" + BOLD_STATISTIC_FRAME + "\\");
		System.out.println(ArtUtils.TAB + str.toUpperCase() + "\t" + ArtUtils.TAB);
		System.out.println(REGULAR_LINE);
		System.out.println(ArtUtils.TAB + "$" + String.format("%.2f", num) + "\t\t\t\t" + ArtUtils.TAB);
		System.out.println("\t\\" + BOLD_STATISTIC_FRAME + "/");

	}
}
