package com.example.qlnhanvien.Model.ObjectGson;

public class ID {
    private String id;

    public ID(String id) {
        this.id = id;
    }

    public ID() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID{" +
                "id='" + id + '\'' +
                '}';
    }
}
