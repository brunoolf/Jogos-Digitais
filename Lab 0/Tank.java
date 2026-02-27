public class Tank {
    private String name;
    private boolean alive;
    private int ammo;
    private int armor;

    public Tank(String name) {
        this.setName(name);
        this.setAlive(true);
        this.setAmmo(5);
        this.setArmor(60);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public String toString() {
        if (this.alive) {
            return this.name + " ," + this.armor + " armor, " + this.ammo + " shells";
        } else {
            return this.name + " DEADE";
        }
    }

    public void explode() {
        this.alive = false;
        System.out.println("explodes");
    }

    public void hit() {
        this.armor -= 20;
        System.out.println(this.name + " is hit");
        if (this.armor <= 0) {
            this.explode();
        }
    }

    public void fire_at(Tank enemy) {
        if (this.ammo >= 1) {
            this.ammo -= 1;
            System.out.println(this.name + " fires on " + enemy.name);
            enemy.hit();
        } else {
            System.out.println(this.name + " has no sheels!!!");
        }
    }
}
