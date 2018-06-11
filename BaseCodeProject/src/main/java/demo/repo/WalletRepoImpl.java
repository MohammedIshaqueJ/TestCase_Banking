package demo.repo;

import java.util.Map;

import demo.beans.Customer;

public class WalletRepoImpl implements WalletRepo {
	private Map<String,Customer> mapObj;
	Customer customer;	
	
	public WalletRepoImpl() {
		super();
		customer = new Customer();
	}

	public WalletRepoImpl(Map<String, Customer> obj) {
		super();
		this.mapObj = obj;
		customer = new Customer();
	}

	public boolean save(Customer customer) {
		mapObj.put(customer.getMobileNo(),customer);
		return true;
	}

	public Customer findOne(String mobileNo) {
		mapObj.get(mobileNo);
		return customer;
	}	
}
