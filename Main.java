package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Runs simulated annealing with a random start state
        SimulatedAnnealing sim = new SimulatedAnnealing(8, 10000000);
        System.out.println("Starting board");
        System.out.println(sim.toString());
        if(sim.solve())
        {
            System.out.println("Solved");
            System.out.println(sim.toString());
        }
        else
            System.out.println("Not Solved\n");


        // Creates a list of queen positions
        // Does not work if 2 queens use same x value
        List<Queen> queens = new LinkedList<>();
        queens.add(new Queen(0, 0));
        queens.add(new Queen(1, 1));
        queens.add(new Queen(2, 2));
        queens.add(new Queen(3, 3));
        queens.add(new Queen(4, 4));
        queens.add(new Queen(5, 5));
        queens.add(new Queen(6, 6));
        queens.add(new Queen(7, 7));


        // Runs simulated annealing with the above state of queens
        SimulatedAnnealing sim2 = new SimulatedAnnealing(8, 10000000, queens);
        System.out.println("Starting board");
        System.out.println(sim2.toString());
        if(sim2.solve())
        {
            System.out.println("Solved");
            System.out.println(sim2.toString());
        }
        else
            System.out.println("Not Solved\n");
    }


}


