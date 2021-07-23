package it.unisa.model;
import java.util.ArrayList;
import java.util.List;



public class Cart {

	private List<CartProduct> prodotti;
	
	public Cart() {
		prodotti = new ArrayList<CartProduct>();
	}
	
	public synchronized int DammiNumeroTotaleProdotti() {
		int somma = 0;
		for(int i=0; i<prodotti.size(); i++) {
			somma+=prodotti.get(i).getPezzi();
		}
		return somma;
	}
	
	public synchronized String DammiTutteLeDescrizioni() {
		String d = "";
		for(int i=0; i<prodotti.size(); i++) {
			d+=" "+prodotti.get(i).getDescrizione(i+1)+"\n";
		}
		return d;
	}
	
	public synchronized float DammiCostoTotaleCarrello() {
		float somma = 0;
		for(int i=0; i<prodotti.size(); i++) {
			somma+=prodotti.get(i).Costototale();
		}
		return somma;
	}
	
	
	public synchronized void addProduct(CartProduct cart) {
        CartProduct order;
        for(int i=0; i<prodotti.size(); i++) {
          order = prodotti.get(i);
          if (order.getIDProdotto().equals(cart.getIDProdotto())) {
              if(order.getPezzi()<cart.getDisponibilità())
            order.incrementa();
            return;
          }
  }
        prodotti.add(cart);
  }
public void deleteProduct(CartProduct cart) {
     CartProduct order;
        for(int i=0; i<prodotti.size(); i++) {
          order = prodotti.get(i);
          if (order.getIDProdotto().equals(cart.getIDProdotto())) {
              if(order.getPezzi()>0) {
            	  order.decrementa();
            	  if(order.getPezzi()==0) prodotti.remove(i);
            	  return;
              }
          }
        }
}


	public List<CartProduct> getProducts() {
		return  prodotti;
	}
}