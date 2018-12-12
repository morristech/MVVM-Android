package android.mvvm.mg.com.mvvm_android.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionGroup {

    @JsonProperty("transaction_details")
    private List<Transaction> transactionList;

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
