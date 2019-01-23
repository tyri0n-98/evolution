package agh.cs.evolution;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private WorldMap map;
    private List<Animal> animalsList;


    public Game(MapSettings mapSettings, AnimalSettings animalSettings){
        this.map = new WorldMap(mapSettings.getMapWidth(), mapSettings.getMapHeight(), mapSettings.getJungleWidth(), mapSettings.getJungleHeight());
        Animal animal = new Animal(new Position(mapSettings.getMapWidth()/2, mapSettings.getMapHeight()/2), Direction.NORTH, map, animalSettings);
        this.map.place(animal);
        this.animalsList = new LinkedList<>();
        this.animalsList.add(animal);
    }

    public void runDay(){
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
        if(this.animalsList.isEmpty()) return;
        map.spawnPlants();
    }

    public void runDays(int numberOfDays){
        for(int i = 0; i < numberOfDays; i++){
            runDay();
        }
        if(numberOfDays != 0)
            System.out.print(map);
/*            for(Animal animal: animalsList){
                for(int i = 0; i<8; i++) System.out.print(animal.genes[i] + " ");
                System.out.print(animal.orientation);
                System.out.println(animal.getPosition());
                System.out.println();
        }*/
    }
}
