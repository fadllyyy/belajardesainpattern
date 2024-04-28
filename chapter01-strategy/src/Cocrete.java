// Interface Strategy
interface StrategiHarga {
    double hitungHarga(double harga);
}

// Concrete Strategy: Elektronik
class StrategiElektronik implements StrategiHarga {
    @Override
    public double hitungHarga(double harga) {
        return harga * 1.1; // Harga produk elektronik dikenakan pajak 10%
    }
}

// Concrete Strategy: Makanan
class StrategiMakanan implements StrategiHarga {
    @Override
    public double hitungHarga(double harga) {
        return harga; // Harga produk makanan tidak dikenakan pajak
    }
}

// Context
class PenentuHargaProduk {
    private StrategiHarga strategiHarga;

    public PenentuHargaProduk(StrategiHarga strategiHarga) {
        this.strategiHarga = strategiHarga;
    }

    public void setStrategiHarga(StrategiHarga strategiHarga) {
        this.strategiHarga = strategiHarga;
    }

    public double hitungHargaAkhir(double harga) {
        return strategiHarga.hitungHarga(harga);
    }
}

//Main untuk program Cocrete
class Mainn {
    public static void main(String[] args) {
        // Inisialisasi contoh concrete strategy
        StrategiHarga strategiElektronik = new StrategiElektronik();
        StrategiHarga strategiMakanan = new StrategiMakanan();

        // Inisialisasi context dengan strategi default
        PenentuHargaProduk penentuHargaProduk = new PenentuHargaProduk(strategiMakanan);

        // Penggunaan strategy
        double hargaProdukElektronik = 100;
        System.out.println("Harga Akhir Produk Elektronik: " + penentuHargaProduk.hitungHargaAkhir(hargaProdukElektronik));

        // Mengganti strategi saat runtime
        penentuHargaProduk.setStrategiHarga(strategiElektronik);
        System.out.println("Harga Akhir Produk Elektronik dengan Pajak: " + penentuHargaProduk.hitungHargaAkhir(hargaProdukElektronik));
    }
}