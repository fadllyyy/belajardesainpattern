// Antarmuka yang lebih kecil dan spesifik
interface Pekerja {
    void bekerja();
}

interface Pemakan {
    void makan();
}

interface Tidur {
    void tidur();
}

class PekerjaManusia implements Pekerja, Pemakan, Tidur {
    public void bekerja() {
        System.out.println("Manusia bekerja...");
    }

    public void makan() {
        System.out.println("Manusia makan...");
    }

    public void tidur() {
        System.out.println("Manusia tidur...");
    }
}

class PekerjaRobot implements Pekerja {
    public void bekerja() {
        System.out.println("Robot bekerja...");
    }
}

class Main {
    public static void main(String[] args) {
        PekerjaManusia manusia = new PekerjaManusia();
        PekerjaRobot robot = new PekerjaRobot();

        manusia.bekerja();
        manusia.makan();
        manusia.tidur();

        robot.bekerja();
    }
}