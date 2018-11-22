package com.dp.dpback.dao;

import java.util.List;

import com.dp.dpback.dto.Category;


public interface CategoryDAO {
	Category getCategoryById(int id);
	List<Category> list(); 
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	
}
