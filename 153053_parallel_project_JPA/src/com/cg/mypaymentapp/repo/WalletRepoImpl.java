package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.plaf.synth.SynthScrollBarUI;

import com.cg.mypaymentapp.JPAUtil.JPAUtil;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transaction;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo{


	@Override
	public boolean save(Customer customer) throws SQLException {
		EntityManager entitymanager = JPAUtil.getEntityManager();

		String mobileNo = customer.getMobileNo();
		String name = customer.getName();
		BigDecimal balance = customer.getWallet().getBalance();

		Customer cust = findOne(mobileNo);
		if(cust!=null)
		{
			entitymanager.getTransaction().begin();
			entitymanager.merge(customer);
			entitymanager.getTransaction().commit();
			return false;

		}
		else {
			entitymanager.getTransaction().begin();
			entitymanager.persist(customer);
			entitymanager.getTransaction().commit();

		}
		
		return true;

	}

	@Override
	public Customer findOne(String mobileNo) throws SQLException {
		EntityManager entitymanager = JPAUtil.getEntityManager();

		Customer cust = new Customer();

		entitymanager.getTransaction().begin();
		cust = entitymanager.find(Customer.class, mobileNo);
		if(cust!=null)
		{
			entitymanager.getTransaction().commit();
			return cust;
		}
		else {
			entitymanager.getTransaction().commit();
			return null;
		}
		
	}
	public void saveTransaction(String mobileNo, String statement) throws SQLException 
	{
		EntityManager entitymanager = JPAUtil.getEntityManager();

		Transaction transaction = new Transaction();
		transaction.setMobileNo(mobileNo);
		transaction.setStatement(statement);
		entitymanager.getTransaction().begin();
		entitymanager.persist(transaction);
		entitymanager.getTransaction().commit();
	}
	@Override
	public List getTransaction(String mobileNo) throws SQLException {
		EntityManager entitymanager = JPAUtil.getEntityManager();

		entitymanager.getTransaction().begin();
		Query query=entitymanager.createQuery("select transaction.statement from Transaction transaction where transaction.mobileNo=?", String.class).setParameter(1, mobileNo);
		List<String> transactionlist = new ArrayList<String>();
		transactionlist = query.getResultList();
		entitymanager.getTransaction().commit();
		return transactionlist;
	}

}
