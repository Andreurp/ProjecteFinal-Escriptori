package net.andreu.projectPC;

public class Linia {
	private int id_linia;
	private int id_comanda;
	private int id_producte;
	private String quantitat;
	private String descompte;
	
	public Linia(int id_linia, int id_comanda, int id_producte, String quantitat, String descompte) {
		super();
		this.id_linia = id_linia;
		this.id_comanda = id_comanda;
		this.id_producte = id_producte;
		this.quantitat = quantitat;
		this.descompte = descompte;
	}

	public int getId_linia() {
		return id_linia;
	}

	public void setId_linia(int id_linia) {
		this.id_linia = id_linia;
	}

	public int getId_comanda() {
		return id_comanda;
	}

	public void setId_comanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}

	public int getId_producte() {
		return id_producte;
	}

	public void setId_producte(int id_producte) {
		this.id_producte = id_producte;
	}

	public String getQuantitat() {
		return quantitat;
	}

	public void setQuantitat(String quantitat) {
		this.quantitat = quantitat;
	}

	public String getDescompte() {
		return descompte;
	}

	public void setDescompte(String descompte) {
		this.descompte = descompte;
	}
}
