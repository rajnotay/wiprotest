package com.webcrawler;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class PageReport {

	public PageReport(String domain, URL url) {
		this.domain = domain;
		this.url = url;
	}
	
	private String domain;
	
	private URL url;
	
	private Set<URL> domainLinks = new HashSet<URL>();
	
	private Set<URL> externalLinks = new HashSet<URL>();
	
	private Set<URL> staticLinks = new HashSet<URL>();

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		
		return domain;
	}

	public void addDomainLink(URL domainLink) {
		domainLinks.add(domainLink);
		
	}

	public void addExternalPage(URL lineurl) {
		externalLinks.add(lineurl);
		
	}

	public void addStaticLink(URL urlLink) {
		staticLinks.add(urlLink);
		
	}
	
	@Override
	public String toString() {
		return "PageReport [for domain=" + domain + ", and url=" + url + ", domainLinks="
				+ domainLinks + ", externalLinks=" + externalLinks
				+ ", staticLinks=" + staticLinks + "]";
	}

}

