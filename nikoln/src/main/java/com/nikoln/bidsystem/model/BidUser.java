package com.nikoln.bidsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "BIDUSERS")
public class BidUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "product")
    private String product;
    @Column(name = "bid")
    private Integer bid;

    public BidUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public BidUser(String userName, String product, int bid) {
        this.userName = userName;
        this.product = product;
        this.bid = bid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
