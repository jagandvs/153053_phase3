package com.cg.mypaymentapp.repo;

import java.sql.SQLException;
import java.util.List;

import com.cg.mypaymentapp.beans.Customer;

public interface WalletRepo {

	public boolean save(Customer customer) throws SQLException;	
	public Customer findOne(String mobileNo) throws SQLException;
	public void saveTransaction(String sourceMobileNo, String string) throws SQLException;
	public List getTransaction(String mobileNo) throws SQLException;
}
