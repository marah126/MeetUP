package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class admin1CTRL implements Initializable {

    @FXML
    private Label LabelName1;

    @FXML
    private Label LabelName2;

    @FXML
    private Label LabelName3;

    @FXML
    private Label LabelUserName;

    @FXML
    private Label Labelcount1;

    @FXML
    private Label Labelcount2;

    @FXML
    private Label Labelcount3;

    @FXML
    private Label countEvent;

    @FXML
    private Label orgCount;

    @FXML
    private Label userCount;

    public void handle(ActionEvent e) throws IOException {

        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("admin2.fxml"));
        stage.setScene(root.getScene());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int arr[][];
        int top3[]=new int[3];
        try {


            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM tbl_org WHERE accepted='yes'");
            rs.next();
            orgCount.setText(String.valueOf(rs.getInt(1)));

            rs=stmt.executeQuery("SELECT COUNT(*) FROM tbl_event");
            rs.next();
            countEvent.setText(String.valueOf(rs.getInt(1)));


            rs=stmt.executeQuery("SELECT COUNT(*) FROM tbl_user");
            rs.next();
            userCount.setText(String.valueOf(rs.getInt(1)));

            rs=stmt.executeQuery("SELECT  count(DISTINCT event_idd) FROM likee");
            rs.next();
            int count= rs.getInt(1);
            arr=new int[count][2];

            rs=stmt.executeQuery("SELECT  DISTINCT event_idd FROM likee");
            int z=0;
            while (rs.next()){
                arr[z][0]=rs.getInt(1);
                z++;
            }
            for (int i=0;i<arr.length;i++) {
                rs = stmt.executeQuery("SELECT  COUNT(event_idd) FROM likee WHERE event_idd= '" + arr[i][0] + "' ");
                rs.next();
                arr[i][1]=rs.getInt(1);

            }
            for(int j=0;j<3;j++) {
                int max=-1;
                int index=0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i][1] > max) {
                        max = arr[i][1];
                        index = i;
                    }
                }
                arr[index][1]=-10;
                top3[j]=arr[index][0];
            }
            rs = stmt.executeQuery("SELECT EVENT_NAME from tbl_event where EVENT_ID= '" + top3[0] + "'");
            rs.next();
            LabelName1.setText(rs.getString(1));

            rs = stmt.executeQuery("SELECT EVENT_NAME from tbl_event where EVENT_ID= '" + top3[1] + "'");
            rs.next();
            LabelName2.setText(rs.getString(1));

            rs = stmt.executeQuery("SELECT EVENT_NAME from tbl_event where EVENT_ID= '" + top3[2] + "'");
            rs.next();
            LabelName3.setText(rs.getString(1));

            rs = stmt.executeQuery("SELECT EVENT_DATE from tbl_event where EVENT_ID= '" + top3[0] + "'");
            rs.next();
            Labelcount1.setText(String.valueOf(rs.getDate(1)));

            rs = stmt.executeQuery("SELECT EVENT_DATE from tbl_event where EVENT_ID= '" + top3[1] + "'");
            rs.next();
            Labelcount2.setText(String.valueOf(rs.getDate(1)));

            rs = stmt.executeQuery("SELECT EVENT_DATE from tbl_event where EVENT_ID= '" + top3[2] + "'");
            rs.next();
            Labelcount3.setText(String.valueOf(rs.getDate(1)));



            con.setAutoCommit(false);
            con.rollback();
            con.close();


        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void Report1(ActionEvent actionEvent) {
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            InputStream input=new FileInputStream(new File("Tree.jrxml"));
            JasperDesign jd= JRXmlLoader.load(input);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jp= JasperFillManager.fillReport(jr, null, con);
            OutputStream output=new FileOutputStream(new File("org.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            input.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void Report2(ActionEvent actionEvent) {
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            InputStream input=new FileInputStream(new File("events.jrxml"));
            JasperDesign jd= JRXmlLoader.load(input);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jp= JasperFillManager.fillReport(jr, null, con);
            OutputStream output=new FileOutputStream(new File("event.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            input.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void Report3(ActionEvent actionEvent) {
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            InputStream input=new FileInputStream(new File("user.jrxml"));
            JasperDesign jd= JRXmlLoader.load(input);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jp= JasperFillManager.fillReport(jr, null, con);
            OutputStream output=new FileOutputStream(new File("user.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            input.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}