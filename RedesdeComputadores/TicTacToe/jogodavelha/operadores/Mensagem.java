package operadores;

import java.io.Serializable;
import java.util.List;

import Jogo.Jogada;
import Jogo.Jogador;
import Jogo.Jogo;

public class Mensagem implements Serializable{		//Eh a estrutura com todos os dados interessantes
													//do Jogo.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tipo;			
	private Jogador jogador;
	private Jogada jogada;
	private List<Jogo> jogosAtivos;
	private List<Jogador> jogadoresOnline;
	private String mensagemCanal; 
	
	public Mensagem(String mensagem, int tipo){
		this.mensagemCanal = mensagem;
		this.tipo = tipo;
	}
	
	public Mensagem(){
		this.mensagemCanal = null;
		this.tipo = 0;
	}
	
	
	public int getTipo() {
		return this.tipo;
	}
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador){
		this.jogador = jogador;
	}
	public Jogada getJogada(){
		return jogada;
	}
	public void setJogada(Jogada jogada){
		this.jogada = jogada;
	}
	public List<Jogo> getJogosAtivos(){
		return jogosAtivos;
	}
	public void setJogosAtivos(List<Jogo> jogosAtivos){
		this.jogosAtivos = jogosAtivos;
	}
	public List<Jogador> getJogadoresOnline(){
		return jogadoresOnline;
	}
	public void setJogadoresOnline(List<Jogador> jogadoresOnline){
		this.jogadoresOnline = jogadoresOnline;
	}
	public String getMensagemCanal(){
		return mensagemCanal;
	}
	public void setMensagemCanal(String mensagemCanal){
		this.mensagemCanal = mensagemCanal;
	}
}
