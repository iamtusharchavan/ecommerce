package com.hefshine.amazon.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hefshine.amazon.model.Cart;
import com.hefshine.amazon.projection.CartProduct;

public interface CartRepo extends JpaRepository<Cart, Integer> {

	public int countByUseridAndProductid(int userid, int productid);
	
	@Query(value = "SELECT c.id,p.id as productid,p.name,p.price,p.quantity,p.description,p.discount,p.rating FROM amazondata.product as p join amazondata.cart as c on c.productid=p.id where c.userid= :userId", nativeQuery = true)
	List<CartProduct> findCartProductByUserId(@Param("userId") int id);
	
//	public int deleteProductByid(int productid);
	
}
