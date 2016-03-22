

****************General:**************************

AtmSimulation is a banking simulation java console application. 
It offers three banking options, view account, deposit and withdraw. It also has options for creating a new account and changing the password of the existing one.

Testing accounts:
1.Name: Ion      Password: abcd     Total cash: 100
2.Name: Dan      Password: efgh     Total cash: 25
3.Name: Radu     Password: ijkl     Total cash: 40



****************Methods:**************************

1. public static void main(String[] args) - main method of the class. 

2. public void start() - starts the program. Options: - login into an existing account 
                                                   - create a new account 
                                                   - exit the program

3. private void login() - authentication into an existing account. On success redirects to main menu. On failure exits the program. Provide a maximum of 3 attempts.

4. private void signup() - create a new account with a 0 cash balance. Checks for unique name and a password longer than 4 characters

5. private void changePassword() - change the password for an existing account. Requires the user to be already logged in. 
                                Enforces the 4 characters rule and also asks for new password re-entry

6. private void logout() - logs out the current user. Clears all the temporary user-related data and returns to the start screen. Does not close the program

7. private void exitProgram() - logs out the current user. Clears all the temporary user-related data and returns to the start screen. Terminates the program

8. private void mainMenu() - main hub for user interaction. Options: - view a summary of the current user's account
                                                                  - witdraw from the account
                                                                  - deposit into the account
                                                                  - change account password
                                                                  - log out
                                                                  - exit program

 9. private void accountSummary() - displays a summary of the current user's account

10. private void cashWithdrawal() - Subtracts a given amount from the current user's cash total. Does not allow negative input or negative result

11. private void cashDeposit() - Adds a given amount to the current user's cash total. Does not allow negative input

12. private boolean repeatOperation() - Provide the user with the option to repeat the operation or quit to previous state

13. private int checkNumber(String numberStr) - Checks if a string can be parsed as a positive integer

14. public AtmSimulation() - Constructor. Creates 3 hard-coded accounts for testing
