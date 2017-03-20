# wiprotest
wiprotest web crawler

Building
mvn clean install, pom.xml available

Running
After importing the project into an IDE can run using the main method in WebCrawler.class, following is an example
java WebCrawler http://wiprodigital.com/

While crawling the specified domain there will be continous output as to what page is being analysed in the domain
Once the analysis is finished then a PageReport line will be written to the system console for every page analysed in the domain in the following format

"PageReport [for domain=" + domain + ", and url=" + url + ", domainLinks="
				+ domainLinks + ", externalLinks=" + externalLinks
				+ ", staticLinks=" + staticLinks + "]"
        
Explanation of what could be done with more time
1) Threading to improve performance
2) More analysis of various page types and mime types
3) Testing on multiple domains
4) Logging
5) Unit and integration testing
6) Better exception and error handling
