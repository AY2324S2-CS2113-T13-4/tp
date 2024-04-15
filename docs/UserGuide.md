# User Guide

## SuperTracker

SuperTracker is a desktop app for managing a supermarket's inventory and transactions,
optimized for use via a Command Line Interface (CLI).

- [Quick Start](#quick-start)
- [Features](#features)
  - [Create a new item: `new`](#create-a-new-item-new)
  - [Delete an item: `delete`](#delete-an-item-delete)
  - [Increase quantity: `add`](#increase-quantity-add)
  - [Decrease quantity: `remove`](#decrease-quantity-remove)
  - [Update an item: `update`](#update-an-item-update)
  - [Find an item: `find`](#find-an-item-find)
  - [Rename an item: `rename`](#rename-an-item-rename)
  - [List all items: `list`](#list-all-items-list)
  - [Print report: `report`](#print-report-report)
  - [Buy items: `buy`](#buy-items-buy)
  - [Sell items: `sell`](#sell-items-sell)
  - [Clear transactions: `clear`](#clear-transactions-clear)
  - [Print expenditure: `exp`](#print-expenditure-exp)
  - [Print revenue: `rev`](#print-revenue-rev)
  - [Print profit: `profit`](#print-profit-profit)
  - [Print a help list: `help`](#print-a-help-list-help)
  - [Quit the program: `quit`](#quit-the-program-quit)
  - [Saving inventory data](#saving-inventory-data)
  - [Loading inventory data](#loading-inventory-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure that you have Java 11 or above installed in your Computer.
2. Download the latest `SuperTracker.jar` from [here](https://github.com/AY2324S2-CS2113-T13-4/tp/releases).
3. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar SuperTracker.jar` command to run the application.
4. Type the command in the command box and press Enter to execute it.
5. Refer to the [Features](#features) below for details of each command and its format.

--------------------------------------------------------------------------------------------------------------------

## Features

> Notes about the command format:
> - Words in uppercase are parameters to be specified by the user
>   - e.g. in `add n/NAME q/QUANTITY`, `NAME` and `QUANTITY` are parameters which can be used as `add n/cheese q/100`
> - Items in square brackets are optional
>   - e.g. `list [q/] [p/]` can be used as `list q/`, `list p/`, `list` etc.
> - Parameters can be in any order
>   - e.g. if the command specifies `n/NAME q/QUANTITY`, `q/QUANTITY n/NAME` is also acceptable
> - Commands that do not take in parameters will ignore any additional parameters input by the user
>   - e.g. if the command specifies `quit now`, it will be interpreted as `quit`
> - If the command has multiple of the same parameters, it uses the first occurrence of that parameter
>   - e.g. if the command specifies `new n/Milk q/100 p/5 n/Cheese`, it will be interpreted as `new n/Milk q/100 p/5`
> - If any of the compulsory parameters are empty an error is thrown
> - The value of a parameter would be interpreted as the trimmed substring after `/` up to the space right before the next valid parameter
>   - e.g. if the command specifies `add n/Milk q/10q/10`, the command would be invalid as `QUANTITY` would be interpreted as `10q/10`, 
>   since there is no space before the second occurrence of `q/`
>   - e.g. if the command specifies `add n/Milk q/10 p/10`, the command would be invalid as `QUANTITY` would be interpreted as `10 p/10`, 
>   since `p/` is not a valid parameter for the add command
>   - e.g. if the command specifies `add n/Milk q/  10   q/20`, the command would be valid as `QUANTITY` would be interpreted as `10`

<br>

### Create a new item: `new`
Create a new item in the inventory

Format: `new n/NAME q/QUANTITY p/PRICE [e/EXPIRY_DATE]`

- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` already exists in the inventory, use the [update](#update-an-item-update) command instead
- If `NAME` contains the character sequence `",,,"`, it will be replaced by a "_" character
  - e.g. `"Ba,,,ll"` will be passed as `"Ba_ll"` instead
  - Please do avoid using `",,,"` as it is the program's file delimiter
- `QUANTITY` must be a non-negative integer and less than or equal to 2147483647
  - e.g. 1, 10, 100
- `PRICE` must be a non-negative integer or decimal number and less than or equal to 2147483647
  - e.g. 1, 0.20, 12.3, 12.345
- If the `PRICE` given has more than 2 decimal places, it will be rounded off to the nearest 2 decimal places
  - e.g. 12.345 ≈ 12.35
- `EXPIRY_DATE` must be in the format of `dd-mm-yyyy` and from 01-01-0001 to 31-12-9999. 
Non-existent dates such as 30-02-2024 are considered invalid
  - e.g. 05-10-2054, 16-07-2245

Example: `new n/Milk q/100 p/5`
```
Milk has been added to the inventory!
Quantity: 100
Price: $5.00
```
Example: `new n/Cheese q/50 p/1.23 e/15-06-2113`
```
Cheese has been added to the inventory!
Quantity: 50
Price: $1.23
Expiry Date: 15/06/2113
```

<br>

### Delete an item: `delete`
Delete an item from the inventory

Format: `delete n/NAME`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` does not exist in the inventory, an error would be thrown

Example: `delete n/Milk`
```
Milk has been deleted!
```

<br>

### Increase quantity: `add`
Increase the quantity of an item

Format: `add n/NAME q/QUANTITY`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` does not exist in the inventory, an error would be thrown
- `QUANTITY` must be a positive integer and less than or equal to 2147483647
  - e.g. 1, 10, 100
- If the new quantity of the item exceeds 2147483647, an error would be thrown

Example: `add n/Milk q/10`
```
10 Milk added to inventory!
Quantity: 110
```

<br>

### Decrease quantity: `remove`
Decrease the quantity of an item

Format: `remove n/NAME q/QUANTITY`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` does not exist in the inventory, an error would be thrown
- `QUANTITY` must be a positive integer and less than or equal to 2147483647
  - e.g. 1, 10, 100
- If `QUANTITY` exceeds the current quantity of the item, the new quantity would be set to 0

Example: `remove n/Milk q/10`
```
10 Milk removed from inventory!
Quantity: 90
```

<br>

### Find an item: `find`
Find the item/items that contain the same name

Format: `find n/NAME`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If no items containing the same name are found, an error would be thrown

Example: `find n/Cheese` 
```
Here are your found items:
1. Name: Cheese cake
   Quantity: 100
   Price: $1.00
2. Name: Cheese
   Quantity: 100
   Price: $1.00
```
<br>

### Rename an item: `rename`
Rename an existing item with a new name.

Format: `rename n/NAME r/NEW_NAME`
- `NAME` is case-insensitive
- `NEW_NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NEW_NAME` contains the character sequence `",,,"`, it will be replaced by a "_" character
  - e.g. `"Ba,,,ll"` will be passed as `"Ba_ll"` instead
  - Please do avoid using `",,,"` as it is the program's file delimiter
- If no items containing NAME is found, an error would be thrown
- If there already exists an item with NEW_NAME, an error would be thrown

Example: `rename n/Cheese r/Mozzerella`
```
Cheese has been successfully renamed to Mozzerella.
Name: Mozzerella
Quantity: 30
Price: $4.00
```
<br>

### Update an item: `update`
Update the quantity, price and/or expiry date of an item

Format: `update n/NAME [q/NEW_QUANTITY] [p/NEW_PRICE] [e/NEW_EXPIRY_DATE]`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` does not exist in the inventory, an error would be thrown
- `NEW_QUANTITY` must be a non-negative integer and less than or equal to 2147483647
  - e.g. 1, 10, 100
- `NEW_PRICE` must be a non-negative integer or decimal number and less than or equal to 2147483647
  - e.g. 1, 0.20, 12.3, 12.345
- If the `NEW_PRICE` given has more than 2 decimal places, it will be rounded off to the nearest 2 decimal places
  - e.g. 12.345 ≈ 12.35
- `NEW_EXPIRY_DATE` must be in the format of `dd-mm-yyyy` and from 01-01-0001 to 31-12-9999 
or `nil` if expiry date is to be removed. Non-existent dates such as 30-02-2024 are considered invalid
  - e.g. 05-10-2054, 16-07-2245
- At least one of the optional parameters must be present

Example: `update n/Milk q/200 p/10 e/15-06-2113`
```
Milk has been successfully updated!
Quantity: 200
Price: $10.00
Expiry Date: 15/06/2113
```
Example: `update n/Milk q/50 p/9.99 e/nil`
```
Milk has been successfully updated!
Quantity: 50
Price: $9.99
```

<br>

### List all items: `list`
List all unique items in the inventory.
Output will be printed to the terminal and each row will contain the name of each item.

Format: `list [q/] [p/] [e/] [sq/] [sp/] [se/] [r/]`
- `q/` will list the quantity of each item in each row
- `p/` will list the price of each item in each row
- `e/` will list the expiry date of each item in each row if it contains a valid expiry date
- In each row, quantity, price and expiry date will be printed in the same order as the flags
  - e.g. if the command specifies `list q/ p/ e/`, the quantity will be printed first followed by price and expiry date
- `sq/` will list the items in order of ascending quantity
- `sp/` will list the items in order of ascending price
- `se/` will list the items in order of ascending date, items with no expiry date will be listed at the end
- If the command has no sorting parameters, the list will be sorted in ascending alphabetical order (A-Z) by default
- If the command has multiple sorting parameters, the list will be sorted in the same order as the sorting flags
  - e.g. if the command specifies `list sq/ sp/ se/`, list will be sorted in order of ascending quantity. 
  Ties will be sorted in order of ascending price, then ascending date, followed by ascending alphabetical order (A-Z)
- If `q/` is not specified but `sq/` is specified, the command will be interpreted as `q/ sq/`. 
Same logic applies to price and expiry date
  - e.g. if the command specifies `list sq/ sp/ q/`, the command will be interpreted as `list sq/ p/ sp/ q/`,
  although the list will be sorted in order of ascending quantity, price will be printed before quantity
- `r/` will reverse the order of the list

Example: `list q/ p/ e/ sp/ r/`
```
There are 3 unique items in your inventory:
1. Name: Milk    Quantity: 100    Price: $5.00
2. Name: Juice    Quantity: 300    Price: $4.00    Expiry Date: 12/03/2025
3. Name: Cheese    Quantity: 200    Price: $3.00
```

<br>

### Print report: `report`
List all items that match the requirements of the report

There are 2 types of reports:
1. **low stock** - list all items that are below a specified threshold provided by the user
2. **expiry** - list all items that are within a week of expiring or have already expired

The report will be printed to the terminal and will contain the name of each item
- If the report type is **low stock**: 
  - The quantity of each item would be listed
  - The report would be sorted in order of ascending quantity
- If the report type is **expiry**:
  - The expiry date of each item would be listed
  - The report would be sorted in order of ascending expiry date

Format (low stock):`report r/REPORT_TYPE t/THRESHOLD_VALUE`

Format (expiry):`report r/REPORT_TYPE [t/THRESHOLD_VALUE]`

- `r/` parameter that specifies the type of report. e.g. **low stock** or **expiry**
- `t/` parameter that specifies the threshold value to be compared to for **low stock report**. 
All items below the threshold value would be listed out.

> Note: If report type is **low stock** threshold value must be **included**. However, if report type is **expiry** 
threshold value must be **excluded** or left **empty**.


Example: `report r/low stock t/35`

```
There are 3 items low on stocks!
1. Name: Orange
   Quantity: 10
2. Name: Banana
   Quantity: 20
3. Name: Apple
   Quantity: 30
```
Example: `report r/expiry` or `report r/expiry t/`

```
There is 1 item close to expiry!
1. Name: Apple
   Expiry Date: 20/04/2024
There is 1 item that is expired!
1. Name: Orange
   Expiry Date: 29/03/2024
```

<br>

### Buy items: `buy`
Buy items from suppliers and add them to the inventory. 
This buy transaction will create a new entry in the transaction list,
with the current date saved as the transaction date.

Format: `buy n/NAME q/QUANTITY p/PRICE`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` does not exist in the inventory, an error would be thrown
- `QUANTITY` must be a positive integer and less than or equal to 2147483647
  - e.g. 1, 10, 100
- If the new quantity of the item exceeds 2147483647, an error would be thrown
- `PRICE` must be a non-negative integer or decimal number and less than or equal to 2147483647
  - e.g. 1, 0.20, 12.3, 12.345
- If the `PRICE` given has more than 2 decimal places, it will be rounded off to the nearest 2 decimal places
  - e.g. 12.345 ≈ 12.35

Example: `buy n/Milk q/10 p/3`
```
10 Milk bought at $3.00 each for $30.00 in total
Quantity: 110
```

<br>

### Sell items: `sell`
Sell items to customers and remove them from the inventory.
This sell transaction will create a new entry in the transaction list,
with the current date saved as the transaction date.

Format: `sell n/NAME q/QUANTITY`
- `NAME` is case-insensitive
  - e.g. `Cheese` will be interpreted as `cheese`
- If `NAME` does not exist in the inventory, an error would be thrown
- `QUANTITY` must be a positive integer and less than or equal to 2147483647
  - e.g. 1, 10, 100
- If `QUANTITY` exceeds the current quantity of the item, the new quantity would be set to 0
- If the quantity sold is 0, no transaction would be added to the transaction list

Example: `sell n/Milk q/10`
```
10 Milk sold at $5.00 each for $50.00 in total
Quantity: 90
```

<br>

### Clear transactions: `clear`
Clear all transactions *before* a specified date

Format `clear [b/BEFORE_DATE]`
- If the optional parameter `[b/BEFORE_DATE]` is not specified or `BEFORE_DATE` is empty,
the current date will be taken as the before date
- `BEFORE_DATE` must be in the format of `dd-mm-yyyy` and from 01-01-0001 to 31-12-9999.
  Non-existent dates such as 30-02-2024 are considered invalid
  - e.g. 05-10-2054, 16-07-2245
> Note: This command does **NOT** clear transactions that occurred on `BEFORE_DATE`

- If the initial command is successful, an additional confirmation message will be shown to the user
- Enter `y` or `Y` to proceed with the clear operation
- Enter anything else to cancel the clear operation
> Note: `y` or `Y` must be exact and should not contain any whitespace or other characters. e.g. entering `y y` or `yY` will cancel the clear operation

Example: `clear b/19-04-2024`
```
Are you sure you want to clear all transactions before 19/04/2024?
Enter 'y' or 'Y' if you wish to proceed
Enter anything else if you wish to cancel the clear operation
```

Example: `y`
```
2 transactions before 19/04/2024 successfully cleared!
```

<br>

### Print expenditure: `exp`
There are 4 types of expenditure commands:
1. **today** - list all buy transactions that occurred today
2. **total** - list all buy transactions in total
3. **day** - list all buy transactions that occurred during the specified day
4. **range** - list all buy transactions that occurred in the specified range of dates 
not inclusive of the start and end dates

The cumulative expenditure will be first printed to the terminal.
Afterward, each buy transaction will be printed to the terminal containing its name, 
quantity, price and transaction date.
All buy transactions will be listed from most recent to least recent.

Format: `exp type/EXPENDITURE_TYPE [from/START_DATE] [to/END_DATE]`

> Note: If the type is **today** or **total**, START_DATE and END_DATE are not supposed to be filled. 
> If the type is **day**, START_DATE is compulsory and END_DATE is not supposed to be filled.
> If the type is **range**, both START_DATE and END_DATE are compulsory.

Example: `exp type/today`

```
Today's expenditure is $94.50
1. Name: Apple
   Quantity: 30
   Price: $2.15
   Transaction Date: 19/04/2024
2. Name: Banana
   Quantity: 10
   Price: $3.00
   Transaction Date: 19/04/2024
```

<br>

### Print revenue: `rev`
There are 4 types of revenue commands:
1. **today** - list all sell transactions that occurred today
2. **total** - list all sell transactions in total
3. **day** - list all sell transactions that occurred during the specified day
4. **range** - list all sell transactions that occurred in the specified range of dates 
not inclusive of the start and end dates

The cumulative revenue will be first printed to the terminal.
Afterward, each sell transaction will be printed to the terminal containing its name,
quantity, price and transaction date.
All sell transactions will be listed from most recent to least recent.

Format: `rev type/REVENUE_TYPE [from/START_DATE] [to/END_DATE]`

> Note: If the type is **today** or **total**, START_DATE and END_DATE are not supposed to be filled.
> If the type is **day**, START_DATE is compulsory and END_DATE is not supposed to be filled.
> If the type is **range**, both START_DATE and END_DATE are compulsory.

Example: `rev type/today`

```
Today's revenue is $39.80
1. Name: Apple
   Quantity: 20
   Price: $0.99
   Transaction Date: 19/04/2024
2. Name: Banana
   Quantity: 10
   Price: $2.00
   Transaction Date: 19/04/2024
```

<br>

### Print profit: `profit`
There are 4 types of profit commands:
1. **today** - calculates the profit for the day
2. **total** - calculates total profit
3. **day** - calculates the profit on a specified day
4. **range** - calculates the profit over the specified range of dates 
not inclusive of the start and end dates

The cumulative revenue will be first printed to the terminal.
Afterward, each sell transaction will be printed to the terminal containing its name,
quantity, price and transaction date.
All sell transactions will be listed from most recent to least recent.

Format: `profit type/PROFIT_TYPE [from/START_DATE] [to/END_DATE]`

> Note: If the type is **today** or **total**, START_DATE and END_DATE are not supposed to be filled.
> If the type is **day**, START_DATE is compulsory and END_DATE is not supposed to be filled.
> If the type is **range**, both START_DATE and END_DATE are compulsory.

Example: `profit type/today`

```
Today's profit is $9.20
Nice! You have a profit.
```

<br>

### Print a help list: `help`
Print a help list which displays all functions of SuperTracker which the user can then choose to see its parameters

There are 13 different inputs the user can key in after the help list is printed.
1. `new`: prints the parameters to create a new item
2. `delete`: prints the parameters to delete an item
3. `change`: prints the parameters to change quantity of an item
4. `update`: prints the parameters to update an item
5. `find`: prints the parameters to find an item
6. `rename`: prints the parameters to rename an item
7. `list`: prints the parameters to list out items
8. `report`: prints the parameters to print a report
9. `exp`: prints the parameters to print expenditure
10. `rev`: prints the parameters to print revenue
11. `profit`: prints the parameters to print profit
12. `transaction`: prints the parameters to buy or sell an item
13. `clear`: prints the parameters to clear transactions

As shown above, a user can choose to input any of these 13 commands or else the user will return to the main console.
>Note: If the input consists of many words, SuperTracker will only take in the first word.
>
>e.g. "delete new" will be interpreted as "delete" and delete parameters will be printed

Format: `help`

Example: `help`

```
Hello! These are the list of commands that I can help you with:
1. Create a new item: type 'new' to show parameters
2. Delete an item: type 'delete' to show parameters
3. Change quantity of an item: type 'change' to show parameters
4. Update an item: type 'update' to show parameters
5. Find an item: type 'find' to show parameters
6. Rename an item: type 'rename' to show parameters
7. List all items: type 'list' to show parameters
8. Print a report: type 'report' to show parameters
9. Print expenditure: type 'exp' to show parameters
10. Print revenue: type 'rev' to show parameters
11. Print profit: type 'profit' to show parameters
12. Buy or sell items: type 'transaction' to show parameters
13. Clear transactions: type 'clear' to show parameters
** Any other invalid input will bring you back to the main console
```
Next input: `new`
```
A new item command should look like this: new n/NAME q/QUANTITY p/PRICE [e/EXPIRY_DATE]
** Refer to UserGuide for further explanation
** DO NOTE that you have been returned to the main console
```

<br>

### Quit the program: `quit`
Quits the program

Format: `quit`

--------------------------------------------------------------------------------------------------------------------

### Saving inventory data
Data in the program is saved to the hard disk in the file path `./data/` in the same directory that
the `SuperTracker.jar` file is in. Item data will be saved automatically after any command that changes the item data in the inventory.
Similarly, transaction data will be saved automatically after commands that add or erases transaction data.

<br>

### Loading inventory data
Data that has been saved to the hard disk will be loaded and read by the program each time it is launched. 
If there is no data file, the program will the skip loading process.

<br>

### Editing the data file
Data of the `SuperTracker` program is stored in a text files in the path `./data/` relative to 
the directory the `SuperTracker.jar` file is in. Inventory data is stored in `items.txt`, and transaction data is stored in `transactions.txt`.
You are **strongly discouraged** from editing and updating the inventory or transaction data directly through the data file.
> Note 1: **Edit the data file at your own caution.** If the changes made to the data file are in an invalid format, the program
> will ignore those changes on its next load. The corrupted changes **will be erased**, so do keep a backup of the data 
> file before editing.
> 
> Note 2: If the program detects duplicate item names in the save file `items.txt`, among all lines of data that share the same
> item name, the bottom most data line will **overwrite** all previous occurrences of the item data that share the same item name.
> The rest of the overwritten lines **will be erased**.
> 
> Note 3: Edited transaction data with dates that have not happened yet **will be treated as corrupted data**, so do avoid adding
> transactions that have not happened yet.
> 
> Note 4: The program is **NOT** responsible for any data loss if you were to delete the save files while the program is running

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates 
with the file that contains the data of your previous SuperTracker inventory.

**Q**: What if I want multiple items with the same name? 
What if I want different batches of the same item with different expiry dates?

**A**: Simply add a unique identifier after the item name when creating a new item. 
e.g. `n/Milk-1`,`n/Milk-2`. The format of the unique identifier is completely up to the user's discretion.

**Q**: Why can't I add new items to the inventory using the buy command?

**A**: The buy command should mainly be used when you want to replenish the stock of an existing item.
If you wish to add a newly bought item to the inventory, 
first create a new item in the inventory with its quantity set to 0 using the [new](#create-a-new-item-new) command,
then use the buy command to increase its quantity.

**Q**: Why am I allowed to set the quantity and/or price of an item to 0?

**A**: We want to provide our users with more flexibility. 
This can be handy for placeholders or situations where items are free.

**Q**: Why am I allowed to set expiry dates that have already passed?

**A**: We want to provide our users with more flexibility.
This can be useful if the user has previously entered an incorrect expiry date, 
allowing them to fix their mistake.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action          | Format                                                             | Examples                                     |
|-----------------|--------------------------------------------------------------------|----------------------------------------------|
| **New**         | `new n/NAME q/QUANTITY p/PRICE e/EXPIRY_DATE`                      | e.g. `new n/Milk q/100 p/5 e/05-12-2113`     |
| **Delete**      | `delete n/NAME`                                                    | e.g. `delete n/Milk`                         |
| **Add**         | `add n/NAME q/QUANTITY`                                            | e.g. `add n/Milk q/10`                       |
| **Remove**      | `remove n/NAME q/QUANTITY`                                         | e.g. `remove n/Milk q/10`                    |
| **Update**      | `update n/NAME [q/NEW_QUANTITY] [p/NEW_PRICE] [e/NEW_EXPIRY_DATE]` | e.g. `update n/Milk q/200 p/10 e/05-08-2113` |
| **Find**        | `find n/NAME`                                                      | e.g. `find n/apple`                          |
| **Rename**      | `rename n/NAME r/NEW_NAME`                                         | e.g. `rename n/Milk r/Chocolate Milk`        |
| **List**        | `list [q/] [p/] [e/] [sq/] [sp/] [se/] [r/]`                       | e.g. `list q/ p/ sp/ r/`                     |
| **Report**      | `report r/REPORT_TYPE [t/THRESHOLD_VALUE]`                         | e.g. `report r/low stock t/10`               |
| **Buy**         | `buy n/NAME q/QUANTITY p/PRICE`                                    | e.g. `buy n/Milk q/10 p/3`                   |
| **Sell**        | `sell n/NAME q/QUANTITY`                                           | e.g. `sell n/Milk q/10`                      |
| **Clear**       | `clear [b/BEFORE_DATE]`                                            | e.g. `clear b/19-04-2024`                    |
| **Expenditure** | `exp type/EXPENDITURE_TYPE [from/START_DATE] [to/END_DATE]`        | e.g. `exp type/today`                        |
| **Revenue**     | `rev type/REVENUE_TYPE [from/START_DATE] [to/END_DATE]`            | e.g. `rev type/today`                        |
| **Profit**      | `profit type/PROFIT_TYPE [from/START_DATE] [to/END_DATE]`          | e.g. `profit type/today`                     |
| **Help**        | `help`                                                             | e.g. `help`                                  |
| **Quit**        | `quit`                                                             | e.g. `quit`                                  |
