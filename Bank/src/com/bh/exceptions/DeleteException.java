package com.bh.exceptions;

import java.util.Date;

public class DeleteException extends Exception {

	private static final long serialVersionUID = 1L;
	private Date timeStamp;

	public DeleteException(int clientId, String clientName, double balance, String message, Date timeStamp) {
		super(message + " " + timeStamp);
		this.timeStamp = timeStamp;
	}

}
