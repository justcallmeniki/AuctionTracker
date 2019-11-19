package com.nikoln.bidsystem.service;

import com.nikoln.bidsystem.model.BidUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nikoln.bidsystem.repository.BidUserRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;

@Service
public class BidUserService {

    @Autowired
    private BidUserRepository bidUserRepository;

    public List<BidUser> findAll() {

        List<BidUser> list = new LinkedList<BidUser>();
        Iterable<BidUser> it = bidUserRepository.findAll();
        it.forEach(i->list.add(i));

        return list;
    }

    public String validateBid(@RequestBody BidUser user){
        if( user.getBid()<=0){
            return "Bid can not be 0 or below 0.";
        }

        Integer previousBid = bidUserRepository.findByUserNameAndProduct(user.getUserName(), user.getProduct());
        if(previousBid != null){
            if(previousBid <= user.getBid()){
                bidUserRepository.updateBid(user.getBid(), user.getUserName(), user.getProduct());
                return "";
            } else {
                return "Current bid value can not be smaller than/equal to previous bid.";
            }
        }
        return "";
    }

    public boolean validateUser(@RequestBody BidUser user){
        boolean valid = (user.getUserName()==null||user.getUserName()==""||user.getProduct()==null||user.getProduct()==""||user.getBid()==null) ? false : true;
        return valid;
    }
}
