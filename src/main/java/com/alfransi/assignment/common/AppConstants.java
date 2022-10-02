package com.alfransi.assignment.common;

public class AppConstants {

    public static final String INTERNAL_SERVER_ERROR = "The Request couldn't be processed. Please try again later";
    public static final String TRANSACTION_SUCCESS = "Transaction Successfully Completed";

    public enum TransactionErrors {
        INSUFFICIENT_BALANCE("Insufficient Balance. Request cannot be processed"),
        ACCOUNT_NOT_FOUND("Account(s) do no exist");
        private final String msg;

        public String value() {
            return msg;
        }
        TransactionErrors(String msg) {
            this.msg = msg;
        }
    }


}
