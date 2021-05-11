package com.sharshag.reconlib;

import java.util.Date;

public class BankTransaction extends BaseTransaction{
    
    private Date transactionDate;
    private double bankamount;
    private String comments;

    public BankTransaction() {
        
    }

    public BankTransaction(Date transDate, double amount, String comments) {
        
        this.transactionDate = transDate;
        this.bankamount = amount;
        this.comments = comments;
    }

    private double getAmount() {
        return this.bankamount;
    }
}
