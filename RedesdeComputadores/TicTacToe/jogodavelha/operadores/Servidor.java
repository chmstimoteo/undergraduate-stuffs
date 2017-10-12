package operadores;

import gui.GuiServidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Jogo.Jogador;
import Jogo.Jogo;

public class Servidor{

	private InetAddress ipServidor;
	private List<Cliente> clientesConectados;
	private List<Jogador> jogadoresAtivos;
	private List<Jogo> jogosAtivos;
	private GuiServidor gui;
	private final int portaServidor = PortasConstantes.portaServidor;
	private OuvinteServidor listenerServer;
	private PorteiroServidor worker;
	private Socket cliente;
	private ServerSocket servidorSocket;
	
	
	public Servidor(GuiServidor gui){
		this.setJogadores(new LinkedList<Jogador>());
		this.setClientesConectados(new LinkedList<Cliente>());
		this.gui = gui;
	}
	/**
	 * @param args
	 */

	public void aguardarConexoes(){
		try {
			this.servidorSocket = new ServerSocket(portaServidor);
			while(true){
				this.cliente = servidorSocket.accept();
				OperarioServidor wor = new OperarioServidor(gui, this, cliente);
				wor.start();
			}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		/*
		try {
			listenerServer = new OuvinteServidor(this, this.gui);
			this.ipServidor = InetAddress.getLocalHost();
			listenerServer.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void esperaMensagem(){
		OperarioServidor worker = new OperarioServidor(gui, this, this.cliente);
		worker.start();
	}
	
	
	
	public void propagarMensagem(Mensagem mensagem){
		for(int i=0;i<this.getJogadores().size();i++){
			this.clientesConectados.get(i).aguardarMensagem();
			Jogador jogador = this.getJogadores().get(i);
			worker = new PorteiroServidor(mensagem, jogador.getIpCliente(), cliente);
			worker.start();
		}
	}
	
	public void propagarMensagem(Mensagem mensagem, String ipCliente){
		Jogador jogador = jogadorIp(ipCliente);
		
		worker = new PorteiroServidor(mensagem, ipCliente, cliente);
		worker.start();
	}
	
	
	public Jogador jogadorIp(String ipCliente){
		Jogador result = null;
		Iterator<Jogador> iterator = jogadoresAtivos.iterator();
		while(iterator.hasNext()){
			Jogador j=iterator.next();
			if(j.getIpCliente().equals(ipCliente)){
				result =j; 
			}
		}
		return result;
	}
	
	
	
	public List<Jogador> getJogadoresAtivos() {
		return jogadoresAtivos;
	}
	public void setJogadoresAtivos(List<Jogador> jogadoresAtivos) {
		this.jogadoresAtivos = jogadoresAtivos;
	}
	public Socket getClienteSocket() {
		return cliente;
	}
	public void setClienteSocket(Socket cliente) {
		this.cliente = cliente;
	}
	public void addCliente(Cliente cliente) {
		this.clientesConectados.add(cliente);
	}
	public List<Cliente> getClientesConectados() {
		return clientesConectados;
	}
	public void setClientesConectados(List<Cliente> clientesConectados) {
		this.clientesConectados = clientesConectados;
	}
	
	public List<Jogador> getJogadores() {
		return jogadoresAtivos;
	}
	
	public void setJogadores(List<Jogador> jogadores){
		this.jogadoresAtivos = jogadores;
	}

	
	public void addJogadores(Jogador jogador){
		this.jogadoresAtivos.add(jogador);
	}
	
	public InetAddress getIpServidor() {
		return ipServidor;
	}
	
	public List<Jogo> getJogosAtivos() {
		return jogosAtivos;
	}
	

	public void setJogosAtivos(List<Jogo> jogosAtivos) {
		this.jogosAtivos = jogosAtivos;
	}
	
	public GuiServidor getGui() {
		return gui;
	}
	
	public OuvinteServidor getListenerServer() {
		return listenerServer;
	}
	public void setListenerServer(OuvinteServidor listenerServer) {
		this.listenerServer = listenerServer;
	}
	public PorteiroServidor getWorker() {
		return worker;
	}
	public void setWorker(PorteiroServidor worker) {
		this.worker = worker;
	}
	public void setIpServidor(InetAddress ipServidor) {
		this.ipServidor = ipServidor;
	}
	
	public void setGui(GuiServidor gui) {
		this.gui = gui;
	}
	

}
