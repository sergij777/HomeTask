package org.itstep.service;

import static org.junit.Assert.*;

import org.itstep.dao.AccountDAO;
import org.itstep.dao.ActionDAO;
import org.itstep.model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.itstep.service.Timer;
public class ShoppingTest {
		
	Account acc;
	AccountDAO accountDao;
	ActionDAO actionDao;
	Shopping aquarium;
	WebDriver driver;
	
	@Before
	public void setData() {
		acc = new Account("serg_sava@ukr.net", "testtest", "Serg", "Sava");
		accountDao = new AccountDAO();
		actionDao = new ActionDAO();
		aquarium = new Shopping();	
	}	
	
	@Test
	public void testRegisterAmazonAccount() {
		Account acc1 = new Account("sdghfry933@gmail.com", "34876tg2", "Vasiliy", "Vasechkin");
		WebDriver driver = aquarium.registerAmazonAccount(acc1);
		accountDao.saveAccount(acc1);		   
		Timer.waitSeconds(10);
		assertTrue(driver.getPageSource().contains("Hello, Vasiliy"));	
		Timer.waitSeconds(5);
		driver.quit();
	}
	
	@Test
	public void testAddItemToWL() {											
		WebDriver driver = aquarium.loginAmazonAccount(acc);		
		Timer.waitSeconds(10);
		driver = aquarium.addItemToWL(driver, "B07DH6KR77");
		Timer.waitSeconds(10);
		assertTrue(driver.getPageSource().contains("B07DH6KR77"));		
		Timer.waitSeconds(5);
		driver.quit();
	}
	
	@Test
	public void testAddItemToCart() {
		WebDriver driver = aquarium.loginAmazonAccount(acc);		
		Timer.waitSeconds(10);
		driver = aquarium.addItemToCart(driver, "B00O2H9AW6");
		Timer.waitSeconds(10);
		assertTrue(driver.getPageSource().contains("B00O2H9AW6"));	
		Timer.waitSeconds(5);
		driver.quit();
	}
}
