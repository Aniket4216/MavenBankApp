package com.example.bank_demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "balance")
public class Balance {

	@Id
	private int accNo;
	private String Acc_HolderName;
	private int balance;
	private String Address;

}
