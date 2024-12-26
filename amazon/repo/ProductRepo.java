package com.hefshine.amazon.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hefshine.amazon.model.Product;
import com.hefshine.amazon.projection.Productui;
import com.hefshine.amazon.projection.ProductuiBuyer;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	List<Product> findByuserid(int userid);
	
	@Query(value="SELECT p.id, p.name, p.price, p.description, p.discount, p.quantity, p.rating, c.name AS catName FROM amazondata.product p INNER JOIN amazondata.category c ON p.categoryid = c.id WHERE p.userid = ?1", nativeQuery = true)
	List<Productui> getCatNameById(int userid);
	
	@Query(value = "select id,name,price,rating,discount,description,quantity,datediff(now(),date) as days from amazondata.product where categoryid=:i and price >=:j and price <= :k and rating<= :l", nativeQuery = true)
	List<ProductuiBuyer> getProductByFilter(@Param("i") int i,@Param("j") int j,@Param("k") int k,@Param("l") int l);
	
}
