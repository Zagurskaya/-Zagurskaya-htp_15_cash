## Banking service

### General description
   The web application provides the ability to buy/sale currency and making communal payment.
      The administrator create new users, update their data or delete them.
      The cashier make operations with clients.
      The controller work with exchange rates, send email to cliens and unload today entries.
  
____
### Users

 The user has three active roles: **ADMIN, KASSIR, CONTROLLER**
 
  * **Admin's scope**  
  
	* View his profile
	* View all users
	* Create new user
	* Delete a user
	* Update user's data
		
  * **Kassir's scope**  
    
	* View his profile
	* Open new duties
	* Close his duties
	* View currencies manual
	* View all rate NB
	* View all rate CB
    * Make operations with clients:(Number operation - name operation)
       * 10 - Buying currency
       * 20 - Selling currency
       * 998 - Communal payment
       * 1000 - Coming
       * 1100 - Spending 
	* View his balance
	* View his success operationds by this number duties
    
  * **Controller's scope**  
    
	* Load courses NB
	* Load courses CB
	* Unload entries
	* Send email



