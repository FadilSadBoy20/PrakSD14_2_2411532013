package Pekan5;

import java.util.Scanner;

public class AntrianPasien {
    // Node class untuk single linked list
    static class Node {
        int noAntrian;
        String nama;
        String keluhan;
        Node next;

        Node(int noAntrian, String nama, String keluhan) {
            this.noAntrian = noAntrian;
            this.nama = nama;
            this.keluhan = keluhan;
            this.next = null;
        }
    }

    private Node head = null;

    public void tambahPasien(int noAntrian, String nama, String keluhan) {
        Node baru = new Node(noAntrian, nama, keluhan);
        if (head == null) {
            head = baru;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = baru;
        }
        System.out.println("\nData pasien berhasil ditambahkan!");
    }

    public void tampilkanAntrian() {
        if (isEmpty()) {
            System.out.println("\nAntrian kosong.");
            return;
        }

        System.out.println("\n--- Daftar Antrian Pasien ---");
        Node current = head;
        int nomor = 1;
        while (current != null) {
            System.out.println(nomor + ". [" + current.noAntrian + "] " + current.nama + " - " + current.keluhan);
            current = current.next;
            nomor++;
        }
    }

    public void hapusPasienPertama() {
        if (isEmpty()) {
            System.out.println("\nAntrian kosong. Tidak ada pasien yang bisa dilayani.");
        } else {
            System.out.println("\nPasien dengan nama " + head.nama + " telah dilayani (dihapus dari antrian).");
            head = head.next;
        }
    }

    public void cariPasien(String nama) {
        if (isEmpty()) {
            System.out.println("\nAntrian kosong.");
            return;
        }

        Node current = head;
        boolean ditemukan = false;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                System.out.println("\nPasien ditemukan: [" + current.noAntrian + "] " + current.nama + " - " + current.keluhan);
                ditemukan = true;
                break;
            }
            current = current.next;
        }

        if (!ditemukan) {
            System.out.println("\nPasien dengan nama '" + nama + "' tidak ditemukan dalam antrian.");
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int hitungPasien() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Main program interaktif
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AntrianPasien antrian = new AntrianPasien();
        int pilihan;

        do {
            System.out.println("\n=== SISTEM ANTRIAN PASIEN KLINIK ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Tampilkan Antrian");
            System.out.println("3. Layani Pasien (Hapus Antrian Pertama)");
            System.out.println("4. Cari Pasien");
            System.out.println("5. Jumlah Pasien");
            System.out.println("6. Keluar");
            System.out.print("\nPilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // flush enter

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nomor Antrian: ");
                    int no = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Pasien: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Keluhan: ");
                    String keluhan = scanner.nextLine();
                    antrian.tambahPasien(no, nama, keluhan);
                    break;
                case 2:
                    antrian.tampilkanAntrian();
                    break;
                case 3:
                    antrian.hapusPasienPertama();
                    break;
                case 4:
                    System.out.print("Masukkan Nama Pasien yang dicari: ");
                    String cariNama = scanner.nextLine();
                    antrian.cariPasien(cariNama);
                    break;
                case 5:
                    System.out.println("\nJumlah pasien saat ini: " + antrian.hitungPasien());
                    break;
                case 6:
                    System.out.println("\nTerima kasih telah menggunakan sistem antrian.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid!");
            }
        } while (pilihan != 6);

        scanner.close();
    }
}


