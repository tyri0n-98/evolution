package agh.cs.evolution;

public class Solver {
    private Game game;

    public Solver(){
        this.game = new Game();
    }
    public void solve(Task task){
        switch (task.getCommand()){
            case START:{
                int numberOfDays = 1;
                int printDays = 1;
                System.out.println(task.getArgs());
                if(task.getArgs().size() == 1){
                    numberOfDays = task.getArgs().get(0);
                    printDays = numberOfDays;
                }else if(task.getArgs().size() == 2) {
                    numberOfDays = task.getArgs().get(0);
                    printDays = task.getArgs().get(1);
                }
                for(int i = 0; i < numberOfDays/printDays; i++) game.runDays(printDays);
                game.runDays(numberOfDays%printDays);
                break;
            }
            case RESET:{
                this.game = new Game();
                break;
            }
        }
    }
}
