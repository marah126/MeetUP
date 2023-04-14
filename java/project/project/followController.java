package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;
import static project.project.ViewEventpopController.org_num;

public class followController implements Initializable {
    @FXML
    private ToggleGroup foll;

    @FXML
    private RadioButton follow;

    @FXML
    private RadioButton un_follow;

    public void confirm(ActionEvent actionEvent) {

        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection con700 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement stmt = con700.createStatement();
            if (follow.isSelected()) {
                ResultSet vfsde= stmt.executeQuery("Select * from FOLLOW where user_idd='"+usr_id+"' and orgnizer_id='"+org_num+"'");
                if(!(vfsde.next())) {
                    Statement reg = con700.createStatement();
                    String strReg = "insert into FOLLOW values('"+usr_id+"','"+org_num+"')";
                    reg.executeUpdate(strReg);
                    con700.setAutoCommit(false);
                    con700.rollback();
JOptionPane.showMessageDialog(null,"Done !");
                } else if (vfsde.next()) {
                    JOptionPane.showMessageDialog(null, "already followed !");
                }
                //btn_regeven=true;
            }
            if (un_follow.isSelected()) {

                ResultSet vfsed = stmt.executeQuery("Select * from FOLLOW where user_idd='"+usr_id+"' and orgnizer_id='"+org_num+"'");
                if((vfsed.next())) {
                    Statement reg = con700.createStatement();
                    String strReg = "Delete from  FOLLOW where user_idd='"+usr_id+"' and orgnizer_id='"+org_num+"'";
                    reg.executeUpdate(strReg);
                    con700.setAutoCommit(false);
                    con700.rollback();
JOptionPane.showMessageDialog(null,"Unfollow done !");

                } else if (!(vfsed.next())) {
                    JOptionPane.showMessageDialog(null,"already not followed !");
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


    public void toClose(ActionEvent actionEvent) {
        Stage see= (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        see.getOwner().getScene().getWindow().setOpacity(1);
        see.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
