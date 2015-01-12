package tw.georgemei.plugin.seo.friendly.urls

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ChineseFriendlyUrlService)
class ChineseFriendlyUrlServiceSpec extends Specification {

	void "when using service method strings are sanitized"() {
		expect:
		service.sanitizeWithDashes(string) == sanitized

		where:

		string						| sanitized
		""							| ""						//Empty string
		"The Lord of the Rings"		| "the-lord-of-the-rings"	//Basics
		"Raúl González Blanco"		| "raul-gonzalez-blanco"	//Accents
		"España"					| "espana"					//N-tilde chars
		"Los 3 Mosqueteros"			| "los-3-mosqueteros"		//Numbers
		"Real Madrid® C.F."			| "real-madrid-cf"			//Edge cases
		"Článok"					| "clanok"
	}

	void "when using service method strings are chinese sanitized"() {
		expect:
		// chinesefriendlyUrlService.sanitizeWithDashes(string) == sanitized
		service.chineseSanitizeWithDashes(string) == sanitized

		where:
		string						| sanitized
		""							| ""						//Empty string
		"灼眼的夏娜"                | "burning-eyes-shakugan-no-shana"  //Chinese
		"The Lord of the Rings"		| "the-lord-of-the-rings"   //Basics
		"Raúl González Blanco"		| "raul-gonzalez-blanco"	//Accents
		"España"					| "espana"					//N-tilde chars
		"Los 3 Mosqueteros"			| "los-3-mosqueteros"		//Numbers
		"Real Madrid® C.F."			| "real-madrid-cf"			//Edge cases
		"Článok"					| "clanok"
	}
}
