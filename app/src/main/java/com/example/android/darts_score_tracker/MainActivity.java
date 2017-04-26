package com.example.android.darts_score_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int playerOneScore = 501;
    int playerOneDartOne;
    int playerOneDartTwo;
    int playerOneDartThree;

    int playerTwoScore = 501;
    int playerTwoDartOne;
    int playerTwoDartTwo;
    int playerTwoDartThree;

    int calculatorValueMain;

    boolean dartOne;
    boolean dartTwo;
    boolean dartThree;
    boolean player1Turn = true;
    boolean player2Turn;

    String playerOneToast = "Player 1";
    String playerTwoToast = "Player 2";
    String bustScore = "Player Bust, Next Player";
    String player1Win = "Winner - Player 1";
    String player2Win = "Winner - Player 2";
    String firstDart = "1st Dart Selected";
    String secondDart = "2nd Dart Selected";
    String thirdDart = "3rd Dart Selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button dartButton1 = (Button)findViewById(R.id.firstDart);
        final Button dartButton2 = (Button)findViewById(R.id.secondDart);
        final Button dartButton3 = (Button)findViewById(R.id.thirdDart);
        final Button nextPlayer = (Button)findViewById(R.id.nextPlayer);
        final Button confirm = (Button)findViewById(R.id.confirmButton);
        final Button reset = (Button)findViewById(R.id.reset);

        dartOne = true;
        dartTwo = false;
        dartThree = false;
        dartButton1.setEnabled(false);
        dartButton2.setEnabled(true);
        dartButton3.setEnabled(true);
        Toast.makeText(getApplicationContext(),firstDart,Toast.LENGTH_SHORT).show();

        //Selects Dart 1.
        dartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dartOne = true;
                dartTwo = false;
                dartThree = false;
                dartButton1.setEnabled(false);
                dartButton2.setEnabled(true);
                dartButton3.setEnabled(true);
                Toast.makeText(getApplicationContext(),firstDart,Toast.LENGTH_SHORT).show();
            }
        });

        //Selects Dart 2.
        dartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dartOne = false;
                dartTwo = true;
                dartThree = false;
                dartButton1.setEnabled(true);
                dartButton2.setEnabled(false);
                dartButton3.setEnabled(true);
                Toast.makeText(getApplicationContext(),secondDart,Toast.LENGTH_SHORT).show();
            }
        });

        // Selects Dart 3.
        dartButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dartOne = false;
                dartTwo = false;
                dartThree = true;
                dartButton1.setEnabled(true);
                dartButton2.setEnabled(true);
                dartButton3.setEnabled(false);
                Toast.makeText(getApplicationContext(),thirdDart,Toast.LENGTH_SHORT).show();
            }
        });


        //Switches turns between player 1 & player 2 when the Next Player button is clicked.
        // Also calculates turn score.
        nextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dartOne = true;
                dartTwo = false;
                dartThree = false;
                dartButton1.setEnabled(false);
                dartButton2.setEnabled(true);
                dartButton3.setEnabled(true);
                if (player1Turn){
                    player1Turn = false;
                    player2Turn = true;
                    int turnScore1 = playerOneDartOne + playerOneDartTwo + playerOneDartThree;
                    playerOneScore = playerOneScore - turnScore1;
                    if (playerOneScore < 0){
                        playerOneScore = playerOneScore + turnScore1;
                        Toast.makeText(getApplicationContext(),bustScore,Toast.LENGTH_LONG).show();
                    }
                    else if (playerOneScore == 0){
                        Toast.makeText(getApplicationContext(),player1Win,Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),playerTwoToast,Toast.LENGTH_LONG).show();
                    }
                    displayForPlayerOne(playerOneScore);
                    playerOneDartOne = 0;
                    displayForPlayerOneD1(playerOneDartOne);
                    playerOneDartTwo = 0;
                    displayForPlayerOneD2(playerOneDartTwo);
                    playerOneDartThree = 0;
                    displayForPlayerOneD3(playerOneDartThree);
                }
                else{
                    player1Turn = true;
                    player2Turn = false;
                    int turnScore2 = playerTwoDartOne + playerTwoDartTwo + playerTwoDartThree;
                    playerTwoScore = playerTwoScore - turnScore2;
                    if (playerTwoScore < 0) {
                        playerTwoScore = playerTwoScore + turnScore2;
                        Toast.makeText(getApplicationContext(),bustScore,Toast.LENGTH_LONG).show();
                    }
                    else if (playerTwoScore == 0){
                        Toast.makeText(getApplicationContext(),player2Win,Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),playerOneToast,Toast.LENGTH_LONG).show();
                    }
                    displayForPlayerTwo(playerTwoScore);
                    playerTwoDartOne = 0;
                    displayForPlayerTwoD1(playerTwoDartOne);
                    playerTwoDartTwo = 0;
                    displayForPlayerTwoD2(playerTwoDartTwo);
                    playerTwoDartThree = 0;
                    displayForPlayerTwoD3(playerTwoDartThree);
                }
            }
        });

        /**
         * Updates relevant textView with the value of the calculator
         * when the Confirm Button is clicked on the calculator.
         */
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1Turn && dartOne){
                    playerOneDartOne = calculatorValueMain;
                    dartOne = false;
                    dartTwo = true;
                    dartThree = false;
                    dartButton1.setEnabled(true);
                    dartButton2.setEnabled(false);
                    dartButton3.setEnabled(true);
                    Toast.makeText(getApplicationContext(),secondDart,Toast.LENGTH_SHORT).show();
                    displayForPlayerOneD1(playerOneDartOne);
                    calculatorValueMain = 0;
                    displayCalculatorValue(calculatorValueMain);
                }
                else if(player1Turn && dartTwo){
                    playerOneDartTwo = calculatorValueMain;
                    dartOne = false;
                    dartTwo = false;
                    dartThree = true;
                    dartButton1.setEnabled(true);
                    dartButton2.setEnabled(true);
                    dartButton3.setEnabled(false);
                    Toast.makeText(getApplicationContext(),thirdDart,Toast.LENGTH_SHORT).show();
                    displayForPlayerOneD2(playerOneDartTwo);
                    calculatorValueMain = 0;
                    displayCalculatorValue(calculatorValueMain);
                }
                else if(player1Turn && dartThree){
                    playerOneDartThree = calculatorValueMain;
                    dartOne = true;
                    dartTwo = false;
                    dartThree = false;
                    dartButton1.setEnabled(false);
                    dartButton2.setEnabled(true);
                    dartButton3.setEnabled(true);
                    Toast.makeText(getApplicationContext(),firstDart,Toast.LENGTH_SHORT).show();
                    displayForPlayerOneD3(playerOneDartThree);
                    calculatorValueMain = 0;
                    displayCalculatorValue(calculatorValueMain);
                }
                else if (player2Turn && dartOne){
                    playerTwoDartOne = calculatorValueMain;
                    dartOne = false;
                    dartTwo = true;
                    dartThree = false;
                    dartButton1.setEnabled(true);
                    dartButton2.setEnabled(false);
                    dartButton3.setEnabled(true);
                    Toast.makeText(getApplicationContext(),secondDart,Toast.LENGTH_SHORT).show();
                    displayForPlayerTwoD1(playerTwoDartOne);
                    calculatorValueMain = 0;
                    displayCalculatorValue(calculatorValueMain);
                }
                else if(player2Turn && dartTwo){
                    playerTwoDartTwo = calculatorValueMain;
                    dartOne = false;
                    dartTwo = false;
                    dartThree = true;
                    dartButton1.setEnabled(true);
                    dartButton2.setEnabled(true);
                    dartButton3.setEnabled(false);
                    Toast.makeText(getApplicationContext(),thirdDart,Toast.LENGTH_SHORT).show();
                    displayForPlayerTwoD2(playerTwoDartTwo);
                    calculatorValueMain = 0;
                    displayCalculatorValue(calculatorValueMain);
                }
                else if(player2Turn && dartThree){
                    playerTwoDartThree = calculatorValueMain;
                    dartOne = true;
                    dartTwo = false;
                    dartThree = false;
                    dartButton1.setEnabled(false);
                    dartButton2.setEnabled(true);
                    dartButton3.setEnabled(true);
                    Toast.makeText(getApplicationContext(),firstDart,Toast.LENGTH_SHORT).show();
                    displayForPlayerTwoD3(playerTwoDartThree);
                    calculatorValueMain = 0;
                    displayCalculatorValue(calculatorValueMain);
                }
            }
        });

        /**
         * Resets Scores to 0
         */
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            playerOneScore = 501;
            playerTwoScore = 501;
            playerOneDartOne = 0;
            playerOneDartTwo = 0;
            playerOneDartThree = 0;
            playerTwoDartOne = 0;
            playerTwoDartTwo = 0;
            playerTwoDartThree = 0;
            dartOne = true;
            dartTwo = false;
            dartThree = false;
            dartButton1.setEnabled(false);
            dartButton2.setEnabled(true);
            dartButton3.setEnabled(true);
            Toast.makeText(getApplicationContext(),firstDart,Toast.LENGTH_SHORT).show();
            displayForPlayerOne(playerOneScore);
            displayForPlayerTwo(playerTwoScore);
            displayForPlayerOneD1(playerOneDartOne);
            displayForPlayerOneD2(playerOneDartTwo);
            displayForPlayerOneD3(playerOneDartThree);
            displayForPlayerTwoD1(playerTwoDartOne);
            displayForPlayerTwoD2(playerTwoDartTwo);
            displayForPlayerTwoD3(playerTwoDartThree);
        }
        });
    }

    /**
     * Displays the given score for Player One.
     */
    public void displayForPlayerOne(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p1Score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Player One Dart One.
     */
    public void displayForPlayerOneD1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p1d1);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Player One Dart Two.
     */
    public void displayForPlayerOneD2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p1d2);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Player One Dart Three.
     */
    public void displayForPlayerOneD3(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p1d3);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Player Two.
     */
    public void displayForPlayerTwo(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p2Score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Player Two Dart One.
     */
    public void displayForPlayerTwoD1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p2d1);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Player Two Dart Two.
     */
    public void displayForPlayerTwoD2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p2d2);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Player Two Dart Three.
     */
    public void displayForPlayerTwoD3(int score) {
        TextView scoreView = (TextView) findViewById(R.id.p2d3);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for calculator.
     */
    public void displayCalculatorValue(int score) {
        TextView scoreView = (TextView) findViewById(R.id.calculatorValue);
        scoreView.setText(String.valueOf(score));
        if (calculatorValueMain > 60){
            calculatorValueMain = 0;
        }
    }
    /**
     * Inputs 1 when 1 Button is clicked on the calculator.
     */
    public void inputOne(View view) {
        String calculatorString = calculatorValueMain + "1";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 2 when 2 Button is clicked on the calculator.
     */
    public void inputTwo(View view) {
        String calculatorString = calculatorValueMain + "2";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 3 when 3 Button is clicked on the calculator.
     */
    public void inputThree(View view) {
        String calculatorString = calculatorValueMain + "3";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 4 when 4 Button is clicked on the calculator.
     */
    public void inputFour(View view) {
        String calculatorString = calculatorValueMain + "4";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 5 when 5 Button is clicked on the calculator.
     */
    public void inputFive(View view) {
        String calculatorString = calculatorValueMain + "5";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 6 when 6 Button is clicked on the calculator.
     */
    public void inputSix(View view) {
        String calculatorString = calculatorValueMain + "6";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 7 when 7 Button is clicked on the calculator.
     */
    public void inputSeven(View view) {
        String calculatorString = calculatorValueMain + "7";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 8 when 8 Button is clicked on the calculator.
     */
    public void inputEight(View view) {
        String calculatorString = calculatorValueMain + "8";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 9 when 9 Button is clicked on the calculator.
     */
    public void inputNine(View view) {
        String calculatorString = calculatorValueMain + "9";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 0 when 0 Button is clicked on the calculator.
     */
    public void inputZero(View view) {
        String calculatorString = calculatorValueMain + "0";
        calculatorValueMain = Integer.parseInt(calculatorString);
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 25 when 25 Button is clicked on the calculator.
     */
    public void inputTwentyFive(View view) {
        calculatorValueMain = 25;
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Inputs 50 when 50 Button is clicked on the calculator.
     */
    public void inputFifty(View view) {
        calculatorValueMain = 50;
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Multiplies calculator value by 2 when the x2 Button is clicked on the calculator.
     */
    public void doubleDartScore(View view) {
        calculatorValueMain = calculatorValueMain * 2;
        if (calculatorValueMain > 60){
            calculatorValueMain = 0;
        }
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Multiplies calculator value by 3 when the x3 Button is clicked on the calculator.
     */
    public void tripleDartScore(View view) {
        calculatorValueMain = calculatorValueMain * 3;
        if (calculatorValueMain > 60){
            calculatorValueMain = 0;
        }
        displayCalculatorValue(calculatorValueMain);
    }
    /**
     * Resets calculator value to 0 when the C Button is clicked on the calculator.
     */
    public void cancelDartScore(View view) {
        calculatorValueMain = 0;
        displayCalculatorValue(calculatorValueMain);
    }

}
