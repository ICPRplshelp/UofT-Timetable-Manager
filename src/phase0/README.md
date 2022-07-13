# Group 0235 Login System

## Instructions

1. Build the program with src/interfaceAdapters/Main as the Main class.
2. Run the built program.
3. Follow the instructions displayed on the console.

## Features

### Autosave

Any time a new user is created,
or a command is ran by a logged in user, all account information will be saved in `accountInformation.csv`.
The only way to log out is by stopping and re-running this program.

### Account Login and Registration 
1. Account registration
   1. Enter ``register`` from the main page to register a new account.
   2. Enter the username and password for the new account as prompted.
2. Account login
   1. Enter ``login`` from the main page to log into an existing account.
   2. Enter the username and password for the account as prompted.

### Standard User Options
You must first be logged into an account to perform the following:
1. Login history
   1. Enter ``history`` to see your login history. 
2. Admin view
   1. Enter ``adminview`` to see all administrator user options if your account is an administrator
3. Admin Promotion
   1. Enter ``secretadmin`` to make yourself an admin.
      1. NOTE: This will be hidden from users in the future. This feature is only included in Phase 0 to test administrator accounts.

### Admin User Options
Administrators can perform all standard user operations, in addition to administrator user operations.
You must be first logged into an administrator account and in Admin view to perform the following:
1. Ban a user
   1. Enter ``ban`` to temporarily ban a user.
   2. Enter the username and the date that the ban should last until (dd/MM/yyyy) as prompted.
2. Delete a user
   1. Enter ``delete`` to delete a user.
   2. Enter the username of the user to delete as prompted.
3. Create a new administrator account
   1. Enter ``new`` to create a new AdminUser.
   2. Enter the username and password of the new account as prompted.
4. Promote a user to admin.
   1. Enter ``promote`` to promote an existing user to admin.
   2. Enter the username of the user you want to promote as prompted.

### Exiting the program

Type `exit` anytime to stop the program. This will not work if you are being specifically prompted to type something.
