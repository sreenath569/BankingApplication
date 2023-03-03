import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


public class BankingApplication {
	
	Map<String, Account> AccountNumToAccount = new HashMap<>();
	
	
	public boolean isValidAccountNum(String accountNum) {
		if(AccountNumToAccount.containsKey(accountNum)) {
			return true;
		}
		return false;
	}
	
	public void createAccount(String accountNum, String accountHolder) {
		if(AccountNumToAccount.containsKey(accountNum)) {
			System.out.println("Account number already exists. Please choose different account number...");
		}
		else {
			AccountNumToAccount.put(accountNum,new Account(accountNum, accountHolder));
			System.out.println("Account successfully created...");
		}
	}
	
	public void depositAmount(String accountNum, int amount) {
		if(!isValidAccountNum(accountNum)) {
			System.out.println("Account Number doesn't exist. Please enter the correct Account Number...");
			return;
		}
		
		if(amount <= 0) {
			System.out.println("Please enter the valid Amount. Amount shouldn't be zero/negative");
			return;
		}
		
		Account acc = AccountNumToAccount.get(accountNum);
		acc.deposit(amount);
				
	}
	
	public void withdrawAmount(String accountNum, int amount) {
		
		if(!isValidAccountNum(accountNum)) {
			System.out.println("Account Number doesn't exist. Please enter the correct Account Number...");
			return;
		}
		if(amount <= 0) {
			System.out.println("Please enter the valid Amount. Amount shouldn't be zero/negative.");
			return;
		}
		
		Account acc = AccountNumToAccount.get(accountNum);
		
		try {
			acc.withdraw(amount);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void checkBalance(String accountNum) {
		if(!isValidAccountNum(accountNum)) {
			System.out.println("Account Number doesn't exist. Please enter the correct Account Number...");
			return;
		}
		
		Account acc = AccountNumToAccount.get(accountNum);
		System.out.println(acc.getAccountHolderName()+" "+acc.getBalance());
		
	}
	
	
	public static void main(String[] args) {
		
		BankingApplication bankUser = new BankingApplication();
		Scanner scan = new Scanner(System.in);
		String bankOperation = new String();
		String[] bankOperationArr = new String[3];
		
		System.out.println("---------Bank Operations-----------");
		System.out.println("To create new Bank Account, Please Enter like CREATE ACCOUNT_NUMBER ACCOUNT_HOLDER_NAME Ex:- CREATE ACC001 SUNIL");
		System.out.println("To deposit amount, ---------Please Enter like DEPOSIT ACCOUNT_NUMBER AMOUNT ------------Ex:- DEPOSIT ACC001 10000");
		System.out.println("To withdraw amount, --------Please Enter like WITHDRAW ACCOUNT_NUMBER AMOUNT -----------Ex:- WITHDRAW ACC001 10000");
		System.out.println("To check balance amount, ---Please Enter like BALANCE ACCOUNT_NUMBER -------------------Ex:- BALANCE ACC001");
		System.out.println("To Exit from Bank Operations, Please Enter like EXIT -----------------------------------Ex:- EXIT");
		
		
		while(true) {
			System.out.println("Please enter the Bank Operation");
			bankOperation = scan.nextLine();
			
			bankOperationArr = bankOperation.split(" ");
			
			switch(bankOperationArr[0]) {
			case "CREATE":
				bankUser.createAccount(bankOperationArr[1], bankOperationArr[2]);
				break;
			case "DEPOSIT":
				bankUser.depositAmount(bankOperationArr[1], Integer.parseInt(bankOperationArr[2]));
				break;
			case "WITHDRAW":
				bankUser.withdrawAmount(bankOperationArr[1], Integer.parseInt(bankOperationArr[2]));
				break;
			case "BALANCE":
				bankUser.checkBalance(bankOperationArr[1]);
				break;
			case "EXIT":
				System.exit(0);
			default :
				System.out.println("Please enter the valid Bank Operation.");
			}
		}
	}
}

class Account{
	private String accountNumber;
	private String accountHolderName;
	private int balance;
	
	public Account(String accountNumber, String accountHolderName) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		balance = 0;
	}
	
	public void deposit(int amount) {
		balance += amount;
		System.out.println("Successfully credited the Amount...");
	}
	
	public void withdraw(int amount) throws InsufficientBalanceException {
		if(amount <= balance) {
			balance -= amount;
			System.out.println("Successfully debited the Amount...");
		}
		else {
			throw new InsufficientBalanceException("Insufficient balance to withdraw...");
		}
	}
	
	public int getBalance() {
		return balance;
	}
	
	public String getAccountHolderName() {
		return accountHolderName;
	}
	
}

class InsufficientBalanceException extends Exception{
	
	public InsufficientBalanceException() {
		
	}
	
	public InsufficientBalanceException(String errorMessage) {
		super(errorMessage);
	}
}
