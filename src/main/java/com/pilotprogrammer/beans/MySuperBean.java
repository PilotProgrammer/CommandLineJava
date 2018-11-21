package com.pilotprogrammer.beans;

import org.springframework.beans.factory.annotation.Value;

public abstract class MySuperBean {
	@Value("${commandline.greeting}")
	protected String middleGreeting;

	public void doStuff(String first, String last) {
		String className = getClass().toString();
		System.out.println(String.format("%s %s %s %s", className, middleGreeting, first, last));
	}
}
