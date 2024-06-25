package com.example.quanlycongviec.Model.ObjectGson;

public class Status {
    private  boolean check;

    private String mess;
    public Status(){

    }

    public Status(boolean check, String mess) {
        this.check = check;
        this.mess = mess;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "Status{" +
                "check=" + check +
                ", mess='" + mess + '\'' +
                '}';
    }
}

