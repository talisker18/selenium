package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	public WebDriver driver;
	WaitHelper waitHelper;
	Logger logger = LoggerFactory.getLogger(SearchCustomerPage.class);
	
	public SearchCustomerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
		waitHelper = new WaitHelper(this.driver);
	}
	
	//elements of this page
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobMonth;
	
	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;
	
	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how = How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtCustomerRoles;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text),'Administrators']")
	@CacheLookup
	WebElement lstitemAdministrators;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text),'Registered']")
	@CacheLookup
	WebElement lstitemRegistered;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text),'Guests']")
	@CacheLookup
	WebElement lstitemGuests;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text),'Vendors']")
	@CacheLookup
	WebElement lstitemVendors;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr/td")
	List<WebElement> tableColumns;
	
	public void setEmail(String email) {
		waitHelper.waitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname) {
		waitHelper.waitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		waitHelper.waitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clickOnSearch() {
		btnSearch.click();
		waitHelper.waitForElement(btnSearch, 30);
	}
	
	public int getNoOfRows() {
		return (tableRows.size());
	}
	
	public int getNoOfColumns() {
		return (tableColumns.size());
	}
	
	public boolean searchCustomersByEmail(String email) {
		boolean flag = false;
		logger.info("email suchparameter= "+email);
		logger.info("tbl rows = "+getNoOfRows());
		
		for(int i=1;i<=getNoOfRows();i++) {
			String emailId = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText(); //td[2] means column no 2 where email is
			System.out.println(emailId);
			logger.info("gefundene email= "+emailId);
			if(emailId.equals(email)) {
				flag=true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomersByName(String fname, String lname) {
		boolean flag = false;
		
		for(int i=1;i<=getNoOfRows();i++) {
			String currentName = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText(); //td[3] means column no 2 where email is
			logger.info("gefundene name= "+currentName);
			String[] names = currentName.split(" "); //separate first name and last name
			if(names[0].equals(fname) && names[1].equals(lname)) {
				flag=true;
			}
		}
		return flag;
	}

}
