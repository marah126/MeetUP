package project.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController  {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView logoIMG;
    @FXML
    private ImageView userIMG;
    @FXML
    private RadioButton userRadioBTN;
    @FXML
    private RadioButton orgRadioBTN;

   /* public void selectBTN(){
        if(userRadioBTN.isSelected()){
            orgRadioBTN.setDisable(true);
        }
        if(orgRadioBTN.isSelected()){
            userRadioBTN.setDisable(true);
        }



    }*/


}