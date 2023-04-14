package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class NewEventController2 implements Initializable {
    @FXML
    private TextField TXTNewAttendence;

    @FXML
    private TextField TXTNewPrice;
    @FXML
    private Label LabelUserName;
    public static boolean previous=false;
    boolean flag=true;
    public void homeAction(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
        stage.setScene(root.getScene());
    }

    public void previousAction(ActionEvent e)throws IOException {
        previous=true;
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("NewEvent.fxml"));
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

    public void btnAddHandle(ActionEvent actionEvent) {
        int xx;
        if(TXTNewAttendence.getText().isBlank()){
            TXTNewAttendence.setStyle("-fx-border-color: #FF0000");
            flag=false;

        }
        if (TXTNewPrice.getText().isBlank()){
            TXTNewPrice.setStyle("-fx-border-color: #FF0000");
            flag=false;
        }
        if (flag){
            try {


                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
                Statement stmt = con.createStatement();
                String i="12/6/2001";
                ResultSet rs=stmt.executeQuery("select max(event_id)from tbl_event");
                rs.next();
                int idd=rs.getInt(1)+3;

                rs=stmt.executeQuery("select  place_id from tbl_place where place_name= '"+NewEventController.values.get(4)+"' ");
                rs.next();
                String place=rs.getString(1);

                rs=stmt.executeQuery("insert into tbl_event values ('"+idd+"','"+NewEventController.values.get(0)+"','"+userSignUpController.org_id+"' , '"+ NewEventController.values.get(1)+"' ,'"+Integer.parseInt(TXTNewAttendence.getText())+"' , '"+Integer.parseInt(TXTNewPrice.getText())+"' ,'"+NewEventController.z+"' , '"+NewEventController.values.get(2)+"' , '"+NewEventController.values.get(3)+"' , '"+place+"', 'on going')");
            }
            catch (SQLException e){
                e.printStackTrace();

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(NewEventController.values.size());
        for (int i=0;i<NewEventController.values.size();i++){
            System.out.println(NewEventController.values.get(i));
        }
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getString(1));
            LabelUserName.setText(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
