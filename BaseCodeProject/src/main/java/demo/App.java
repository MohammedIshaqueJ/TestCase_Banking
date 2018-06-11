package demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import demo.beans.Customer;
import demo.repo.WalletRepo;
import demo.repo.WalletRepoImpl;
import demo.service.WalletService;
import demo.service.WalletServiceImpl;


public class App {
    public static void main( String[] args ){
    	Map<String,Customer> data= new HashMap<String, Customer>();
        WalletRepo walletrepo = new WalletRepoImpl(data);
        
        WalletService service = new WalletServiceImpl(walletrepo);     
        
    }
}
