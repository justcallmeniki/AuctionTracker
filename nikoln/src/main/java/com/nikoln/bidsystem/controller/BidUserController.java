package com.nikoln.bidsystem.controller;

import com.nikoln.bidsystem.model.BidUser;
import com.nikoln.bidsystem.repository.BidUserRepository;
import com.nikoln.bidsystem.service.BidUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class BidUserController {

    @Autowired
    private BidUserRepository bidUserRepository;

    @Autowired
    private BidUserService bidUserService;

    @GetMapping("/all")
    public ResponseEntity<List<BidUser>> findAll() {
        List<BidUser> list = bidUserService.findAll();
        return new ResponseEntity<List<BidUser>>(list, HttpStatus.OK);
    }

    @PostMapping("/addNewBid")
    public ResponseEntity<Object> createBid(@RequestBody BidUser user){
        if(!bidUserService.validateUser(user)){
            return new ResponseEntity<>("Invalid user! UserName, product and bid are required fields. Please provide them.", HttpStatus.BAD_REQUEST);
        }

        String bidValidatonFailMessage = bidUserService.validateBid(user);
        if(bidValidatonFailMessage!=""){
            return new ResponseEntity<>(bidValidatonFailMessage, HttpStatus.BAD_REQUEST);
        }

        bidUserRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getCurrentWinningBid")
    public ResponseEntity<Integer> getCurrentWinningBid(String product){
        Integer winningBid = bidUserRepository.findCurrentWinningBid(product);
        return new ResponseEntity<Integer>(winningBid, HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<String>> getProductsForAUser(String userName){
        List<String> products = bidUserRepository.getProductsForAUser(userName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/getBids")
    public ResponseEntity<List<Object[]>> getBidsForAProduct(String product){
        List<Object[]> bids = bidUserRepository.getBidsForAProduct(product);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }
}
