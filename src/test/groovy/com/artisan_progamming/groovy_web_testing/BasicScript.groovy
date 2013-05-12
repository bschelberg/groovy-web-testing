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
		assert $("div.vectorTabs a[accesskey='e']").text() == 'Edit'
		assert $('input', placeholder: 'Search').displayed
		assert $('a', text: 'Read').text() == 'Read'
		assert $('a', title: contains('You can edit this page')).text() == 'Edit'
		assert $('#toc').find('li', 1).text() == '2 Role and development'
		assert $('li').filter('.toclevel-1').size() == 4
		assert $('#toc span').not('.tocnumber').size() == 5
		assert $('h2').has('.mw-headline').size() == 4
		assert $("h2", 1).text() == 'Name'
		assert $('h2')*.text()[0..1] == ['Name', 'Role and development']
	}
}
