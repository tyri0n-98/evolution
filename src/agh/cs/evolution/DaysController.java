package agh.cs.evolution;

public class DaysController {
    private WorldMap map;

    public DaysController(WorldMap map){
        this.map = map;
    }

    public void runDay(){
        map.getElements().forEach((k,v) -> {
            if(v instanceof Animal){
                Animal animal = (Animal) v;
                animal.dayPassed();
                animal.chooseDirection();
                animal.moveForward();
                if(animal.reproduce() != null) map.place(animal);
            }
        });
        map.spawnPlants();
    }

    public void runDays(int numberOfDays){
        for(int i = 0; i < numberOfDays; i++){
            runDay();
        }
        System.out.print(map);
    }
}
