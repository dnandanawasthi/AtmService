You are tasked with developing software for an ATM machine. The software is responsible for validating customer account details and performing basic operations including balance inquiries and cash withdrawals.
A third party is developing the UI and will provide data to the application in an agreed format defined below. The application should receive the data, process the operations and then output the results in the required format also defined below. For the purposes of the test you are free to implement any mechanism for feeding input into your solution.
The solution should meet the following business requirements:
•	The ATM cannot dispense more money than it holds.
•	The ATM has a starting number of notes in the following denominations, €5, €10, €20, €50
•	The customer cannot withdraw more funds then they have access to.
•	The ATM should not dispense funds if the pin is incorrect.
•	The ATM should not expose the customer balance if the pin is incorrect.
•	The ATM should only dispense the exact amounts requested.
•	The ATM should dispense the minimum number of notes per withdrawal
•	The ATM should initialize with €2000 made up of 20 x €50s, 30 x €20s, 30 x €10s and 20 x €5s
•	The ATM should also initialize with the following accounts:

Account Number	PIN	Opening Balance	Overdraft
123456789	1234	800	200
987654321	4321	1230	150

Input / Output
•	The user should be able to request a balance check along with maximum withdrawal amount (if any).
•	The user should be able to request a withdrawal and if successful details of the notes that would be dispensed along with remaining balance.
•	User should be provided with meaningful error messages.


Command to build application:
        mvn clean install

Command to build docker image of application:
        mvn clean package docker:build

Command to build run docker image
        docker run -it -p 8080:8080 atmservice

Command to stop all the container
        docker stop $(docker ps -a -q)

Command to remove all the container
        docker rm $(docker ps -a -q)

Command to remove all the image
        docker rmi -f $(docker images -q)


Below are the sample URLs to test application output using Postman plugin

To check balance for given account number and pin
    http://localhost:8080/customer/balance/123456789?pin=1234

To withdraw amount
    http://localhost:8080/customer/withdraw/123456789?pin=1234&withdrawAmount=1000&atmId=1001

To check exceptional message
    http://localhost:8080/customer/balance/123456789?pin=12345

To get all the accounts
    http://localhost:8080/customer/accounts

