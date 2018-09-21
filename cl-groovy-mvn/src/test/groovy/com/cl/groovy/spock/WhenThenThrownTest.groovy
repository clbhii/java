package com.cl.groovy.spock;

import java.util.function.Consumer

import org.spockframework.util.Assert

import com.cl.groovy.CatchUtil

import spock.lang.Specification

class WhenThenThrownTest extends Specification {

	def "testTryDo"() {
		when:
		CatchUtil.tryDo(1, { throw new IllegalArgumentException(it.toString())} as Consumer)

		then:
		def ex = thrown(Exception)
		ex.class.name == "java.lang.RuntimeException"
//		ex.cause.class.name == "java.lang.IllegalArgumentException"
	}
}


