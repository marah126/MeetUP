package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;
import static project.project.UserDashController.ev_likes;
import static project.project.UserDashController.even_id;

public class ViewEventpopController implements Initializable {
    @FXML
    private Button btn_commeve;
    @FXML
    private CheckBox lllik;
    @FXML
    private ToggleButton btn_regeven;

    @FXML
    private ToggleButton btnlikeeve;

    @FXML
    private Label lbl_evecap;

    @FXML
    private Label lbl_evedate;

    @FXML
    private Label lbl_evelikes;

    @FXML
    private Label lbl_eveloc;

    @FXML
    private Label lbl_evename;

    @FXML
    private Label lbl_eventreg;

    @FXML
    private Label lbl_eveorg1;

    @FXML
    private Label lbl_evepri;

    @FXML
    private Label lbl_evetime;
    public boolean selected_btn=false ;
    public static int org_num ;
    @FXML
    private TextArea text_evedesc;
    public void make_like(ActionEvent actionEvent) {
        //  int cou = -1;
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con70 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con70.createStatement();
            if (btnlikeeve.isSelected()==true) {
                btnlikeeve.setStyle("-fx-background-color: black"); // if i made like
                ResultSet vfs = stmt.executeQuery("Select user_idd from LIKEE where EVENT_IDD='"+even_id+"' and USER_IDD='"+usr_id+"'");
                if(!(vfs.next())) {
                    Statement reg = con70.createStatement();
                    String strReg = "insert into likee values('"+usr_id+"','"+even_id+"')";
                    reg.executeUpdate(strReg);
                    con70.setAutoCommit(false);
                    con70.rollback();
                    selected_btn=true;
                    ev_likes++;
                } else if (vfs.next()) {
                    JOptionPane.showMessageDialog(null, "hhhhhh");
                }

            }
            if (btnlikeeve.isSelected()==false) {
                btnlikeeve.setStyle("-fx-background-color: green"); //if not liked ( green)
                ResultSet vfsee = stmt.executeQuery("Select * from LIKEE where EVENT_IDD='"+even_id+"' and USER_IDD='"+usr_id+"'");
                if((vfsee.next())) {
                    Statement reg = con70.createStatement();
                    String strReg = "Delete from LIKEE where EVENT_IDD='"+even_id+"' and USER_IDD='"+usr_id+"'";
                    selected_btn=true;
                    reg.executeUpdate(strReg);
                    con70.setAutoCommit(false);
                    con70.rollback();
                    ev_likes--;
                } else if (!(vfsee.next())) {

                }
            }
            con70.close();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnlikeeve.setSelected(selected_btn);
        String sql = "Select * from tbl_event where EVENT_ID=?";
        String sqll="Select * from tbl_place where place_id=?";
        String sqlll="Select * from tbl_org where org_id=?";
        //like flag check
        {
            if (selected_btn == false) {
                btnlikeeve.setSelected(false);
                btnlikeeve.setStyle("-fx-background-color: green");

            } else if (selected_btn == true) {
                btnlikeeve.setStyle("-fx-background-color: black");
            }
        }
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con77 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            PreparedStatement ps = con77.prepareStatement(sql);
            ps.setString(1, String.valueOf(even_id));
            ResultSet rd = ps.executeQuery();
            String time = null;
            String pricee = null;
            StringBuilder sb = null;
            String desc = null;
            String loc = null;
            if (rd.next()) {
                if (rd.getInt("Price") == 0) {
                    pricee = "Free";
                } else if (rd.getInt("Price") != 0) {
                    pricee = String.valueOf(rd.getInt("PRICE"));
                }
                lbl_evename.setText(rd.getString("event_name"));
                org_num = Integer.parseInt(rd.getString("organizer_id"));
                loc = rd.getString("EVENT_LOCATION");
                lbl_evepri.setText("Price : " + pricee);
                time = rd.getString("START_TIME") + " - " + rd.getString("END_TIME");
                lbl_evetime.setText("At " + time);
                lbl_evecap.setText("Maximum capacity =" + String.valueOf(rd.getInt("EVENT_CAPACITY")));
                lbl_evedate.setText(String.valueOf(rd.getDate("EVENT_DATE")));
                //     text_evedesc.setText(rd.getString("EVENT_DESC"));
                lbl_evelikes.setText((String.valueOf(ev_likes)) + " LIKES");
            }
            //pringing the place name from the loction number
            PreparedStatement d = con77.prepareStatement(sqll);
            d.setString(1, loc);
            ResultSet rdd = d.executeQuery();
            rdd.next();
            String plc_name = rdd.getString("place_name");
            {

            lbl_eveloc.setText("Location : " + plc_name + " - " + loc);
            //pringing the org  name from the org id
            PreparedStatement ddb = con77.prepareStatement(sqlll);
            ddb.setInt(1, org_num);
            ResultSet rde = ddb.executeQuery();
            rde.next();
            String Org_nameV = rde.getString("org_name");
            //setting the label organization name
            lbl_eveorg1.setText("Organized by: " + " " + Org_nameV);
        }//setting the label location

            {
                Statement stmr=con77.createStatement();
                ResultSet reu=stmr.executeQuery("Select count (distinct user_idd) from registration where event_idd='"+UserDashController.even_id+"'");
                if(reu.next()) {
                    int reg_people = reu.getInt(1);
                    lbl_eventreg.setText(String.valueOf(reg_people) + " people are going");
                }

            }//setting the register location







               /* String see = (rd.getString(8));
                String cll = (rd.getString(7));
                choice_e.setValue(cll);

                if (see.matches("F")) {
                    rbtn_ef.setSelected(true);

                } else if (see.matches("M")) {
                    rbtn_em.setSelected(false);
                }*/


            con77.setAutoCommit(false);
            con77.rollback();
            con77.close();

        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }


