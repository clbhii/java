package com.cl.groovy.spock;

import com.cl.groovy.SearchUtil

import spock.lang.Specification

class ExpectWhereTest extends Specification {

    def "testSearch"() {
        expect:
        SearchUtil.search(arr as int[], key) == result

        where:
        arr       | key | result
        []        | 1   | -1
        [1]       | 1   | 0
        [1]       | 2   | -1
        [3]      | 2   | -1
        [1, 2, 9] | 2   | 1
        [1, 2, 9] | 9   | 2
        [1, 2, 9] | 3   | -1
        //null      | 0   | -1
    }

}
