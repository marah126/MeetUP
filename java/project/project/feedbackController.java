package project.project;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class feedbackController implements Initializable {

    @FXML
    private Label LabelUserName;

    @FXML
    private TableView<feedbackClass> feedbackTable;

    @FXML
    private TableColumn<feedbackClass, Integer> eventIDColoumn;

    @FXML
    private TableColumn<feedbackClass, String> feebackColoumn;

    @FXML
    private TableColumn<feedbackClass, Integer> userIDColoumn;

    @FXML
    void btnBack(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // userIDColoumn=new TableColumn<>("User ID");
        userIDColoumn.setCellValueFactory(new PropertyValueFactory<feedbackClass,Integer>("userID"));
        eventIDColoumn.setCellValueFactory(new PropertyValueFactory<feedbackClass,Integer>("eventID"));
        feebackColoumn.setCellValueFactory(new PropertyValueFactory<feedbackClass,String>("feedback"));
        feedbackClass f=new feedbackClass();

        ObservableList<feedbackClass>feedbacks=feedbackTable.getItems();

       //feedbacks=feedbackTable.getItems();
        feedbacks.add(f);
        feedbackTable.setItems(feedbacks);

        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet  rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getString(1));

            LabelUserName.setText(rs.getString(1));


            rs=stmt.executeQuery("SELECT * FROM REGISTRATION WHERE EVENT_IDD IN(SELECT EVENT_ID FROM tbl_event WHERE ORGANIZER_ID= '"+userSignUpController.org_id+"')");

            while (rs.next()){
                f=new feedbackClass();
                f.setUserID(rs.getInt(1));
                f.setEventID(rs.getInt(2));
                f.setFeedback(rs.getString(3));
                feedbacks=feedbackTable.getItems();
                feedbacks.add(f);
                feedbackTable.setItems(feedbacks);


            }

            con.setAutoCommit(false);
            con.rollback();
            con.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void homeAction(ActionEvent e) throws IOException {
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