    public void close_event_pop(ActionEvent acti) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) acti.getSource()).getScene().getWindow();
        Stage d = stage;
        Stage root = FXMLLoader.load(getClass().getResource("UserDash.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());


    }

    public void op_homee3(ActionEvent actionEvent) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage d = stage;
        Stage root = FXMLLoader.load(getClass().getResource("UserDash.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());
    }

    public void make_fb(ActionEvent actionEvent) throws IOException {
        Stage st;
        Parent roe;

        if (actionEvent.getSource() == btn_commeve) {
            st = new Stage();
            roe = FXMLLoader.load(getClass().getResource("MakeFB.fxml"));
            st.setScene(new Scene(roe));
            st.initModality(Modality.APPLICATION_MODAL);
            st.initStyle(StageStyle.UNDECORATED);
            st.initOwner(btn_commeve.getScene().getWindow());
            btn_commeve.getScene().getWindow().setOpacity(0.6);
            st.centerOnScreen();
            st.showAndWait();
        }
    }



    public void Reg_event(ActionEvent actionEvent) {
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con700 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con700.createStatement();

            if (btn_regeven.isSelected()==true) {
                btn_regeven.setStyle("-fx-background-color: black");
                ResultSet vfsde= stmt.executeQuery("Select user_idd from REGISTRATION where EVENT_IDD='"+even_id+"' and USER_IDD='"+usr_id+"'");
                if(!(vfsde.next())) {
                    Statement reg = con700.createStatement();
                    String strReg = "insert into REGISTRATION values('"+usr_id+"','"+even_id+"',' ')";
                    reg.executeUpdate(strReg);
                    con700.setAutoCommit(false);
                    con700.rollback();
                    con700.commit();

                } else if (vfsde.next()) {
                    JOptionPane.showMessageDialog(null, "hhhhhh");
                }
                //btn_regeven=true;
            }
            if (btn_regeven.isSelected()==false) {
                btn_regeven.setStyle("-fx-background-color: green");
                ResultSet vfsed = stmt.executeQuery("Select user_idd from REGISTRATION where EVENT_IDD='"+even_id+"' and USER_IDD='"+usr_id+"'");
                if((vfsed.next())) {
                    Statement reg = con700.createStatement();
                    String strReg = "Delete from REGISTRATION where EVENT_IDD='"+even_id+"' and USER_IDD='"+usr_id+"'";
                    reg.executeUpdate(strReg);
                    con700.setAutoCommit(false);
                    con700.rollback();


                } else if (!(vfsed.next())) {

                }
               // selected_btn=false;
            }
            con700.close();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }


    }

    public void follow_organizer(MouseEvent mouseEvent) throws IOException {
        Stage st;
        Parent roe;
        if(mouseEvent.getSource()==lbl_eveorg1){
            st = new Stage();
            roe = FXMLLoader.load(getClass().getResource("follow.fxml"));
            st.setScene(new Scene(roe));
            st.initModality(Modality.APPLICATION_MODAL);
            st.initStyle(StageStyle.UNDECORATED);
            st.initOwner(lbl_eveorg1.getScene().getWindow());
           lbl_eveorg1.getScene().getWindow().setOpacity(0.6);
            st.centerOnScreen();
            st.showAndWait();}
        }

    }









