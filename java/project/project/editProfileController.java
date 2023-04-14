package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class editProfileController implements Initializable {

    @FXML
    private TextField TXTName;

    @FXML
    private TextField TXTSearch;

    @FXML
    private PasswordField TxtConfirm;

    @FXML
    private TextArea TxtDesc;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtManager;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private TextField TxtPhone;

    @FXML
    private Label labelConfirm;

    @FXML
    private Label labelWarning;

    @FXML
    private Label LabelUserName;

    public boolean validate_phone(String s){
        String phonee = s ;
        String regexx = "(059)([0-9]{7})";
        String regexa = "(056)([0-9]{7})";
        boolean re = s.matches(regexx)||s.matches(regexa);
        return re ;

    }


    public boolean validate_email(String s)
    {
        String in_email = s ;
        String regex = "^[a-zA-Z0-9+_.-]+@[gmail.com]+$";
        boolean result = s.matches(regex);
        return result ;

    }


    @FXML
    public void homeAction(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
        stage.setScene(root.getScene());
    }
    @FXML
    void btnEventsHandle(ActionEvent e)throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("events.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnFeedbackHandle(ActionEvent e) throws IOException{
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("feedback.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnProfileHandle(ActionEvent e)throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("myProfile.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnSearchHandle(ActionEvent event) {

    }

    @FXML
    void btnStatisticsHandle(ActionEvent e)throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("statistics.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnUserProfileHandle(ActionEvent e) throws IOException{
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("myProfile.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    public void btnSaveHandle(ActionEvent actionEvent) {
        boolean flag=true;
        char ch;
        if (TxtPassword.getText().length() != 8) {
            TxtPassword.setStyle("-fx-border-color:#FF0000");
            labelWarning.setText("Password length should be 8 characters");
            flag=false;
        }

        if (!(TxtPassword.getText().equals(TxtConfirm.getText()))) {
            TxtPassword.setStyle("-fx-border-color:#FF0000");
            TxtConfirm.setStyle("-fx-border-color:#FF0000");
            labelWarning.setText("Password and Confirm password don't match");
            flag=false;
        }

        if ((TxtPassword.getText().equals(TxtConfirm.getText()))) {
            TxtPassword.setStyle("-fx-border-color:#008000");
            TxtConfirm.setStyle("-fx-border-color:#008000");
        }

        if (validate_phone(TxtPhone.getText()) == false) {
            TxtPhone.setStyle("-fx-border-color:#FF0000");
            TxtPhone.setText("Not valid in Palestine");
            flag=false;
        }

        if (validate_email(TxtEmail.getText()) == false) {
            TxtEmail.setStyle("-fx-border-color:#FF0000");
            TxtEmail.setText("Only gemail");
            flag=false;
        }
        if(TXTName.getText().isBlank() || TxtManager.getText().isBlank() || TxtPhone.getText().isBlank() || TxtEmail.getText().isBlank() ||
            TxtPassword.getText().isBlank() || TxtConfirm.getText().isBlank()){
            if(TXTName.getText().isBlank())
                TXTName.setStyle("-fx-border-color:#FF0000");
            if(TxtManager.getText().isBlank())
                TxtManager.setStyle("-fx-border-color:#FF0000");
            if(TxtPhone.getText().isBlank())
                TxtPhone.setStyle("-fx-border-color:#FF0000");
            if(TxtEmail.getText().isBlank())
                TxtEmail.setStyle("-fx-border-color:#FF0000");
            flag=false;
        }
            if(flag) {
                try {

                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
                    Statement stmt = con.createStatement();
                    stmt.executeQuery("update tbl_org set ORG_NAME='" + TXTName.getText() + "' ,MANAGER_NAME='" + TxtManager.getText() + "',ORG_EMAIL='" + TxtEmail.getText() + "' \n" +
                            ",ORG_PHONE='" + TxtPhone.getText() + "' \n" +
                            ",ORG_PASSWORD='" + TxtPassword.getText() + "' \n" +
                            ",ORG_DESC='" + TxtDesc.getText() + "' where org_id='" + userSignUpController.org_id + "' ");

                    labelWarning.setText("Edit saved");
                    TxtEmail.setStyle("-fx-border-color:#008000");
                    TxtPhone.setStyle("-fx-border-color:#008000");
                    TxtManager.setStyle("-fx-border-color:#008000");
                    TXTName.setStyle("-fx-border-color:#008000");
                    TxtConfirm.setStyle("-fx-border-color:#008000");
                    TxtPassword.setStyle("-fx-border-color:#008000");


                } catch (SQLException e) {
                    e.printStackTrace();


                }
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            TXTName.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getString(1));
            LabelUserName.setText(rs.getString(1));


            rs = stmt.executeQuery(" select MANAGER_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            TxtManager.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_EMAIL FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            TxtEmail.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_PHONE FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            TxtPhone.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_DESC FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            TxtDesc.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_PASSWORD FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            TxtPassword.setText(rs.getString(1));



        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }


    public void passwordHandle(javafx.scene.input.MouseEvent mouseEvent) {
        labelConfirm.setDisable(false);
        TxtConfirm.setDisable(false);

    }
}
