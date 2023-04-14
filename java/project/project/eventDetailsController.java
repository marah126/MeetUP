package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class eventDetailsController implements Initializable {

    @FXML
    private Label LabelAttendenceCount;

    @FXML
    private Label LabelDateDetail;

    @FXML
    private Text LabelDescription;

    @FXML
    private Label LabelLikesCount;

    @FXML
    private Label LabelNameDetail;

    @FXML
    private Label LabelPlaceDetail;

    @FXML
    private Label LabelPriceDetail;

    @FXML
    private Label LabelStatus;

    @FXML
    private Label LabelTimeDetail;

    @FXML
    private Label LabelUserName;

    @FXML
    private ListView<String> ListAttendence;

    @FXML
    private ListView<String> ListLikes;


    String names [];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs=stmt.executeQuery("SELECT *FROM tbl_event where event_id= '"+eventsController.eventid+"' ");
            rs.next();
            LabelNameDetail.setText(rs.getString(2));
            LabelDescription.setText(rs.getString(4));
            LabelPriceDetail.setText(rs.getString(6));
            LabelTimeDetail.setText(rs.getString(8)+" - "+rs.getString(9));
            LabelDateDetail.setText(String.valueOf(rs.getDate(7)));
            LabelStatus.setText(rs.getString(11));
            LabelPlaceDetail.setText(rs.getString(10));

            rs=stmt.executeQuery("SELECT count(EVENT_IDD)FROM REGISTRATION where EVENT_IDD= '"+eventsController.eventid+"' ");
            rs.next();
            LabelAttendenceCount.setText(String.valueOf(rs.getInt(1)));


            rs=stmt.executeQuery("SELECT count(EVENT_IDD)FROM likee where EVENT_IDD= '"+eventsController.eventid+"' ");
            rs.next();
            LabelLikesCount.setText(String.valueOf(rs.getInt(1)));

            rs=stmt.executeQuery("SELECT F_NAME,L_NAME from tbl_user where USER_ID in (SELECT USER_IDD FROM likee where  EVENT_IDD= '"+eventsController.eventid+"') ");

            while (rs.next()){
                ListLikes.getItems().add(rs.getString(1)+" "+rs.getString(2));
            }

            rs=stmt.executeQuery("SELECT F_NAME,L_NAME from tbl_user where USER_ID in (SELECT USER_IDD FROM REGISTRATION where  EVENT_IDD= '"+eventsController.eventid+"') ");

            while (rs.next()){
                ListAttendence.getItems().add(rs.getString(1)+" "+rs.getString(2));
            }









        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void btnHomeAction(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
        stage.setScene(root.getScene());
    }

    @FXML
    void btnEventsHandle(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("events.fxml"));
        stage.setScene(root.getScene());

    }
    @FXML
    void btnProfileHandle(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("myProfile.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnSearchHandle(ActionEvent event) {

    }

    @FXML
    void btnStatisticsHandle(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("statistics.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnUserProfileHandle(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("myProfile.fxml"));
        stage.setScene(root.getScene());

    }
}




