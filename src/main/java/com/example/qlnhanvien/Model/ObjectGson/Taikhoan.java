package com.example.qlnhanvien.Model.ObjectGson;

public class Taikhoan {
    private String ten;
    private  String Email;
    private  String password ;
    public Taikhoan(){

    }
    public  Taikhoan( String ten, String Email, String password){
        this.ten = ten;
        this.Email = Email;
        this.password = password;

    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Taikhoan{" +
                "ten='" + ten + '\'' +
                ", Email='" + Email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
