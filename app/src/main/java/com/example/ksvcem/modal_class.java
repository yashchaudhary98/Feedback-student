package com.example.ksvcem;

public class modal_class {
    String fac_name, fac_dept, year_of_exp, email,purl;



    modal_class(){


    }

    public modal_class(String purl) {
        this.purl = purl;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public modal_class(String fac_name, String fac_dept, String year_of_exp, String email) {
        this.fac_name = fac_name;
        this.fac_dept = fac_dept;
        this.year_of_exp = year_of_exp;
        this.email = email;
    }

    public String getFac_name() {
        return fac_name;
    }

    public void setFac_name(String fac_name) {
        this.fac_name = fac_name;
    }

    public String getFac_dept() {
        return fac_dept;
    }

    public void setFac_dept(String fac_dept) {
        this.fac_dept = fac_dept;
    }

    public String getYear_of_exp() {
        return year_of_exp;
    }

    public void setYear_of_exp(String year_of_exp) {
        this.year_of_exp = year_of_exp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
