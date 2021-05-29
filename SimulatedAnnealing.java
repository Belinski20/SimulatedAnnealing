package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

    private int temperature;
    private int boardSize;
    private double threshold = .1;
    private Board current;

    public SimulatedAnnealing(int boardSize, int temperature)
    {
        this.boardSize = boardSize;
        this.temperature = temperature;
        current = new Board(boardSize);
        current.randomize();
    }

    public SimulatedAnnealing(int boardSize, int temperature, List<Queen> queens)
    {
        this.boardSize = boardSize;
        this.temperature = temperature;
        current = new Board(boardSize, queens);
    }

    /**
     * while temperature is above 0
     * check if the current configuration is a possible solution
     * decrement temperature by 1
     * get next neighbor
     * if next neighbor has a greater cost than current
     *  next neighbor is the new current
     * if e ^ temp * (current cost - neighbor cost) is greater than the threshold
     *  next neighbor is the new current
     *
     * @return if solution is found
     */
    public boolean solve()
    {
        while(temperature > 0)
        {
            if(current.getCost() == 0)
                return true;

            temperature -= 1;

            Board NN = getNextNeighbor();

            if(NN.getCost() > current.getCost())
                current = NN;
            else if (Math.exp(temperature * (current.getCost()) - NN.getCost()) > threshold)
                current = NN;
        }
        return false;
    }

    /**
     * choose a random queen x value
     * while true
     *  choose a random spot to move the queen to
     *  if the queen is not there
     *      go through each queen
     *          set a copy of each queen to a new board
     *          if random queen x is found add it with new position
     *  return new board
     *
     * @return a new board which was a neighbor
     */
    public Board getNextNeighbor()
    {
        Random random = new Random();
        int queenIndex = random.nextInt(boardSize);
        List<Queen> queens = new LinkedList<>();

        while(true)
        {
            int newQueenSpot = random.nextInt(boardSize);

            if(current.getQueens().get(queenIndex).getY() != newQueenSpot) {

                for (int i = 0; i < boardSize; i++) {
                    if (i == queenIndex)
                        queens.add(new Queen(current.getQueens().get(i).getX(), newQueenSpot));
                    else
                        queens.add(new Queen(current.getQueens().get(i).getX(), current.getQueens().get(i).getY()));
                }
                return new Board(boardSize, queens);
            }
        }
    }

    /**
     * prints the solution board out
     * @return
     */
    @Override
    public String toString()
    {
        String board = "";
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                boolean isQueen = false;
                for(Queen queen : current.getQueens())
                {
                    if(queen.getX() == i && queen.getY() == j)
                    {
                        isQueen = true;
                        board += "|Q|";
                    }
                }
                if(!isQueen)
                {
                    board += "|_|";
                }
            }
            board += "\n";
        }
        return board;
    }
}
