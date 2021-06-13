package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this); //this LoginPage object
	}
	
	@FindBy(id="Email")
	@CacheLookup //web element that never changes, so we can lookup in cache
	WebElement txtEmail;
	
	@FindBy(id="Password")
	@CacheLookup //web element that never changes, so we can lookup in cache
	WebElement txtPassword;
	
	//@FindBy(xpath="//input[@value='Log in']")
	@FindBy(xpath="//button[@class='button-1 login-button']")
	@CacheLookup //web element that never changes, so we can lookup in cache
	WebElement btnLogin;
	
	@FindBy(linkText="Logout")
	@CacheLookup //web element that never changes, so we can lookup in cache
	WebElement linkLogout;
	
	public void setUsername(String username) {
		this.txtEmail.clear();
		this.txtEmail.sendKeys(username);
	}
	
	public void setPassword(String pw) {
		this.txtPassword.clear();
		this.txtPassword.sendKeys(pw);
	}
	
	public void clickLogin() {
		this.btnLogin.click();
	}
	
	public void clickLogout() {
		this.linkLogout.click();
	}

}
