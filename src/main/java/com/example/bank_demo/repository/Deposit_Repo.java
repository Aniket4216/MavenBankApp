package com.example.bank_demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_demo.model.Deposit;

@Repository
public interface Deposit_Repo extends ReactiveMongoRepository<Deposit, Integer>{

}
