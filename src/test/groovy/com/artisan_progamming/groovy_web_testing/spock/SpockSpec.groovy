package com.artisan_progamming.groovy_web_testing.spock;

import static org.junit.Assert.*;
import geb.Page;
import geb.spock.GebSpec;

class WikipediaHomePage extends Page {
	static url = "http://www.wikipedia.org/"
	static at = { title == "Wikipedia" }
	static content = {
		searchInput { $('#searchInput') }
		goButton(to: [WikipediaGebPage, WikipediaSpockPage]) { $('input', name: 'go') }
	}
	
	def searchFor(String searchValue) {
		searchInput = searchValue
		goButton.click()
	}
}

class WikipediaGebPage extends Page {
	static at = { title.startsWith 'Geb' }
	static content = {
		heading { $('h1') }
		tableOfContents { $('#toc') }
		tableOfContentLinks { tableOfContents.find('li a') }
	}
}

class WikipediaSpockPage extends Page {
	static at = { title.startsWith 'Spock' }
	static content = {
		heading { $('h1') }
		tableOfContents { $('#toc') }
		tableOfContentLinks { tableOfContents.find('li a') }
	}
}

class SpockSpec extends GebSpec {

	def "should find Spock on Wikipedia"() {
		given:
		aUserIsAtTheWikipediaHomePage()
		
		when:
		searchFor('Spock')
		
		then:
		heading.text() == 'Spock'
	}
	
	def aUserIsAtTheWikipediaHomePage() {
		to WikipediaHomePage
	}
}
