package org.example.Model;

import java.time.LocalDate;

public class CongViec {
    private String IDCongviec;
    private  String IDNhanvien;
    private String hanNop;
    private String NgayBatDau;
    private  String Baocaocongviec;
    private String LinkNopsanpham;
    private  String Trangthaithuchien;
    private  String Tencongviec;
    private String nhanVien;



    public CongViec(String IDCongviec, String IDNhanvien, String hanNop, String ngayBatDau, String baocaocongviec, String linkNopsanpham, String trangthaithuchien, String tencongviec) {
        this.IDCongviec = IDCongviec;
        this.IDNhanvien = IDNhanvien;
        this.hanNop = hanNop;
        NgayBatDau = ngayBatDau;
        Baocaocongviec = baocaocongviec;
        LinkNopsanpham = linkNopsanpham;
        Trangthaithuchien = trangthaithuchien;
        Tencongviec = tencongviec;
    }
    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public CongViec() {
    }

    public String getIDCongviec() {
        return IDCongviec;
    }

    public void setIDCongviec(String IDCongviec) {
        this.IDCongviec = IDCongviec;
    }

    public String getIDNhanvien() {
        return IDNhanvien;
    }

    public void setIDNhanvien(String IDNhanvien) {
        this.IDNhanvien = IDNhanvien;
    }

    public String getHanNop() {
        return hanNop;
    }

    public void setHanNop(String hanNop) {
        this.hanNop = hanNop;
    }

    public String getBaocaocongviec() {
        return Baocaocongviec;
    }

    public void setBaocaocongviec(String baocaocongviec) {
        Baocaocongviec = baocaocongviec;
    }

    public String getLinkNopsanpham() {
        return LinkNopsanpham;
    }

    public void setLinkNopsanpham(String linkNopsanpham) {
        LinkNopsanpham = linkNopsanpham;
    }

    public String getTrangthaithuchien() {
        return Trangthaithuchien;
    }

    public void setTrangthaithuchien(String trangthaithuchien) {
        Trangthaithuchien = trangthaithuchien;
    }

    public String getTencongviec() {
        return Tencongviec;
    }

    public void setTencongviec(String tencongviec) {
        Tencongviec = tencongviec;
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "IDCongviec='" + IDCongviec + '\'' +
                ", IDNhanvien='" + IDNhanvien + '\'' +
                ", hanNop=" + hanNop +
                ", NgayBatDau=" + NgayBatDau +
                ", Baocaocongviec='" + Baocaocongviec + '\'' +
                ", LinkNopsanpham='" + LinkNopsanpham + '\'' +
                ", Trangthaithuchien='" + Trangthaithuchien + '\'' +
                ", Tencongviec='" + Tencongviec + '\'' +
                '}';
    }
}
