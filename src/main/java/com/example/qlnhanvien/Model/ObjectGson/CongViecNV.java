package com.example.qlnhanvien.Model.ObjectGson;

public class CongViecNV {
    private String email;
    private String data;

    public CongViecNV(String email, String data) {
        this.email = email;
        this.data = data;
    }

    public CongViecNV() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CongViecNV{" +
                "email='" + email + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
