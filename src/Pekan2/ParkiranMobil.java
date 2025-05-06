package Pekan2;

import java.util.ArrayList;
import java.util.Scanner;

class Mobil {
    String platNomor;

    public Mobil(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPlatNomor() {
        return platNomor;
    }
}

class Parkiran {
    ArrayList<Mobil> daftarMobil = new ArrayList<>();

    public Parkiran() {
        // Tambah 7 mobil awal
        String[] platAwal = {
            "BA 1111 AA", "BA 2222 BB", "BA 3333 CC", "BA 4444 DD",
            "BA 5555 EE", "BA 6666 FF", "BA 7777 GG"
        };
        for (String plat : platAwal) {
            daftarMobil.add(new Mobil(plat));
        }
    }

    public void tambahMobil(String platNomor) {
        daftarMobil.add(new Mobil(platNomor));
        System.out.println("Mobil dengan plat " + platNomor + " berhasil ditambahkan.");
    }

    public void keluarkanMobil(String platNomor) {
        boolean ditemukan = false;
        for (int i = 0; i < daftarMobil.size(); i++) {
            if (daftarMobil.get(i).getPlatNomor().equalsIgnoreCase(platNomor)) {
                daftarMobil.remove(i);
                System.out.println("Mobil dengan plat " + platNomor + " berhasil dikeluarkan.");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan.");
        }
    }

    public void tampilkanParkiran() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Parkiran kosong.");
        } else {
            System.out.println("Isi Parkiran:");
            for (Mobil m : daftarMobil) {
                System.out.println("- " + m.getPlatNomor());
            }
        }
    }

    public void cariMobil(String platNomor) {
        boolean ditemukan = false;
        for (Mobil m : daftarMobil) {
            if (m.getPlatNomor().equalsIgnoreCase(platNomor)) {
                System.out.println("Mobil dengan plat " + platNomor + " ditemukan di parkiran.");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan.");
        }
    }
}

public class ParkiranMobil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parkiran parkiran = new Parkiran();
        int pilihan;

        do {
            System.out.println("\n=== Menu Parkiran Mobil ===");
            System.out.println("1. Tambah mobil ke Parkiran");
            System.out.println("2. Keluarkan mobil dari Parkiran");
            System.out.println("3. Tampilkan isi Parkiran");
            System.out.println("4. Cari mobil di Parkiran");
            System.out.println("5. Keluar");
            System.out.print("Pilih Opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // untuk mengonsumsi newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan plat nomor mobil: ");
                    String platMasuk = scanner.nextLine();
                    parkiran.tambahMobil(platMasuk);
                    break;
                case 2:
                    System.out.print("Masukkan plat nomor mobil yang ingin dikeluarkan: ");
                    String platKeluar = scanner.nextLine();
                    parkiran.keluarkanMobil(platKeluar);
                    break;
                case 3:
                    parkiran.tampilkanParkiran();
                    break;
                case 4:
                    System.out.print("Masukkan plat nomor mobil yang dicari: ");
                    String platCari = scanner.nextLine();
                    parkiran.cariMobil(platCari);
                    break;
                case 5:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
