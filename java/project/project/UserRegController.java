package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class UserRegController implements Initializable{

@FXML
private Label lbl_pass;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton rbtn_female;
    @FXML
    private RadioButton rbtn_male;

    @FXML
    private TextField txt_firstname;
    @FXML
    private TextField txt_lastname;
    @FXML
    private PasswordField txt_userConfirmpass;
    @FXML
    private TextField txt_useremail;

    @FXML
    private CheckBox chk_show;

    @FXML
    private TextField txt_pss;
    @FXML
    private PasswordField txt_userpass;
    @FXML
    private TextField txt_userphone;

    @FXML
    private Label lblError;

    @FXML
    private Label lblvalemail;
    @FXML
    private Label lblvalphone;
    @FXML
    private Label lblvalphone1;
    @FXML
    private ChoiceBox<String> choice_faculty;

@FXML
private ProgressBar progressar;
    @FXML
    private int a = 0 ;
    private String[] faculty = {"Faculty of Economics and Social Sciences",
            "Faculty of Agriculture and Veterinary Medicine",
            "Faculty of Sharia", "Faculty of Medicine and Health Sciences", "Faculty of Science", "Faculty of Humanities",
            "Faculty of Educational Sciences", "Faculty of Fine Arts", "Faculty of Law", "Faculty of Engineering and Information Technology"
    };


    private String validpass(String sd)
    {
        int count = 0;
        int num =0;
        int lowe = 0;
        int upper =0;
        int special =0;
        for(int i=0 ; i<(sd.length()); i++)
        {
            int ascii=(char)sd.charAt(i);
            if(ascii>=48 &&ascii<=57)
            {
                num++;
            } else if (ascii>=65 && ascii<=90)
            {upper++;
            } else if (ascii>=97&& ascii<=122){
                lowe++;
            } else {special++;}
        }
        if(num!=0) {count++;}
        if(upper!=0) {count++;}
        if(lowe!=0) {count++;}
        if(special!=0) {count++;}
        if(count==1) {return "Weak !";}
        else if( count==2) {return "Meduium !";}
        else if (count==3){return "Strong !";}
        else return "Very Strong !";
    }




    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        choice_faculty.getItems().addAll(faculty);
        progressar.setVisible(false);
    }
    ArrayList<TextField> txtList = new ArrayList<>();
//check if all field are fill or not

    public boolean validate_email(String s)
    {
        String in_email = s ;
        String regex = "^[a-zA-Z0-9+_.-]+@[gmail.com]+$";
        boolean result = s.matches(regex);
        return result ;

    }
    public void validate_phone()
    {
        String s= txt_userphone.getText();
        String regexx = "(059)([0-9]{7})";
        String regexa = "(056)([0-9]{7})";
        if( s.matches(regexx)||s.matches(regexa))
        {
            lblvalphone.setText("");

        }
else {   txt_userphone.setText("");
            lblvalphone.setText("invalid !");
            new animatefx.animation.FadeIn(lblvalphone).play();
}

    }


    public void check_all_fields(ActionEvent actionEvent) {
 txtList.add(txt_firstname);
        txtList.add(txt_lastname);
        txtList.add(txt_useremail);
        txtList.add(txt_userpass);
        txtList.add(txt_userphone);
        txtList.add(txt_userConfirmpass);

        if ((validate_email(txt_useremail.getText())) == false ) {

            lblvalemail.setText("only gmail.com !");
            new animatefx.animation.FadeIn(lblvalemail).play();
            txt_useremail.setText("");
        }
        else {lblvalemail.setText("");}

      validate_phone();

        try {
            if (!rbtn_female.isSelected() && !rbtn_male.isSelected()) {
                lblvalphone1.setText("please select");
                new animatefx.animation.FadeIn(lblvalphone1).play();

            } else {
                lblvalphone1.setText("");
            }


            if (choice_faculty.getSelectionModel().isEmpty()) {
                new animatefx.animation.Tada(choice_faculty).play();
                choice_faculty.setStyle("-fx-border-color: red");

            }// make sure faculty is selected
            if ((txt_userpass.getText().length() < 8 || txt_userpass.getText().length() > 8)) {
                txt_userpass.setText(null);
                txt_userpass.setPromptText("Should have length 8");
                txt_userpass.setStyle("-fx-border-color: red");
                new animatefx.animation.Tada(txt_userpass).play();

            }

{

}

            if (!(txt_userpass.getText()).matches(txt_userConfirmpass.getText())) {
                txt_userConfirmpass.setText(null);
                txt_userConfirmpass.setPromptText("Password missmatch");
                txt_userConfirmpass.setStyle("-fx-border-color: red");
                new animatefx.animation.Tada(txt_userConfirmpass).play();

            }


            for (TextField nodes : txtList) {
                if (nodes.getText().length() == 0) {
                    nodes.setPromptText(" ✘ Please fill  !");
                    nodes.setStyle("-fx-border-color: red ; -fx-border-width:2;");
                    new animatefx.animation.Shake(nodes).play();
                } else {
                    nodes.setStyle(null);
                }
            } //make sure not empty
        }catch (Exception v) {

        }
int t=7;

        if(!(txt_firstname.getText().isEmpty())&&!(txt_lastname.getText().isEmpty())&&!(txt_useremail.getText().isEmpty())&&
                !(txt_userphone.getText().isEmpty())&&(rbtn_male.isSelected()||rbtn_female.isSelected())&&!(choice_faculty.getSelectionModel().isEmpty())&&
                !(txt_userpass.getText().isEmpty())&&!(txt_userConfirmpass.getText().isEmpty())) {

          t=6;
        }
    if(t==6) {
        String gen = null;
        if (rbtn_female.isSelected()) {
            gen = "F";
        } else if (rbtn_male.isSelected()) {
            gen = "M";
        }

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##project", "123456");
            Statement reg = con.createStatement();
          String strReg = "insert into tbl_user values(1,'" + this.txt_firstname.getText() + "','" + this.txt_lastname.getText() + "','" + this.txt_useremail.getText() + "','" + this.txt_userphone.getText() + "','" + this.txt_userpass.getText() + "', '" + this.choice_faculty.getSelectionModel().getSelectedItem() + "','"+gen+"')";
            reg.executeUpdate(strReg);

         //   con.commit();
       //     con.close();


        } catch (Exception e) {
           e.printStackTrace();
        }



    }




        }//end button


    public void check_pass_strength(KeyEvent keyEvent) {
        String d =validpass(txt_userpass.getText());
        lbl_pass.setText(d);
        progressar.setVisible(true);
        if(d.matches("Weak !")) {progressar.setProgress(0.2);}
      else if(d.matches("Meduium !")) {progressar.setProgress(0.4);}
       else if(d.matches("Strong !")) {progressar.setProgress(0.75);}
       else if(d.matches("Very Strong !")) {progressar.setProgress(0.99);}

    }

    public void show_paaword(ActionEvent actionEvent) {
        if (chk_show.isSelected()) {

            txt_pss.setText(txt_userpass.getText());
            txt_pss.setVisible(true);
            txt_userpass.setVisible(false);
            return;
        }
        txt_pss.setText(txt_pss.getText());
        txt_userpass.setVisible(true);
        txt_pss.setVisible(false);
    }

    public void txt_strength(KeyEvent keyEvent) {
        String d =validpass(txt_pss.getText());
        lbl_pass.setText(d);
    }
}


        //ORACLE




