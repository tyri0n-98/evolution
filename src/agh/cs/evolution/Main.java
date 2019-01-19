package agh.cs.evolution;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        WorldMap map = new WorldMap(100,30,10,10);
        Animal animal = new Animal(new Position(50,15), Direction.EAST, map);
/*        map.place(animal);
        int[] genes = {1,1,2,2,1,1,3,4};
        Animal animal1 = animal.reproduce();
        if(animal1 != null) map.place(animal1);
        for(int i=0; i<8; i++) System.out.print(animal.genes[i] + " ");
        System.out.println();
        if(animal1 != null)
            for(int i=0; i<8; i++) System.out.print(animal1.genes[i] + " ");*/
        map.place(animal);
        map.spawnPlants();
        System.out.print(map);
        List<Animal> animalList = new LinkedList<>();
        animalList.add(animal);
        DaysController daysController = new DaysController(map, animalList);
        for(int i = 0; i < 10000; i++) daysController.runDays(1);
    }
}