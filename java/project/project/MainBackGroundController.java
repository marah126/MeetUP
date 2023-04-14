package project.project;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author DELL
 */
public class MainBackGroundController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private VBox vbox ;
    private Parent fxml;
@FXML
private RadioButton rbtn_user,rbtn_org;

@FXML
private Alert alert ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /* circle image */

        /*transition*/
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*17);
        t.play();
        t.setOnFinished((e) -> {


                try {
                    fxml = FXMLLoader.load(getClass().getResource("Sign_In.fxml"));
                    vbox.getChildren().removeAll();
                    vbox.getChildren().setAll(fxml);
                } catch (IOException es) {

                }

                }
        );



    }
    @FXML
    private void op_sign_in(ActionEvent ev)
    {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*17);
        t.play();
        t.setOnFinished((e) ->{

                    try{
                        fxml=FXMLLoader.load(getClass().getResource("Sign_In.fxml"));
                        vbox.getChildren().removeAll();
                        vbox.getChildren().setAll(fxml);
                    }catch(IOException es)
                    {

                    }
                }
        );
    }

    @FXML
    private void op_sign_up(ActionEvent ev)
    {       if(rbtn_user.isSelected()) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {

                    try {
                        fxml = FXMLLoader.load(getClass().getResource("UserRegC.fxml"));
                        vbox.getChildren().removeAll();
                        vbox.getChildren().setAll(fxml);
                    } catch (IOException es) {
                        es.printStackTrace();

                    }
                }
        );
    }
    else if (rbtn_org.isSelected()) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {

                    try {
                        fxml = FXMLLoader.load(getClass().getResource("OrgReggC.fxml"));
                        vbox.getChildren().removeAll();
                        vbox.getChildren().setAll(fxml);
                    } catch (IOException es) {
                        es.printStackTrace();

                    }
                }
        );
    }
    else if(!(rbtn_org.isSelected())&& !(rbtn_user.isSelected()))
    {

        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning ! ");
        alert.setHeaderText(null);
        alert.setContentText("Please select one accout type in order to sign up !");
        alert.showAndWait();


    }
    }


    public void close(MouseEvent mouseEvent) {

        Stage s= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        s.close();

    }

    public void minimize(MouseEvent mouseEvent) {
        Stage s= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        s.setIconified(true);
    }


}


