package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

// project import
import rps.bll.game.*;
import rps.bll.player.*;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Label lblMakeYourMove;
    @FXML
    public Label lblRoundNumber;
    @FXML
    private Label lblBotName;
    @FXML
    private Label lblText;
    @FXML
    private ImageView imageViewPlayer;
    @FXML
    private ImageView imageViewAI;

    private GameState gameState;



    public GameViewController() {
        gameState = new GameState();
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Label
        lblText.setText("Welcome to RPS Game!");
        lblBotName.setText("Bot Name: " + getRandomBotName());
        lblMakeYourMove.setText("Make Your Move");
        lblRoundNumber.setText("Round:" + gameState.getRoundNumber());

        // ImageView's
        imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/rock-paper-scissors.png"))));
        imageViewAI.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/rock-paper-scissors.png"))));

    }

    @FXML
    private void btnRock(ActionEvent actionEvent) {

        imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/rock.png"))));

    }

    @FXML
    private void btnPapir(ActionEvent actionEvent) {
        imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/hand-paper.png"))));

    }

    @FXML
    private void btnScissor(ActionEvent actionEvent) {
        imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/Scissors.png"))));
    }

    @FXML
    private void btnRandomMove(ActionEvent actionEvent) {
        ArrayList<Move> randomMove = new ArrayList<>();
        randomMove.add(Move.Rock);
        randomMove.add(Move.Paper);
        randomMove.add(Move.Scissor);


        Move selectedMove = randomMove.get(new Random().nextInt(randomMove.size()));

        if (selectedMove == Move.Paper) {
            imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/hand-paper.png"))));
        }
        else if (selectedMove == Move.Scissor) {
            imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/Scissors.png"))));
        }
        else if (selectedMove == Move.Rock) {
            imageViewPlayer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/rock.png"))));
        }
    }


    private String getRandomBotName() {
        String[] botNames = new String[] {
                "R2D2",
                "Mr. Data",
                "3PO",
                "Bender",
                "Marvin the Paranoid Android",
                "Bishop",
                "Robot B-9",
                "HAL"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }
}
