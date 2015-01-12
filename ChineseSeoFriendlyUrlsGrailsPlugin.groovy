class ChineseSeoFriendlyUrlsGrailsPlugin {
	def version = "1.0"
	def grailsVersion = "2.2 > *"
	def title = "Chinese Seo Friendly Urls Plugin"
	def description = '''\
Helps to easily convert any string into a SEO-friendly one, eg from "The Lord of the Rings" to "the-lord-of-the-rings".

Useful if you want SEO-friendly URL's like /book/the-lord-of-the-rings instead of /book/show/123.
'''
	def documentation = "http://grails.org/plugin/chinese-seo-friendly-urls"
	def license = "GPL2"
	def developers = [
		[name: "George Mei", email: "cofemei@gmail.com"]
	]
   def issueManagement = [url: 'https://github.com/cofemei/chinese-seo-friendly-urls/issues']
	def scm = [url: "https://github.com/cofemei/chinese-seo-friendly-urls"]

	def doWithDynamicMethods = { ctx ->
		def friendlyUrlService = ctx.chineseFriendlyUrlService

		String.metaClass.asFriendlyUrl = { ->
			friendlyUrlService.sanitizeWithDashes(delegate)
		}

		String.metaClass.asChineseFriendlyUrl = { ->
			friendlyUrlService.chineseSanitizeWithDashes(delegate)
		}
	}
}
