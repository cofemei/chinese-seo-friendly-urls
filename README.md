## Chinese SEO Friendly URL's Grails Plugin

It will translate your chinese text to english url based on [Microsoft Translator](https://datamarket.azure.com/dataset/bing/microsofttranslator).

First, subscribe to [Microsoft Translator](https://datamarket.azure.com/dataset/bing/microsofttranslator) on Windows Azure Marketplace.

Then, fill your Marketplace application info in <code>grails-app/conf/ChineseFriendlyUrlService.groovy</code>. You can create an app on "Marketplace Developer":https://datamarket.azure.com/developer/applications page.

``` groovy
bing {
	clientId = "Enter your Windows Azure Client Id here "
	clientSecret = "/* Enter your Windows Azure Client Secret here */"
}
```

This plugin is borrowed from [seo-friendly-urls](https://github.com/alvarosanchez/seo-friendly-urls), and refer [chinese_permalink](https://github.com/flyerhzm/chinese_permalink)



This is a simple Grails plugin which helps to easily convert any string into a SEO-friendly one,
eg, from `"The Lord of the Rings"` to `"the-lord-of-the-rings"`.

Useful if you want SEO-friendly URL's like `/book/the-lord-of-the-rings` instead of `/book/show/123`.

The code is borrowed from Wordpress's [formatting.php](http://core.svn.wordpress.org/trunk/wp-includes/formatting.php),
and initially ported to Groovy by [Jesús Lanchas](https://github.com/chechu).

## Usage

The plugin provides a simple Grails service, `chineseFriendlyUrlService`, which you can inject like any other service in your application.
That service has only two mehod, `String sanitizeWithDashes(String text)`. `String chineseSanitizeWithDashes(String text)`.

For convenience, the method `asChineseFriendlyUrl()` is also added to the String meta class.

So, given this domain class:
``` groovy
class Book {
	String title
	String sanitizedTitle

	def beforeValidate() {
		if (!sanitizedTitle) sanitizedTitle = title?.asChineseFriendlyUrl()
	}

	static constraints = {
		sanitizedTitle unique:true	//As an alternative, you may decide to make sanitizedTitle replace the default id.
	}
}
```

And given the following URL mapping:
``` groovy
class UrlMappings {

	static mappings = {
		"/book/$sanitizedTitle"(controller:'book', action:'show')

		...
	}
}
```

You can do the following in your controller:

``` groovy
class BookController {

	def show() {
		[book: Book.findBySanitizedTitle(params.sanitizedTitle)]
	}
}
```

Note that you can also use `friendlyUrlService.chineseSanitizeWithDashes()` in your controller.

## Examples

The following is a snippet of the provided
[Spock unit test](seo-friendly-urls/blob/master/test/unit/es/salenda/plugins/seo/friendly/urls/ChineseFriendlyUrlServiceSpec.groovy):

``` groovy
string                      | sanitized
"灼眼的夏娜"                | "burning-eyes-shakugan-no-shana"  //Chinese
"The Lord of the Rings"     | "the-lord-of-the-rings"   //Basics
"Raúl González Blanco"      | "raul-gonzalez-blanco"    //Accents
"España"                    | "espana"                  //N-tilde chars
"Los 3 Mosqueteros"         | "los-3-mosqueteros"       //Numbers
"Real Madrid® C.F."         | "real-madrid-cf"          //Edge cases
```

''' shell
grails test-app :unit  -echoOut
grails test-app :integration  -echoOut

'''
