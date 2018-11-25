package com.pilotprogrammer.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import com.pilotprogrammer.beans.MyAnnotationConfigBean;
import com.pilotprogrammer.beans.MyComponentScanBean;
import com.pilotprogrammer.beans.MyXmlConfigBean;
import com.pilotprogrammer.config.Config;

public class Start {

	public static void main(String[] args) {
		Options options = new Options();

		Option first = new Option("f", "FirstName", true, "Your first name");
		first.setRequired(true);
		options.addOption(first);

		Option last = new Option("l", "LastName", true, "Your last name");
		last.setRequired(false);
		options.addOption(last);
		
		Option appHome = new Option("h", "appHome", true, "Current executing directory of JAR file");
		appHome.setRequired(false);
		options.addOption(appHome);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);
			System.exit(1);
		}

		String firstN = cmd.getOptionValue("FirstName");
		String lastN = cmd.getOptionValue("LastName");
		String appH = cmd.getOptionValue("appHome");
		
		if (!StringUtils.isEmpty(appH)) {
			System.setProperty("appHome", appH); // "/Users/garrettgranacher/eclipse-workspace/google-samples/CommandLineJava"
		} else {
			formatter.printHelp("utility-name", options);
			System.exit(1);
		}
		
		AnnotationConfigApplicationContext ctx = null;
		try {
			ctx = new AnnotationConfigApplicationContext();

			ctx.register(Config.class);
			ctx.refresh();

			MyAnnotationConfigBean myAnnotationConfigBean = ctx.getBean(MyAnnotationConfigBean.class);
			myAnnotationConfigBean.sayHello(firstN, lastN);
			
			MyXmlConfigBean myXmlConfigBean = ctx.getBean(MyXmlConfigBean.class);
			myXmlConfigBean.sayHello(firstN, lastN);
			
			MyComponentScanBean myComponentScanBean = ctx.getBean(MyComponentScanBean.class);
			myComponentScanBean.sayHello(firstN, lastN);
		} finally {
			if (ctx != null) {
				ctx.close();
			}
		}
	}

}
