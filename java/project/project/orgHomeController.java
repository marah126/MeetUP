package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class orgHomeController implements Initializable {
    @FXML
    public BorderPane border1;
    @FXML
    public BorderPane border2;
    @FXML
    private Label countEvent;

    @FXML
    private Label countFeedback;

    @FXML
    private Label countFollower;

    @FXML
    private Label countLikes;

    @FXML
    private Label LabelUserName;
    @FXML
    private Label LabelName1;

    @FXML
    private Label LabelName2;

    @FXML
    private Label LabelName3;
    @FXML
    private Label LabelDate1;

    @FXML
    private Label LabelDate2;

    @FXML
    private Label LabelDate3;

    @FXML
    private ImageView logo;

    int x[][];
    int top3ID[]=new int[3];

    @FXML
    public void btnHomeAction(ActionEvent e) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
        stage.setScene(root.getScene());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(userSignUpController.org_id);
        try {



            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(" select ORG_NAME FROM tbl_org WHERE ORG_ID='" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getString(1));
            LabelUserName.setText(rs.getString(1));

            rs = stmt.executeQuery("select count (EVENT_ID) from tbl_event where ORGANIZER_ID= '" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getInt(1));
            countEvent.setText(String.valueOf(rs.getInt(1)));

            rs = stmt.executeQuery("select count (ORGNIZER_ID) from follow where ORGNIZER_ID= '" + userSignUpController.org_id + "' ");
            rs.next();
            System.out.println(rs.getInt(1));
            countFollower.setText(String.valueOf(rs.getInt(1)));

            rs = stmt.executeQuery("select count (*) from (SELECT EVENT_ID,ORGANIZER_ID  from TBL_EVENT \n" +
                    "inner join likee on tbl_event.organizer_id='" + userSignUpController.org_id + "' AND likee.event_idd = tbl_event.event_id)");
            rs.next();
            System.out.println(rs.getInt(1));
            countLikes.setText(String.valueOf(rs.getInt(1)));

            rs = stmt.executeQuery("select count (*) from (SELECT EVENT_ID,ORGANIZER_ID  from TBL_EVENT \n" +
                    "inner join REGISTRATION on tbl_event.organizer_id='" + userSignUpController.org_id + "' AND REGISTRATION.event_idd = tbl_event.event_id)");
            rs.next();
            System.out.println(rs.getInt(1));
            countFeedback.setText(String.valueOf(rs.getInt(1)));

            rs=stmt.executeQuery("select count (distinct event_id) from (SELECT EVENT_ID,ORGANIZER_ID  from TBL_EVENT \n" +
                    "inner join likee on tbl_event.organizer_id='"+userSignUpController.org_id+"'  AND likee.event_idd = tbl_event.event_id)");

            rs.next();
            int size=rs.getInt(1);
            System.out.println(size);
            x=new int[size][2];
            if(size !=0) {
                rs = stmt.executeQuery("select distinct event_id from (SELECT EVENT_ID,ORGANIZER_ID  from TBL_EVENT \n" +
                        "inner join likee on tbl_event.organizer_id='" + userSignUpController.org_id + "'  AND likee.event_idd = tbl_event.event_id)");

            for(int i=0 ; rs.next();i++){
                x[i][0]= rs.getInt(1);
            }
            for (int i=0;i< x.length;i++) {

                rs = stmt.executeQuery("select count (EVENT_IDD) FROM likee where event_idd='" + x[i][0] + "' ");
                rs.next();
                x[i][1]= rs.getInt(1);
            }
            for (int i=0; i<x.length ;i++){
                System.out.print(x[i][0]+" "+x[i][1]+"\n");

            }

            for(int j=0;j<3;j++) {
                int max=-1;
                int index=0;
                for (int i = 0; i < x.length; i++) {
                    if (x[i][1] > max) {
                        max = x[i][1];
                        index = i;
                    }
                }
                x[index][1]=-10;
                top3ID[j]=x[index][0];
            }
           for (int i=0;i<3;i++){
               System.out.print(top3ID[i]+" ");
           }
                rs = stmt.executeQuery("SELECT EVENT_NAME from tbl_event where EVENT_ID= '" + top3ID[0] + "'");
                rs.next();
                LabelName1.setText(rs.getString(1));
                rs = stmt.executeQuery("SELECT EVENT_NAME from tbl_event where EVENT_ID= '" + top3ID[1] + "'");
                rs.next();
                LabelName2.setText(rs.getString(1));
                rs = stmt.executeQuery("SELECT EVENT_NAME from tbl_event where EVENT_ID= '" + top3ID[2] + "'");
                rs.next();
                LabelName3.setText(rs.getString(1));
                rs = stmt.executeQuery("SELECT EVENT_DATE from tbl_event where EVENT_ID= '" + top3ID[0] + "'");
                rs.next();
                LabelDate1.setText(String.valueOf(rs.getDate(1)));
                rs = stmt.executeQuery("SELECT EVENT_DATE from tbl_event where EVENT_ID= '" + top3ID[1] + "'");
                rs.next();
                LabelDate2.setText(String.valueOf(rs.getDate(1)));
                rs = stmt.executeQuery("SELECT EVENT_DATE from tbl_event where EVENT_ID= '" + top3ID[2] + "'");
                rs.next();
                LabelDate3.setText(String.valueOf(rs.getDate(1)));

                con.setAutoCommit(false);
                con.rollback();
                con.close();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void creatAction(ActionEvent e) throws IOException {
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
        try{
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            InputStream input=new FileInputStream(new File("Tree.jrxml"));
            JasperDesign jd= JRXmlLoader.load(input);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jp= JasperFillManager.fillReport(jr, null, con);
            OutputStream output=new FileOutputStream(new File("bill.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            input.close();


        }
        catch (Exception e){

        }

    }

    @FXML
    void btnStatisticsHandle(ActionEvent e)throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    void btnUserProfileHandle(ActionEvent e) throws IOException{
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("myProfile.fxml"));
        stage.setScene(root.getScene());

    }
}


