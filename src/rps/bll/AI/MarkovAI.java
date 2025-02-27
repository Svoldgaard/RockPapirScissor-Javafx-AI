package rps.bll.AI;

import rps.bll.game.Move;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MarkovAI {
    private static Map<Move, Map<Move, Integer>> transitionMatrix;
    private static Move lastMove;

    public MarkovAI() {
        transitionMatrix = new HashMap<>();
        for(Move move : Move.values()) {
            transitionMatrix.put(move, new HashMap<>());
            for(Move nextMove : Move.values()) {
                transitionMatrix.get(move).put(nextMove, 0);
            }
        }
        lastMove = null;
    }

    public static void updateMatrix(Move opponentMove) {
        if(lastMove != null) {
            Map<Move, Integer> transitions = transitionMatrix.get(lastMove);
            transitions.put(opponentMove, transitions.get(opponentMove) + 1);
        }
        lastMove = opponentMove;
    }

    public Move predictNextMove(){
        if(lastMove == null) {
            return Move.values()[new Random().nextInt(3)];
        }

        Map<Move, Integer> transitions = transitionMatrix.get(lastMove);
        Move mostLikleyMove = Move.Rock;
        int maxCount = -1;

        for(Move move : Move.values()){
            if(transitions.get(move) > maxCount) {
                maxCount = transitions.get(move);
                mostLikleyMove = move;
            }
        }

        return getCounterMove(mostLikleyMove);
    }

    private Move getCounterMove(Move move) {
        if(move == Move.Rock) return Move.Paper;
        if(move == Move.Paper) return Move.Scissor;
        return Move.Rock;
    }
}
