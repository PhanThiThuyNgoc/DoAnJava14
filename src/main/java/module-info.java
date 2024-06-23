module com.example.qlnhanvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;

    opens com.example.qlnhanvien.Model.ObjectGson to com.google.gson;
    opens com.example.qlnhanvien.Model to com.google.gson, javafx.base;
    opens com.example.qlnhanvien to javafx.fxml;
    exports com.example.qlnhanvien;
    exports com.example.qlnhanvien.View;
    opens com.example.qlnhanvien.View to javafx.fxml;


}