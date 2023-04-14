package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;
import static project.project.UserDashController.even_id;

public class makeFBController implements Initializable {


    @FXML
    private Button ssfb;

    @FXML
    private TextArea text_fb; @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void sendfb(javafx.event.ActionEvent actionEvent) {
        String sql = "Select * from Registration  where user_idd=? and event_idd=?";
        String sqll="UPDATE Registration SET FEEDBACKK=? where user_idd=? and event_idd=?";
        int fb_chk= 0;
        int a=0 ;
        int b=0;
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn88 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
             PreparedStatement psw = conn88.prepareStatement(sql);
            psw.setString(1, String.valueOf(usr_id));
            psw.setString(2, String.valueOf(even_id));
           ResultSet rso = psw.executeQuery();
            //   conn.commit();
            if (rso.next()) {
               a=rso.getInt("event_idd");
               b=rso.getInt("user_idd");
               fb_chk =1;
           //     conn88.close();
                PreparedStatement pswp = conn88.prepareStatement(sqll);
                pswp.setString(1,text_fb.getText());
                pswp.setInt(2, b);
                pswp.setInt(3,a);
                pswp.executeUpdate();

                Alert d = new Alert(Alert.AlertType.INFORMATION);

               d.setHeaderText("Done ! ");
                //Adding buttons to the dialog pane
               d.showAndWait();
                conn88.setAutoCommit(false);
                conn88.rollback();
                conn88.close();
            }
            else {
                if(fb_chk==0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("can't make feedback to this event");
                //Setting the content of the dialog
                alert.setContentText("In order to make feed back to event , you should register first");
                //Adding buttons to the dialog pane
                alert.showAndWait();}
            }


        } catch (Exception ed) {


        }


    }


    public void closeFBB(ActionEvent actionEvent) {
        Stage see= (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        see.getOwner().getScene().getWindow().setOpacity(1);
        see.close();
    }
}
