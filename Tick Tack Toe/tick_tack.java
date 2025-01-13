import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class tick_tack {
    int bordWidth = 600;
    int bordHeight = 650;
    JFrame frame = new JFrame("Tick Tack Toe");

    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();

    JPanel boardPanel = new JPanel();

    JButton[][] buttonsList = new JButton[3][3];

    String[] values = new String[9];

    public tick_tack() {
        frame.setVisible(true);
        frame.setSize(bordWidth, bordHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Areal", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tick-Tack-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                buttonsList[i][j] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Areal", Font.BOLD, 50));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() != "X" && tile.getText() != "O") {
                            tile.setText("X");

                            if (isWinning(values, "X")) {
                                textLabel.setText("You Win!");
                            } else {
                                int computerMove = computerMove();
                                int row = computerMove / 3;
                                int column = computerMove % 3;
                                JButton comButton = buttonsList[row][column];
                                comButton.setText("O");
                                if (isWinning(values, "O")) {
                                    textLabel.setText("Computer Won!");
                                }
                            }
                        }
                    }
                });

            }
        }
    }

    void updateValuesArray() {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = buttonsList[i][j];
                values[k] = b.getText();
                k++;

            }
        }
    }

    boolean isWinning(String[] array, String v) {
        updateValuesArray();
        if (array[0] == v && array[1] == v && array[2] == v) {
            return true;
        } else if (array[3] == v && array[4] == v && array[5] == v) {
            return true;
        } else if (array[6] == v && array[7] == v && array[8] == v) {
            return true;
        } else if (array[0] == v && array[3] == v && array[6] == v) {
            return true;
        } else if (array[1] == v && array[4] == v && array[7] == v) {
            return true;
        } else if (array[6] == v && array[7] == v && array[8] == v) {
            return true;
        } else if (array[0] == v && array[4] == v && array[8] == v) {
            return true;
        } else if (array[2] == v && array[4] == v && array[6] == v) {
            return true;
        } else {
            return false;
        }
    }

    int computerMove() {
        int[] availableMoves = new int[9];

        // Create a Copy of The Board
        String[] copyValues = (String[]) values.clone();

        // Default Computer Move is Zero
        int comMove = 0;

        // for loop for Updating available Moves Array
        int k = 0;
        for (int i = 0; i < 9; i++) {
            if (values[i] != "X" && values[i] != "O") {
                availableMoves[k] = i;
                k++;
            }
        }

        // Substitute each available move to a copy of the values array and check for a
        // Winning Move
        for (int i = 0; i < availableMoves.length; i++) {
            copyValues = (String[]) values.clone();
            copyValues[availableMoves[i]] = "O";
            if (isWinning(copyValues, "O")) {
                comMove = availableMoves[i];
                return comMove;
            }
        }

        // Check for opponent's Winning Moves
        for (int i = 0; i < availableMoves.length; i++) {
            copyValues = (String[]) values.clone();
            copyValues[availableMoves[i]] = "X";
            if (isWinning(copyValues, "X")) {
                comMove = availableMoves[i];
                return comMove;
            }
        }

        // Corners and Centers
        int[] corners = { 0, 2, 4, 6, 8 };
        int[] availableCorners = new int[5];
        int n = 0;
        for (int i = 0; i < corners.length; i++) {
            for (int j = 0; j < availableMoves.length; j++) {
                if (availableMoves[j] == corners[i]) {
                    availableCorners[n] = corners[i];
                    n++;
                }
            }
        }
        Random random = new Random();
        int move;
        int p = 0;

        do {
            int randomVal = random.nextInt(corners.length);
            move = corners[randomVal];
            if (values[move] != "X" && values[move] != "O") {
                comMove = move;
                return comMove;
            }
            p++;
        } while (p <= corners.length);

        // Other moves
        int[] other = { 1, 3, 5, 7 };
        int[] availableOther = new int[4];
        int m = 0;
        for (int i = 0; i < other.length; i++) {
            for (int j = 0; j < availableMoves.length; j++) {
                if (availableMoves[j] == corners[i]) {
                    availableOther[m] = corners[i];
                    m++;
                }
            }
        }
        p = 0;

        do {
            int randomVal = random.nextInt(corners.length);
            move = other[randomVal];
            if (values[move] != "X" && values[move] != "O") {
                comMove = move;
                return comMove;
            }
            p++;
        } while (p <= corners.length);

        return comMove;
    }

}