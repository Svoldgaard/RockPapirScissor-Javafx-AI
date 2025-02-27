package rps.bll.player;

//Project imports
import rps.bll.AI.MarkovAI;
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.ArrayList;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    private MarkovAI markovAI;
    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
        markovAI = new MarkovAI();
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();

        // AI with only 1 move
        //return Move.Rock;


        // Ai with Random Move
        /*
        ArrayList<Move> AIMove = new ArrayList<>();
        AIMove.add(Move.Rock);
        AIMove.add(Move.Paper);
        AIMove.add(Move.Scissor);

        int Random = new Random().nextInt(AIMove.size());
        return AIMove.get(Random);
        */

        // MarkovAI
        if (results.isEmpty()) {
            return Move.values()[new Random().nextInt(3)]; // Start randomly
        }
        
        Result lastResult = results.get(results.size() - 1);

        // Determine the opponent's move
        Move lastOpponentMove;

        if (lastResult.getWinnerPlayer() == this) {
            lastOpponentMove = lastResult.getLoserMove();  // Bot won, opponent lost
        } else if (lastResult.getLoserPlayer() == this) {
            lastOpponentMove = lastResult.getWinnerMove(); // Bot lost, opponent won
        } else {
            lastOpponentMove = lastResult.getWinnerMove(); // Handle ties (assuming one move is recorded)
        }

        // Update Markov AI
        MarkovAI.updateMatrix(lastOpponentMove);

        // Predict next move
        return markovAI.predictNextMove();



    }
}
