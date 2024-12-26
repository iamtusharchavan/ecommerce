package com.hefshine.amazon.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hefshine.amazon.model.Cart;
import com.hefshine.amazon.model.MyOrder;
import com.hefshine.amazon.model.OrderProduct;
import com.hefshine.amazon.model.Product;
import com.hefshine.amazon.model.Ratings;
import com.hefshine.amazon.projection.BuyerHistory;
import com.hefshine.amazon.projection.CartProduct;
import com.hefshine.amazon.projection.ProductuiBuyer;
import com.hefshine.amazon.repo.CartRepo;
import com.hefshine.amazon.repo.MyOrderRepo;
import com.hefshine.amazon.repo.OrderProductRepo;
import com.hefshine.amazon.repo.ProductRepo;
import com.hefshine.amazon.repo.RatingRepo;

@RestController
@CrossOrigin
@RequestMapping("buyer")
public class BuyerController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	MyOrderRepo myOrderRepo;
	
	@Autowired
	OrderProductRepo orderProductRepo;
	
	@Autowired
	RatingRepo ratingRepo;
	
    @RequestMapping("getAllProductsByFilter")
    public List<ProductuiBuyer> getAllProductByFilter(@RequestBody int[] a){
    	return productRepo.getProductByFilter(a[0], a[1], a[2], a[3]);
    }
    
    @RequestMapping("addToCart/{pid}/{uid}")
    public int addToCart(@PathVariable int uid, @PathVariable int pid) {
    	int count = cartRepo.countByUseridAndProductid(uid, pid);
    	
    	if(count==0) {
    		Cart ca = new Cart();
    		ca.setProductid(pid);
    		ca.setUserid(uid);
    		cartRepo.save(ca);
    		return 1;
    	}else {
    		return 0;
    	}
    }
    
    @RequestMapping("getCartProduct/{id}")
    public List<CartProduct> getCartProduct(@PathVariable int id){
    	return cartRepo.findCartProductByUserId(id);
    }
    
    @RequestMapping("placeOrder/{id}")
    public int placeOrder(@PathVariable int id, @RequestBody int[][] a) {
    	try {
			MyOrder order = new MyOrder();
			order.setDate(new Date());
			order.setUserid(id);
			myOrderRepo.save(order);
			double totalAmount=0;
			for(int i=0; i<a.length;i++) {
				int[] a1 = a[i];
				int cartid=a1[0];
				int quantity = a1[1];
				Cart cart = cartRepo.findById(cartid).get();
				int productid = cart.getProductid();
				Product product = productRepo.findById(productid).get();
				OrderProduct obj = new OrderProduct();
				
				double amount = product.getPrice()*quantity;
				amount = amount -(amount*product.getDiscount()/100);
				obj.setAmount(amount);
				totalAmount+=amount;
				
				obj.setDate(new Date());
				obj.setProductid(productid);
				obj.setMyorderid(order.getId());
				obj.setQuantity(quantity);
				
				orderProductRepo.save(obj);
				
				cartRepo.delete(cart);
				
			}
			order.setAmount(totalAmount);
			myOrderRepo.save(order);
			return 1;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    @RequestMapping("history/{id}")
	public List<BuyerHistory> historyProduct(@PathVariable int id){
		return orderProductRepo.getProductHistory(id);
	}
    
    @RequestMapping("addRating")
    public int addRating(@RequestBody int[] a) {
    	try {
			int userid=a[0];
			int productid=a[1];
			int stars=a[2];
			int count= ratingRepo.countByProductidAndUserid(productid, userid);
			
			if(count==1) {
				Ratings rev = ratingRepo.findByUseridAndProductid(userid, productid);
				rev.setStars(stars);
				rev.setDate(new Date());
				ratingRepo.save(rev);
			}else if(count==0) {
				Ratings r = new Ratings();
				r.setDate(new Date());
				r.setProductid(productid);
				r.setStars(stars);
				r.setUserid(userid);
				ratingRepo.save(r);
			}else {
				 return 0;
			}
			double avg=ratingRepo.getAvgRatingByProductid(productid);
			Product product=productRepo.findById(productid).get();
			product.setRating(avg);
			productRepo.save(product);
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			  return 0;
		}
    }
    
    @DeleteMapping("deleteProduct/{productid}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productid) {
    	 try {
             cartRepo.deleteById(productid);
             return ResponseEntity.ok("Product deleted successfully");
         } catch (Exception e) {
             e.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product");
         }
    }

}
