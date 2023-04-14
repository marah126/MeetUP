package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class myProfileController implements Initializable {

    @FXML
    private Text desc;
    @FXML
    private Label LabelCollege;

    @FXML
    private Label LabelEdit;

    @FXML
    private Label LabelEmail;

    @FXML
    private Label LabelEvents;

    @FXML
    private Label LabelFollowers;

    @FXML
    private Label LabelManager;

    @FXML
    private Label LabelPassword;

    @FXML
    private Label LabelPhone;

    @FXML
    private Label userName;

    @FXML
    private Label LabelUserName;

    @FXML
    void btnDeleteProfile(ActionEvent event) {
        try {

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
        Statement stmt = con.createStatement();
        stmt.executeQuery("DELETE FROM tbl_org where org_id= '"+userSignUpController.org_id+"' ");

            JOptionPane.showMessageDialog(null,"You deleted your account");
            Stage s= (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.close();



        }    catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            userName.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getString(1));
            LabelUserName.setText(rs.getString(1));


            rs = stmt.executeQuery(" select ORG_EMAIL FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            LabelEmail.setText(rs.getString(1));

            rs = stmt.executeQuery(" select MANAGER_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            LabelManager.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_PHONE FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            LabelPhone.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_PASSWORD FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            LabelPassword.setText(rs.getString(1));

            rs = stmt.executeQuery(" select ORG_DESC FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            desc.setText(rs.getString(1));

            rs = stmt.executeQuery("select count (EVENT_ID) from tbl_event where ORGANIZER_ID= '" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getInt(1));
            LabelEvents.setText(String.valueOf(rs.getInt(1))+"Events");

            rs = stmt.executeQuery("select count (ORGNIZER_ID) from follow where ORGNIZER_ID= '" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getInt(1));
            LabelFollowers.setText(String.valueOf(rs.getInt(1))+"Followers");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editHandle(MouseEvent mouseEvent) throws IOException{
        EventObject event;
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("editProfile.fxml"));
        stage.setScene(root.getScene());

    }
}
