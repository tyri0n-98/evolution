package agh.cs.evolution;

import java.util.Random;

public class Animal implements IMapElement{
    private Position position;
    private int[] genes = new int[8];
    private int energy;
    private int maxEnergy = 100;
    private Direction orientation;

    public Animal(Position pos, Direction orientation){
        this.position = pos;
        this.energy = this.maxEnergy;
        this.orientation = orientation;
        for(int i = 0; i<8; i++){
            this.genes[i] = 1;
        }
    }
    public Animal(Position pos, Direction orientation, int[] genes){
        this.position = pos;
        this.orientation = orientation;
        this.genes = genes;
        this.energy = this.maxEnergy;
    }

    public boolean canReproduce(){
        return this.energy >= 50;
    }

    private int[] mutate(){
        Random randomGenerator = new Random();
        int geneToMutate = randomGenerator.nextInt(7);
        int mutationValue = randomGenerator.nextInt(3) - 1;
        if(this.genes[geneToMutate] == 1 && mutationValue == -1) return this.genes;
        int[] mutatedGenes = this.genes;
        mutatedGenes[geneToMutate] += mutationValue;
        return mutatedGenes;
    }

    public Animal reproduce(){
        return new Animal(this.position.subtract(new Position(0, 1)), this.orientation, this.mutate());
    }

    public Position getPosition(){
        return this.position;
    }
}
