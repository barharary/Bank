package com.bh.beans.people;

import com.bh.Utils.ArtUtils;
import com.bh.Utils.ClientUtils;

public class RegularClient extends Client {

	private static int localId;

	public RegularClient() {
		super();
		this.setName(getName() + ++localId);
		this.setInterstRate(ClientUtils.getIntrestRate(this));

	}

	@Override
	public String toString() {
		return super.toString() + ArtUtils.TAB + " Type: " + getClass().getSimpleName() + ArtUtils.TAB
				+ ArtUtils.NEW_LINE + ArtUtils.VERTICAL_LINE;
	}

}
