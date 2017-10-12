/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 *
 * @author Usuario
 */
public class libAllTest{

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } 
    
    public static Test suite() {
        TestSuite testSuite = new TestSuite("Run all tests for JUnit Demo");
        
        testSuite.addTest(GraphTest.suite());
        //testSuite.addTest(CrossFinderTest.suite());
        
        return testSuite;
    }
}
