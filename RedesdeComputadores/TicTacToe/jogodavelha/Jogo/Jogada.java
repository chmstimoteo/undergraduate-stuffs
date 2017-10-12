package Jogo;

import gui.TabuleiroCliente;

public class Jogada {

	private Jogador player1;
	private Jogador player2;
	private TabuleiroCliente tabuleirop1;
	private TabuleiroCliente tabuleirop2;
	private TabuleiroJogo tabuleiro;
	
	public Jogada(Jogador p1, Jogador p2, TabuleiroCliente t1, TabuleiroCliente t2){
		this.player1 = p1;
		this.player2 = p2;
		this.tabuleirop1 = t1;
		this.tabuleirop2 = t2;
		this.tabuleiro = new TabuleiroJogo(p1.getNickname(), p2.getNickname());
	}
	
	public void start(){
			
	}
	
	
	
}
