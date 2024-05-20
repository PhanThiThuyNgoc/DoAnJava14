module com.example.qlnhanvien {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.qlnhanvien to javafx.fxml;
    exports com.example.qlnhanvien;
    exports com.example.qlnhanvien.View;
    opens com.example.qlnhanvien.View to javafx.fxml;
}