package com.webcrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

/**
 * Main class for example
 *
 */
public class WebCrawler {
	
	private static String DOMAIN;
	
	private static final String usage = "Please provide domain to use in the format http://xxx.xxx";

	public static void main(String[] args) {
		
		checkArgs(args);
		
		DOMAIN = args[0];

		startCrawling();
		
		System.out.println("Exiting OK");
	}

	private static void startCrawling() {
		DomainCrawler domainCrawler = new DomainCrawler();
		try {
			List<PageReport> reports = domainCrawler.produceReport(DOMAIN);
			
			Iterator<PageReport> reportsIter = reports.iterator();
			while(reportsIter.hasNext()) {
				System.out.println(reportsIter.next());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(usage);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}

	private static void checkArgs(String[] args) {
		
		if (args.length<1) {
			throw new IllegalArgumentException(usage);
		} else if (!args[0].startsWith("http")) {
			throw new IllegalArgumentException(usage);
		}
		
	}

}
