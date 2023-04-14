package project.project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;

public class UserDashController implements Initializable {
    @FXML
    private Label lblname;
    @FXML
    private Label curr_name;
    @FXML
    private ResultSet rd;
    @FXML
    private Label ss;
    @FXML
    private VBox evlike;

    @FXML
    private Pane bck;

    @FXML
    private Label lbl_top1;
    @FXML
    private Button btn_top1;

    @FXML
    private Button btn_top2;

    @FXML
    private Button btn_top3;

    @FXML
    private Label lbl_top1name;

    @FXML
    int x[][];
    @FXML
    private Label lbl_top1org;

    @FXML
    private Label lbl_top2;

    @FXML
    int[] top3ID = new int[3];
    @FXML
    int[] top3ID_sum = new int[3];
    @FXML
    private Label lbl_top2name;
    @FXML
    public static int ev_likes=0;

    @FXML
    private Label lbl_top2org;

    @FXML
    private Label lbl_top3;

    @FXML
    private Label lbl_top3name;

    @FXML
    private Label lbl_top3org;
    @FXML
    public static int even_id=0 ;
@FXML
private ResultSet vs ;
    @FXML
    void op_prof(ActionEvent er) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) er.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("profileInforUser.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());
    }

    @FXML
    void op_hpme(ActionEvent er) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) er.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("UserDash.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());
    }
    @FXML
    void opcelender(ActionEvent er) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) er.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("UerViewMyEvent.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());

    }

    @FXML
    void openfoll(ActionEvent event) {
    }

    @FXML
    void openlike(ActionEvent event) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql ="Select * from tbl_user where USER_ID=?";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project","123456");
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setString(1, String.valueOf(usr_id));
            rd=ps.executeQuery();
            rd.next();
            String first = rd.getString(1);
          curr_name.setText((rd.getString(2))+" "+(rd.getString(3)));
          lblname.setText((rd.getString(2))+" "+(rd.getString(3)));
            Statement stmt = con.createStatement();
            vs=stmt.executeQuery("Select count (distinct event_idd) from likee ");
            vs.next();
            int size=vs.getInt(1);
            System.out.println(size);
         x=new int[size][2];
if(size!=0)
{
    vs=stmt.executeQuery("Select distinct event_idd from likee");
    for(int i=0 ; vs.next();i++){
        x[i][0]= vs.getInt(1);
    }
    for (int i=0;i< x.length;i++) {

        vs = stmt.executeQuery("select count (EVENT_IDD) FROM likee where event_idd='" + x[i][0] + "' ");
        vs.next();
        x[i][1]= vs.getInt(1);
    }
    for (int i=0; i<x.length ;i++){
        System.out.print(x[i][0]+" "+x[i][1]+"\n");

    }

    for(int j = 0; j<3; j++) {
        int max=-1;
        int index=0;
        for (int i = 0; i < x.length; i++) {
            if (x[i][1] > max) {
                max = x[i][1];
                index = i;

            }
            top3ID_sum[j]=max;
        }
        x[index][1]=-10;
        top3ID[j]=x[index][0];
    }
    for (int i=0;i<3;i++){
        System.out.print(top3ID[i]+" ");
    }

    vs= stmt.executeQuery("SELECT * from tbl_event where EVENT_ID= '" + top3ID[0] + "'");
    if(vs.next()) {
        lbl_top1name.setText(vs.getString("event_name"));
        lbl_top1org.setText(String.valueOf(vs.getDate("event_date")));
        lbl_top1.setText((String.valueOf (top3ID_sum[0])+" likes"));


    }
    vs= stmt.executeQuery("SELECT * from tbl_event where EVENT_ID= '" + top3ID[1] + "'");
    if(vs.next()) {
        lbl_top2name.setText(vs.getString("event_name"));
        lbl_top2org.setText(String.valueOf(vs.getDate("event_date")));
        lbl_top2.setText((String.valueOf (top3ID_sum[1])+" likes"));
    }
    vs= stmt.executeQuery("SELECT * from tbl_event where EVENT_ID= '" + top3ID[2] + "'");
    if(vs.next()) {

        lbl_top3name.setText(vs.getString("event_name"));
        lbl_top3org.setText(String.valueOf(vs.getDate("event_date")));
        lbl_top3.setText((String.valueOf (top3ID_sum[2])+" likes"));
    }

}
            con.setAutoCommit(false);
            con.rollback();
            con.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }







    }











    public void op_top1(ActionEvent arr) throws IOException {
        even_id=top3ID[0];
        ev_likes=top3ID_sum[0];
            EventObject event;
            Stage se = (Stage) ((Node) arr.getSource()).getScene().getWindow();
            Stage roo = FXMLLoader.load(getClass().getResource("ViewEventpop.fxml"));
            se.centerOnScreen();
            se.setScene(roo.getScene());

    }


    public void op_top2(ActionEvent actionEvent) throws IOException {
        even_id=top3ID[1];
ev_likes=top3ID_sum[1];
        EventObject event;
        Stage se = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage roo = FXMLLoader.load(getClass().getResource("ViewEventpop.fxml"));
        se.centerOnScreen();
        se.setScene(roo.getScene());
    }

    public void op_top3(ActionEvent actionEvent) throws IOException {
        even_id=top3ID[2];
ev_likes=top3ID_sum[2];
        EventObject event;
        Stage se = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage roo = FXMLLoader.load(getClass().getResource("ViewEventpop.fxml"));
        se.centerOnScreen();
        se.setScene(roo.getScene());
    }


    public void stylepane(MouseEvent mouseEvent) {
       {
            new animatefx.animation.ZoomIn(bck).play();
new animatefx.animation.ZoomIn(bck).play();
        }
    }

    public void evlik(MouseEvent mouseEvent) {


    }


    public void close_prog(ActionEvent actionEvent) throws IOException {
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
