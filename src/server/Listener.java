package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import logic.Player;

public class Listener extends Thread {

	private PacoteSincronizado pac;
	private DatagramSocket socketServidor;//Primeiro socket, utilizado para "ouvir" solicitações do cliente de login e desafio.

	public Listener(PacoteSincronizado pac, DatagramSocket socketServidor) {
		this.pac = pac;
		this.socketServidor = socketServidor;
	}

	@Override
	public void run() {

		try {

			while (!isInterrupted()) {

				byte[] dadosRecepcao = new byte[1024];
				DatagramPacket pacoteRecepcao = new DatagramPacket(dadosRecepcao, dadosRecepcao.length);
				socketServidor.receive(pacoteRecepcao);//Recebe pacote do cliente
				String frase = new String(pacoteRecepcao.getData());
				InetAddress DirectionIP = pacoteRecepcao.getAddress();
				int porta = pacoteRecepcao.getPort();
				System.out.println("porta: " + porta);
				String[] palavras = frase.trim().split(";");
				Player jogador = new Player(palavras[0], porta, DirectionIP, palavras[1]);//Cria um objeto chamado player que pega os atributos da mensagem
				pac.receberPacote(jogador);//Passa esse objeto para o gerenciador.
			}

		} catch (SocketException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		}

	}
}
