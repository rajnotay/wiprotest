package com.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomainCrawler {
	
	private static final String HREF = "href=\"";

	/**
	 * A set of pages that have been analysed
	 */
	private final Set<URL> processedPages = new HashSet<URL>();
	
	/**
	 * A set of pages to be analysed
	 */
	private final Set<URL> unprocessedPages = new HashSet<URL>();
	
	

	/**
	 * 
	 * @param domain to crawl
	 * @throws IOException 
	 */
	public List<PageReport> produceReport(final String domain) throws IOException {
		
		List<PageReport> reports = new ArrayList<PageReport>();
		
		URL url = new URL(domain);
		PageReport report = new PageReport(domain, url);
		
		analysePage(url, report);
		
		reports.add(report);
		
		while (!unprocessedPages.isEmpty()) {
			URL page = unprocessedPages.iterator().next();
			PageReport pageReport = new PageReport(domain, page);
			analysePage(page, pageReport);
			
			reports.add(pageReport);
		}
		
		return reports;
		
	}
	
	public void analysePage(final URL url, final PageReport report) throws IOException {
		System.out.println("processing url:"+url.toString());
		BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	//System.out.println(inputLine);
        	if (inputLine.contains(HREF)) {
        		
        		String link = getLink(inputLine);
        		//System.out.println(link);
        		
        		if (link.startsWith(report.getDomain()) && !link.endsWith("php")) {
	        		URL lineurl = new URL(link);
	        		//System.out.println("url:"+lineurl.toString());
	        		
	        		report.addDomainLink(lineurl);
	        		
	        		if (!processedPages.contains(lineurl) && !lineurl.equals(url)) {
	        			unprocessedPages.add(lineurl);
	        		}
        		} else {
        			try {
        				URL lineurl = new URL(link);
    	        		//System.out.println("externalurl:"+lineurl.toString());
    	        		
        				report.addExternalPage(lineurl);
        			} catch (MalformedURLException e) {
        				System.out.println("Ignoring href: " + link);
        			}
        		}
        		
        		if (inputLine.contains("url(\'")) {
        			String urlLink = getUrlLink(inputLine);
        			if (urlLink!=null && urlLink.length()>4) {
	        			URL lineurl = new URL(urlLink);
	        			report.addStaticLink(lineurl);
        			}
        		}
        	}
        }
        in.close();
        
        unprocessedPages.remove(url);
        processedPages.add(url);
	}

	private String getUrlLink(String inputLine) {
		//System.out.println("static url inputLine: " + inputLine);
		int indexUrlLink = inputLine.indexOf("url(\'");
		
		int indexEndUrl = inputLine.indexOf("\'", indexUrlLink+5);
		
		String link = inputLine.substring(indexUrlLink+5, indexEndUrl);
		//System.out.println("static url: " + link);
		return link;
	}

	private String getLink(String inputLine) {
		int indexHref = inputLine.indexOf(HREF);
		
		int indexEndRef = inputLine.indexOf("\"", indexHref+6);
		
		String link = inputLine.substring(indexHref+6, indexEndRef);
		return link;
	}

}
