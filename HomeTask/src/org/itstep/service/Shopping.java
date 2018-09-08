package org.itstep.service;

import java.util.List;

import org.itstep.model.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Shopping {
	 private static final String SEPARATOR = System.getProperty("file.separator");
	 
	 private static final String USER_DIR = System.getProperty("user.dir");
	 
	 private static final String DRIVER_PATH = USER_DIR + SEPARATOR + "lib" + SEPARATOR + "chromedriver.exe";
	 	 
	 private static final String BASE_URL = "https://www.amazon.com";

	 public static WebDriver getWebDriver() {		 
	  
		 System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
	  
		 WebDriver driver = new ChromeDriver();	  
	  
		 return driver;
	 }
	 
		public WebDriver registerAmazonAccount(Account account) {

			  WebDriver driver = getWebDriver();
			  Timer.waitSeconds(3);
			  driver.get("https://www.amazon.com");
			  Timer.waitSeconds(5);

			  WebElement registerBlock = driver.findElement(By.id("nav-flyout-ya-newCust"));
			  WebElement registerLinkElement = registerBlock.findElement(By.tagName("a"));

			  String registerLink = registerLinkElement.getAttribute("href");

			  driver.get(registerLink);
			  Timer.waitSeconds(25);

			  WebElement nameElement = driver.findElement(By.id("ap_customer_name"));
			  WebElement emailElement = driver.findElement(By.id("ap_email"));
			  WebElement passwordElement = driver.findElement(By.id("ap_password"));
			  WebElement checkPasswordElement = driver.findElement(By.id("ap_password_check"));
			  WebElement submitElement = driver.findElement(By.id("continue"));

			  nameElement.sendKeys(account.getFirstName() + " " + account.getSecondName());
			  emailElement.sendKeys(account.getLogin());
			  passwordElement.sendKeys(account.getPassword());
			  checkPasswordElement.sendKeys(account.getPassword());

			  submitElement.submit();

			  String pageLink = driver.getCurrentUrl();
			  Timer.waitSeconds(6);
			  driver.get(pageLink);

			  Timer.waitSeconds(20);

			  return driver;
		}	 
	 
		public WebDriver loginAmazonAccount(Account account) {
			  WebDriver driver = getWebDriver();
			  
			  Timer.waitSeconds(5);
			  driver.get(BASE_URL);
			  Timer.waitSeconds(5);
			  
			  WebElement loginBlock = driver.findElement(By.id("nav-flyout-ya-signin"));
			  WebElement loginLinkElement = loginBlock.findElement(By.tagName("a"));
			  
			  String loginLink = loginLinkElement.getAttribute("href");
			  
			  loginLink = loginLink.contains(BASE_URL) ? loginLink : BASE_URL+loginLink;
			  
			  driver.get(loginLink);
			  Timer.waitSeconds(10);
			  
			  //if the page contains fields "email" and "password"
			  if (driver.getPageSource().contains("ap_password")) {
			  
			  WebElement emailElement = driver.findElement(By.id("ap_email"));
			  WebElement passwordElement = driver.findElement(By.id("ap_password"));
			  WebElement submitElement = driver.findElement(By.id("signInSubmit"));
			  
			  emailElement.sendKeys(account.getLogin());
			  passwordElement.sendKeys(account.getPassword());			  
			  submitElement.submit();	
			  
			  }
			  //if the page contains only field "email" 
			  //but field "password" will be on the next page
			  else {
				  WebElement emailElement = driver.findElement(By.id("ap_email"));
				  WebElement submitElement1 = driver.findElement(By.id("continue"));
				  
				  emailElement.sendKeys(account.getLogin());				  			  
				  submitElement1.submit();
				  Timer.waitSeconds(5);
				  
				  WebElement passwordElement = driver.findElement(By.id("ap_password"));
				  WebElement submitElement2 = driver.findElement(By.id("signInSubmit"));
				  
				  passwordElement.sendKeys(account.getPassword());			  
				  submitElement2.submit();	
				  
			  }
			  
			  Timer.waitSeconds(5);
			  
			  String currentPage = driver.getCurrentUrl();
			  driver.get(currentPage);
			  Timer.waitSeconds(5);
			  return driver;
		}

		public WebDriver addItemToWL(WebDriver driver, String itemAsin) {
			  
			  WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
			  searchInput.clear();			  
			  Timer.waitSeconds(5);
			  searchInput.sendKeys(itemAsin);
			  Timer.waitSeconds(10);
			  
			  WebElement searchBox = driver.findElement(By.id("nav-search"));
			  List<WebElement> inputElements = searchBox.findElements(By.tagName("input"));
			  for (WebElement inputElement : inputElements) {
			   if(inputElement.getAttribute("type").equals("submit")) {
			    inputElement.submit();
			    break;
			   }
			  }
			  Timer.waitSeconds(5);
			  String currentUrl = driver.getCurrentUrl();
			  driver.get(currentUrl);			  
			  Timer.waitSeconds(5);
			  
			  WebElement productsBlock = driver.findElement(By.id("s-results-list-atf"));			  
			  List<WebElement> productList = productsBlock.findElements(By.tagName("li"));			    
			  
			  String productLink = null;
			    for (WebElement productElement : productList) {
			     if (productLink != null) break;
			     if(productElement.getAttribute("data-asin") == null) continue;  
			     
			     if(productElement.getAttribute("data-asin").equals(itemAsin)) {
			      List<WebElement> productLinkElements = productElement.findElements(By.tagName("a"));
			      for (WebElement aElement : productLinkElements) {
			        
			          if(aElement.getAttribute("class") == null) continue; 
			         
			           if (aElement.getAttribute("class").contains("s-access-detail-page")) {            
			            productLink = aElement.getAttribute("href");
			              
			            if (!productLink.contains(BASE_URL)) {
			             productLink = BASE_URL + productLink;
			            } 			            
			            break; 
			           }          
			        }
			      }     
			    }
			  driver.get(productLink);
	          Timer.waitSeconds(5);	          
	         
			  String currentWindow = driver.getWindowHandle();  			  
			  WebElement wlBtn = driver.findElement(By.id("add-to-wishlist-button-submit"));			  
			  wlBtn.click();
			  Timer.waitSeconds(10);
			  
			  return driver;
		}					
		
		public WebDriver addItemToCart(WebDriver driver, String itemAsin) {
			  
			  WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
			  searchInput.clear();			  
			  Timer.waitSeconds(5);
			  searchInput.sendKeys(itemAsin);
			  Timer.waitSeconds(10);
			  
			  WebElement searchBox = driver.findElement(By.id("nav-search"));
			  List<WebElement> inputElements = searchBox.findElements(By.tagName("input"));
			  for (WebElement inputElement : inputElements) {
			   if(inputElement.getAttribute("type").equals("submit")) {
			    inputElement.submit();
			    break;
			   }
			  }
			  Timer.waitSeconds(5);
			  String currentUrl = driver.getCurrentUrl();
			  driver.get(currentUrl);
			  
			  Timer.waitSeconds(5);
			  
			  WebElement productsBlock = driver.findElement(By.id("s-results-list-atf"));
			  
			  List<WebElement> productList = productsBlock.findElements(By.tagName("li"));
			  			  
			  String productLink = null;
			    for (WebElement productElement : productList) {
			     if (productLink != null) break;
			     if(productElement.getAttribute("data-asin") == null) continue;  
			     
			     if(productElement.getAttribute("data-asin").equals(itemAsin)) {
			      List<WebElement> productLinkElements = productElement.findElements(By.tagName("a"));
			      for (WebElement aElement : productLinkElements) {
			        
			          if(aElement.getAttribute("class") == null) continue; 
			         
			           if (aElement.getAttribute("class").contains("s-access-detail-page")) {            
			            productLink = aElement.getAttribute("href");
			              
			            if (!productLink.contains(BASE_URL)) {
			             productLink = BASE_URL + productLink;
			            } 
			            
			            break; 
			           }          
			        }
			      }     
			    }
			  driver.get(productLink);
	          Timer.waitSeconds(5);
			  
			  WebElement wlBtn = driver.findElement(By.id("add-to-cart-button"));
			  
			  wlBtn.click();
			  Timer.waitSeconds(10);
			  
			  return driver;
		}				
}
