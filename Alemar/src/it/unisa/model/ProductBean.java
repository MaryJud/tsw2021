package it.unisa.model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String IDProdotto;
	String nome;
	String descrizione;
	private String codiceCat;
	float prezzoNoIVA;
	int disponibilit�;
	int sconto;
	
	public ProductBean() {
		IDProdotto ="";
		nome = "";
		descrizione = "";
		disponibilit� = 0;
		sconto=0;
		prezzoNoIVA=0;  //controllare
	}

	public int getSconto() {
		return sconto;
	}
	
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	
	public String getCode() {
		return IDProdotto;
	}

	public void setCode(String code) {
		this.IDProdotto = code;
	}

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public String getDescription() {
		return descrizione;
	}

	public void setDescription(String description) {
		this.descrizione = description;
	}

	public float getPrice() {
		return prezzoNoIVA;
	}

	public void setPrice(float f) {
		this.prezzoNoIVA = f;
	}

	public int getQuantity() {
		return disponibilit�;
	}

	public void setQuantity(int quantity) {
		this.disponibilit� = quantity;
	}
	
	public void decrementaDisp(int Pezzi) {
		this.disponibilit�-=Pezzi;
	}

	@Override
	public String toString() {
		return nome + " (" + IDProdotto + "), " + prezzoNoIVA + " " + disponibilit� + ". " + descrizione+ " "+sconto;
	}

	/**
	 * @return the codiceCat
	 */
	public String getCodiceCat() {
		return codiceCat;
	}

	/**
	 * @param codiceCat the codiceCat to set
	 */
	public void setCodiceCat(String codiceCat) {
		this.codiceCat = codiceCat;
	}

}