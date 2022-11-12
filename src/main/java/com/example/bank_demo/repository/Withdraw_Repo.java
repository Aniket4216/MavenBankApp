package com.example.bank_demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_demo.model.Withdraw;

@Repository
public interface Withdraw_Repo extends ReactiveMongoRepository<Withdraw, Integer> {

}
