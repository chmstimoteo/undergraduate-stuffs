package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for sicv.data.mem");
		//$JUnit-BEGIN$
		suite.addTest(StreetRepositoryListTest.suite());
		suite.addTest(VehicleRepositoryListTest.suite());
		suite.addTest(CrossRepositoryListTest.suite());
		suite.addTest(ForwardingAgentRepositoryListTest.suite());
		suite.addTest(ReferencePointRepositoryListTest.suite());
		suite.addTest(OccurenceRepositoryListTest.suite());
		//$JUnit-END$
		return suite;
	}

}
