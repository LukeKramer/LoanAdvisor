# LoanAdvisor
##Android App
##Introduction
The Domain that my assignment is based on is the loan domain. So i decide to create a domain that allows users to take out small loans based on their salaries.The system will then use the user salary and the amount of money they would like to loan to determine if the user would receive the the amount they would like or not. 

##Small Loan Domain UML
![Domain Model](/DomainUpdate.png)
##Description of the Small Loan Domain Model
A Person would register to use the application. The registration process would insure that the person gets added as a client in the Database.

The User will then recieve loggin details from the application. 

The user needs internet connection to loggin or register or make loan on the app on the app.

The user would then be able to apply for a loan. When applying for a loan two elements are needed number1 the clients income
(salary) and number2 the a desired amount of money that a user would want to loan out.Once these two elements are received 
the first thing that happens is the client income is used to Determine the Type of client and then type is sent to help calculate the loan risk.The clients requested loan amount is also then sent to help calculate the loan risk. The calculate loan receive date from the loan table in the database which holds the max and minimum amount of money available for different types of loans.At this point the Calculation of loans holds 4 elements number1 the userType number2 the users requested amount number 3 maximum loan amount number 4 minimum loan amount.These element are used to produce a risk report. That Report is used to determine a result that holds the approval or disapproval infomation about the loan and Vendor who could provide such a loan .That result is then mail to the user and 
can be referranced by the user when every please within their email. 

##Key Point

User income is used to determine which one of the three category types a user can fall under namely premium,working and minimum wage. These types are used in addition with the amount the client would like to loan out to determine weather a loan will be approved or not.

The Blue entities namely clients,loans and results represent the tables within the applications Loan database.
These enetitys are sent to the database on the backend in the cloud as well.

##Services

These services are bound services that run on its on repective thread to allow maximum through put and minimal application loading delays. These are the services in the order they will be called(ActivateclientService,ActivateUserTypeService,ActivateLoginService,ActivateUserNameService,ActivatePasswordService,ActivateBankConfirmationService,ActivateLoanResultService,ActivateLoanService).

##ClientAPI

This directory contains the methods used to communicate with the cloud/Backend.
These methods are also implemented in its Appropriate Activity
