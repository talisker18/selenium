Feature: Customers

 Background: steps for every scenario
 	Given User Launch Chrome browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and password as "admin"
	And Click on Login
	Then User can view Dashboard

@sanity
Scenario: Add new Customer
	##Given User Launch Chrome browser
	##When User opens URL "https://admin-demo.nopcommerce.com/login"
	##And User enters Email as "admin@yourstore.com" and password as "admin"
	##And Click on Login
	##Then User can view Dashboard
	##When User click on customers menu
	And click on customers menu item
	And click on add new button
	Then user can view add new customer page
	When user enter customer info
	And click on save button
	Then user can view confirmation message "The new customer has been added successfully."
	And close browser
	
@regression	
Scenario: Search customer by EmailID
	##Given User Launch Chrome browser
	##When User opens URL "https://admin-demo.nopcommerce.com/login"
	##And User enters Email as "admin@yourstore.com" and password as "admin"
	##And Click on Login
	##Then User can view Dashboard
	##When User click on customers menu
	When User click on customers menu
	And click on customers menu item
	And Enter customer Email "victoria_victoria@nopCommerce.com"
	When Click on search button
	Then User should found Email in the search table "victoria_victoria@nopCommerce.com"
	And close browser

@regression	
Scenario: Search customer by Name
	##Given User Launch Chrome browser
	##When User opens URL "https://admin-demo.nopcommerce.com/login"
	##And User enters Email as "admin@yourstore.com" and password as "admin"
	##And Click on Login
	##Then User can view Dashboard
	##When User click on customers menu
	When User click on customers menu
	And click on customers menu item
	And Enter customer FirstName "Victoria"
	And Enter customer LastName "Terces"
	When Click on search button
	Then User should found FirstName "Victoria" and LastName "Terces" in the search table
	And close browser
	