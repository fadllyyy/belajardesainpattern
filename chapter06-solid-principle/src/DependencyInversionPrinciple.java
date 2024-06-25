interface DapatDihidupkan {
    void hidupkan();
    void matikan();
}

class Lampu implements DapatDihidupkan {
    public void hidupkan() {
        System.out.println("Lampu: dinyalakan...");
    }

    public void matikan() {
        System.out.println("Lampu: dimatikan...");
    }
}

class Saklar {
    private DapatDihidupkan perangkat;

    public Saklar(DapatDihidupkan perangkat) {
        this.perangkat = perangkat;
    }

    public void operasikan(boolean nyala) {
        if (nyala) {
            perangkat.hidupkan();
        } else {
            perangkat.matikan();
        }
    }
}

class Main {
    public static void main(String[] args) {
        DapatDihidupkan lampu = new Lampu();
        Saklar saklar = new Saklar(lampu);
        saklar.operasikan(true);
        saklar.operasikan(false);
    }
}