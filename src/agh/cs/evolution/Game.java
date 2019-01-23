package agh.cs.evolution;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private WorldMap map;
    private List<Animal> animalsList;
    private int day;
    private boolean gameEnded;

    public Game(MapSettings mapSettings, AnimalSettings animalSettings){
        this.day = 0;
        this.gameEnded = false;
        this.map = new WorldMap(mapSettings.getMapWidth(), mapSettings.getMapHeight(), mapSettings.getJungleWidth(), mapSettings.getJungleHeight());
        Animal animal = new Animal(new Position(mapSettings.getMapWidth()/2, mapSettings.getMapHeight()/2), Direction.NORTH, map, animalSettings);
        this.map.place(animal);
        this.animalsList = new LinkedList<>();
        this.animalsList.add(animal);
    }

    private void runDay(){
        List<Animal> newAnimals = new LinkedList<>();
        List<Animal> deadAnimals = new LinkedList<>();
        for(Animal animal: animalsList){
            animal.dayPassed();
            if(animal.isDead()) deadAnimals.add(animal);
            else {
                animal.chooseDirection();
                animal.moveForward();
                Animal animal1 = animal.reproduce();
                if (animal1 != null && map.place(animal1)) {
                    newAnimals.add(animal1);
                }
            }
        }
        this.animalsList.removeAll(deadAnimals);
        this.animalsList.addAll(newAnimals);
        this.day++;
        if(this.animalsList.isEmpty()) return;
        map.spawnPlants();
    }

    private void endGame(){
        this.gameEnded = true;
        System.out.println("The population has gone extinct after " + this.day + " days");
    }

    public void runDays(int numberOfDays){
        if(!gameEnded) {
            for (int i = 0; i < numberOfDays; i++) {
                if (!this.animalsList.isEmpty())
                    runDay();
                else
                    break;
            }
            if (numberOfDays != 0) {
                System.out.println("Day: " + this.day);
                System.out.print(map);
            }
            if(this.animalsList.isEmpty()) endGame();
        }
    }
}
