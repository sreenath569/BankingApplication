Banking Application
-------------------

Features & Functionalities
--------------------------
1. Create new Account
2. Deposit Amount
3. Withdraw Amount
4. Display Balance


Implementation
--------------

Account class :-
  i. Attributes of the Account(AccountHolderName, Balance) are made as Private, so that direct access to these attributes was restricted.
  ii. Account attributes are made available only through Public getters and setters methods in which exceptions are handled wherever required.
  
BankingApplication class :-
  i. HashMap is used to store Account Number and Object of the respective Account.
  ii. Implemented methods(deposit, withdraw, showBalance), so that these methods will invoke the methods in Account class when the user performs Bank Operations.
  
InsufficientBalanceException class :-
  i. To handle the exceptional case, whenever the user tries to withdraw the amount which is greater than the available balance in the Account.
