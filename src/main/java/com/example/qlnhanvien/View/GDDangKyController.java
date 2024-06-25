package com.example.qlnhanvien.View;

import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.ObjectGson.Taikhoan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GDDangKyController implements Initializable {
    @FXML
    private TextField tf_fullname;
    @FXML
    private TextField tf_mail;

    @FXML
    private PasswordField tf_password;
    @FXML
    private Button bt_signup;
    @FXML
    private Button bt_login;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ten = tf_fullname.getText();
                String Email = tf_mail.getText();
                String password = tf_password.getText();

                if(!ten.isEmpty() && !Email.isEmpty() && !password.isEmpty()){ // kiểm tra dữ liệu người nhập có để trống không
                    // mã hoá mật khẩu
                    password = String.valueOf(password.hashCode());
                    //boolean check = Controller.dangkitaikhoan(ten, Email, password ); // hàm đăng kí tài khoản
                    boolean check = new StreamSocket<Taikhoan, Boolean>().SendRequest("/DangKyTaiKhoan", new Taikhoan(ten, Email , password), Boolean.class);
                    if (check == true){
                        DBUtils.printAlertMsg("Thông báo  ", "Đăng kí thành công "); // hien thi ra thông báo
                        //ActionEvent actionEvent,String fxmlFile, String title, int height, int width
                        try {
                            DBUtils.changeScene(actionEvent, "com/example/qlnhanvien/View/GiaoDienDangNhap.fxml", "Đăng nhập", 500, 800) ;
                            // chuyển qua giao diện của đng nhập
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        DBUtils.printAlertMsg("Thông báo ", " Đăng kí không thành công ");

                    }


                    

                } else {
                    DBUtils.printAlertMsg("thông báo", "Vui lòng nhập đủ thông tin");
                }

            }
        });
        bt_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                    try {
                        DBUtils.changeScene(actionEvent, "com/example/qlnhanvien/View/GiaoDienDangNhap.fxml", "Đăng nhập",500,800 );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

            }
        });



    }
}
