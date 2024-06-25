package com.example.quanlycongviec.Model.ObjectGson;

public class Request{
    private String Request;
    public Request(){

    }

    public Request(String request) {
        Request = request;
    }

    public String getRequest() {
        return Request;
    }


    public void setRequest(String request) {
        Request = request;
    }



    @Override
    public String toString() {
        return "Request{" +
                "Request='" + Request;
    }
}
