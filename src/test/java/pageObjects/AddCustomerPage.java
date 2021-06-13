package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
public WebDriver driver;
	
	public AddCustomerPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this); //this LoginPage object
	}
	
	By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	
	By btnAddnew = By.xpath("//a[@class='btn bg-blue']");
	
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles= By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	
	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");
	
	By txtFirstName = By.xpath("//input[@id='FirstName']");
	By txtLastName = By.xpath("//input[@id='LastName']");
	
	By txtDob = By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName = By.xpath("//input[@id='Company']");
	
	By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	//action methods
	public void clickOnCustomersMenu() {
		this.driver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		this.driver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddnew() {
		this.driver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		this.driver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		this.driver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) {
		/*if(!role.equals("Vendors")) {
			this.driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span")).click();
		}*/
		this.driver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(role.equals("Administrators")) {
			listitem=this.driver.findElement(lstitemAdministrators);
		}else if(role.equals("Guests")) {
			listitem=this.driver.findElement(lstitemGuests);
		}else if(role.equals("Registered")) {
			listitem=this.driver.findElement(lstitemRegistered);
		}else if(role.equals("Vendors")) {
			listitem=this.driver.findElement(lstitemVendors);
		}else {
			listitem=this.driver.findElement(lstitemGuests);
		}
		
		listitem.click();
		
		/**
		 * if click() does not work:
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click();", listitem);
		 * */
	}
	
	public void setManagerOfVendor(String value) {
		//select for dropdown
		Select dropdown = new Select (this.driver.findElement(drpmgrOfVendor));
		dropdown.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			this.driver.findElement(rdMaleGender).click();;
		}else if(gender.equals("Female")) {
			this.driver.findElement(rdFeMaleGender).click();;
		}else {
			this.driver.findElement(rdMaleGender).click();; //default
		}
	}
	
	public void setFirstName(String fname) {
		this.driver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		this.driver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob) {
		this.driver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String cname) {
		this.driver.findElement(txtCompanyName).sendKeys(cname);
	}
	
	public void setAdminContent(String content) {
		this.driver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
		this.driver.findElement(btnSave).click();
	}

}
