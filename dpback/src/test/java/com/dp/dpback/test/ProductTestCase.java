package com.dp.dpback.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dp.dpback.dao.ProductDAO;
import com.dp.dpback.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.dp.dpback");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");

	}

	// Getting a list category
	/*@Test
	public void testProductCURD() {
		product = new Product();
		// Create operation
		product.setBrand("Digi Pretty");
		product.setName("Anarkali Kurtis");
		product.setDescription("This is awesome Kurti is inred color");
		product.setActive(true);
		product.setCategoryId(33);
		product.setSupplierId(3);
		assertEquals("Something went wrong while inserting new product !", true, productDAO.addProduct(product));
		// Update products
		product = productDAO.getProductById(33);
		product.setUnitPrice(999.00);
		assertEquals("Something went wrong while update  product !", true, productDAO.updateProduct(product));
		// get product
		assertEquals("Something went wrong while get product !", "Digi Pretty",
				productDAO.getProductById(33).getBrand());
		// Delete products
		assertEquals("Something went wrong while update  product !", true, productDAO.deleteProduct(product));
		// get all products

		// assertEquals("Something went wrong while update product !", 6,
		// productDAO.list().size());
	}*/

	@Test
	public void testListActiveProducts() {

		assertEquals("Something went wrong while check active products !", 3, productDAO.listActiveProductByCategory(3).size());

	}

	@Test
	public void testListActiveProductByCategory() {

		assertEquals("Successfully  fatched list of categories inside the table!", 3, productDAO.listActiveProductByCategory(3).size());

	}

	@Test
	public void testGetLetestActiveProducts() {

		assertEquals("Successfully  fatched list of categories inside the table!", 3, productDAO.getLetestActiveProducts(3).size());

	}
}
