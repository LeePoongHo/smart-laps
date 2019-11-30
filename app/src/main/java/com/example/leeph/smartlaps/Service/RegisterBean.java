package com.example.leeph.smartlaps.Service;

public class RegisterBean {

    private String mem_id;
    private String mem_passwd;
    private String mem_sex;
    private String mem_name;
    private String mem_email;
    private String mem_major;
    

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public void setMem_passwd(String mem_passwd) {
        this.mem_passwd = mem_passwd;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public void setMem_sex(String mem_sex) {
        this.mem_sex = mem_sex;
    }

    public String getMem_id() {
        return mem_id;
    }

    public String getMem_passwd() {
        return mem_passwd;
    }

    public String getMem_name() {
        return mem_name;
    }

    public String getMem_email() {
        return mem_email;
    }

    public String getMem_sex() {
        return mem_sex;
    }

    public String getMem_major() {
        return mem_major;
    }

    public void setMem_major(String mem_major) {
        this.mem_major = mem_major;
    }
}

