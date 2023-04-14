package project.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class tempCtrl implements Initializable {
    @FXML
    private TableColumn<Event, Integer> idcoloumn;

    @FXML
    private TableColumn<Event, String> namecoloumn;

    @FXML
    private TableView<Event> table;
    @FXML
    private TextArea area;

    @FXML
    private Button btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       PropertyValueFactory p= new PropertyValueFactory<Event, Integer>("image");
       idcoloumn.setCellFactory(p);
        PropertyValueFactory q=new PropertyValueFactory<Event,String>("name");
       namecoloumn.setCellFactory(q);
    }
    @FXML
    void handleBtn(ActionEvent event) {

        try {
           /* DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##marah","123");
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("select * from test_tbl");
            //this.area.setText("ID \t name \n");
            Event ev=new Event();
            ObservableList<Event> events=table.getItems();

                ev.setImage(1);
                ev.setName("marah");
                events.add(ev);

            table.setItems(events);

            con.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
