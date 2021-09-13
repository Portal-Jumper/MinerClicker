package local;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameController {

    @FXML
    ImageView gemImage;

    @FXML
    void pressGem(){
        gemImage.setImage(new Image("Ruby.png"));
    }

}
