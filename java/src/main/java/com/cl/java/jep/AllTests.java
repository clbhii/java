/*****************************************************************************

@header@
@date@
@copyright@
@license@

*****************************************************************************/

package com.cl.java.jep;

import junit.framework.*;

public class AllTests {
	
	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All JUnit Tests");
		suite.addTest(new JEPTest("testParseExpression"));
		suite.addTest(new LogarithmTest("testLogarithm"));
		suite.addTest(new NaturalLogarithmTest("testNaturalLogarithm"));
		return suite;
	}
}
