package it.unisa.model;

import java.sql.Date;

public class OrdineBean {
	
    private String IDOrdine;
    private int IDAccount;
    private Date DataSpedizione;
    private int NumProdotti;
    private String DescrizioneProdotti;
    private String StatoOrdine;
    private Date DataConsegna;
    private Date DataOrdine;
    private float PrezzoFinale;
    private boolean valid;
	
    public float getPrezzoFinale() {
		return PrezzoFinale;
	}
    public void setPrezzoFinale(float f) {
    	this.PrezzoFinale=f;
    }
    public Date getDataSpedizione() {
		return DataSpedizione;
	}
    public void setDataSpedizione(java.util.Date data) {
		DataSpedizione = (Date) data;
	}
    
    public int getNumProdotti() {
		return NumProdotti;
	}
    public void setNumProdotti(int prod) {
		NumProdotti = prod;
	}
    
    public String getIDOrdine() {
		return IDOrdine;
	}
    public void setIDOrdine(String ID) {
		IDOrdine = ID;
	}
     

    public Date getDataConsegna() {
		return DataConsegna;
	}
    public void setDataConsegna(Date data) {
		DataConsegna = data;
	}
    
    public String getStatoOrdine() {
		return StatoOrdine;
	}
    public void setStatoOrdine(String stato) {
		StatoOrdine = stato;
	}
	
    public Date getDataOrdine() {
		return DataOrdine;
	}
    public void setDataOrdine(Date date) {
		DataOrdine = date;
	}
    
    public String getDescrizioneProdotti() {
		return DescrizioneProdotti;
	}
    public void setDescrizioneProdotti(String Prod) {
		DescrizioneProdotti = Prod;
	}
    
    public boolean isValid() {
       return valid;
	}
    public void setValid(boolean newValid) {
       valid = newValid;
	}
	/**
	 * @return the iDAccount
	 */
	public int getIDAccount() {
		return IDAccount;
	}
	/**
	 * @param iDAccount the iDAccount to set
	 */
	public void setIDAccount(int iDAccount) {
		IDAccount = iDAccount;
	}
	
    

}

