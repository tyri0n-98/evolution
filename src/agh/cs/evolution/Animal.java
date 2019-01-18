package agh.cs.evolution;

import java.util.Random;

public class Animal implements IMapElement{
    private Position position;
    private int[] genes = new int[8];
    private int energy;
    private int maxEnergy = 100;
    private Direction orientation;
    private WorldMap map;

    public Animal(Position pos, Direction orientation, WorldMap map){
        this.position = pos;
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
        this.genes = genes;
        this.energy = this.maxEnergy;
        this.map = map;
    }

    public boolean canReproduce(){
        return this.energy >= 50;
    }

    public int[] mutate(){
        Random randomGenerator = new Random();
        int geneToMutate = randomGenerator.nextInt(7);
        int mutationValue = randomGenerator.nextInt(3) - 1;
        if(this.genes[geneToMutate] == 1 && mutationValue == -1) return this.genes;
        int[] mutatedGenes = this.genes;
        mutatedGenes[geneToMutate] += mutationValue;
        return mutatedGenes;
    }

    public void eat(){
        int energyValue = 25;
        if(this.energy + energyValue > maxEnergy) this.energy = maxEnergy;
        else this.energy += energyValue;
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
        return new Animal(this.position.subtract(new Position(0, 1)), this.orientation, this.mutate(), this.map);
    }

    public Position getPosition(){
        return this.position;
    }

    public String toString(){
        return "A";
    }
}
