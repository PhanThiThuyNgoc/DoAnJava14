package org.example.objectGson;

public class DangNhap {

    private String Email;
    private String matkhau;

    public DangNhap() {

    }

    public DangNhap(String email, String matkhau) {
        Email = email;
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return Email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    @Override
    public String toString() {
        return "Taikhoan{" +
                "Email='" + Email + '\'' +
                ", matkhau='" + matkhau + '\'' +
                '}';
    }
}
