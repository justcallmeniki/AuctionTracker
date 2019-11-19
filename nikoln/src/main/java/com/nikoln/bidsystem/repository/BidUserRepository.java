package com.nikoln.bidsystem.repository;

import com.nikoln.bidsystem.model.BidUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BidUserRepository extends JpaRepository<BidUser, Long> {
    @Query("SELECT bid FROM BidUser WHERE lower(userName)=lower(:userName) AND lower(product)=lower(:product)")
    public Integer findByUserNameAndProduct(@Param("userName") String userName, @Param("product") String product);

    @Transactional
    @Modifying
    @Query("UPDATE BidUser SET bid=:bid WHERE lower(userName)=lower(:userName) AND lower(product)=lower(:product)")
    public void updateBid(@Param("bid") int bid, @Param("userName") String userName, @Param("product") String product);

    @Query("SELECT max(bid) FROM BidUser b WHERE lower(b.product) = lower(:product)")
    public Integer findCurrentWinningBid(@Param("product") String product);

    @Query("SELECT DISTINCT product FROM BidUser WHERE lower(userName)=lower(:userName)")
    public List<String> getProductsForAUser(@Param("userName") String userName);

    @Query("SELECT userName, bid FROM BidUser WHERE lower(product)=lower(:product)")
    public List<Object[]> getBidsForAProduct(@Param("product") String product);
}