package agh.cs.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement{
    private Position position;
    private int[] genes = new int[8];
    private int energy;
    private int maxEnergy = 100;
    private Direction orientation;
    private WorldMap map;
    private int age;

    public Animal(Position pos, Direction orientation, WorldMap map){
        this.position = pos;
        this.age = 0;
        this.energy = this.maxEnergy;
        this.orientation = orientation;
        this.map = map;
        for(int i = 0; i<8; i++){
            this.genes[i] = 1;
        }
    }

    public Animal(Position pos, Direction orientation, int[] genes, WorldMap map){
        this.position = pos;
        this.orientation = orientation;
        this.age = 0;
        this.genes = genes;
        this.energy = this.maxEnergy;
        this.map = map;
    }

    public boolean canReproduce(){
        return this.energy >= 50 && this.age >= 5;
    }

    public int[] mutate(){
        Random randomGenerator = new Random();
        int geneToMutate = randomGenerator.nextInt(8);
        int mutationValue = randomGenerator.nextInt(3) - 1;
        if(this.genes[geneToMutate] == 1 && mutationValue == -1) return this.genes;
        int[] mutatedGenes = this.genes;
        mutatedGenes[geneToMutate] += mutationValue;
        return mutatedGenes;
    }

    public void chooseDirection(){
        List<Integer> genesList = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i < this.genes.length; i++){
            for(int j = 0; j < this.genes[i]; j++){
                genesList.add(i);
                sum += 1;
            }
        }
        Random randomGenerator = new Random();
        int chosenDirection = genesList.get(randomGenerator.nextInt(sum));
        this.orientation = this.orientation.multipleNext(chosenDirection);
    }

    public void eat(){
        int energyValue = 25;
        if(this.energy + energyValue > maxEnergy) this.energy = maxEnergy;
        else this.energy += energyValue;
    }

    public void dayPassed(){
        this.age += 1;
        this.energy -= 10;
    }

    public void moveForward(){
        Position movePosition = this.position.add(this.orientation.getVector());
        if(map.canMoveTo(movePosition)){
            Position beforeMove = this.position;
            this.position = movePosition;
            map.positionChanged(beforeMove, movePosition);
        }
    }

    //TODO Correct method reproduce
    public Animal reproduce(){
        if(this.canReproduce())
            return new Animal(this.position.subtract(new Position(0, 1)), this.orientation, this.mutate(), this.map);
        else
            return null;
    }

    public Position getPosition(){
        return this.position;
    }

    public String toString(){
        return "A";
    }
}
