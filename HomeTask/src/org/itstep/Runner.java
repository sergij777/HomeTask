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
				
		Account acc = new Account("superpuper999456@meta.ua", "1234567890qwer", "Kolya", "Petrov");
		AccountDAO accountDao = new AccountDAO();
		Shopping aquarium = new Shopping();	
		WebDriver driver = aquarium.loginAmazonAccount(acc);
		Timer.waitSeconds(10);		
		
		ActionDAO actionDao = new ActionDAO();
		GoodDAO goodDao = new GoodDAO();		
		ArrayList<String> asins = new ArrayList<String>();
		asins = goodDao.getAllGoodsAsin();
		int i = 1;
		
		//all goods add to wish list
		//each second goods add to cart
		for (String ass : asins) {
			driver = aquarium.addItemToWL(driver, ass);
			actionDao.saveAction(ass, acc, "added to wish list");				
				
				if ( i/2 == 0 ) {
				driver = aquarium.addItemToCart(driver, ass);
				actionDao.saveAction(ass, acc, "added to Cart");
				}
			i++;
		}   
		driver.quit();  		 
	}
}
