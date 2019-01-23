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
            case RUN:{
                int numberOfDays = 1;
                int printDays = 1;
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
            case RESET_ANIMAL_SETTINGS:{
                this.animalSettings.setDefaults();
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case RESET_MAP_SETTINGS:{
                this.mapSettings.setDefaults();
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case SET_ANIMAL_ENERGY_LOSS:{
                if(task.getArgs().size() < 1){
                    System.out.println("Wrong arguments");
                    return;
                }
                this.animalSettings.setDailyEnergyLoss(task.getArgs().get(0));
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case SET_ANIMAL_ENERGY_GAIN:{
                if(task.getArgs().size() < 1){
                    System.out.println("Wrong arguments");
                    return;
                }
                this.animalSettings.setEnergyGain(task.getArgs().get(0));
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case SET_ANIMAL_ENERGY_TO_REPRODUCE:{
                if(task.getArgs().size() < 1){
                    System.out.println("Wrong arguments");
                    return;
                }
                this.animalSettings.setEnergyToReproduce(task.getArgs().get(0));
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case SET_ANIMAL_AGE_TO_REPRODUCE:{
                if(task.getArgs().size() < 1){
                    System.out.println("Wrong arguments");
                    return;
                }
                this.animalSettings.setAgeToReproduce(task.getArgs().get(0));
                this.game = new Game(this.mapSettings, this.animalSettings);
                return;
            }
            case DISPLAY_SETTINGS:{
                System.out.println(this.mapSettings);
                System.out.print(this.animalSettings);
                return;
            }
            case HELP:{
                System.out.println("run                                                                               - runs one day (you can also press Enter)\n" +
                                              "run [arg]                                                                         - runs multiple days and prints the map when finished\n" +
                                              "run [arg1] [arg2]                                                                 - runs number of days specified in arg1 and prints the map after amount of days specified in arg2\n" +
                                              "reset                                                                             - resets the game but preserves settings\n" +
                                              "displaySettings                                                                   - displays current map and animals settings\n" +
                                              "setMapSize [mapWidth] [mapHeight] [jungleWidth] [jungleHeight]                    - sets size of the map and resets the game (DEFAULT: " + MapSettings.getDefaultMapWidth()+ " " + MapSettings.getDefaultMapHeight() + " " + MapSettings.getDefaultJungleWidth() + " " + MapSettings.getDefaultJungleHeight() + ")\n" +
                                              "setAnimalEnergyLoss [arg]                                                         - sets amount of energy that animals lose after each day (DEFAULT: " + AnimalSettings.getDefaultDailyEnergyLoss() +", MIN: 0, MAX: 100)\n" +
                                              "setAnimalEnergyGain [arg]                                                         - sets amount of energy that animals gain after eating one plant(DEFAULT: " + AnimalSettings.getDefaultEnergyGain() +", MIN: 0, MAX: 100)\n" +
                                              "setAnimalEnergyToReproduce [arg]                                                  - sets minimal amount of energy needed to reproduce (DEFAULT: " + AnimalSettings.getDefaultEnergyToReproduce() +", MIN: 0, MAX: 100)\n" +
                                              "setAnimalAgeToReproduce [arg]                                                     - sets minimal age needed to reproduce (DEFAULT: " + AnimalSettings.getDefaultAgeToReproduce() + ", MIN: 0)\n" +
                                              "resetSettings                                                                     - resets all the settings to default values\n" +
                                              "resetAnimalSettings                                                               - resets animals setting to default values\n" +
                                              "resetMapSettings                                                                  - resets map settings to default values\n" +
                                              "help                                                                              - displays help\n" +
                                              "exit                                                                              - close program");
                return;
            }
            case ERROR:{
                System.out.println("Wrong command");
            }
            case EXIT: return;
        }
    }
}
