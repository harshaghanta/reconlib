package com.sharshag.reconlib;

import java.util.Date;

public class CashTransaction extends BaseTransaction {
    
    private double cashamount;
    private String comments;
    private Date transactionDate;

    public CashTransaction(Date transDate, double amount, String comments) {
        
        this.transactionDate = transDate;
        this.cashamount = amount;
        this.comments = comments;
    }

    private double getAmount() {
        return this.cashamount;
    }

}
