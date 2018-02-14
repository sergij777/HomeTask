package org.itstep;

import java.util.ArrayList;
import java.util.Date;

import org.itstep.dao.AccountDAO;
import org.itstep.dao.ActionDAO;
import org.itstep.dao.GoodDAO;
import org.itstep.model.Account;
import org.itstep.model.Good;
import org.itstep.service.Shopping;
import org.itstep.service.Timer;
import org.openqa.selenium.WebDriver;

public class Runner {

	public static void main(String[] args) {
		
		/*
		Account acc = new Account("superpuper999456@meta.ua", "1234567890qwer", "Kolyan", "Petrov");
		AccountDAO accountDao = new AccountDAO();
		Shopping aquarium = new Shopping();	
		WebDriver driver = aquarium.loginAmazonAccount(acc);
		Timer.waitSeconds(10);
		driver = aquarium.createWishList(driver);
		
		*/
		
//		Account acc = new Account();
//		AccountDAO accountDao = new AccountDAO();
//		acc = accountDao.getAccount("serg_sava@ukr.net");			
//		Shopping aquarium = new Shopping();										
//		WebDriver driver = aquarium.loginAmazonAccount(acc);		
//		Timer.waitSeconds(10);
		
		
		Account acc1 = new Account("superpuper999456a01@meta.ua", "1234567890qwer", "Kolyan", "Petrov");
	//	Account acc2 = new Account("sdghfry931@gmail.com", "34876tg2", "Vasiliy", "Vasechkin");
	//	Account acc3 = new Account("mjvfrts671@gmail.com", "34876tg2", "Gena", "Krocodil");
		ArrayList<Account> accounts = new ArrayList<Account>();	
		accounts.add(acc1);
	//	accounts.add(acc2);
	//	accounts.add(acc3);				
		AccountDAO accountDao = new AccountDAO();
		Shopping aquarium = new Shopping();	
		
		for (Account acc : accounts) {				
			WebDriver driver = aquarium.registerAmazonAccount(acc);
			accountDao.saveAccount(acc);		   
			Timer.waitSeconds(10);
			driver = aquarium.createWishList(driver);
		
		
		
		ActionDAO actionDao = new ActionDAO();
		GoodDAO goodDao = new GoodDAO();		
		ArrayList<String> asins = new ArrayList<String>();
		asins = goodDao.getAllGoodsAsin();
		int i = 1;
		for (String ass : asins) {
			driver = aquarium.addItemToWL(driver, ass);
			actionDao.saveAction(ass, acc, "added to wish list");
			if (i == 2) {
				driver = aquarium.addItemToCart(driver, ass);
				actionDao.saveAction(ass, acc, "added Cart");
			}
			i++;
		}   
		driver.quit();  
		}  
	}
}
