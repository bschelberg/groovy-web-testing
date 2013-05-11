package com.artisan_progamming.groovy_web_testing;

import static org.junit.Assert.*;
import geb.spock.GebSpec;

class BasicScript extends GebSpec {

	def "should find Geb on Wikipedia"() {
		given: "user is at Wikipedia page"
		go 'http://www.wikipedia.org'
		
		when: "they search for 'Geb'"
		$('input', name: 'search').value('Geb')
		$('input', name: 'go').click()
		
		then: 'I should see the Geb page'
		assert $('h1').text() == 'Geb'
	}
}
