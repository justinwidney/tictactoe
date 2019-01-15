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
    private int[] score = {0,0,0};	// X wins, O wins, Ties

    public TicTacToeGame(){
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

    public void printScore(){
        System.out.println("X wins: " + score[0]  + "    O wins: " + score[1] +
                    "    Ties:" + score[2]+ "");
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);

        while (board.getWinner() == null){
            board.printBoard();
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

	if(board.getWinner() == Player.X){
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

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
