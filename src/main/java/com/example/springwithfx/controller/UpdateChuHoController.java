package com.example.springwithfx.controller;

import com.example.springwithfx.config.StageManager;
import com.example.springwithfx.model.HoKhau;
import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.service.HoKhauService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UpdateChuHoController  {

    @FXML
    public TextField fullNameField;
    public DatePicker dobPicker;
    public TextField placeOfBirthField;
    public TextField hometownField;
    public TextField ethnicityField;
    public TextField jobField;
    public TextField idField;
    public RadioButton maleButton;

    private final HoKhauService hoKhauService;

    private HoKhau hoKhau;

    private Stage stage;
    private Scene scene;

    private StageManager stageManager;

    public UpdateChuHoController(HoKhauService hoKhauService, StageManager stageManager) {
        this.hoKhauService = hoKhauService;
        this.stageManager = stageManager;
    }

    public void InitData(long id,NhanKhau nhanKhau){
        hoKhau = hoKhauService.getHoKhau(id);
        fullNameField.setText(nhanKhau.getHoTen());
        placeOfBirthField.setText(nhanKhau.getNoiSinh());
        hometownField.setText(nhanKhau.getNguyenQuan());
        ethnicityField.setText(nhanKhau.getDanToc());
        jobField.setText(nhanKhau.getNgheNghiep());
        idField.setText(nhanKhau.getCmnd());
        if(nhanKhau.getGioiTinh().equals("Nam")){
            maleButton.setSelected(true);
        }
        else{
            maleButton.setSelected(false);
        }


    }
    public String getGender(){
        if(maleButton.isSelected()){
            return "Nam";
        }
        else{
            return "Nữ";
        }
    }

    public void save(ActionEvent event) throws IOException {
        NhanKhau nhanKhau = new NhanKhau();
        nhanKhau.setCmnd(idField.getText());
        nhanKhau.setHoTen(fullNameField.getText());
        nhanKhau.setNgaySinh(java.sql.Date.valueOf(dobPicker.getValue()));
        nhanKhau.setNoiSinh(placeOfBirthField.getText());
        nhanKhau.setNguyenQuan(hometownField.getText());
        nhanKhau.setNgheNghiep(jobField.getText());
        nhanKhau.setDanToc(ethnicityField.getText());
        nhanKhau.setGioiTinh(getGender());
        nhanKhau.setQuanHeVoiChuHo("Chủ hộ");
        hoKhau.setChuHo(nhanKhau);
        hoKhauService.UpdateHoKhau(hoKhau.getId(),nhanKhau);
        stageManager.switchScene(stage,scene, new FXMLLoader(getClass().getResource("../views/hokhau.fxml")),event);
    }


}
