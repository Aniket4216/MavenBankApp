package com.example.bank_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_demo.model.Balance;
import com.example.bank_demo.model.Deposit;
import com.example.bank_demo.model.Transfer;
import com.example.bank_demo.model.Withdraw;
import com.example.bank_demo.repository.Balance_Repo;
import com.example.bank_demo.repository.Deposit_Repo;
import com.example.bank_demo.repository.Transfer_Repo;
import com.example.bank_demo.repository.Withdraw_Repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BankApp_Controller {

	Balance balance = new Balance();
	Deposit deposit = new Deposit();
	Withdraw withdraw = new Withdraw();
	Transfer transfer = new Transfer();

	@Autowired
	private Balance_Repo balance_repo;
	@Autowired
	private Deposit_Repo deposit_repo;
	@Autowired
	private Withdraw_Repo withdraw_repo;
	@Autowired
	private Transfer_Repo transfer_repo;

//	Create account in bank
	@PostMapping("/createAccount")
	public Mono<Balance> createAccount(@RequestBody Balance balance) {
		return balance_repo.save(balance);
	}

//	Update Account Details 
	@PutMapping("/updateAccount/{accNo}")
	public Mono<Balance> updateAccount(@PathVariable int accNo, @RequestBody Balance balance) {
		return balance_repo.findById(accNo).doOnNext(b -> {
			b.setAddress(balance.getAddress());
			b.setAcc_HolderName(balance.getAcc_HolderName());
		}).flatMap(balance_repo::save);
	}

//	Delete Account Details
	@DeleteMapping("/deleteAccount/{accNo}")
	public Mono<Void> deleteAccount(@PathVariable int accNo) {
		return balance_repo.deleteById(accNo);
	}

//	Retrieve all accounts in bank
	@GetMapping("/showAll")
	public Flux<Balance> getAllAccount() {
		return balance_repo.findAll();
	}

//	View Balance
	@GetMapping("/viewBalance/{accNo}")
	public Mono<Balance> getUserById(@PathVariable int accNo) {
		return balance_repo.findById(accNo);
	}

//	Deposit amount in account
	@PostMapping("/deposit/{accNo}/amount/{amount}")
	public Mono<Deposit> depositAmount(@PathVariable int accNo, @PathVariable int amount) {
		balance_repo.findById(accNo).doOnNext(u -> u.setBalance(u.getBalance() + amount)).flatMap(balance_repo::save)
				.subscribe();
		return deposit_repo.save(new Deposit(accNo, amount));
	}

//	Withdraw amount in account
	@PostMapping("/withdraw/{accNo}/amount/{amount}")
	public Mono<Withdraw> withdrawAmount(@PathVariable int accNo, @PathVariable int amount) {
		balance_repo.findById(accNo).doOnNext(u -> u.setBalance(u.getBalance() - amount)).flatMap(balance_repo::save)
				.subscribe();
		return withdraw_repo.save(new Withdraw(accNo, amount));
	}

//	Transfer amount in account
	@PostMapping("/transfer/depositor/{accNo}/receiver/{aNo}/amount/{amount}")
	public Mono<Transfer> depositAmount(@PathVariable int accNo, @PathVariable int aNo, @PathVariable int amount) {
		balance_repo.findById(accNo).doOnNext(u -> u.setBalance(u.getBalance() - amount)).flatMap(balance_repo::save)
				.subscribe();
		balance_repo.findById(aNo).doOnNext(u -> u.setBalance(u.getBalance() + amount)).flatMap(balance_repo::save)
				.subscribe();
		return transfer_repo.save(new Transfer(accNo, aNo, amount));
	}

}