package com.example.albiferenc.dartscounterold;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.albiferenc.dartscounterold.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private Player player1;
    private Player player2;
    private int round = 1;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        player1 = new Player(501);      // TODO jatekosok szamatol fuggoen inicializalom
        player2 = new Player(501);
        player1.setIsActive(true);
        player2.setIsActive(false);

        decimalFormat = new DecimalFormat("#0.00");

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.button101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(101);
            }
        });

        binding.button301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(301);
            }
        });

        binding.button501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(501);
            }
        });

        binding.buttonBust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(Constants.BUST);
                binding.editText.setText("");
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            int currentThrow = 0;
            @Override
            public void onClick(View view) {
                if(!binding.editText.getText().toString().isEmpty()) {
                    currentThrow = Integer.parseInt(binding.editText.getText().toString());
                    calculate(currentThrow);
                    binding.editText.setText("");
                }
            }
        });

        binding.Average1TextView.setText(Constants.AVG);
        binding.Average2TextView.setText(Constants.AVG);
        binding.RoundTextView.setText(Constants.ROUND);
    }

    public void calculate(int currentThrow) {
        if(currentThrow == Constants.BUST || (getActivePlayer().calculateScore(currentThrow) == Constants.BUST)) {
            calculateStatistics(round, currentThrow);   // In case of bust, the value of currentThrow is zero
            refreshView();
            setActivePlayer();
        } else if(getActivePlayer().calculateScore(currentThrow) == Constants.WIN) {
            getActivePlayer().substractScore(currentThrow);
            refreshView();
            // Player won
        } else {
            getActivePlayer().substractScore(currentThrow);
            calculateStatistics(round, currentThrow);
            refreshView();
            setActivePlayer();
        }
    }
    public void calculateStatistics(int round, int currentThrow) {
        getActivePlayer().getScores().add(currentThrow);
        getActivePlayer().calculateAverage(round);
    }
    public void refreshView() {
        if(player1.isActive()) {
            if(player1.getScore() == 0) {
                binding.Score1TextView.setText(R.string.winner);
                binding.buttonSubmit.setEnabled(false);
            } else {
                binding.Score1TextView.setText(player1.getStrScore());
                binding.Average1TextView.setText((Constants.AVG + decimalFormat.format(player1.getAverage())));
                binding.Scores1TextView.setText(player1.getStrScores());
                // TODO tobbi mezot frissiteni: avg, previous, stb.
            }
        } else {
            if(player2.getScore() == 0) {
                binding.Score2TextView.setText(R.string.winner);
                binding.buttonSubmit.setEnabled(false);
            } else {
                binding.Score2TextView.setText(player2.getStrScore());
                binding.Average2TextView.setText((Constants.AVG + decimalFormat.format(player2.getAverage())));
                binding.Scores2TextView.setText(player2.getStrScores());
            }
            // TODO tobbi mezot frissiteni: avg, previous, stb.
        }
        binding.RoundTextView.setText((Constants.ROUND + String.valueOf(round)));
    }

    public void resetGame(int startScore) {
        player1 = new Player(startScore);
        player2 = new Player(startScore);
        player1.setIsActive(false);
        player2.setIsActive(true);
        refreshView();
        player1.setIsActive(true);
        player2.setIsActive(false);
        round = 1;
        binding.buttonSubmit.setEnabled(true);
        refreshView();
    }

    public Player getActivePlayer() {
        if(player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }
    public void setActivePlayer() {
        if(player1.isActive()) {
            player1.setIsActive(false);
            player2.setIsActive(true);
            binding.Player2TextView.setTypeface(null, Typeface.BOLD);
            binding.Player1TextView.setTypeface(null, Typeface.NORMAL);
        } else {
            player2.setIsActive(false);
            player1.setIsActive(true);
            binding.Player1TextView.setTypeface(null, Typeface.BOLD);
            binding.Player2TextView.setTypeface(null, Typeface.NORMAL);
            round++;    // Increasing round counter after player 2 finished throwing
        }
    }
}
