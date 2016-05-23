package net.andreu.projectPC;

public class Producte {
	private int id_producte;
	private String nom;
	private int marca;
	private int tipus;
	private String caracteristiques;
	private String restriccions;
	private String imatge;
	private float preu;
	private int stock;
	
	public Producte(int id_producte, String nom, int marca, int tipus, String caracteristiques, String restriccions,
			String imatge, float preu, int stock) {
		super();
		this.id_producte = id_producte;
		this.nom = nom;
		this.marca = marca;
		this.tipus = tipus;
		this.caracteristiques = caracteristiques;
		this.restriccions = restriccions;
		this.imatge = imatge;
		this.preu = preu;
		this.stock = stock;
	}


	public int getId_producte() {
		return id_producte;
	}


	public void setId_producte(int id_producte) {
		this.id_producte = id_producte;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getMarca() {
		return marca;
	}


	public void setMarca(int marca) {
		this.marca = marca;
	}


	public int getTipus() {
		return tipus;
	}


	public void setTipus(int tipus) {
		this.tipus = tipus;
	}


	public String getCaracteristiques() {
		return caracteristiques;
	}


	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}


	public String getRestriccions() {
		return restriccions;
	}


	public void setRestriccions(String restriccions) {
		this.restriccions = restriccions;
	}


	public String getImatge() {
		return imatge;
	}


	public void setImatge(String imatge) {
		this.imatge = imatge;
	}


	public float getPreu() {
		return preu;
	}


	public void setPreu(float preu) {
		this.preu = preu;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
