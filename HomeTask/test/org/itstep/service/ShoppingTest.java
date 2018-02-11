package org.itstep.service;

import static org.junit.Assert.*;

import org.itstep.dao.AccountDAO;
import org.itstep.dao.ActionDAO;
import org.itstep.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.itstep.service.Timer;
public class ShoppingTest {
	
	
	Account acc;
	AccountDAO accountDao;
	
	@Before
	public void setData() {
		acc = new Account();
		accountDao = new AccountDAO();
		
	}


	
	
	@Test
	public void testAddItemToWL() {
		Account acc = new Account();
		AccountDAO accountDao = new AccountDAO();
		acc = accountDao.getAccount("serg_sava@ukr.net");	
		ActionDAO actionDao = new ActionDAO();
		Shopping aquarium = new Shopping();									
		WebDriver driver = aquarium.loginAmazonAccount(acc);		
		Timer.waitSeconds(10);
		
	}

	@Test
	public void testAddItemToCart() {
		
	}

	
	// driver = imitator.addItemToWL(driver, "B077N43PST");		
	//assertTrue(driver.getPageSource().contains(""));
}
