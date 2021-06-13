package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws IOException {
		//reading config.properties file
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile); //load input stream into properties object
		
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j#2.properties");
	    // Write code here that turns the phrase above into concrete actions
		
		String br = configProp.getProperty("browser");
		
		if(br.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver(); //oder super.driver
		}
		
		if(br.equals("firefox")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver(); //oder super.driver
		}
		
		if(br.equals("ie")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver(); //oder super.driver
		}
		
		logger.info("**********Launching Browser ***********");
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
	    lp = new LoginPage(driver); //oder super.lp
	   
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("**********Opening URL ***********");
	    // Write code here that turns the phrase above into concrete actions
	    driver.get(url);
	    driver.manage().window().maximize(); //full screen
	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
	    // Write code here that turns the phrase above into concrete actions
		logger.info("**********Provide login details ***********");
	    lp.setUsername(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		logger.info("**********started login ***********");
	    // Write code here that turns the phrase above into concrete actions
	    lp.clickLogin();
	    Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    if(driver.getPageSource().contains("Login was unsuccessful.")) {
	    	driver.close();
	    	Assert.assertTrue(false);
	    }else {
	    	Assert.assertEquals(title, driver.getTitle());
	    }
	    Thread.sleep(3000);
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		logger.info("**********click on logout ***********");
	    lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("**********clsoing browser ***********");
	    // Write code here that turns the phrase above into concrete actions
	    driver.quit(); //vs driver.close() --> quit ist mächtiger und schliesst alles
	}
	
	//customer feature step definitions
	

	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		//adCust defined in baseclass
		super.addCust = new AddCustomerPage(super.driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", super.addCust.getPageTitle());
	}

	@When("User click on customers menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	    super.addCust.clickOnCustomersMenu();
	}
	@When("click on customers menu item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
	    super.addCust.clickOnCustomersMenuItem();
	}
	@When("click on add new button")
	public void click_on_add_new_button() throws InterruptedException {
		Thread.sleep(3000);
	    super.addCust.clickOnAddnew();
	}
	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", super.addCust.getPageTitle());
	}
	@When("user enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("**********adding new customer ***********");
	    super.addCust.setEmail(randomstring()+"@gmail.com");
	    super.addCust.setPassword("test123");
	    //super.addCust.setCustomerRoles("Guest");
	    Thread.sleep(3000);
	    super.addCust.setManagerOfVendor("Vendor 2");
	    super.addCust.setGender("Male");
	    super.addCust.setFirstName("Joel");
	    super.addCust.setLastName("Henz");
	    super.addCust.setDob("9/16/1987");
	    super.addCust.setCompanyName("Henz gmbh");
	    super.addCust.setAdminContent("this is for testing...");
	}
	@When("click on save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("**********saving customer data ***********");
	    super.addCust.clickOnSave();
	    Thread.sleep(3000);
	}
	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) { //string defined in Customers.feature
	    Assert.assertTrue(super.driver.findElement(By.tagName("body")).getText().contains(string));
	}
	
	// steps for searching customer id by email

	@When("Enter customer Email {string}")
	public void enter_customer_email(String email) {
		logger.info("**********search customer by email ***********");
	    super.searchCust=new SearchCustomerPage(super.driver);
	    super.searchCust.setEmail(email);
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    super.searchCust.clickOnSearch();
	    Thread.sleep(3000);
	}
	@Then("User should found Email in the search table {string}")
	public void user_should_found_email_in_the_search_table(String email) {
	    boolean status=super.searchCust.searchCustomersByEmail(email);
	    Assert.assertEquals(true, status);
	}
	
	
	//steps for searching customer by name
	
	@When("Enter customer FirstName {string}")
	public void enter_customer_first_name(String string) {
		logger.info("**********search customer by name ***********");
	    //new scenario, so we have to build new SearchCustomerPage
		super.searchCust=new SearchCustomerPage(super.driver);
		super.searchCust.setFirstName(string);
	}

	@When("Enter customer LastName {string}")
	public void enter_customer_last_name(String string) {
		super.searchCust.setLastName(string);
	}
	@Then("User should found FirstName {string} and LastName {string} in the search table")
	public void user_should_found_first_name_and_last_name_in_the_search_table(String fname, String lname) {
	    boolean status=super.searchCust.searchCustomersByName(fname, lname);
	    Assert.assertTrue(status);
	}




}
