package agh.cs.evolution;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String input ="";
        Scanner inputScanner = new Scanner(System.in);
        Solver solver = new Solver();
        System.out.println("Evolution (type exit to leave, type help for more information)");
        while(!input.equals("exit")){
            System.out.print(">> ");
            input = inputScanner.nextLine();
            Task task = new Task(input);
            solver.solve(task);
        }
    }
}