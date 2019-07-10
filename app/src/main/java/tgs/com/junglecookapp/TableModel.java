package tgs.com.junglecookapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TableModel {

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

        @SerializedName("tbl_id")
        @Expose
        private String tbl_id;
        @SerializedName("tbl_name")
        @Expose
        private String tbl_name;
        @SerializedName("tbl_time")
        @Expose
        private String tbl_time;
        @SerializedName("tbl_type")
        @Expose
        private String tbl_type;

        public String getTbl_id() {
            return tbl_id;
        }

        public void setTbl_id(String tbl_id) {
            this.tbl_id = tbl_id;
        }

        public String getTbl_name() {
            return tbl_name;
        }

        public void setTbl_name(String tbl_name) {
            this.tbl_name = tbl_name;
        }

        public String getTbl_time() {
            return tbl_time;
        }

        public void setTbl_time(String tbl_time) {
            this.tbl_time = tbl_time;
        }

        public String getTbl_type() {
            return tbl_type;
        }

        public void setTbl_type(String tbl_type) {
            this.tbl_type = tbl_type;
        }

        public String getTbl_status() {
            return tbl_status;
        }

        public void setTbl_status(String tbl_status) {
            this.tbl_status = tbl_status;
        }

        public String getPro_total() {
            return pro_total;
        }

        public void setPro_total(String pro_total) {
            this.pro_total = pro_total;
        }

        @SerializedName("tbl_status")
        @Expose
        private String tbl_status;

        @SerializedName("pro_total")
        @Expose
        private String pro_total;



    }


}
