package com.example.bank_demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_demo.model.Balance;

@Repository
public interface Balance_Repo extends ReactiveMongoRepository<Balance, Integer>{

}
