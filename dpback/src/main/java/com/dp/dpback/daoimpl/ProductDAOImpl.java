package com.dp.dpback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dp.dpback.dao.ProductDAO;
import com.dp.dpback.dto.Product;
@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Product getProductById(int id) {
		try{
		return sessionFactory
				.getCurrentSession()
				.get(Product.class, Integer.valueOf(id));
		}catch(Exception ex){
			
			ex.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Product> list() {
		try{
			return sessionFactory
					.getCurrentSession()
					.createQuery("FROM Product", Product.class).getResultList();
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
			
			
	}

	@Override
	public boolean addProduct(Product product) {
		try{
			sessionFactory
					.getCurrentSession()
					.persist(product);
			return true;
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			
			
	}

	@Override
	public boolean updateProduct(Product product) {
		try{
				sessionFactory
					.getCurrentSession()
					.update(product);
			return true;
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			
		
	}

	@Override
	public boolean deleteProduct(Product product) {
		product.setActive(false);
		try{
			sessionFactory
				.getCurrentSession()
				.update(product);
		return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts="FROM Product WHERE active=:active";
		try{
			return sessionFactory
					.getCurrentSession()
					.createQuery(selectActiveProducts, Product.class)
					.setParameter("active", true) .getResultList();
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
			
			
	}

	@Override
	public List<Product> listActiveProductByCategory(int categoryId) {
		String selectActiveProductsById="FROM Product WHERE active=:active and categoryId= :categoryId";
		try{
			return sessionFactory
					.getCurrentSession()
					.createQuery(selectActiveProductsById, Product.class)
					.setParameter("active", true).setParameter("categoryId", categoryId)
							.getResultList();
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
			
	
	}

	@Override
	public List<Product> getLetestActiveProducts(int count) {
	
			return sessionFactory
					.getCurrentSession()
					.createQuery("FROM Product WHERE active=:active ORDER BY id", Product.class)
					.setParameter("active", true)
					.setFirstResult(0)
					.setMaxResults(count)
							.getResultList();
			
			
			
	}

}
