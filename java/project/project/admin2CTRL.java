package project.project;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class admin2CTRL implements Initializable {

    @FXML
    private TableColumn<org,String> emailCol;

    @FXML
    private TableColumn<org, String> managerCol;

    @FXML
    private TableColumn<org, String> nameCol;

    @FXML
    private TableColumn<org, String> phoneCol;

    @FXML
    private TableColumn<org, String> DescCol;


    @FXML
    private TableView<org> orgTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<org,String>("orgName"));
        managerCol.setCellValueFactory(new PropertyValueFactory<org,String>("manName"));
        DescCol.setCellValueFactory(new PropertyValueFactory<org,String>("desc"));
        emailCol.setCellValueFactory(new PropertyValueFactory<org,String>("Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<org,String>("phone"));

        ObservableList<org> oo=orgTable.getItems();

        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(" SELECT org_name,manager_name,org_desc,org_email,org_phone FROM tbl_org WHERE ACCEPTED='No' ");
            org o;

            while (rs.next()){
                o=new org();
                o.setOrgName(rs.getString(1));
                o.setManName(rs.getString(2));
                o.setDesc(rs.getString(3));
                o.setEmail(rs.getString(4));
                o.setPhone(rs.getString(5));
                oo=orgTable.getItems();
                oo.add(o);
                orgTable.setItems(oo);

            }


        con.setAutoCommit(false);
        con.rollback();
        con.close();

    }catch (SQLException ex) {
        ex.printStackTrace();
    }
    }

    public void btnHomeAction(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage.setScene(root.getScene());
    }

    public void btnAction2(ActionEvent actionEvent) {
    }

    public void accept(ActionEvent actionEvent) {

        org o=orgTable.getSelectionModel().getSelectedItem();
        try{
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("UPDATE tbl_org SET accepted='yes' WHERE org_name= '"+o.getOrgName()+"' ");
        orgTable.getItems().removeAll(orgTable.getSelectionModel().getSelectedItem());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refuse(ActionEvent actionEvent) {
        org o=orgTable.getSelectionModel().getSelectedItem();
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM tbl_org where org_name = '"+o.getOrgName()+"' ");
            orgTable.getItems().removeAll(orgTable.getSelectionModel().getSelectedItem());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
