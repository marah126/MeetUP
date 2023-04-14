package project.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class eventsController implements Initializable {


    @FXML
    private TableColumn<Event,String> dateColoumn;

    @FXML
    private TableView<Event> eventsTable;

    @FXML
    private TableColumn<Event,Integer> IDColoumn;

    @FXML
    private TableColumn<Event, String> locationColoumn;

    @FXML
    private TableColumn<Event, String> nameColoumn;

    @FXML
    private ChoiceBox<String> statusList;
    public static int eventid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        IDColoumn.setCellValueFactory(new PropertyValueFactory<Event,Integer>("id"));
        nameColoumn.setCellValueFactory(new PropertyValueFactory<Event,String>("title"));
        locationColoumn.setCellValueFactory(new PropertyValueFactory<Event,String>("location"));
        dateColoumn.setCellValueFactory(new PropertyValueFactory<Event,String>("date"));
        statusList.setItems(FXCollections.observableArrayList("All","on going","canceled"));

        ObservableList<Event> ee=eventsTable.getItems();
        Event e;
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(" SELECT EVENT_ID,EVENT_NAME,EVENT_DATE,EVENT_LOCATION FROM tbl_event where ORGANIZER_ID= '"+userSignUpController.org_id+"' ");


            while (rs.next()){
                e=new Event();
                e.setId(rs.getInt(1));
                e.setTitle(rs.getString(2));
                e.setDate(String.valueOf(rs.getDate(3)));
                e.setLocation(rs.getString(4));
                ee=eventsTable.getItems();
                ee.add(e);
                eventsTable.setItems(ee);


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
            Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
            stage.setScene(root.getScene());
    }

    public void chooseHandle(ActionEvent actionEvent) {
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs;
            Event e;
            ObservableList<Event> eee;
            if (statusList.getValue().equals("on going")) {
                eventsTable.getItems().clear();
                rs = stmt.executeQuery(" SELECT EVENT_ID,EVENT_NAME,EVENT_DATE,EVENT_LOCATION FROM tbl_event where ORGANIZER_ID= '"+userSignUpController.org_id+"' and STATUS = 'on going'");

                while (rs.next()){
                    e=new Event();
                    e.setId(rs.getInt(1));
                    e.setTitle(rs.getString(2));
                    e.setDate(String.valueOf(rs.getDate(3)));
                    e.setLocation(rs.getString(4));
                    eee=eventsTable.getItems();
                    eee.add(e);
                    eventsTable.setItems(eee);


                }



            }else if(statusList.getValue().equals("canceled")){
                eventsTable.getItems().clear();
                rs = stmt.executeQuery(" SELECT EVENT_ID,EVENT_NAME,EVENT_DATE,EVENT_LOCATION FROM tbl_event where ORGANIZER_ID= '"+userSignUpController.org_id+"' and STATUS = 'canceled'");

                while (rs.next()){
                    e=new Event();
                    e.setId(rs.getInt(1));
                    e.setTitle(rs.getString(2));
                    e.setDate(String.valueOf(rs.getDate(3)));
                    e.setLocation(rs.getString(4));
                    eee=eventsTable.getItems();
                    eee.add(e);
                    eventsTable.setItems(eee);


                }

            }else{
                eventsTable.getItems().clear();
                rs = stmt.executeQuery(" SELECT EVENT_ID,EVENT_NAME,EVENT_DATE,EVENT_LOCATION FROM tbl_event where ORGANIZER_ID= '"+userSignUpController.org_id+"'");

                while (rs.next()){
                    e=new Event();
                    e.setId(rs.getInt(1));
                    e.setTitle(rs.getString(2));
                    e.setDate(String.valueOf(rs.getDate(3)));
                    e.setLocation(rs.getString(4));
                    eee=eventsTable.getItems();
                    eee.add(e);
                    eventsTable.setItems(eee);


                }
                con.setAutoCommit(false);
                con.rollback();
                con.close();
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
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

    public void viewHandle(ActionEvent e)throws IOException {
        if(eventsTable.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null,"pleas select event ");
        }
        else {
            Event eve = eventsTable.getSelectionModel().getSelectedItem();
            eventid = eve.getId();
            EventObject event;
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("detailes.fxml"));
            stage.setScene(root.getScene());
        }
    }

    public void btnAction2(ActionEvent actionEvent) {
    }

    public void cancelHandel(ActionEvent actionEvent) {
        try {
            Event e=eventsTable.getSelectionModel().getSelectedItem();
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT STATUS FROM tbl_event WHERE event_id=  '"+e.getId()+"' ");
            rs.next();
            if(rs.getString(1).equals("canceled")){
                JOptionPane.showMessageDialog(null,"This event is already canceled");
            }
            else {
                rs=stmt.executeQuery("UPDATE tbl_event SET status='canceled' WHERE event_id= '"+e.getId()+"' ");
                JOptionPane.showMessageDialog(null,"Event calceled");
            }
            con.setAutoCommit(false);
            con.rollback();
            con.close();

    }
        catch (SQLException ex) {
        ex.printStackTrace();
        }
    }
}



