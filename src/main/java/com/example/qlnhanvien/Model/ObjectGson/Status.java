package com.example.qlnhanvien.Model.ObjectGson;

public class Status {
    private  boolean check;
    public Status(){

    }
    public Status(boolean check){
        this.check= check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Status{" +
                "check=" + check +
                '}';
    }
}
