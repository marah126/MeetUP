package project.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class itemController {
    @FXML
    private Label Date;

    @FXML
    private Label Name;

    @FXML
    private Label Place;

    @FXML
    private ImageView image;

    public void setData(Event ev){
       // Image im=new Image(getClass().getResourceAsStream(ev.getImage()));
      //  image.setImage(im);
        Name.setText(ev.getTitle());
        Date.setText(ev.getDate());
        Place.setText(ev.getLocation());

    }

}
