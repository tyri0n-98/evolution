package agh.cs.evolution;

public class MapSettings {
    private static final int DEFAULT_MAP_WIDTH = 50;
    private static final int DEFAULT_MAP_HEIGHT = 30;
    private static final int DEFAULT_JUNGLE_WIDTH = 10;
    private static final int DEFAULT_JUNGLE_HEIGHT = 10;

    private int mapWidth;
    private int mapHeight;
    private int jungleWidth;
    private int jungleHeight;

    public MapSettings(){
        setDefaults();
    }

    public void setDefaults(){
        mapWidth = DEFAULT_MAP_WIDTH;
        mapHeight = DEFAULT_MAP_HEIGHT;
        jungleWidth = DEFAULT_JUNGLE_WIDTH;
        jungleHeight = DEFAULT_JUNGLE_HEIGHT;
    }

    public void setMapSize(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

}
