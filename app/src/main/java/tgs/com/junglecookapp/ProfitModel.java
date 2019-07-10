package tgs.com.junglecookapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfitModel {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    @SerializedName("grand_total")
    @Expose
    private String grand_total;
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("sale")
        @Expose
        private String sale;

        @SerializedName("expense")
        @Expose
        private String expense;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getExpense() {
            return expense;
        }

        public void setExpense(String expense) {
            this.expense = expense;
        }



        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProfit_loss() {
            return profit_loss;
        }

        public void setProfit_loss(String profit_loss) {
            this.profit_loss = profit_loss;
        }

        @SerializedName("profit_loss")
        @Expose
        private String profit_loss;

        @SerializedName("status")
        @Expose
        private String status;



    }


}
