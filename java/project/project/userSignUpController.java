package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventObject;
import java.util.ResourceBundle;

public class userSignUpController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    ImageView userIMG;
    @FXML
    public  static int org_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        org_id=10;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##project","123456");
            Statement stmt=con.createStatement();
            String i="12/6/2001";
            String n=name.getText();
            //String st="INSERT INTO tbl_event VALUES(12,'eid',4,null,100,150,'"+i+"','1:00','2:00',null ,'on going')";
            //stmt.executeUpdate("INSERT INTO tbl_event VALUES(12,'eid',4,null,100,150,'"+i+"','8:00','9:00',null ,'on going')");

            con.setAutoCommit(false);

            con.rollback();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @FXML
    private void handle(ActionEvent e)throws IOException{
        org_id=22;
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("orgnizationHome.fxml"));
        stage.setScene(root.getScene());


    }
}
