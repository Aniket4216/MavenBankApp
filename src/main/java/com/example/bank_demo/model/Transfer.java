package com.example.bank_demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transfer")
public class Transfer {
	private int accNo;
	private int destaccNo;
	private int amount;
}
