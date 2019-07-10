package tgs.com.junglecookapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductModel {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("message")
    @Expose
    private String message;
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

        @SerializedName("pro_id")
        @Expose
        private String pro_id;
        @SerializedName("pro_name")
        @Expose
        private String pro_name;
        @SerializedName("pro_category")
        @Expose
        private String pro_category;

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_category() {
            return pro_category;
        }

        public void setPro_category(String pro_category) {
            this.pro_category = pro_category;
        }

        public String getPro_quantity() {
            return pro_quantity;
        }

        public void setPro_quantity(String pro_quantity) {
            this.pro_quantity = pro_quantity;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getPro_total() {
            return pro_total;
        }

        public void setPro_total(String pro_total) {
            this.pro_total = pro_total;
        }

        @SerializedName("pro_quantity")
        @Expose
        private String pro_quantity;

        @SerializedName("pro_price")
        @Expose
        private String pro_price;

        @SerializedName("pro_total")
        @Expose
        private String pro_total;



    }


}
