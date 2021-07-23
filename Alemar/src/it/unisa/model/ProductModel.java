package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel {
	
	public ProductBean doRetrieveByKey(String code) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String s) throws SQLException;

	public void doSave(ProductBean prodotto) throws SQLException;

	public void doUpdate(String id,String x, String set) throws SQLException;

}
