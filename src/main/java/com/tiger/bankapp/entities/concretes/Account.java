package com.tiger.bankapp.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="balance")
	private Double balance;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
