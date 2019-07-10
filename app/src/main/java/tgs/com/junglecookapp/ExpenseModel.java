package tgs.com.junglecookapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpenseModel {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(String total_expense) {
        this.total_expense = total_expense;
    }

    @SerializedName("total_expense")
    @Expose
    private String total_expense;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Response {

        @SerializedName("expense_id")
        @Expose
        private String expense_id;
        @SerializedName("expense_on")
        @Expose
        private String expense_on;
        @SerializedName("exp_category")
        @Expose
        private String exp_category;

        @SerializedName("amount")
        @Expose
        private String amount;

        public String getExpense_id() {
            return expense_id;
        }

        public void setExpense_id(String expense_id) {
            this.expense_id = expense_id;
        }

        public String getExpense_on() {
            return expense_on;
        }

        public void setExpense_on(String expense_on) {
            this.expense_on = expense_on;
        }

        public String getExp_category() {
            return exp_category;
        }

        public void setExp_category(String exp_category) {
            this.exp_category = exp_category;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getExp_date() {
            return exp_date;
        }

        public void setExp_date(String exp_date) {
            this.exp_date = exp_date;
        }

        public String getAttachment_url() {
            return attachment_url;
        }

        public void setAttachment_url(String attachment_url) {
            this.attachment_url = attachment_url;
        }

        public String getTotal_expense() {
            return total_expense;
        }

        public void setTotal_expense(String total_expense) {
            this.total_expense = total_expense;
        }

        @SerializedName("exp_date")
        @Expose
        private String exp_date;

        @SerializedName("attachment_url")
        @Expose
        private String attachment_url;

        @SerializedName("total_expense")
        @Expose
        private String total_expense;

        public String getExpense_note() {
            return expense_note;
        }

        public void setExpense_note(String expense_note) {
            this.expense_note = expense_note;
        }

        @SerializedName("expense_note")
        @Expose
        private String expense_note;



    }


}
