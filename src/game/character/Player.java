package game.character;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int healingPotions;
    private boolean hasArmor;
    private int supernovaUses;
    private int blackHoleUses;
    private int cosmicStrikeUses;

    public Player(String name, int health, int attack, int defense, int healingPotions, boolean hasArmor) {
        this.name = name;
        this.health = health;
        this.maxHealth = health; // maxHealth diinisialisasi dengan health
        this.attack = attack;
        this.defense = defense;
        this.healingPotions = healingPotions;
        this.hasArmor = hasArmor;
        this.supernovaUses = 2; // Misalnya kamu mulai dengan 2 kali penggunaan
        this.blackHoleUses = 2; // Misalnya kamu mulai dengan 2 kali penggunaan
        this.cosmicStrikeUses = 2; // Misalnya kamu mulai dengan 2 kali penggunaan
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealingPotions() {
        return healingPotions;
    }

    public void setHealingPotions(int healingPotions) {
        this.healingPotions = healingPotions;
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    public void setArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    public int getSupernovaUses() {
        return supernovaUses;
    }

    public void useSupernova() {
        if (supernovaUses > 0) {
            supernovaUses--;
        }
    }

    public int getBlackHoleUses() {
        return blackHoleUses;
    }

    public void useBlackHole() {
        if (blackHoleUses > 0) {
            blackHoleUses--;
        }
    }

    public int getCosmicStrikeUses() {
        return cosmicStrikeUses;
    }

    public void useCosmicStrike() {
        if (cosmicStrikeUses > 0) {
            cosmicStrikeUses--;
        }
    }

    public void useHealingPotion() {
        if (healingPotions > 0) {
            healingPotions--;
            health = maxHealth; // Misalnya, penyembuhan penuh
        }
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void resetSkillUses() {
        supernovaUses = 2;
        blackHoleUses = 2;
        cosmicStrikeUses = 2;
    }

    public void addHealingPotion(int amount) {
        this.healingPotions += amount;
    }
}
