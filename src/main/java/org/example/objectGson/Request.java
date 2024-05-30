package org.example.objectGson;

public class Request <T> {
    private  String Request;
    private  T Value;
    public  Request(){

    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }

    public T getValue() {
        return Value;
    }

    public void setValue(T value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "Request{" +
                "Request='" + Request + '\'' +
                ", Value=" + Value +
                '}';
    }
}
