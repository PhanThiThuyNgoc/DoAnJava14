package org.example.Model;

import java.time.LocalDate;

public class NhanVien {
    private  String  IDNhanvien ;
    private  String Hovaten;
    private  String chucvu;
    private  String diachi;
    private String ngaysinh;
    private  String gioitinh;
    private  String sodienthoai;
    private  String Gmail;
    private  String ngaybatdaulamviec;
    private String linkAvatar;

    public NhanVien(String hovaten,  String diachi, String ngaysinh, String gioitinh, String sodienthoai, String gmail) {
        Hovaten = hovaten;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sodienthoai = sodienthoai;
        Gmail = gmail;
    }

    public NhanVien() {
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getIDNhanvien() {
        return IDNhanvien;
    }

    public void setIDNhanvien(String IDNhanvien) {
        this.IDNhanvien = IDNhanvien;
    }

    public String getHovaten() {
        return Hovaten;
    }

    public void setHovaten(String hovaten) {
        Hovaten = hovaten;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getNgaybatdaulamviec() {
        return ngaybatdaulamviec;
    }

    public void setNgaybatdaulamviec(String ngaybatdaulamviec) {
        this.ngaybatdaulamviec = ngaybatdaulamviec;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "IDNhanvien='" + IDNhanvien + '\'' +
                ", Hovaten='" + Hovaten + '\'' +
                ", chucvu='" + chucvu + '\'' +
                ", diachi='" + diachi + '\'' +
                ", ngaysinh=" + ngaysinh +
                ", gioitinh='" + gioitinh + '\'' +
                ", sodienthoai='" + sodienthoai + '\'' +
                ", Gmail='" + Gmail + '\'' +
                ", ngaybatdaulamviec=" + ngaybatdaulamviec +
                ", linkAvatar='" + linkAvatar + '\'' +
                '}';
    }
}
