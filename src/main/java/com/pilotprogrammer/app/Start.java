package com.pilotprogrammer.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pilotprogrammer.beans.MyAnnotationConfigBean;
import com.pilotprogrammer.beans.MyComponentScanBean;
import com.pilotprogrammer.beans.MyXmlConfigBean;
import com.pilotprogrammer.config.Config;

public class Start {

	public static void main(String[] args) {
		System.setProperty("appHome", "/Users/garrettgranacher/eclipse-workspace/google-samples/CommandLineJava");
//		for (int index = 0; index < args.length; index ++) {
//			System.out.println(String.format("arg %s", args[index]));
//		}

		Options options = new Options();

		Option input = new Option("i", "input", true, "input file path");
		input.setRequired(true);
		options.addOption(input);

		Option output = new Option("o", "output", true, "output file");
		output.setRequired(false);
		options.addOption(output);

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

		String inputFilePath = cmd.getOptionValue("input");
		String outputFilePath = cmd.getOptionValue("output");

		System.out.println(inputFilePath);
		System.out.println(outputFilePath);
		
		AnnotationConfigApplicationContext ctx = null;
		try {
			ctx = new AnnotationConfigApplicationContext();

			ctx.register(Config.class);
			ctx.refresh();

			MyAnnotationConfigBean myAnnotationConfigBean = ctx.getBean(MyAnnotationConfigBean.class);
			myAnnotationConfigBean.doStuff();
			
			MyXmlConfigBean myXmlConfigBean = ctx.getBean(MyXmlConfigBean.class);
			myXmlConfigBean.doStuff();
			
			MyComponentScanBean myComponentScanBean = ctx.getBean(MyComponentScanBean.class);
			myComponentScanBean.doStuff();
		} finally {
			if (ctx != null) {
				ctx.close();
			}
		}
	}

}
