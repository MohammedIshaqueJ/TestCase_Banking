package demo.service;

import java.math.BigDecimal;

import demo.beans.Customer;
import demo.beans.Wallet;
import demo.repo.WalletRepo;

public class WalletServiceImpl implements WalletService {

	private WalletRepo walletRepo;
	private Customer customer;
	private Wallet wallet;
	
	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
		customer= new Customer();
		wallet = new Wallet();
	}
	
	public Customer createAccount(String name, String mobileNo,BigDecimal amount) {
		wallet= new Wallet(amount);
		customer = new Customer(name, mobileNo,wallet);
		if(walletRepo.save(customer))
		{
		return customer;
		}
		else
			return null;
	}

	public Customer showBalance(String mobileNo) {
		
		if(walletRepo.findOne(mobileNo)!=null)
		{
			return walletRepo.findOne(mobileNo);
		}
		else
			return null;
		
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {

		Customer src = walletRepo.findOne(sourceMobileNo);		
		Wallet src_Wallet = src.getWallet();
		
		Customer trg = walletRepo.findOne(targetMobileNo);
		Wallet trg_Wallet = trg.getWallet();
		
		if((src_Wallet!=null && trg_Wallet !=null)){
			
			BigDecimal sourceamount = src_Wallet.getBalance();
			sourceamount = sourceamount.subtract(amount);
			src_Wallet.setBalance(sourceamount);
			
			BigDecimal targetamount = trg_Wallet.getBalance();
			targetamount = targetamount.add(amount);
			trg_Wallet.setBalance(targetamount);
						
			src.setWallet(src_Wallet);
			trg.setWallet(trg_Wallet);
			
			walletRepo.save(src);
			walletRepo.save(trg);
			
			return src;
		}
		else
		{
		return null;
		}
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		
		customer = walletRepo.findOne(mobileNo);
		if(customer.getMobileNo()!=null)
		{
			wallet.setBalance(wallet.getBalance().add(amount));
			customer.setWallet(wallet);
			walletRepo.save(customer);
			return customer;
		}
		else			
		return null;
	}

	
}
