package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;
/**
 *
 * @author DELL
 */
public class SignInController implements Initializable  {
    @FXML
    public static int org_idd;



    @FXML
    private TextField passwordText ;
    @FXML
    private CheckBox checkBox ;
    @FXML
    private PasswordField txtSignIn;

    @FXML
    private RadioButton rbtn_admin;
    @FXML
    private ToggleGroup account1;

    @FXML
    private Button btnSignIn;

    @FXML
    private RadioButton rbtn_orgg;

    @FXML
    private RadioButton rbtn_userr;
    @FXML
    private TextField txtSigInEmail;

    @FXML
    private ResultSet rs;
    @FXML
    private PreparedStatement pst;
    @FXML
    private Connection conn;
    @FXML
    public static int usr_id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void Show_pass(ActionEvent actionEvent) {

        if (checkBox.isSelected()) {

            passwordText.setText(txtSignIn.getText());
            passwordText.setVisible(true);
            txtSignIn.setVisible(false);
            return;
        }
        passwordText.setText(passwordText.getText());
        txtSignIn.setVisible(true);
        passwordText.setVisible(false);


    }
    @FXML
    void open_user_dash(ActionEvent e) throws IOException {
        int r= 0 ;
        int b=0 ;
        int c=0 ;
        {
            if(rbtn_userr.isSelected()) {
                String sql = "Select * from tbl_user  where email=? and user_password=?";
                try {

                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txtSigInEmail.getText());
                    pst.setString(2, txtSignIn.getText());
                    rs = pst.executeQuery();
                    //   conn.commit();
                    if (rs.next()) {
                        String a =rs.getString("USER_ID".toString());
                        JOptionPane.showMessageDialog(null, "Success");

                        usr_id=Integer.valueOf(a);

                        r =1;
                        conn.close();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "wrong user email or password ! ");

                    }

                } catch (Exception ed) {
                    JOptionPane.showMessageDialog(null, "failed , wrong email or password");

                }

            }

            if(r==1){

                EventObject event;
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Stage root = FXMLLoader.load(getClass().getResource("UserDash.fxml"));

                stage.setScene(root.getScene());
                stage.centerOnScreen();
            }}

        if(rbtn_orgg.isSelected()) {
            String sql = "Select * from tbl_org where org_email=? and org_password=? and Accepted='yes'";
            try {

                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtSigInEmail.getText());
                pst.setString(2, txtSignIn.getText());
                rs = pst.executeQuery();
                //   conn.commit();
                if (rs.next()) {
                    String ss =rs.getString("org_id".toString());
                    JOptionPane.showMessageDialog(null, "Success");
                    org_idd=Integer.valueOf(ss);
                    userSignUpController.org_id=org_idd;
                    b =1;
                    conn.close();
                }
                else {
                    JOptionPane.showMessageDialog(null, "wrong information or not accepted yet ! ");

                }

            } catch (Exception ed) {
                ed.printStackTrace();

            }

        }

        if(b==1){

            EventObject event;
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));

            stage.setScene(root.getScene());
            stage.centerOnScreen();
        }

        if(rbtn_admin.isSelected()) {
            try {

                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
                Statement st = conn.createStatement();
                rs = st.executeQuery("Select * from tbl_admin");
                rs.next();
                String eemail=rs.getString(2);
                String pass_a=String.valueOf(rs.getInt(1));
                if((txtSigInEmail.getText().equals(eemail))&&(txtSignIn.getText().equals(pass_a))){
                    JOptionPane.showMessageDialog(null, "Success");
                    c =1;
                    conn.close();
                }
                else {
                    JOptionPane.showMessageDialog(null, "wrong user email or password ! ");

                }

            } catch (Exception ed) {
                ed.printStackTrace();

            }

        }

        if(c==1){

            EventObject event;
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("admin.fxml"));

            stage.setScene(root.getScene());
            stage.centerOnScreen();
        }}



    public void reset_password(MouseEvent mouseEvent) {
        if(rbtn_userr.isSelected())
        {


        }
    }
}