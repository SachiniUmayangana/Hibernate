package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOType;
import lk.ijse.hibernate.bo.custom.impl.ItemBOImpl;
import lk.ijse.hibernate.dto.ItemDTO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ItemController {

    ItemBOImpl itemBO= BOFactory.getInstance().getBO(BOType.ITEM);

    @FXML
    private Label lblID;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private TableView<ItemDTO> tblProducts;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    public void initialize() throws Exception {
        tblProducts.setItems(loadAll());
        lblID.setText(generateNewID());
    }

    private String generateNewID() {
        String s = null;
        try {
            s = itemBO.newItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private ObservableList<ItemDTO> loadAll() throws Exception {
        List<ItemDTO> all=  itemBO.findAll();

        ObservableList<ItemDTO> observableList= FXCollections.observableArrayList();
        for (ItemDTO dto:all) {
            observableList.add(new ItemDTO(
                            dto.getCode(),
                            dto.getDescription(),
                            dto.getUnitPrice(),
                            dto.getQtyOnHand()
                    )
            );
            colCode.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Address"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Salary"));
            tblProducts.setItems(observableList);
        }

        return observableList;
    }



    public void searchProductOnAction(javafx.event.ActionEvent event) throws Exception {
        String id=txtCode.getText();
        ItemDTO dto=itemBO.findItem(id);

        if (dto!=null){
            txtCode.setText(dto.getCode());
            txtDescription.setText(dto.getDescription());
            txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));

        }
    }

    public void updateOnAction(javafx.event.ActionEvent event) {
        String id=txtCode.getText();
        String description=txtDescription.getText();
        double unitPrice= Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand= Integer.parseInt(txtQtyOnHand.getText());



        ItemDTO item = new ItemDTO(id, description, unitPrice,qtyOnHand);


        try {
            boolean updated = itemBO.updateItem(item);

            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
                clearAll();
                generateNewID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOnAction(javafx.event.ActionEvent event) {

        String id=lblID.getText();
        String description=txtDescription.getText();
        double unitPrice= Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand= Integer.parseInt(txtQtyOnHand.getText());



        ItemDTO item = new ItemDTO(id, description, unitPrice,qtyOnHand);


        try {
            boolean added = itemBO.addItem(item);

            if (added) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
                clearAll();
                generateNewID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearAll() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    public void deleteOnAction(javafx.event.ActionEvent event) throws Exception {
        String id=txtCode.getText();

        boolean isDeleted=itemBO.deleteItem(id);

        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION," DELETED").showAndWait();

        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"NOT DELETED").showAndWait();

        }
    }

    public void clearAllOnAction(javafx.event.ActionEvent event) {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }


}
