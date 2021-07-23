package it.unisa.model;

public class ImageBean {
	
	 private int IDProdotto;
	 private byte[] foto;
	 
	 public byte[] getFoto() {
		return foto;
	}
	 public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	 public int getIDProdotto() {
		return IDProdotto;
	 }
	 
	 public void setIDProdotto(int iDProdotto) {
		IDProdotto = iDProdotto;
	 }
}
