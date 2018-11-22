package com.dp.dpWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dp.dpWeb.exception.ProductNotFoundException;
import com.dp.dpback.dao.CategoryDAO;
import com.dp.dpback.dao.ProductDAO;
import com.dp.dpback.dto.Category;
import com.dp.dpback.dto.Product;




@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value ={"/","/home", "/index"})
	public ModelAndView index(){
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("categories", categoryDAO.list());	
		mv.addObject("userClickHome", true);
		return mv;
	}
	@RequestMapping(value ={"/about"})
	public ModelAndView about(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value ={"/contact"})
	public ModelAndView contact(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	@RequestMapping(value ={"/show/all/products"})
	public ModelAndView showAllProducts(){
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);
		return mv;
	}	
	@RequestMapping(value ={"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id){
		ModelAndView mv= new ModelAndView("page");
		Category category=null;
		category =categoryDAO.getCategoryById(id);
		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list());	
		mv.addObject("category", categoryDAO.getCategoryById(id));
		mv.addObject("userCategoryProduct", true);
		return mv;
	}
	//Viewing a single product
	@RequestMapping(value ={"show/{id}/product"})
	public ModelAndView showSingleProducts(@PathVariable("id") int id) throws com.dp.dpWeb.exception.ProductNotFoundException{
		ModelAndView mv= new ModelAndView("page");
		Product product = productDAO.getProductById(id);
		if(product == null) throw new ProductNotFoundException();
		//update the views count
		product.setViews(product.getViews()+1);
		productDAO.updateProduct(product);
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		return mv;
	}
	
}


//=======================================================================
//test code
/*@RequestMapping(value ={"/test"})
public ModelAndView test(@RequestParam(value="gretting", required=false) String greet){
	ModelAndView mv= new ModelAndView("page");
	mv.addObject("welcomeMsg", greet);
	
	return mv;
}

@RequestMapping(value ={"/test/{gretting}"})
public ModelAndView test1(@PathVariable("gretting") String greet){
	ModelAndView mv= new ModelAndView("page");
	mv.addObject("welcomeMsg", greet);
	
	return mv;
}*/

