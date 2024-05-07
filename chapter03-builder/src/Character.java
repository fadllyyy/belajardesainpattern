class Character {
    private String name;
    private String type;
    private String strength;
    private String weapon;

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Character:"
                + "\n-----------"
                + "\nName: " + getName()
                + "\nType: " + getType()
                + "\nStrength: " + getStrength()
                + "\nWeapon: " + getWeapon();
    }
}

// Builder untuk membangun karakter
class CharacterBuilder {
    private Character character;

    public CharacterBuilder() {
        this(new Character());
    }

    public CharacterBuilder(Character character) {
        this.character = character;
    }

    public CharacterBuilder name(String name) {
        character.setName(name);
        return this;
    }

    public CharacterBuilder type(String type) {
        character.setType(type);
        return this;
    }

    public CharacterBuilder strength(String strength) {
        character.setStrength(strength);
        return this;
    }

    public CharacterBuilder weapon(String weapon) {
        character.setWeapon(weapon);
        return this;
    }

    public Character build() {
        return character;
    }
}

// Kelas untuk mengarahkan pembangunan karakter
class CharacterDirector {
    // Singleton
    private static CharacterDirector director = null;

    private CharacterDirector() {}

    public static synchronized CharacterDirector getInstance() {
        if (director == null) {
            director = new CharacterDirector();
        }
        return director;
    }

    // Metode untuk membangun karakter pahlawan
    public Character buildHero() {
        CharacterBuilder builder = new CharacterBuilder();
        builder.name("Hero");
        builder.type("Warrior");
        builder.strength("High");
        builder.weapon("Sword");
        return builder.build();
    }

    // Metode untuk membangun karakter musuh
    public Character buildEnemy() {
        CharacterBuilder builder = new CharacterBuilder();
        builder.name("Enemy");
        builder.type("Monster");
        builder.strength("High");
        builder.weapon("Claws");
        return builder.build();
    }
}

class Main {
    public static void main(String[] args) {
        // Membuat karakter pahlawan
        CharacterDirector director = CharacterDirector.getInstance();
        Character hero = director.buildHero();
        System.out.println("Hero:");
        System.out.println(hero);

        System.out.println(); // Spasi

        // Membuat karakter musuh
        Character enemy = director.buildEnemy();
        System.out.println("Enemy:");
        System.out.println(enemy);
    }
}
