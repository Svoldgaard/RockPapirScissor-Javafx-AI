package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

// Project imports
import rps.bll.game.*;
import rps.bll.player.*;

/**
 * JavaFX Controller for the Rock-Paper-Scissors game
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Label lblPlayerPoints;
    @FXML
    private Label lblAIPoints;
    @FXML
    private TextArea textAreaResulte;
    @FXML
    private Label lblMakeYourMove;
    @FXML
    private Label lblRoundNumber;
    @FXML
    private Label lblBotName;
    @FXML
    private Label lblText;
    @FXML
    private ImageView imageViewPlayer;
    @FXML
    private ImageView imageViewAI;

    private IPlayer human;
    private IPlayer bot;
    private GameManager gameManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String botName = getRandomBotName();

        lblRoundNumber.setText("First Round");
        lblText.setText("Welcome to RPS Game!");
        lblBotName.setText("Bot Name: " + botName);
        lblMakeYourMove.setText("Make Your Move:");
        textAreaResulte.setText("Game started! Select Rock, Paper, or Scissors.");


        // this is not implemented yet
        lblPlayerPoints.setText("Player games won: ");
        lblAIPoints.setText("AI games won: ");

        // Initialize game manager
        human = new Player("Player", PlayerType.Human);
        bot = new Player(botName, PlayerType.AI);
        gameManager = new GameManager(human, bot);
    }

    /**
     * Plays a round based on player's move
     * @param playerMove The move selected by the player
     */
    private void playRound(Move playerMove) {
        gameManager.playRound(playerMove);
        GameState gameState = (GameState) gameManager.getGameState();
        Result lastResult = gameState.getHistoricResults().get(gameState.getHistoricResults().size() - 1);

        // Update images
        updateMoveImages(playerMove, lastResult.getLoserMove());

        // Update UI
        textAreaResulte.appendText("\n" + getResultAsString(lastResult));
        lblRoundNumber.setText("Round: " + gameState.getRoundNumber());
    }

    /**
     * Updates the images based on player and AI move
     */
    private void updateMoveImages(Move playerMove, Move aiMove) {
        imageViewPlayer.setImage(getMoveImage(playerMove));
        imageViewAI.setImage(getMoveImage(aiMove));
    }

    /**
     * Returns the corresponding image for the move
     */
    private Image getMoveImage(Move move) {
        String fileName = switch (move) {
            case Rock -> "rock.png";
            case Paper -> "hand-paper.png";
            case Scissor -> "Scissors.png";
        };
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rps/Image/" + fileName)));
    }

    @FXML
    private void btnRock(ActionEvent actionEvent) {
        playRound(Move.Rock);
    }

    @FXML
    private void btnPapir(ActionEvent actionEvent) {
        playRound(Move.Paper);
    }

    @FXML
    private void btnScissor(ActionEvent actionEvent) {
        playRound(Move.Scissor);
    }

    @FXML
    private void btnRandomMove(ActionEvent actionEvent) {
        Move[] moves = Move.values();
        Move randomMove = moves[new Random().nextInt(moves.length)];
        playRound(randomMove);
    }

    private String getRandomBotName() {
        String[] botNames = { "R2D2", "Mr. Data", "3PO", "Bender", "Marvin", "Bishop", "Robot B-9", "HAL" };
        return botNames[new Random().nextInt(botNames.length)];
    }

    private String getResultAsString(Result result) {
        String statusText = switch (result.getType()) {
            case Win -> " wins against ";
            case Tie -> " ties with ";

        };

        return "Round #" + result.getRoundNumber() + ": " +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ")" +
                statusText +
                result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }
}
