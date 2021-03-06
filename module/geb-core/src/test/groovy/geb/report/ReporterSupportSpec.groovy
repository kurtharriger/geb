/* 
 * Copyright 2011 the original author or authors.
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
package geb.report

import spock.lang.*

import geb.Browser

class ReporterSupportSpec extends Specification {
	
	def reportDir = new File("build/tmp/ReporterSupportSpec")
	
	def setup() {
		reportDir.deleteDir()
	}
	
	def "report filename escaping"() {
		given:
		def reporter = new ReporterSupport(reportDir) {
			void writeReport(String reportNameBase, Browser browser) {
				getFile(reportNameBase, "12 | 34") << "content"
			}
		}
		
		when:
		reporter.writeReport("12 | 34", null)
		
		then:
		new File(reportDir, "12___34.12___34").exists()
	}
	
	def cleanup() {
		reportDir.deleteDir()
	}
	
	
}