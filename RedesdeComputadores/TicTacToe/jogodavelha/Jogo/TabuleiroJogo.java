package Jogo;

public class TabuleiroJogo {

	private boolean[][] table;
	private String[][] jogadasRealizadas;
	private String p1;
	private String p2;
	
	public TabuleiroJogo(String nick1, String nick2){
		table = new boolean[3][3];
		jogadasRealizadas = new String[3][3];
		p1 = nick1;
		p2 = nick2;
	}
	
	public boolean marcar(String nick, int x, int y){
		if(x>=0 && x<3 && y>=0 && y<3 && !isComplete()){
			if(nick.equals(p1))
				this.table[x][y] = true; 
			else
				this.table[x][y] = false;
			this.jogadasRealizadas[x][y] = nick;
			return true;
		}else
			return false;
	}
	
	public boolean[][] getTabuleiro(){
		return this.table;
	}
	
	public boolean velha(){
		return (!fimJogo());
	}
	
	public boolean isComplete(){
		for(int i=0; i<jogadasRealizadas.length; i++){
			for(int j=0; j<jogadasRealizadas[0].length; j++){
				if(jogadasRealizadas[i][j].equals(""));
			}
		}
		return false;
	}

	public boolean fimJogo(){		//Alguem ganhou?
		return fim(true)||fim(false);
	}
	
	private boolean fim(boolean atual) {		//Analisa se para o jogador 1 acabou e depois para o outro jogador. 
		boolean result = false;
		int cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8;
		cont1=cont2=cont3=cont4=cont5=cont6=cont7=cont8=0;
		
		for(int i=0,j=table[0].length; i<table.length && j>=0; i++, j--){
			if(this.table[i][i]==atual)
				cont1++;
			if(this.table[0][i]==atual)
				cont2++;
			if(this.table[1][i]==atual)
				cont3++;
			if(this.table[2][i]==atual)
				cont4++;
			if(this.table[i][0]==atual)
				cont5++;
			if(this.table[i][1]==atual)
				cont6++;
			if(this.table[i][2]==atual)
				cont7++;
			if(this.table[j][i]==atual)
				cont8++;
		}
		if(cont1 ==3 || cont2==3 || cont3==3 || cont4==3 || cont5==3 || cont6==3 || cont7 == 3 || cont8 == 3)
			result = true;
		return result;	
	}
	
	
	
	
	
	
}
