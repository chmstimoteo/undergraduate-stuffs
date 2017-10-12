package factory;



import java.util.Random;

import trees.AVLTree;
import trees.BinaryCompleteTree;
import trees.BinarySearchTree;
import trees.BinaryTree;
import exceptions.AvisoException;


//Classe que implementa toda lógica do programa
//Determina as médias de inserção e pesquisa de cada árvore proposta

public class Factory {

	
	
	private Comparable[] arrayInsetion;
	private Comparable[] arraySearch;
	
    private BinaryCompleteTree<Integer> binaryCompletyTree;
	private BinarySearchTree<Integer> binarySearchTree;
    private AVLTree<Integer> avlTree;
	
	private static  enum Opcoes {SEARCH_N,SEARCH_BINERY,SEARCH_AVL};
    
    
	
    private  final int MIL = 1000;
    private  final int CEM = 100;
    
    public Factory() 
    {
    	this.arrayInsetion = new Comparable[this.MIL];
    	this.arraySearch = new Comparable[this.CEM];
    	this.binaryCompletyTree = new BinaryCompleteTree<Integer>();
    	this.binarySearchTree = new BinarySearchTree<Integer>();
    	this.avlTree = new AVLTree<Integer>();
    	this.preencherArrays();
    }
    
    
    public void preencherArrays(){
        Random random = new Random();
        int aleatorio;
        int indice;

        for(indice = 0; indice < this.MIL; indice++){
                
        	    aleatorio = 100 + random.nextInt(900);
        	    Comparable numero = new Integer(aleatorio);
                this.arrayInsetion[indice] = numero;
        }
        
        for(indice = 0; indice <this.CEM; indice++){
            
    	    aleatorio = 100 + random.nextInt(900);
    	    Comparable numero = new Integer(aleatorio);
            this.arraySearch[indice] = numero;
    }
       
    }
    
   
    public String[]  insertion() throws AvisoException
    {
    	String[] medias =  new String[3];
    	medias[0] = String.valueOf(this.insertion(Opcoes.SEARCH_N));
    	medias[1] = String.valueOf(this.insertion(Opcoes.SEARCH_BINERY));
        medias[2] = String.valueOf(this.insertion(Opcoes.SEARCH_AVL));
        
        return medias;
    }
    
    public String[] search() throws AvisoException
    {
    	String[] medias =  new String[3];
    	medias[0] = String.valueOf(this.search(Opcoes.SEARCH_N));
    	medias[1] = String.valueOf(this.search(Opcoes.SEARCH_BINERY));
        medias[2] = String.valueOf(this.search(Opcoes.SEARCH_AVL));
        
        return medias;
    }
    
    public long insertion(Opcoes op) throws AvisoException{
        
    	long media = 0;
        switch(op)
        {
        case SEARCH_N: media = this.insertionSerchN();break;
        	
        case SEARCH_BINERY: media = this.insertionBinarySearchTree();break;
        	
        case SEARCH_AVL: media = this.insertionSearchAvl();break;	
        }
        
        return media;
    }
    
    
  public long search(Opcoes op) throws AvisoException{
        
    	long media = 0;
        switch(op)
        {
        case SEARCH_N: media = this.searchSerchN();break;
        	
        case SEARCH_BINERY: media = this.searchBinarySearchTree();break;
        	
        case SEARCH_AVL: media = this.searchSearchAvl();break;	
        }
        
        return media;
    }

    private long preencherArvore(BinaryTree tree)
    {
    	long soma = 0;
    	long tempo_0 = 0;
    	long tempo = 0;
        long dt = 0;
        
    	for(int i=0;i<this.MIL;i++)
    	{
    		//tem um syso
    		//System.out.println(i);
    		tempo_0 = System.nanoTime();
    		tree.insert(this.arrayInsetion[i]);
    		tempo = System.nanoTime();;
    		dt = tempo - tempo_0;
            soma+=dt;   	
    	}
    	System.out.println("Acabou");
    	return (soma/this.MIL);
    }
    
    private long insertionBinarySearchTree() throws AvisoException
    {
    	return this.preencherArvore(this.binarySearchTree);
    }
    
     
    private long insertionSerchN() throws AvisoException
    {
        return this.preencherArvore(this.binaryCompletyTree);
    }
    
    private long insertionSearchAvl() throws AvisoException
    {
    	
        return this.preencherArvore(this.avlTree);
    }
    
    
    
    
    public long searchArvore(BinaryTree<Integer> tree)
    {
    	long soma = 0;
    	long tempo_0 = 0;
    	long tempo = 0;
        long dt = 0;
        
    	for(int i=0;i<this.CEM;i++)
    	{
    		try{
    		tempo_0 = System.nanoTime();
    		tree.find((Integer)this.arraySearch[i]);
    		tempo = System.nanoTime();;
    		dt = tempo - tempo_0;
            soma+=dt;
    		}
    		catch(AvisoException e)
    		{
    			System.err.println(e.getMessage());
    		}
    	}
    	
    	return (soma/this.CEM);
    }
    
    
    private long searchBinarySearchTree() throws AvisoException
    {
    	return this.searchArvore(this.binarySearchTree);
    }
    
     
    private long searchSerchN() throws AvisoException
    {
    	return this.searchArvore(this.binaryCompletyTree);
    }
    
    private long searchSearchAvl() throws AvisoException
    {
    	
    	return this.searchArvore(this.avlTree);
    }
    
      
}
