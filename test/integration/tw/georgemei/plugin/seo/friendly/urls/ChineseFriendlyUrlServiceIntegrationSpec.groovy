package tw.georgemei.plugin.seo.friendly.urls

import grails.test.spock.IntegrationSpec

class ChineseFriendlyUrlServiceIntegrationSpec extends IntegrationSpec {

	def chineseFriendlyUrlService

	def "String dynamic method is injected"() {
		expect:
		string == "The Lord of the Rings"
		string.asChineseFriendlyUrl() == chineseFriendlyUrlService.sanitizeWithDashes(string)
		string.asChineseFriendlyUrl() == "the-lord-of-the-rings"

		where:
		string = "The Lord of the Rings"
	}

	def "String dynamic method is injected chinese"() {
		expect:
		string == "灼眼的夏娜"
		string.asChineseFriendlyUrl() == chineseFriendlyUrlService.chineseSanitizeWithDashes(string)
		string.asChineseFriendlyUrl() == "burning-eyes-shakugan-no-shana"

		where:
		string = "灼眼的夏娜"
	}
}
