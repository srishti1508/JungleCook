package tgs.com.junglecookapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaleModel {

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

        @SerializedName("sale_id")
        @Expose
        private String sale_id;
        @SerializedName("subtotal")
        @Expose
        private String subtotal;
        @SerializedName("discount")
        @Expose
        private String discount;

        @SerializedName("total")
        @Expose
        private String total;

        public String getSale_id() {
            return sale_id;
        }

        public void setSale_id(String sale_id) {
            this.sale_id = sale_id;
        }

        public String getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(String subtotal) {
            this.subtotal = subtotal;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGrand_total() {
            return grand_total;
        }

        public void setGrand_total(String grand_total) {
            this.grand_total = grand_total;
        }

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("grand_total")
        @Expose
        private String grand_total;



    }


}
