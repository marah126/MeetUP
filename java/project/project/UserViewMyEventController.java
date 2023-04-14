package project.project;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EventObject;
import java.util.ResourceBundle;

public class UserViewMyEventController implements Initializable {
    @FXML
    private TableView<MyEvent> table_event;
    @FXML
    private TableColumn<MyEvent, String> ED;//date



    @FXML
    private TableColumn<MyEvent, String> ES;

    @FXML
    private TableColumn<MyEvent, String> ID;

    @FXML
    private TableColumn<MyEvent, String> NM;// name

    @FXML
    private ChoiceBox<String> choose;




    public void op_home1(javafx.event.ActionEvent actionEvent) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("UserDash.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());

    }

    public void op_prof1(javafx.event.ActionEvent actionEvent) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("profileInforUser.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());
    }

    public void op_celen1(javafx.event.ActionEvent actionEvent) throws IOException {

        EventObject event;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("UerViewMyEvent.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene()); }

    public void op_foll1(ActionEvent actionEvent) {
    }

    public void op_like1(ActionEvent actionEvent) {
    }
  
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID.setCellValueFactory(new PropertyValueFactory<MyEvent,String>("id"));
        NM.setCellValueFactory(new PropertyValueFactory<MyEvent,String>("name"));


        ED.setCellValueFactory(new PropertyValueFactory<MyEvent,String>("date"));
        ES.setCellValueFactory(new PropertyValueFactory<MyEvent,String>("status"));
        choose.setItems(FXCollections.observableArrayList("All","MY Event","Follower Events"));
        ObservableList<MyEvent> myEvents = table_event.getItems();


        int ev;
        ResultSet rb;
        int size_tb=-5;

        try {

                MyEvent es;
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project","123456");
                Statement stmte = con.createStatement();
                ResultSet rs = stmte.executeQuery("SELECT event_id, event_name ,PLACE_NAME,STATUS from tbl_event,tbl_place where EVENT_LOCATION= PLACE_ID");
                   while( rs.next()) {
                       es=new MyEvent();
                     es.setId(String.valueOf(rs.getInt(1)));
                     es.setName(rs.getString(2));
                     es.setDate(rs.getString(3));
                     es.setStatus(rs.getString(4));
                       myEvents=table_event.getItems();
                       myEvents.add(es);
                       table_event.setItems(myEvents);
                   }
            con.setAutoCommit(false);
            con.rollback();
            con.close();

        } catch (Exception ee) {
            System.out.println("flugvkdbjfffffffffffffff");
            ee.printStackTrace();
        }

    }


    public void op_all_event(MouseEvent mouseEvent) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("ALLEVENT_table.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());

    }

    public void sort(ActionEvent actionEvent) {
        int arr[];

        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs;
            MyEvent e;
            ObservableList<MyEvent> eee;

            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            if (choose.getValue().equals("MY Event")) {
                table_event.getItems().clear();
                rs = stmt.executeQuery(" select count(EVENT_IDD)from registration WHERE user_idd= '" + SignInController.usr_id + "' ");
                rs.next();
                int count = rs.getInt(1);
                arr = new int[count];

                for (int i = 0; i < arr.length; i++) {
                    rs = stmt.executeQuery("select event_idd from registration WHERE user_idd = '" + SignInController.usr_id + "' ");
                    rs.next();
                    arr[i] = rs.getInt(1);
                }
                for (int i = 0; i < arr.length; i++) {
                    rs = stmt.executeQuery("select tbl_event.event_id ,tbl_event.event_name,tbl_place.place_name,tbl_event.status\n" +
                            "from tbl_event,tbl_place\n" +
                            "WHERE tbl_event.event_location=tbl_place.place_id and tbl_event.event_id = '" + arr[i] + "' ");
                    rs.next();
                    e = new MyEvent();
                    e.setId(String.valueOf(rs.getInt(1)));
                    e.setName(rs.getString(2));
                    e.setDate(rs.getString(3));
                    e.setStatus(rs.getString(4));

                    eee = table_event.getItems();
                    eee.add(e);
                    table_event.setItems(eee);
                }
            } else if (choose.getValue().equals("Follower Events")) {
                table_event.getItems().clear();

                int z = 0;
                rs = stmt.executeQuery(" SELECT tbl_event.event_id, tbl_event.event_name,tbl_event.event_location,tbl_event.status from tbl_event,tbl_place WHERE tbl_place.place_id=tbl_event.event_location and tbl_event.organizer_id in(SELECT ORGNIZER_ID from follow where user_idd ='" + SignInController.usr_id + "' )");

                while (rs.next()) {
                    e = new MyEvent();
                    e.setId(String.valueOf(rs.getInt(1)));
                    e.setName(rs.getString(2));
                    e.setDate(rs.getString(3));
                    e.setStatus(rs.getString(4));

                    eee = table_event.getItems();
                    eee.add(e);
                    table_event.setItems(eee);

                }

            } else {


                MyEvent es;
               rs = stmt.executeQuery("SELECT event_id, event_name ,PLACE_NAME,STATUS from tbl_event,tbl_place where EVENT_LOCATION= PLACE_ID");
                while (rs.next()) {
                    es = new MyEvent();
                    es.setId(String.valueOf(rs.getInt(1)));
                    es.setName(rs.getString(2));
                    es.setDate(rs.getString(3));
                    es.setStatus(rs.getString(4));
                    eee = table_event.getItems();
                    eee.add(es);
                    table_event.setItems(eee);
                }
            }
        }catch (Exception e){
                e.printStackTrace();
            }


        }


    public void explore(ActionEvent actionEvent)throws IOException{
        if(table_event.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null,"pleas select event ");
        }

        MyEvent e=table_event.getSelectionModel().getSelectedItem();
        int w= Integer.parseInt(e.getId());
        System.out.println(w);
        UserDashController.even_id=w;
        System.out.println(UserDashController.even_id);
        try {


            EventObject event;
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("ViewEventpop.fxml"));
            stage.centerOnScreen();
            stage.setScene(root.getScene());
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    public void closeProg(ActionEvent actionEvent) throws IOException {
        Stage stg;
        Parent roo;
        Platform.exit();
        stg=new Stage();
        roo= FXMLLoader.load(getClass().getResource("MainBackGround.fxml"));
        stg.setScene(new Scene(roo));
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.initStyle(StageStyle.UNDECORATED);
        stg.centerOnScreen();
        stg.showAndWait();
    }
}
