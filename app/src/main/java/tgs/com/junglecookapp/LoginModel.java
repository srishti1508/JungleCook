package tgs.com.junglecookapp;

import java.util.List;

public class LoginModel {

    String status;
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Response> response;
    public List<Response> getResponse() {
        return response;
    }
    public void setResponse(List<Response> response) {
        this.response = response;
    }
    public class Response {
        String user_id;
        String user_name;
        String base_id;
        String user_type;
        String course_name;
        String branch_name;
        String student_semester;
        String student_section;
        String session_year;
        String user_logged_type;

        public String getUser_logged_type() {
            return user_logged_type;
        }

        public void setUser_logged_type(String user_logged_type) {
            this.user_logged_type = user_logged_type;
        }

        public String getCourse_name() {
            return course_name;
        }
        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }
        public String getBranch_name() {
            return branch_name;
        }
        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }
        public String getStudent_semester() {
            return student_semester;
        }
        public void setStudent_semester(String student_semester) {
            this.student_semester = student_semester;
        }
        public String getStudent_section() {
            return student_section;
        }
        public void setStudent_section(String student_section) {
            this.student_section = student_section;
        }
        public String getSession_year() {
            return session_year;
        }
        public void setSession_year(String session_year) {
            this.session_year = session_year;
        }
        public String getUser_id() {
            return user_id;
        }
        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
        public String getUser_name() {
            return user_name;
        }
        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
        public String getBase_id() {
            return base_id;
        }
        public void setBase_id(String base_id) {
            this.base_id = base_id;
        }
        public String getUser_type() {
            return user_type;
        }
        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }
    }
}
