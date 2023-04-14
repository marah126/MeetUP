package project.project;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.ResourceBundle;

public class NewEventController implements Initializable {
    @FXML
    private DatePicker NewDate;

    @FXML
    private TextField TXTNewDescription;

    @FXML
    private TextField TXTNewTitle;
    @FXML
    private Label LabelUserName;
    @FXML
    private ComboBox<String> Location;

    @FXML
    private ComboBox<String> StartTime;

    @FXML
    private ComboBox<String> EndTime;

    @FXML
    private RadioButton btnOnline;

    @FXML
    private Label warnning;
    public static ArrayList<String>values =new ArrayList<String>();
    public static String z;

    public void homeAction(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
        stage.setScene(root.getScene());
    }

    public void nextAction(ActionEvent e) throws IOException {
        boolean flag=true;

        if(TXTNewTitle.getText().isBlank()){
            TXTNewTitle.setStyle("-fx-border-color:#FF0000");
            warnning.setText("You should fill Event name");
            flag=false;
        }else {
            TXTNewTitle.setStyle("-fx-border-color:#008000");

        }

        if(StartTime.getValue()==null){
            StartTime.setStyle("-fx-border-color: #FF0000");
            warnning.setText("You should select Start time ");
            flag=false;
        }else {
            StartTime.setStyle("-fx-border-color: #008000");
        }
        if (EndTime.getValue()==null){
            EndTime.setStyle("-fx-border-color: #FF0000");
            warnning.setText("You should select End time ");
            flag=false;
        }else {
            EndTime.setStyle("-fx-border-color: #008000");
        }
        if(StartTime.getValue()!=null && EndTime.getValue()!=null) {
            if (StartTime.getValue().equals(EndTime.getValue())) {
                EndTime.setStyle("-fx-border-color: #FF0000");
                StartTime.setStyle("-fx-border-color: #FF0000");
                warnning.setText("Start time equal End time");
                flag = false;
            }
        }
        else {
            EndTime.setStyle("-fx-border-color: #008000");
            StartTime.setStyle("-fx-border-color: #008000");
            warnning.setText(" ");
        }

        if (NewDate.getValue()==null){
            NewDate.setStyle("-fx-border-color: #FF0000");
            flag=false;
        }else {
            NewDate.setStyle("-fx-border-color: #008000");
        }

        if(btnOnline.isSelected()==false){
            if (Location.getItems().isEmpty()){
                    Location.setStyle("-fx-border-color-color: #FF0000");
                    warnning.setText("You should select Location");
                    flag=false;
            }
            else {
                Location.setStyle("-fx-border-color:#008000");
            }
        }
        if (flag) {
            String ss[]=new String[3];
             String s= String.valueOf( NewDate.getValue());
             ss=s.split("-");
             for (int i=0; i< ss.length;i++){
                 System.out.println(ss[i]);
             }
              z=ss[2]+"/"+ss[1]+"/"+ss[0];
             System.out.println(z);
            values.add(0,TXTNewTitle.getText());
            values.add(1,TXTNewDescription.getText());
            values.add(2,StartTime.getValue());
            values.add(3,EndTime.getValue());
            values.add(4,Location.getValue());
            values.add(5,String.valueOf(NewDate.getValue()));


            EventObject event;
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("NewEvent2.fxml"));
            stage.setScene(root.getScene());
        }
    }

    @FXML
    void btnEventsHandle(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("events.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnFeedbackHandle(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("feedback.fxml"));
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
    @FXML
    void btnOnlineHandle(ActionEvent event) {

        if (btnOnline.isSelected()) {
            StartTime.setItems(FXCollections.observableArrayList("8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                    "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14;30", "15:00", "15:30", "16:00", "16:30",
                    "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00",
                    "22:30", "23:00"));
            EndTime.setItems(FXCollections.observableArrayList("8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                    "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14;30", "15:00", "15:30", "16:00", "16:30",
                    "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00",
                    "22:30", "23:00", "23:30", "00:00"));
            Location.setDisable(true);
        }
        else{
            Location.setDisable(false);
            StartTime.setItems(FXCollections.observableArrayList("8:00","8:30","9:00","9:30","10:00","10:30",
                    "11:00","11:30","12:00","12:30","13:00","13:30","14:00","14;30","15:00","15:30","16:00","16:30",
                    "17:00","17:30","18:00","18:30"));
            EndTime.setItems(FXCollections.observableArrayList("8:00","8:30","9:00","9:30","10:00","10:30",
                    "11:00","11:30","12:00","12:30","13:00","13:30","14:00","14;30","15:00","15:30","16:00","16:30",
                    "17:00","17:30","18:00","18:30","19:00"));

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(NewEventController2.previous){
            TXTNewTitle.setText(values.get(0));
            TXTNewDescription.setText(values.get(1));
            StartTime.setValue(values.get(2));
            EndTime.setValue(values.get(3));
            Location.setValue(values.get(4));
            NewDate.setValue(LocalDate.parse(values.get(5)));
        }

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getString(1));
            LabelUserName.setText(rs.getString(1));

           StartTime.setItems(FXCollections.observableArrayList("8:00","8:30","9:00","9:30","10:00","10:30",
                    "11:00","11:30","12:00","12:30","13:00","13:30","14:00","14;30","15:00","15:30","16:00","16:30",
                    "17:00","17:30","18:00","18:30"));
           EndTime.setItems(FXCollections.observableArrayList("8:00","8:30","9:00","9:30","10:00","10:30",
                   "11:00","11:30","12:00","12:30","13:00","13:30","14:00","14;30","15:00","15:30","16:00","16:30",
                   "17:00","17:30","18:00","18:30","19:00"));

            Location.setItems(FXCollections.observableArrayList("Engineer Amphitheater",
                   " Science Amphitheater",
                    "The Big Theater",
                    "N_Gate",
                    "computer_lab",
                    "online"));
           Location.setVisibleRowCount(5);
           StartTime.setVisibleRowCount(5);
           EndTime.setVisibleRowCount(5);
           System.out.println(StartTime.getValue());
           System.out.println(EndTime.getSelectionModel());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
