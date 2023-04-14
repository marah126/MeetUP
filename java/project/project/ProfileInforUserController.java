package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;

public class ProfileInforUserController implements Initializable {


    @FXML
    private Label lo;
    @FXML
    private Label lblcoll;
    @FXML
    private Button btnSignOut;
    @FXML
    private Label lblemailu;

    @FXML
    private Label lblgenu;

    @FXML
    private Label lblpassu;

    @FXML
    private Button btnedit;
    @FXML
    private Button btndel;
    @FXML
    private Label lblphoneu;

    @FXML
    private Label lbl_f;

    @FXML
    private Label lbl_reg;

    @FXML
    void op_prof1(ActionEvent er) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) er.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("profileInforUser.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());
    }

    @FXML
    void op_hpme1(ActionEvent er) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) er.getSource()).getScene().getWindow();
        Stage d = stage;
        Stage root = FXMLLoader.load(getClass().getResource("UserDash.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());
    }
    @FXML
    void opcelender1(ActionEvent er) throws IOException {
        EventObject event;
        Stage stage = (Stage) ((Node) er.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("UerViewMyEvent.fxml"));
        stage.centerOnScreen();
        stage.setScene(root.getScene());

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql ="Select * from tbl_user where USER_ID=?";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project","123456");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, String.valueOf(usr_id));
            ResultSet rd = ps.executeQuery();
            rd.next();
            String first = rd.getString(1);
            lo.setText((rd.getString(2))+" "+(rd.getString(3)));
lblemailu.setText((rd.getString(4)));
            lblphoneu.setText((rd.getString(5)));
            lblpassu.setText((rd.getString(6)));
            lblcoll.setText((rd.getString(8)));
            String see=(rd.getString(7));
            if(see.matches("F")){
            lblgenu.setText("Female");}
            else if (see.matches("M")){
                lblgenu.setText("Male");}

            Statement stmr=con.createStatement();
            ResultSet reu=stmr.executeQuery("Select count (distinct event_idd) from registration where user_idd='"+usr_id+"'");
            if(reu.next()) {
                int reg_ev = reu.getInt(1);
                lbl_reg.setText(String.valueOf(reg_ev) + " Events");

            }

            Statement stmre=con.createStatement();
            ResultSet reue=stmre.executeQuery("Select count (distinct orgnizer_id) from FOLLOW where user_idd='"+usr_id+"'");
            if(reue.next()) {
                int org_ev = reu.getInt(1);
                lbl_reg.setText(String.valueOf(org_ev)+ " Following");

            }

            con.setAutoCommit(false);
            con.rollback();
            con.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }
    @FXML
    private void opnedit(ActionEvent eventt) throws Exception {
        Stage st;
        Parent ro;

        if(eventt.getSource()==btnedit)
        {
            st=new Stage();
            ro=FXMLLoader.load(getClass().getResource("userAccView.fxml"));
            st.setScene(new Scene(ro));
            st.initModality(Modality.APPLICATION_MODAL);
            st.initStyle(StageStyle.UNDECORATED);
            st.initOwner(btnedit.getScene().getWindow());
            btnedit.getScene().getWindow().setOpacity(0.6);
            st.centerOnScreen();
            st.showAndWait();

        }


    }


    public void opendel(ActionEvent ass) throws IOException {
        Stage st;
        Parent ro;

        if(ass.getSource()==btndel)
        {
            st=new Stage();
            ro=FXMLLoader.load(getClass().getResource("delaccU.fxml"));
            st.setScene(new Scene(ro));
            st.initModality(Modality.APPLICATION_MODAL);
            st.initStyle(StageStyle.UNDECORATED);
            st.initOwner(btnedit.getScene().getWindow());
            btnedit.getScene().getWindow().setOpacity(0.6);
            st.centerOnScreen();
            st.showAndWait();

        }
    }

    public void clooseProg(ActionEvent actionEvent) throws IOException {
        Stage st;
        Parent ro;
            st=new Stage();
            ro=FXMLLoader.load(getClass().getResource("MainBackGroung.fxml"));
            st.setScene(new Scene(ro));
            st.initModality(Modality.APPLICATION_MODAL);
            st.initStyle(StageStyle.UNDECORATED);
            st.initOwner(btnedit.getScene().getWindow());
            btnedit.getScene().getWindow().setOpacity(0.6);
            st.centerOnScreen();
            st.showAndWait();

    }
}
