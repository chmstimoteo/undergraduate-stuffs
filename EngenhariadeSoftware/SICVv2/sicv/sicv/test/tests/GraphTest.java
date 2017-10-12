
package tests;

import java.util.Random;

import sicv.lib.Graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class GraphTest extends TestCase{

    private Graph graph;
    
    public GraphTest() {
    }

    
    public static void setUpClass() throws Exception {
    }

    
    public static void tearDownClass() throws Exception {
    }

    
    private void criarGrafo(){
        int x[] = {1,2,3,4,5,6,7,8,9,10};
        int y[] = {1,2,3,4,5,6,7,8,9,10};
        boolean directed = true;
        for(int i=0; i<20; i++){
            Random r = new Random();
            int num = r.nextInt(10);
            int num2 = r.nextInt(10);
            graph.insertEdge(x[num], y[num2],num, directed);
        }
    }
    
    private void exemploPadrao(){
        int x[] = {1,2,3,4,5,6,7,8,9,10};
        boolean directed = true;
        graph.insertEdge(x[0], x[1],10, directed);
        graph.insertEdge(x[1], x[2],2, directed);
        graph.insertEdge(x[2], x[3],5, directed);
        graph.insertEdge(x[3], x[0],4, directed);
        graph.insertEdge(x[1], x[4],8, directed);
        graph.insertEdge(x[9], x[7],2, directed);
        graph.insertEdge(x[5], x[8],14, directed);
        graph.insertEdge(x[7], x[9],1, directed);
        
        
        
    }
    
    //@Before
    public void setUp() {
        int n = 10;
        graph = new Graph(n);
        criarGrafo();
    }

    //@After
    public void tearDown() {
        graph = null;
    }

    /**
     * Test de getDistance method, da class Graph.
     */
    //@Test
    public void testGetDistance() {
        int source = 1;
        int destination = 10;
        boolean expected = true;
        boolean result = true;
        
        for(int i=0; i<100; i++){
            int atual = graph.getDistance(source, destination);
            if((atual >(destination*10)) && (atual!= 1000000))
                result = result && false;
            
        }
        
        assertEquals("GetDistanceTest failed", expected, result);
    }

   
    /**
     * Test de insertEdge method, da class Graph.
     */
    //@Test
    public void testInsertEdge() {
        boolean expected = true;
        boolean result = true;
        int peso = 1;
        graph.insertEdge(1,9,peso,true);
        
        for(int i=0; i<100; i++){
            
            
            int atual = graph.getDistance(1,9);
                if(atual > peso)
                    result = (result && false);
        }
        
        assertEquals("InsertEdgeTest failed", expected, result);        
    }
    
    //@Suite 
    public static Test suite() {
        return new TestSuite(GraphTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }
    

}