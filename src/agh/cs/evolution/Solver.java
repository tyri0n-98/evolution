package agh.cs.evolution;

public class Solver {
    private Game game;
    private AnimalSettings animalSettings;
    private MapSettings mapSettings;

    public Solver(){
        this.animalSettings = new AnimalSettings();
        this.mapSettings = new MapSettings();
        this.game = new Game(this.mapSettings, this.animalSettings);
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
                return;
            }
            case RESET:{
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case SETMAPSIZE:{
                if(task.getArgs().size() < 4 || task.getArgs().get(2) > task.getArgs().get(0) || task.getArgs().get(3) > task.getArgs().get(1)) {
                    System.out.println("Wrong arguments");
                    return;
                }
                this.mapSettings.setMapSize(task.getArgs().get(0), task.getArgs().get(1), task.getArgs().get(2), task.getArgs().get(3));
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case RESETSETTINGS:{
                this.mapSettings.setDefaults();
                this.animalSettings.setDefaults();
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
        }
    }
}
