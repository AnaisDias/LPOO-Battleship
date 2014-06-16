package logic;

import java.io.Serializable;
import java.net.InetAddress;

public class Player implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	

	private  Board field;
	
	
	
	private String name;
	private int porta;
	private InetAddress iP;
	private String acao;

	private boolean isMyTurn;
	


	/**
	 * Constructor of player
	 * 
	 * @param palavras
	 *			name of player
	 * @param porta
	 * 			port in which player is
	 * @param iP 
	 * 			player's ip address
	 */
	public Player(String palavras, int porta, InetAddress iP){
		name = palavras;
		this.setPorta(porta);
		this.setiP(iP);

	}

	/**
	 * Constructor of player
	 * 
	 * @param acao
	 *			
	 * @param porta
	 * 			port in which player is
	 * @param iP 
	 * 			player's ip address
	 * @param name
	 * 			player's name
	 */
	public Player(String acao, int porta, InetAddress directionIP,
			String name) {
		this.acao = acao;
		this.porta = porta;
		this.iP = directionIP;
		this.name = name;
	}

	

	/**
	 * get the boolean turn 
	 * @return Boolean turn
	 */
	public boolean geyMyturn()
	{
		return this.isMyTurn;
	}

	
	/**
	 * get the field of the player
	 * @return Field field
	 */
	public  Board getField() {
		return field;
	}

	

	/**
	 * Set the name of player
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Constructor creates a new player with a field and name
	 * @param Field field
	 * @param String name
	 */
	public Player(Board field, String name) {
		super();
		this.field = field;
		this.name = name;
	}


	/**
	 * Constructor of player
	 * 
	 * @param String name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Returns player's name
	 * 
	 * @return name
	 */
	public String getPlayerName() {
		return name;
	}

	/**
	 * Returns player's port
	 * 
	 * @return port
	 */
	public int getPorta() {
		return porta;
	}


	/**
	 * Sets player's port
	 * 
	 * @param porta		player's port
	 */
	public void setPorta(int porta) {
		this.porta = porta;
	}

	/**
	 * Returns player's iP
	 * 
	 * @return iP
	 */
	public InetAddress getiP() {
		return iP;
	}


	/**
	 * Sets player's iP
	 * 
	 * @param iP
	 */
	public void setiP(InetAddress iP) {
		this.iP = iP;
	}


	/**
	 * Returns player's action for server purposes
	 * 
	 * @return acao
	 */
	public String getAcao() {
		return acao;
	}


	/**
	 * Sets player's action according to server
	 * 
	 * @param acao
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}
}
