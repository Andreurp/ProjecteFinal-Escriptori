package net.andreu.projectPC;

public class Comanda {
	private int id_comanda;
	private String data;
	private String estat;
	
	public Comanda(int id_comanda, String data, String estat) {
		super();
		this.id_comanda = id_comanda;
		this.data = data;
		this.estat = estat;
	}

	public int getId_comanda() {
		return id_comanda;
	}

	public void setId_comanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEstat() {
		return estat;
	}

	public void setEstat(String estat) {
		this.estat = estat;
	}
}
