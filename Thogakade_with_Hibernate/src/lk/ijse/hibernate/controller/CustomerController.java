package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOType;
import lk.ijse.hibernate.bo.custom.impl.CustomerBOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;


import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXButton btnSave;
    public Label lblID;
    public JFXTextField txtSalary;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableView tblCustomers;
    public JFXTextField txtId;

    CustomerBOImpl customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);


    public void initialize () throws Exception {
        tblCustomers.setItems(loadAll());
        lblID.setText(generateNewID());
    }

    private ObservableList<CustomerDTO> loadAll() throws Exception {
        ArrayList<CustomerDTO> all= (ArrayList<CustomerDTO>) customerBO.findAll();

        ObservableList<CustomerDTO> observableList= FXCollections.observableArrayList();
        for (CustomerDTO dto:all) {
            observableList.add(new CustomerDTO(
                            dto.getId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getSalary()
                    )
            );
            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
            tblCustomers.setItems(observableList);
        }

        return observableList;
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = lblID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary= Double.parseDouble(txtSalary.getText());


        CustomerDTO customer = new CustomerDTO(id, name, address,salary);


        try {
            boolean added = customerBO.addCustomer(customer);

            if(added){
                new Alert(Alert.AlertType.CONFIRMATION,"OK").showAndWait();
            }

            txtName.clear();
            txtAddress.clear();
            lblID.setText(generateNewID());
            txtSalary.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateNewID(){
        String s = null;
        try {
            s = customerBO.newCustomerID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary= Double.parseDouble(txtSalary.getText());


        CustomerDTO customer = new CustomerDTO(id, name, address,salary);


        try {
            boolean updated = customerBO.updateCustomer(customer);

            if(updated){
                new Alert(Alert.AlertType.CONFIRMATION,"OK").showAndWait();
            }

            txtName.clear();
            txtAddress.clear();
            txtId.clear();
            txtSalary.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent event) throws Exception {
        String id=txtId.getText();

        boolean deleted=customerBO.deleteCustomer(id);

        if (deleted){
            new Alert(Alert.AlertType.CONFIRMATION,"OK").showAndWait();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"NOT DELETED").showAndWait();

        }
    }

    public void searchCustomerOnAction(ActionEvent event) throws Exception {

        String id=txtId.getText();
        CustomerDTO dto=customerBO.findCustomer(id);

        if (dto!=null){
            txtId.setText(dto.getId());
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtSalary.setText(String.valueOf(dto.getSalary()));

        }

    }

    public void clearAllOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }
}