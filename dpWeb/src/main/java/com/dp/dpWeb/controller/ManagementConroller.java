package com.dp.dpWeb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dp.dpWeb.util.FileUtil;
import com.dp.dpWeb.validator.ProductValidator;
import com.dp.dpback.dao.CategoryDAO;
import com.dp.dpback.dao.ProductDAO;
import com.dp.dpback.dto.Category;
import com.dp.dpback.dto.Product;

@Controller
@RequestMapping(value="/manage")

public class ManagementConroller {
	@Autowired
	private CategoryDAO categoryDAO;	
	@Autowired
	private ProductDAO productDAO;
	private static final Logger logger = LoggerFactory.getLogger(ManagementConroller.class);
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public  ModelAndView  showManageProducts(@RequestParam(name="operation", required=false ) String operation )	{
		ModelAndView mv= new ModelAndView("page");
		
		
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		nProduct.setActive(true);
		nProduct.setSupplierId(1);
		mv.addObject("product", nProduct);
		
		if(operation!=null){
			
			if(operation.equals("product")){
				mv.addObject("message", "Products added successfully!");	
			}
			     
		}
		
		return mv;
	}
	
	@ModelAttribute("categories") 
	public List<Category> modelCategories() {
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}
	//Handling Product submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public  String  handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request)	{
		
		new ProductValidator().validate(mProduct, results);
		
		if(results.hasErrors()) {
			model.addAttribute("message", "Validation fails for adding the product!");
			model.addAttribute("userClickManageProduct",true);
			model.addAttribute("title","Manage Products");
			return "page";
		}	
		
		logger.info(mProduct.toString());
		
		if(mProduct.getId()==0){
		productDAO.addProduct(mProduct);
		}
				else{
			productDAO.updateProduct(mProduct);
			}
		//upload the file
		 if(!mProduct.getFile().getOriginalFilename().equals("") ){
			FileUtil.uploadFile(request, mProduct.getFile(), mProduct.getCode()); 
		 }
		
		return "redirect:/manage/products?operation=product";
	
	}

	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.GET)
	@ResponseBody
	public String managePostProductActivation(@PathVariable int id) {		
		Product product = productDAO.getProductById(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDAO.updateProduct(product);		
		return (isActive)? "Product Dectivated Successfully!": "Product Activated Successfully";
	}

	
	@RequestMapping("/{id}/product")
	public ModelAndView manageProductEdit(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		// Product nProduct = new Product();		
		mv.addObject("product", productDAO.getProductById(id));

			
		return mv;
		
	}
	
	@RequestMapping(value = "/category", method=RequestMethod.POST)
	public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {					
		categoryDAO.add(mCategory);		
		return "redirect:" + request.getHeader("Referer") + "?success=category";
	}
}
