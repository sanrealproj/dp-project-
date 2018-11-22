package com.dp.dpback.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dp.dpback.dao.CategoryDAO;
import com.dp.dpback.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

		query.setParameter("active", true);

		return query.getResultList();
	}

	@Override
	public Category getCategoryById(int id) {
	
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	@Transactional
	public boolean add(Category category) {
		// Add the category
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {

			return false;
		}
	}

	@Override
	public boolean update(Category category) {
	
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		return false;
		}
	}
	
	

}
