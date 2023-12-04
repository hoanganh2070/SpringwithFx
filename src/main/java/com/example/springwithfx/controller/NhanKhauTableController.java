package com.example.springwithfx.controller;

import com.example.springwithfx.config.StageManager;
import com.example.springwithfx.model.HoKhauDto;
import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.service.NhanKhauService;
import com.example.springwithfx.views.WarningDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class NhanKhauTableController implements Initializable {

    private Stage stage;
    private Scene scene;

    private final StageManager stageManager;
    private final NhanKhauService nhanKhauService;

    @FXML
    public TableView<NhanKhau> personTable;
    public TableColumn actionColumn;
    public TextField searchField;

    public NhanKhauTableController(StageManager stageManager, NhanKhauService nhanKhauService) {
        this.stageManager = stageManager;
        this.nhanKhauService = nhanKhauService;
    }

    public void commonSwitch(FXMLLoader fxmlLoader, ActionEvent event) throws IOException {
        stageManager.switchScene(stage, scene, fxmlLoader, event);
    }
    public void switchNhanKhau(ActionEvent event){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/form_nhankhau.fxml"));
            commonSwitch(fxmlLoader, event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionColumn.setCellFactory(param -> new TableCell<NhanKhau,Void>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            private final FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
            private final HBox pane = new HBox(deleteIcon, editIcon);

            {
                deleteIcon.setStyle( " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#ff1744;"
                );
                editIcon.setStyle(
                        " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill: #00E676;"
                );
                pane.setSpacing(30);
                pane.setPadding(new Insets(0, 0, 0, 45));
                deleteIcon.setOnMouseClicked(event -> {
                            WarningDialog warningDialog = new WarningDialog();
                            Optional<ButtonType> result = warningDialog.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.YES) {
                                NhanKhau nhanKhau = getTableView().getItems().get(getIndex());
                                nhanKhauService.deleteNhanKhau(Long.parseLong(nhanKhau.getCmnd()));
                                personTable.getItems().remove(getIndex());
                            } else {
                                System.out.println("no");
                            }
                        }
                );

                editIcon.setOnMouseClicked(event -> {

                });
            }


            protected void updateItem(Void aVoid, boolean b) {
                super.updateItem(aVoid, b);
                if (b) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });
        ObservableList<NhanKhau> nhanKhauList = FXCollections.observableList(nhanKhauService.getAllNhanKhau());
        personTable.setItems(nhanKhauList);
    }

    public void searchHoTen() {
        ObservableList<NhanKhau> nhanKhauList = FXCollections.observableList(nhanKhauService.searchHoTen(searchField.getText()));
        personTable.setItems(nhanKhauList);
    }
}
