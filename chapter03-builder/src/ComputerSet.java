public class Computer {
    private String brand;
    private String model;

    public Computer(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return brand + " " + model;
    }
}

class ComputerSet {
    private Computer computer;
    private String keyboard, mouse, speaker, monitor;

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

}

class ComputerSetBuilder {
    private ComputerSet computerSet;

    public ComputerSetBuilder() {
        this(new ComputerSet());
    }

    public ComputerSetBuilder(ComputerSet computerSet) {
        this.computerSet = computerSet;
    }

    public void computer(Computer computer) {
        computerSet.setComputer(computer);
    }

    public void keyboard(String keyboard) {
        computerSet.setKeyboard(keyboard);
    }

    public void mouse(String mouse) {
        computerSet.setMouse(mouse);
    }

    public void speaker(String speaker) {
        computerSet.setSpeaker(speaker);
    }

    public void monitor(String monitor) {
        computerSet.setMonitor(monitor);
    }

    public ComputerSet getResult() {
        return computerSet;
    }

    public void resetBuilder() {
        computerSet = new ComputerSet();
    }
}

class Mains {
    public static void main(String[] args) {
        // Membuat builder untuk membangun sebuah ComputerSet
        ComputerSetBuilder builder = new ComputerSetBuilder();

        // Memasukkan spesifikasi komputer ke dalam builder
        builder.computer(new Computer("HP", "Gaming")); // Ganti dengan merek dan model komputer yang diinginkan
        builder.keyboard("Keyboard mekanis"); // Jenis keyboard
        builder.mouse("Mouse nirkabel"); // Jenis mouse
        builder.speaker("Sistem speaker 2.1"); // Jenis speaker
        builder.monitor("Monitor IPS 27 inci"); // Jenis monitor

        // Mendapatkan hasil dari pembangunan ComputerSet
        ComputerSet computerSet = builder.getResult();

        // Menampilkan hasil ComputerSet
        System.out.println("Spesifikasi ComputerSet :");
        System.out.println("Computer: " + computerSet.getComputer());
        System.out.println("Keyboard: " + computerSet.getKeyboard());
        System.out.println("Mouse: " + computerSet.getMouse());
        System.out.println("Speaker: " + computerSet.getSpeaker());
        System.out.println("Monitor: " + computerSet.getMonitor());
    }
}