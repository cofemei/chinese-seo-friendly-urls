class ChineseSeoFriendlyUrlsGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.3 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Chinese Seo Friendly Urls Plugin" // Headline display name of the plugin
    def author = "george mei"
    def authorEmail = "cofemei@gmail.com"
    def description = '''\
Helps to easily convert any string into a SEO-friendly one, eg from "The Lord of the Rings" to "the-lord-of-the-rings".

Useful if you want SEO-friendly URL's like /book/the-lord-of-the-rings instead of /book/show/123.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/chinese-seo-friendly-urls"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "GPL2"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "mei9g", email: "cofemei@gmail.com" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/cofemei/chinese-seo-friendly-urls" ]

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
