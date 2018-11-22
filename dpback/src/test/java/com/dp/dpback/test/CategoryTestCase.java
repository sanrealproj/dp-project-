package com.dp.dpback.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dp.dpback.dao.CategoryDAO;
import com.dp.dpback.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.dp.dpback");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");

	}

	/*
	 * @Test public void testAddCategory(){ category = new Category();
	 * 
	 * category.setName("Kurtis"); category.setDescription(
	 * "This is some description for laptop!");
	 * category.setProductURL("CAT_105.png");
	 * 
	 * assertEquals("Successfully added a category inside the table!"
	 * ,true,categoryDAO.add(category));
	 * 
	 * }
	 */
	/*
	 * //Getting a single category
	 * 
	 * @Test public void testGetCategory(){ Category category=
	 * categoryDAO.getCategoryById(3); assertEquals(
	 * "Successfully get a category inside the table!"
	 * ,"Mobile",category.getName());
	 * 
	 * }
	 */

	// Getting a single category
	@Test
	public void testUpdateCategory() {
		int id = 1;
		category = categoryDAO.getCategoryById(id);
		category.setName("Kids Party Wears");
		assertEquals("Successfully updated a category inside the table!", true, categoryDAO.update(category));

	}

/*	// Getting a single category
	@Test
	public void testDeleteCategory() {
		int id = 33;
		category = categoryDAO.getCategoryById(id);
		assertEquals("Successfully delete a category inside the table!", true, categoryDAO.delete(category));

	}*/
	// Getting a list category
	/*@Test
	public void testActiveList() {
		assertEquals("Successfully  fatched list of categories inside the table!", 4, categoryDAO.list().size());

	}*/

}
