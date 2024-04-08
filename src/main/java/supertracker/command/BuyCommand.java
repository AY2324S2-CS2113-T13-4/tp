package supertracker.command;

import supertracker.item.Transaction;
import supertracker.item.TransactionList;
import supertracker.ui.Ui;

import java.time.LocalDate;

public class BuyCommand extends AddCommand {
    private static final String BUY_FLAG = "b";
    private double price;
    private LocalDate currentDate;

    public BuyCommand(String name, int quantity, double price, LocalDate currentDate) {
        super(name, quantity);
        this.price = price;
        this.currentDate = currentDate;
    }

    @Override
    public void execute() {
        super.executeWithoutUi();
        Transaction transaction = new Transaction(newItem.getName(), quantity, price, currentDate, BUY_FLAG);
        TransactionList.add(transaction);
        Ui.buyCommandSuccess(newItem, transaction);
    }
}