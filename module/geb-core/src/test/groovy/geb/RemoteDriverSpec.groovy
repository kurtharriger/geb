/* Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb

import geb.test.util.*
import spock.lang.*

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver

import org.openqa.selenium.TakesScreenshot

class RemoteDriverSpec extends Specification {

	def "get augmented driver"() {
		when:
		def server = new WebDriverServer()
		server.start()
		
		and:
		def browser = new Browser(new RemoteWebDriver(new URL(server.baseUrl[0..-2]), DesiredCapabilities.htmlUnit()))
		
		then:
		!browser.driver.is(browser.augmentedDriver)
		
		cleanup:
		browser?.driver?.quit()
		server?.stop()
	}
	
}