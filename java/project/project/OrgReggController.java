package project.project;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 *
 * @author DELL
 */
public class OrgReggController implements Initializable {
    @FXML
    private Button btnSignUp;
    @FXML
    private Label lbl_inalNumb;

    @FXML
    private Label confirmLabel;

    @FXML
    private Label lbl_invalEm;

    @FXML
    private Label lbl_pss_strength;


    @FXML
    private Label phoneLabel;

    @FXML
    private ProgressBar progresspr;

    @FXML
    private TextArea txt_desc;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_managernm;

    @FXML
    private TextField txtnam;

    @FXML
    private TextField txt_passw;

    @FXML
    private TextField txt_phonenum;

    @FXML
    private TextField txt_reppass;



    private String validpasss(String sd) {
        int count = 0;
        int num = 0;
        int lowe = 0;
        int upper = 0;
        int special = 0;
        for (int i = 0; i < (sd.length()); i++) {
            int ascii = (char) sd.charAt(i);
            if (ascii >= 48 && ascii <= 57) {
                num++;
            } else if (ascii >= 65 && ascii <= 90) {
                upper++;
            } else if (ascii >= 97 && ascii <= 122) {
                lowe++;
            } else {
                special++;
            }
        }
        if (num != 0) {
            count++;
        }
        if (upper != 0) {
            count++;
        }
        if (lowe != 0) {
            count++;
        }
        if (special != 0) {
            count++;
        }
        if (count == 1) {
            return "Weak !";
        } else if (count == 2) {
            return "Meduium !";
        } else if (count == 3) {
            return "Strong !";
        } else return "Very Strong !";
    }

    ArrayList<TextField> txttList = new ArrayList<TextField>();

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        progresspr.setVisible(false);
    }

    public boolean validate_email(String s) {
        String in_email = s;
        String regex = "^[a-zA-Z0-9+_.-]+@[gmail.com]+$";
        boolean result = s.matches(regex);
        return result;

    }

    public void validate_phone() {
        String s = txt_phonenum.getText();
        String regexx = "(059)([0-9]{7})";
        String regexa = "(056)([0-9]{7})";
        if (s.matches(regexx) || s.matches(regexa)) {
            lbl_inalNumb.setText("");

        } else {
            txt_phonenum.setText("");
            lbl_inalNumb.setText("invalid !");
            new animatefx.animation.FadeIn(lbl_inalNumb).play();
        }

    }

    @FXML
    void Signup_org(ActionEvent event) {
        txttList.add(txtnam);
        txttList.add(txt_managernm);
        txttList.add(txt_phonenum);
        txttList.add(txt_passw);
        txttList.add(txt_email);
        txttList.add(txt_reppass);


        if ((validate_email(txt_email.getText())) == false) {

            lbl_invalEm.setText("only gmail.com !");
            new animatefx.animation.FadeIn(lbl_invalEm).play();
            txt_email.setText("");
        } else {
            lbl_invalEm.setText("");
        }

        validate_phone();

        try {

            if ((txt_passw.getText().length() < 8 || txt_passw.getText().length() > 8)) {
                txt_passw.setText(null);
                txt_passw.setPromptText("Should have length 8");
                txt_passw.setStyle("-fx-border-color: red");
                new animatefx.animation.Tada(txt_passw).play();

            }


            if (!(txt_passw.getText()).matches(txt_reppass.getText())) {
                txt_reppass.setText(null);
                txt_reppass.setPromptText("Password missmatch");
                txt_reppass.setStyle("-fx-border-color: red");
                new animatefx.animation.Tada(txt_reppass).play();

            }
            if(txt_desc.getText().length()==0)
            {
                txt_desc.setPromptText(" ✘ Please fill  !");
                txt_desc.setStyle("-fx-border-color: red ; -fx-border-width:2;");
                new animatefx.animation.Shake(txt_desc).play();
            }


            for (TextField nodes : txttList) {
                if (nodes.getText().length() == 0) {
                    nodes.setPromptText(" ✘ Please fill  !");
                    nodes.setStyle("-fx-border-color: red ; -fx-border-width:2;");
                    new animatefx.animation.Shake(nodes).play();
                } else {
                    nodes.setStyle(null);
                }
            } //make sure not empty
        } catch (Exception v) {

        }
        int t = 7;

        if (!(txtnam.getText().isEmpty()) && !(txt_managernm.getText().isEmpty()) && !(txt_email.getText().isEmpty()) &&
                !(txt_phonenum.getText().isEmpty()) && !(txt_passw.getText().isEmpty()) && !(txt_reppass.getText().isEmpty()) && !(txt_desc.getText().isEmpty())) {

            t = 6;
        }
        if (t == 6) {


            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
                Statement reg = con.createStatement();

                String strReg = "insert into tbl_org values(1,'" + this.txtnam.getText() + "','" + this.txt_managernm.getText() + "','" + this.txt_email.getText() + "','" + this.txt_phonenum.getText() + "' , '" + this.txt_passw.getText() + "','" + this.txt_desc.getText() + "','No')";
                reg.executeUpdate(strReg);

          //      con.commit();
          //      con.close();


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ok 🙂");
            }


        }


    }
    @FXML
    void pssStrong(KeyEvent event) {
        String d =validpasss(txt_passw.getText());
        lbl_pss_strength.setText(d);
        progresspr.setVisible(true);
        if(d.matches("Weak !")) {progresspr.setProgress(0.2);}
        else if(d.matches("Meduium !")) {progresspr.setProgress(0.4);}
        else if(d.matches("Strong !")) {progresspr.setProgress(0.75);}
        else if(d.matches("Very Strong !")) {progresspr.setProgress(0.99);}
    }

}








