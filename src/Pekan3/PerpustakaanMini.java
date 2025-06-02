package Pekan3;

import java.util.Scanner;
import java.util.Stack;

public class PerpustakaanMini {

    // Inner class untuk merepresentasikan buku
    static class Buku {
        String judul;

        public Buku(String judul) {
            this.judul = judul;
        }

        public String getJudul() {
            return judul;
        }

        @Override
        public String toString() {
            return judul;
        }
    }

    // Stack buku
    static Stack<Buku> tumpukanBuku = new Stack<>();

    // Fungsi tambah buku
    public static void tambahBuku(String judul) {
        tumpukanBuku.push(new Buku(judul));
        System.out.println("Buku \"" + judul + "\" telah ditambahkan ke tumpukan.");
    }

    // Fungsi ambil buku teratas
    public static void ambilBuku() {
        if (!tumpukanBuku.isEmpty()) {
            Buku buku = tumpukanBuku.pop();
            System.out.println("Buku diambil: " + buku.getJudul());
        } else {
            System.out.println("Tumpukan kosong. Tidak ada buku untuk diambil.");
        }
    }

    // Fungsi lihat tumpukan
    public static void lihatTumpukan() {
        if (tumpukanBuku.isEmpty()) {
            System.out.println("Tumpukan kosong.");
        } else {
            System.out.println("Tumpukan Buku Saat Ini:");
            for (int i = tumpukanBuku.size() - 1; i >= 0; i--) {
                System.out.println("- " + tumpukanBuku.get(i).getJudul());
            }
        }
    }

    // Fungsi cari buku
    public static void cariBuku(String judul) {
        boolean ditemukan = false;
        for (Buku buku : tumpukanBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                ditemukan = true;
                break;
            }
        }
        if (ditemukan) {
            System.out.println("Buku \"" + judul + "\" ditemukan di dalam tumpukan.");
        } else {
            System.out.println("Buku \"" + judul + "\" tidak ditemukan.");
        }
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tambahkan 7 buku awal
        String[] bukuAwal = {
            "Algoritma Dasar", "Struktur Data", "Basis Data",
            "Pemrograman Java", "Jaringan Komputer", "Sistem Operasi", "Kecerdasan Buatan"
        };
        for (String judul : bukuAwal) {
            tumpukanBuku.push(new Buku(judul)); // tanpa output
        }

        int pilihan;
        do {
            System.out.println("\n=== MENU PERPUSTAKAAN MINI ===");
            System.out.println("1. Tambah Buku ke Tumpukan");
            System.out.println("2. Ambil Buku Teratas");
            System.out.println("3. Lihat Tumpukan Buku");
            System.out.println("4. Cari Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // konsumsi newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan judul buku: ");
                    String judulBaru = scanner.nextLine();
                    tambahBuku(judulBaru);
                    break;
                case 2:
                    ambilBuku();
                    break;
                case 3:
                    lihatTumpukan();
                    break;
                case 4:
                    System.out.print("Masukkan judul buku yang dicari: ");
                    String dicari = scanner.nextLine();
                    cariBuku(dicari);
                    break;
                case 5:
                    System.out.println("Program selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
