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
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;

public class DelaccUController implements Initializable {
    @FXML
    private PasswordField txtconpass;
    @FXML
    private ResultSet res;
    @FXML
    private Button btndel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void clofun(javafx.scene.input.MouseEvent mouseEvent) {
        Stage s= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        s.getOwner().getScene().getWindow().setOpacity(1);
        s.close();

    }

    public void delacc(ActionEvent actionEvent) {

        String sql = "DELETE FROM tbl_user WHERE user_id =? and user_password=? ";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn4 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            PreparedStatement pss = conn4.prepareStatement(sql);
            pss.setInt(1, usr_id);
            pss.setString(2,txtconpass.getText());
             int i= pss.executeUpdate();
              if(i==1){
              JOptionPane.showMessageDialog(null, "done deleting");
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


            conn4.close();}
              else{
                  JOptionPane.showMessageDialog(null, "wrong information");
                  txtconpass.setText("");

              }


        } catch (Exception ed) {
            JOptionPane.showMessageDialog(null, "failed ");
        }








    }
}
