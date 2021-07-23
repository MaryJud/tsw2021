package it.unisa.model;

import java.sql.Date;

public class AccountBean {
	
    private int IDAccount;
    private String Username;
    private String Password;
    private Date DataRegistrazione;
    private String GiftCard;
    private String Nome;
    private String Cognome;
    private String CF;
    private String Email;
    private boolean valid;
    private int admin;
	
    public Date getDataRegistrazione() {
		return DataRegistrazione;
	}
    public void setDataRegistrazione(java.util.Date dataRegistrazione2) {
		DataRegistrazione = (Date) dataRegistrazione2;
	}
    
    public String getGiftCard() {
		return GiftCard;
	}
    public void setGiftCard(String giftCard) {
		GiftCard = giftCard;
	}
    
    public int getIDAccount() {
		return IDAccount;
	}
    public void setIDAccount(int id) {
		IDAccount = id;
	}
    
    public String getNomeUtente() {
		return Username;
	}
    public void setNomeUtente(String nomeUtente) {
		Username = nomeUtente;
	}
    
    public String getPassword() {
		return Password;
	}
    public void setPassword(String password) {
		Password = password;
	}
    
    public String getCF() {
		return CF;
	}
    public void setCF(String cF) {
		CF = cF;
	}
    
    public String getCognome() {
		return Cognome;
	}
    public void setCognome(String cognome) {
		Cognome = cognome;
	}
	
    public String getEmail() {
		return Email;
	}
    public void setEmail(String email) {
		Email = email;
	}
    
    public String getNome() {
		return Nome;
	}
    public void setNome(String nome) {
		Nome = nome;
	}
    
    public String getUsername() {
		return Username;
	}
    public void setUsername(String username) {
		Username = username;
	}
    
    public boolean isValid() {
       return valid;
	}
    public void setValid(boolean newValid) {
       valid = newValid;
	}
    
	@Override
	public String toString() {
		return "AccountBean [Username=" + Username + ", Password=" + Password + ", Nome=" + Nome + ", Cognome="
				+ Cognome + ", CF=" + CF + ", Email=" + Email + "]";
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
    

}

