package com.example.springwithfx.controller;

import com.example.springwithfx.config.StageManager;
import com.example.springwithfx.model.HoKhauDto;
import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.service.HoKhauService;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class HoKhauTableController implements Initializable {


    private Stage stage;
    private Scene scene;

    @FXML
    public TableView<HoKhauDto> addressTable;

    public TableColumn actionColumn;



    private final HoKhauService hoKhauService;
    private final StageManager stageManager;
    private final NhanKhauService nhanKhauService;

    public HoKhauTableController(HoKhauService hoKhauService, StageManager stageManager, NhanKhauService nhanKhauService) {
        this.hoKhauService = hoKhauService;
        this.stageManager = stageManager;
        this.nhanKhauService = nhanKhauService;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionColumn.setCellFactory(param -> new TableCell<HoKhauDto,Void>() {
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
                        HoKhauDto hoKhauDto = getTableView().getItems().get(getIndex());
                        hoKhauService.deleteHoKhau(hoKhauDto.getId());
                        addressTable.getItems().remove(getIndex());
                    } else {
                        System.out.println("no");
                    }
                }
                );

                editIcon.setOnMouseClicked(event -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/chuhoupdate.fxml"));
                    HoKhauDto hoKhauDto = getTableView().getItems().get(getIndex());
                    try {
                        stageManager.switchScene(stage, scene, fxmlLoader, event);
                        UpdateChuHoController chuHoController = fxmlLoader.getController();
                        NhanKhau nhanKhau = nhanKhauService.getNhanKhau(Long.parseLong(hoKhauDto.getChuHo()));
                        chuHoController.InitData(hoKhauDto.getId(),nhanKhau);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
        ObservableList<HoKhauDto> hoKhauDtos = FXCollections.observableArrayList(hoKhauService.findAll());
        addressTable.setItems(hoKhauDtos);
    }

    public void commonSwitch(FXMLLoader fxmlLoader, ActionEvent event) throws IOException {
        stageManager.switchScene(stage, scene, fxmlLoader, event);
    }

    @FXML
    public void switchNhauKhau(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/chuho.fxml"));
            commonSwitch(fxmlLoader, event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
