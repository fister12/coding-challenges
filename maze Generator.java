import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class RandomMazeGenerator extends JFrame implements ActionListener {
    private JPanel mazePanel;
    private JButton generateButton;

    private static final int MAZE_SIZE = 10;

    public RandomMazeGenerator() {
        super("Random Maze Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the maze panel
        mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(MAZE_SIZE, MAZE_SIZE));
        generateMaze();
        add(mazePanel, BorderLayout.CENTER);

        // Create the generate button
        generateButton = new JButton("Generate Maze");
        generateButton.addActionListener(this);
        add(generateButton, BorderLayout.SOUTH);

        // Set the size and show the window
        setSize(500, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            generateMaze();
        }
    }

    private void generateMaze() {
        mazePanel.removeAll();
        boolean[][] maze = generateRandomMaze();
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                JPanel cell = new JPanel();//After removing all the previous cells new cells are created and given colours according to the true or false values
                cell.setBackground(maze[i][j] ? Color.BLACK : Color.WHITE);
                mazePanel.add(cell);
            }
        }
        validate();
    }

    private boolean[][] generateRandomMaze() {
        Random random = new Random();
        boolean[][] maze = new boolean[MAZE_SIZE][MAZE_SIZE];

        // Set the outer walls
        for (int i = 0; i < MAZE_SIZE; i++) {
            maze[i][0] = true;
            maze[i][MAZE_SIZE-1] = true;
            maze[0][i] = true;
            maze[MAZE_SIZE-1][i] = true;
        }

        // Generate the maze
        for (int i = 2; i < MAZE_SIZE; i += 2) {
            for (int j = 2; j < MAZE_SIZE; j += 2) {
                maze[i][j] = true;
                if (j != MAZE_SIZE-2) {
                    maze[i][j+1] = random.nextBoolean();
                }
                if (i != MAZE_SIZE-2) {
                    maze[i+1][j] = random.nextBoolean();
                }
            }
        }

        return maze;
    }

    public static void main(String[] args) {
        new RandomMazeGenerator();
    }
  //This program uses creates a panel using JFrame and a button the maze is envisioned as a matrix each cell has 4 neighbours each time it connects to a random neighbor 
  //When you press the "Generate Maze" button the same algorithm just runs again a cell can be randomly assinged either true or false values if it is true then it is shoecased
  //As a white cell if it is false then a black square each time the button is pressed the cells is reassinged true or false values randomly and a new maze is created 
  //This is a dumb version of a maze but it does the trick in a very simple way after the button is pressed the mazepanel is first cleared and colours are assinged 
  //According to random true or false values which is given using another function.
  
  
  
