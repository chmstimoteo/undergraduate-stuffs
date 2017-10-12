package Jogo;


public class Jogador {
	 
	private String nickname;
	private String ipCliente;
	
	public Jogador(String nickname){
		this.nickname = nickname;
	}
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getIpCliente(){
		return ipCliente;
	}
	public void setIpCliente(String ipCliente){
		this.ipCliente = ipCliente;
	}
}
