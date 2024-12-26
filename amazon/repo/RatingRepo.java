package com.hefshine.amazon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hefshine.amazon.model.Ratings;

public interface RatingRepo extends JpaRepository<Ratings, Integer> {

    int countByProductidAndUserid(int productid,int userid);
	
	Ratings findByUseridAndProductid(int userid,int productid);
	
	@Query(value = "select avg(stars) from amazondata.ratings where productid=:pid",nativeQuery = true)
	double getAvgRatingByProductid(int pid);
}
