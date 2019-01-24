package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private Board board;
    private int[] score;  // X wins, O wins, Ties

    public TicTacToeGame(){
        score = new int[] {0,0,0};
        board = new Board();
    }

    public void resetBoard(){
        board = new Board();
    }

    public void promptNextPlayer(){
        switch(board.getCurrentPlayer()){
            case X:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;
            case O:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;

        }
    }

    private void printScore(){
        System.out.println("X wins: " + score[0]  + "    O wins: " + score[1] +
                    "    Ties:" + score[2] +
                    "\nX losses: " + score[1]  + "    O losses: " + score[0]);
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);

        while (board.getWinner() == null){
            board.printBoard();

            // If all moves have been played with no winner result game in tie
            if (board.getMoveCount() == 9){
                System.out.println("Players have tied the game.");
                score[2] += 1;
                printScore();
                return;
            }

            promptNextPlayer();
            String line = keyboardScanner.nextLine();
            String input[] = line.split(",");
            try {
                board.playMove(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            } catch (InvalidMoveException e) {
                System.out.println("Invalid coordinates. Try again");
                promptNextPlayer();
            }
        }

        if (board.getWinner() == Player.X){
            score[0] += 1;
        }
        else if (board.getWinner() == Player.O){
            score[1] += 1;
        }
        else{
            score[2] += 1;
        }
        board.printBoard();
        System.out.println("Player " + board.getWinner() + " has won the game!");
        printScore();
    }

    private boolean promptNewGame() {
        while (true) {
            Scanner keyboardScanner = new Scanner(System.in);
            System.out.println("Do you want to play another game (y/n)?");
            String line = keyboardScanner.nextLine().toLowerCase();
            if (line.contentEquals("y")) {
                return true;
            } else if (line.contentEquals("n")) {
                printScore();
                return false;
            }
        }
    }

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
        while (game.promptNewGame()) {
            game.resetBoard();
            game.playGame();
        }
    }
}
