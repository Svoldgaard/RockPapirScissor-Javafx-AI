package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Label lblText;
    @FXML
    private ImageView imageViewPlayer;
    @FXML
    private ImageView imageViewAI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lblText.setText("Welcome to RPS Game!");
        // TODO
    }

    @FXML
    private void btnRock(ActionEvent actionEvent) {
    }

    @FXML
    private void btnPapir(ActionEvent actionEvent) {
    }

    @FXML
    private void btnScissor(ActionEvent actionEvent) {
    }

    @FXML
    private void btnRandomMove(ActionEvent actionEvent) {
    }
}
