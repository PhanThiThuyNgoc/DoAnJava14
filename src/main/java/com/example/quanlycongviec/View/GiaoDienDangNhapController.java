package com.example.quanlycongviec.View;


import com.example.quanlycongviec.Controller.Controller;
import com.example.quanlycongviec.Model.ObjectGson.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GiaoDienDangNhapController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private PasswordField matkhau;
    @FXML
    private AnchorPane idanchor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String Email = email.getText();
                String passWord = matkhau.getText();

                if(Email != "" && passWord != "" && Email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    System.out.println("Click to Login");
                    Status check = Controller.kiemtradangnhap(Email, passWord );
                    System.out.println(check);

                    if(check.isCheck()) {

                        if(check.getMess().equals("1")){
                            try {
                                new DBUtils().changeScene(actionEvent, "GiaoDienNhanVien.fxml", "Trang nhân viên", 500, 800);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else {
                            try {
                                new DBUtils().changeScene(actionEvent, "Gocc.fxml", "Trang quản lý", 500, 800);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    } else {
                        DBUtils.printAlertMsg("Thông báo", "Không đúng thông tin đăng nhập!");
                    }

                    }else {
                    DBUtils.printAlertMsg("Thông báo", "Không đúng định dạng email!");
                }
                }
        });
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // sau khi đăng ký thành công thì load giao diện đăng nhập
                    new DBUtils().changeScene(actionEvent, "Giaodiendangki.fxml", "Đăng ký",500,800 );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
