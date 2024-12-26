package com.hefshine.amazon.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hefshine.amazon.model.OrderProduct;
import com.hefshine.amazon.projection.BuyerHistory;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Integer> {

	@Query(value="SELECT o.id,p.name,p.description,p.price,p.discount,o.quantity,o.amount as total,p.id as productid FROM amazondata.order_product as o join amazondata.my_order as m on o.myorderid=m.id join amazondata.product as p on p.id=o.productid where m.userid= :userid", nativeQuery = true)
	public List<BuyerHistory> getProductHistory(@Param("userid") int userid);
	
	@Query(value = "select avg(stars) from amazondata.ratings where productid=:pid",nativeQuery = true)
	double getAvgRatingByProductid(int pid);
}
