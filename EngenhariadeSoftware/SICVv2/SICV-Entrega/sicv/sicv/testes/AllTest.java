package testes;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTest{
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for sicv.data.mem");
		//$JUnit-BEGIN$
		suite.addTest(OccurrenceRecordTest.suite());
		suite.addTest(EmployeeRecordTest.suite());
		//$JUnit-END$
		return suite;
	}

}
