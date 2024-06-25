module com.example.quanlycongviec {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.quanlycongviec to javafx.fxml;
    exports com.example.quanlycongviec;
    opens com.example.quanlycongviec.Model.ObjectGson to com.google.gson;
    exports com.example.quanlycongviec.View;
    opens com.example.quanlycongviec.View to javafx.fxml;
}