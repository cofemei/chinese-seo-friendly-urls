package tw.georgemei.plugin.seo.friendly.urls

import java.util.regex.Pattern
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

class ChineseFriendlyUrlService{
	
	static transactional = false
	def grailsApplication

	def chineseSanitizeWithDashes(text) {
		if(!text) return ""

		GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader())
        ConfigObject config
        try {
        	config = new ConfigSlurper().parse(classLoader.loadClass('ChineseFriendlyUrlService'))
        	println config
        	Translate.setClientId(config.bing.clientId);
	    	Translate.setClientSecret(config.bing.clientSecret);
	    	String translatedText = Translate.execute(text, Language.CHINESE_TRADITIONAL, Language.ENGLISH);
	    	return sanitizeWithDashes(translatedText)
        }
        catch (Exception e) {
       		log.error "Error: ${e.message}", e
       		return "Translate failed"
        }
	}
	/**
	 * This method transforms the text passed as an argument to a text without spaces,
	 * html entities, accents, dots and extranges characters (only %,a-z,A-Z,0-9, ,_ and - are allowed).
	 *
	 * Borrowed from Wordpress: file wp-includes/formatting.php, function sanitize_title_with_dashes
	 * http://core.svn.wordpress.org/trunk/wp-includes/formatting.php
	 */
	def sanitizeWithDashes(text) {
		
		if(!text) return ""

		// Preserve escaped octets
		text = text.replaceAll('%([a-fA-F0-9][a-fA-F0-9])','---$1---')
		text = text.replaceAll('%','')
		text = text.replaceAll('---([a-fA-F0-9][a-fA-F0-9])---','%$1')

		// Remove accents
		text = removeAccents(text)

		// To lower case
		text = text.toLowerCase()

		// Kill entities
		text = text.replaceAll('&.+?;','')

		// Dots -> ''
		text = text.replaceAll('\\.','')

		// Remove any character except %a-zA-Z0-9 _-
		text = text.replaceAll('[^%a-zA-Z0-9 _-]', '')

		// Trim
		text = text.trim()

		// Spaces -> dashes
		text = text.replaceAll('\\s+', '-')

		// Dashes -> dash
		text = text.replaceAll('-+', '-')

		// It must end in a letter or digit, otherwise we strip the last char
		if (!text[-1].charAt(0).isLetterOrDigit()) text = text[0..-2]

		return text
	}

	/**
	 * Converts all accent characters to ASCII characters.
	 *
	 * If there are no accent characters, then the string given is just returned.
	 *
	 */
	private def removeAccents(text) {
		java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	}


}
