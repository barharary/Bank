package com.bh.beans;

import com.bh.Utils.ArtUtils;

public class InterestTask implements Runnable {

	@Override
	public void run() {
//		Thread thread = new Thread();
		final int sleepTime = 60000;

		welcomeThreadMessage(sleepTime);
//		thread.start();
		while (true) {
			try {
				messageSleep(sleepTime);
				Thread.sleep(sleepTime);
				updateInterest();
				wokeMessage();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				BankSystem.getInstance().printAll();
			}
		}
	}

	public void welcomeThreadMessage(int sleepTime) {
		System.out.println( //
				ArtUtils.NEW_LINE + ArtUtils.TAB + "Hey! i'm Bob, your balance update Thread." + ArtUtils.NEW_LINE
						+ ArtUtils.TAB + "I'll update the clients balance for every " + sleepTime / 1000 + " sec.");
	}

	public void messageSleep(int sleep) throws InterruptedException {
		Thread.sleep(5000);
		System.out.println(ArtUtils.NEW_LINE //
				+ ArtUtils.TAB + "I'm now going to sleep for " + sleep / 1000 + " sec. Bye bye.");
		Thread.sleep(2000);
		System.out.println(ArtUtils.NEW_LINE + ArtUtils.TAB + "Please press enter to continue");
	}

	public void updateInterest() {
		BankSystem.getInstance().getClients().forEach(c -> c.getAccount().setBalance //
		(c.getAccount().getBalance() * (1 + c.getInterstRate())));
	}

	public void wokeMessage() {
		System.out.println(
				ArtUtils.NEW_LINE + ArtUtils.NEW_LINE + ArtUtils.NEW_LINE + "Hey! It's Bob again, I just woke up."
						+ ArtUtils.NEW_LINE + "This is your newly update clients balance:");

	}

}
