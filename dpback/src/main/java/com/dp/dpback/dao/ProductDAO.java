package com.dp.dpback.dao;

import java.util.List;

import com.dp.dpback.dto.Product;


public interface ProductDAO {
	
	Product getProductById(int id);
	List<Product> list();
	boolean addProduct (Product product);
	boolean updateProduct (Product product);
	boolean deleteProduct (Product product);
	List<Product> listActiveProducts();
	List<Product> listActiveProductByCategory(int categoryId);
	List<Product> getLetestActiveProducts(int count);
}
