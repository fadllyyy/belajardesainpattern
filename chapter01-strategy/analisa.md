Analisa tugas Cocrete:

Interface Strategy (StrategiHarga) (baris 1-3) :
Merupakan kontrak yang mendefinisikan metode hitungHarga() yang harus diimplementasikan oleh semua strategi konkret.
Concrete Strategies (baris 6-20) :
StrategiElektronik: Mengimplementasikan metode hitungHarga() untuk menghitung harga akhir produk elektronik dengan menambahkan pajak sebesar 10% dari harga awal.
StrategiMakanan: Mengimplementasikan metode hitungHarga() untuk mengembalikan harga produk makanan tanpa penambahan pajak.
Context (PenentuHargaProduk) (baris 22-37) :
Merupakan kelas yang menggunakan objek strategi untuk menghitung harga akhir produk.
Memiliki metode setStrategiHarga() untuk mengatur strategi yang digunakan saat runtime.
Memiliki metode hitungHargaAkhir() yang memanggil metode hitungHarga() dari objek strategi yang sedang digunakan.
