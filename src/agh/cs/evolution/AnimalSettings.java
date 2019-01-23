package agh.cs.evolution;

public class AnimalSettings {
    private static final int DEFAULT_DAILY_ENERGY_LOSS = 5;
    private static final int DEFAULT_ENERGY_TO_REPRODUCE = 95;
    private static final int DEFAULT_ENERGY_GAIN = 90;
    private static final int DEFAULT_AGE_TO_REPRODUCE = 2;

    private int dailyEnergyLoss;
    private int energyToReproduce;
    private int energyGain;
    private int ageToReproduce;

    public AnimalSettings(){
        setDefaults();
    }

    public void setDefaults(){
        this.dailyEnergyLoss = DEFAULT_DAILY_ENERGY_LOSS;
        this.energyToReproduce = DEFAULT_ENERGY_TO_REPRODUCE;
        this.energyGain = DEFAULT_ENERGY_GAIN;
        this.ageToReproduce = DEFAULT_AGE_TO_REPRODUCE;
    }

    public void setDailyEnergyLoss(int dailyEnergyLoss) {
        this.dailyEnergyLoss = dailyEnergyLoss;
    }

    public void setEnergyToReproduce(int energyToReproduce) {
        this.energyToReproduce = energyToReproduce;
    }

    public void setEnergyGain(int energyGain) {
        this.energyGain = energyGain;
    }

    public void setAgeToReproduce(int ageToReproduce) {
        this.ageToReproduce = ageToReproduce;
    }

    public int getDailyEnergyLoss() {
        return dailyEnergyLoss;
    }

    public int getEnergyToReproduce() {
        return energyToReproduce;
    }

    public int getEnergyGain() {
        return energyGain;
    }

    public int getAgeToReproduce() {
        return ageToReproduce;
    }
}
