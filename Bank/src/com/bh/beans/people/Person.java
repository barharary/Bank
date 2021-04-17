package com.bh.beans.people;

import com.bh.Utils.ArtUtils;
import com.bh.Utils.ClientUtils;

public abstract class Person {

	private int id;
	private String name;
	private double age;

	private static int IdCunter;

	public Person() {
		this.id = ++IdCunter;
		this.name = getClass().getSimpleName();
		this.age = ClientUtils.generateAge();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		System.out.println("person");

		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("person");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ArtUtils.REGULAR_UNDERLINE + ArtUtils.NEW_LINE + "| Id:" + id + ArtUtils.TAB + " name: " + name
				+ ArtUtils.TAB + " age: " + age;
	}

}
