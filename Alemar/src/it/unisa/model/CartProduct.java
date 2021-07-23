package it.unisa.model;

public class CartProduct {
	private ProductBean prodotto;
	private int Pezzi;
	public CartProduct(ProductBean b) {
		setProdotto(b);
		this.Pezzi=1;
	}
	public int getPezzi() {
		return Pezzi;
	}
	
	public ProductBean getProdotto() {
		return prodotto;
	}
	public void setProdotto(ProductBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setPezzi(int pezzi) {
		Pezzi = pezzi;
	}
	public Float getPrezzo() {
		return this.prodotto.getPrice();
	}
	public String getIDProdotto() {
		return this.prodotto.getCode();
	}
	public String getNome() {
		return this.prodotto.getName();
	}
	public String getDescrizione(int i) {
		return "Prodotto "+i+"->"+this.prodotto.getDescription();
	}
	public int getSconto() {
		return this.prodotto.getSconto();
	}
	public int getDisponibilità() {
		return this.prodotto.getQuantity();
	}
	
	public void incrementa() {
		this.Pezzi=this.Pezzi+1;
	}
	public void decrementa() {
		this.Pezzi=this.Pezzi-1;
	}

	public float Costototale() {
		float costo= this.Pezzi*this.prodotto.getPrice();
		return costo;
	}
	
	public void cancellaOrdine() {
		this.Pezzi=0;
	}
	
	
}
