grails.config.locations = ["file:./${appName}-config.groovy"]

log4j = {
	error 'org.codehaus.groovy.grails',
	      'org.springframework',
	      'org.hibernate',
	      'net.sf.ehcache.hibernate'
}

grails {
	plugin {
		chineseSeoFriendlyUrls {
			bing {
				clientId = "CLIENT-ID"
				clientSecret = "CLIENT-SECRET"
			}
		}
	}
}
