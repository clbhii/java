package com.cl.groovy.spock;

import com.cl.groovy.HttpService
import com.cl.groovy.HttpClient

import spock.lang.Specification

class HttpClientTest extends Specification {

    HttpClient httpClient = new HttpClient()
    HttpService httpService = Mock(HttpService)

    def setup() {
        httpClient.httpService = httpService
    }

    def "testHttpClientQuery"() {

        given:
        def resp = "say"
		
        when:
		//_代表任意一个参数,如果有二个参数就用_,_
		httpService.sayHello(_) >> resp
		

        then:
		httpClient.send(a) == result;

        where:
        a     | result
        "dd"    | "ddsay"
//		"dd1"    | "ddsay"

    }
}