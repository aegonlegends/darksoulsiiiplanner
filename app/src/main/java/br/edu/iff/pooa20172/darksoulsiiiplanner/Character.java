package br.edu.iff.pooa20172.darksoulsiiiplanner;

public class Character {
    int level, covenant;
    String name;

    public Character(String name, int level, int covenant) {
        this.level = level;
        this.covenant = covenant;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCovenant() {
        return covenant;
    }

    public void setCovenant(int covenant) {
        this.covenant = covenant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
