package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static project.project.SignInController.usr_id;

public class UserAccViewController implements Initializable {
    @FXML
    private ChoiceBox<String> choice_e;
    @FXML
    private RadioButton rbtn_ef;

    @FXML
    private RadioButton rbtn_em;


    @FXML
    private PasswordField txt_epass;

    @FXML
    private TextField txteemail;

    @FXML
    private TextField txtefname;

    @FXML
    private TextField txtelastname;

    @FXML
    private TextField txtephone;


    private String[] facultyy = {"Faculty of Economics and Social Sciences",
            "Faculty of Agriculture and Veterinary Medicine",
            "Faculty of Sharia", "Faculty of Medicine and Health Sciences", "Faculty of Science", "Faculty of Humanities",
            "Faculty of Educational Sciences", "Faculty of Fine Arts", "Faculty of Law", "Faculty of Engineering and Information Technology"
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice_e.getItems().addAll(facultyy);
        String sql ="Select * from tbl_user where USER_ID=?";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project","123456");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, String.valueOf(usr_id));
            ResultSet rd = ps.executeQuery();

            if(rd.next()) {
                txtefname.setText(rd.getString(2));
                txtelastname.setText(rd.getString(3));
                txteemail.setText(rd.getString(4));
                txtephone.setText(rd.getString(5));
                txt_epass.setText((rd.getString(6)));
                String see = (rd.getString(8));
                String cll = (rd.getString(7));
                choice_e.setValue(cll);

                if (see.matches("F")) {
                    rbtn_ef.setSelected(true);

                } else if (see.matches("M")) {
                    rbtn_em.setSelected(false);
                }
                con.close();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    @FXML
    public void closw(javafx.scene.input.MouseEvent mouseEvent) {
        Stage s= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        s.getOwner().getScene().getWindow().setOpacity(1);
        s.close();

    }



    public void btnupdate(ActionEvent actionEvent) {
        String genr = null;
        if (rbtn_ef.isSelected()) {
            genr = "F";
        } else if (rbtn_em.isSelected()) {
            genr = "M";
        }
        String sql = "UPDATE tbl_user SET f_name=?, l_name=?, email=?, phone=? ,user_password=?, collge=? ,gender=? WHERE user_id=?";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           Connection conn3 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            PreparedStatement pss = conn3.prepareStatement(sql);
           pss.setString(1,txtefname.getText());
           pss.setString(2,txtelastname.getText());
            pss.setString(3,txteemail.getText());
            pss.setString(4,txtephone.getText());
            pss.setString(5,txt_epass.getText());
            pss.setString(6,choice_e.getSelectionModel().getSelectedItem());
            pss.setString(7,genr);
            pss.setInt(8, usr_id);
            pss.executeUpdate();
            conn3.close();

        } catch (Exception ed) {
            ed.printStackTrace();
        }












    }
}
