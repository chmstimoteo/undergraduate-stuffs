package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsGeneral {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tests");
		//$JUnit-BEGIN$
		suite.addTest(StreetRepositoryListTest.suite());
		suite.addTest(CrossRepositoryListTest.suite());
		suite.addTest(ReferencePointRepositoryListTest.suite());
		suite.addTest(VehicleRepositoryListTest.suite());
		suite.addTest(OccurenceRepositoryListTest.suite());
		suite.addTest(ForwardingAgentRepositoryListTest.suite());
		suite.addTest(GraphTest.suite());
		//$JUnit-END$
		return suite;
	}

}
