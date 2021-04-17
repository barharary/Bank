package com.bh.exceptions;

public class ClientException extends Exception {

	private static final long serialVersionUID = 1L;

	public enum Errors {

		NOT_EXISTS("\t|Cannot do the operation. Client does not exist."), //

		ALREADY_EXISTS("\t|Cannot do the operation. Client already exist.");

		private String value;

		Errors(String string) {
			this.value = string;
		}

	}

	public ClientException(Errors exception) throws ClientException {
		super(exception.value);

	}

}
