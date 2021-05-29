package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

    private List<Queen> queens;
    private int cost;
    private int boardSize;
    private Random random;

    public Board(int boardSize)
    {
        this.boardSize = boardSize;
        queens = new LinkedList<>();
        random = new Random();
        cost = 0;
    }

    public Board(int boardSize, List<Queen> queens)
    {
        this.boardSize = boardSize;
        this.queens = queens;
        random = new Random();
        cost = 0;
    }

    /**
     * iterate each queen
     * when 2 queens are vetical, horizontal, or diagonal
     * increment the cost
     * due to double counts
     * divide total cost by 2
     *
     * Original version was really messy but found github repo which compressed everything I previously wrote into a single statement
     * https://github.com/udnisap/N-Queens-with-Simulated-Annealing/blob/master/src/n/queens/State.java
     */
    public void calculateCost()
    {
        cost = 0;

        for(int x = 0; x < boardSize; x++)
        {
            for(int y = 0; y < boardSize; y++)
            {
                if(x == y)
                    continue;

                if(queens.get(x).getX() == queens.get(y).getX()
                        || queens.get(x).getY() == queens.get(y).getY()
                        || (queens.get(x).getX() - queens.get(y).getX() == queens.get(x).getY() - queens.get(y).getY())
                        || (queens.get(x).getX() - queens.get(y).getX() == queens.get(y).getY() - queens.get(x).getY()))
                    cost++;
            }
        }

        cost = cost / 2;
    }

    public int getCost()
    {
        calculateCost();
        return cost;
    }

    public List<Queen> getQueens()
    {
        return queens;
    }

    public void randomize()
    {
        for(int i = 0; i < boardSize; i++)
        {
            queens.add(new Queen(i, random.nextInt(boardSize)));
        }
    }
}
