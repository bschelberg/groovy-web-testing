package com.artisan_progamming.groovy_web_testing;

import static org.junit.Assert.*;

import geb.Page;
import geb.spock.GebSpec;

class WikipediaHomePage extends Page {
	static url = "http://www.wikipedia.org/"
	static at = { title == "Wikipedia" }
	static content = {
		searchInput { $('#searchInput') }
		goButton { $('input', name: 'go') }
	}
}

class WikipediaGebPage extends Page {
	static at = { title.startsWith 'Geb' }
	static content = {
		heading { $('h1') }
		tableOfContents { $('#toc') }
		tableOfContentLinks { tableOfContents.find('li a') }
		sectionHeading { headingText -> $('h2', text: contains(headingText)) }
		firstParagraphInSection { headingText -> sectionHeading(headingText).next('p') }
		templateOptions(required: true, cache: false, to: null, wait: false) {}
		
	}
}

class PageObjectSpec extends GebSpec {

	def "should find Geb on Wikipedia"() {
		given: "a user is at the Wikipedia Home page"
		to WikipediaHomePage
		
		when: "they search for 'Geb'"
		searchInput = 'Geb'
		goButton.click()
		
		then: 'they should see the Geb page'
		at WikipediaGebPage
		heading.text() == 'Geb'
		tableOfContentLinks*.text() == ['1 Name', '2 Role and development', '3 Goose', '4 Notes']
		firstParagraphInSection('Role and development').text().startsWith('The oldest representation')
	}
}
