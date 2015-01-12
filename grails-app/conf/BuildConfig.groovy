grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {
		runtime "com.memetix:microsoft-translator-java-api:0.6.2", {
			excludes 'junit'
		}

		test "org.spockframework:spock-grails-support:0.7-groovy-2.0", {
			export = false
		}
	}

	plugins {
		build ':release:3.0.1', ':rest-client-builder:2.0.3', {
			export = false
		}

		test(":spock:0.7") {
			exclude "spock-grails-support"
			export = false
		}
	}
}
