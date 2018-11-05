package com.pilotprogrammer.beans;

public abstract class MySuperBean {
	public void doStuff() {
		String className = getClass().toString();
		System.out.println(String.format("%s is doing stuff!", className));
	}
}
