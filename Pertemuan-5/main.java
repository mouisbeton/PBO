import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Dosen pakIlham = new Dosen("Pak Ilham Gurat A", "00004");
        Dosen pakIrfan = new Dosen("Pak Irfan Subakti", "00003");
        Dosen buLicha = new Dosen("Bu Adhatus Solicha", "00001");
        Dosen pakFB = new Dosen("Pak Fajar Baskoro", "00002");

        ArrayList<MataKuliah> daftarMK = new ArrayList<>();
        daftarMK.add(new MataKuliah("Matematika Diskrit (A)", 3, pakIlham));
        daftarMK.add(new MataKuliah("Pemrograman Berorientasi Obyek (A)", 3, pakFB)); 
        daftarMK.add(new MataKuliah("Pemrograman Web (D)", 3, pakIrfan));
        daftarMK.add(new MataKuliah("Sistem Basis Data (C)", 3, buLicha));

        System.out.println("=== PENGATURAN MAHASISWA ===");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMhs = sc.nextLine();
        System.out.print("Masukkan NRP mahasiswa: ");
        String nimMhs = sc.nextLine();
        Mahasiswa mhs = new Mahasiswa(namaMhs, nimMhs);
        System.out.println("Selamat datang, " + mhs.nama + "!");

        int pilihan;
        do {
            System.out.println("\n=== SISTEM AKADEMIK FRS ===");
            System.out.println("1. Info Mata Kuliah Tersedia");
            System.out.println("2. Ambil Mata Kuliah");
            System.out.println("3. Drop Mata Kuliah");
            System.out.println("4. Tampilkan FRS Saat Ini");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                pilihan = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                pilihan = -1; 
            }

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- INFO MATA KULIAH ---");
                    for (int i = 0; i < daftarMK.size(); i++) {
                        MataKuliah mk = daftarMK.get(i);
                        System.out.println((i + 1) + ". " + mk.namaMK + " (" + mk.sks + " SKS) | Dosen: " + mk.dosen.nama + " | Kuota tersisa: " + mk.kuota);
                    }
                    break;

                case 2:
                    System.out.println("\n--- AMBIL MATA KULIAH ---");
                    if (daftarMK.isEmpty()) {
                        System.out.println("Tidak ada mata kuliah yang tersedia.");
                        break;
                    }
                    
                    for (int i = 0; i < daftarMK.size(); i++) {
                        MataKuliah mk = daftarMK.get(i);
                        System.out.println((i + 1) + ". " + mk.namaMK + " (SKS: " + mk.sks + ", Kuota: " + mk.kuota + ")");
                    }
                    
                    System.out.print("Pilih nomor mata kuliah: ");
                    try {
                        int pilihMK = Integer.parseInt(sc.nextLine());
                        if (pilihMK > 0 && pilihMK <= daftarMK.size()) {
                            mhs.ambilMatkul(daftarMK.get(pilihMK - 1));
                        } else {
                            System.out.println("Pilihan tidak valid!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                    }
                    break;

                case 3:
                    if (mhs.matkulDiambil.isEmpty()) {
                        System.out.println("\nBelum ada mata kuliah yang diambil untuk di-drop.");
                        break;
                    }

                    System.out.println("\n--- MATA KULIAH YANG DIAMBIL ---");
                    for (int i = 0; i < mhs.matkulDiambil.size(); i++) {
                        MataKuliah mkDrop = mhs.matkulDiambil.get(i);
                        System.out.println((i + 1) + ". " + mkDrop.namaMK + " (Dosen: " + mkDrop.dosen.nama + ")");
                    }

                    System.out.print("\nPilih nomor mata kuliah yang ingin didrop: ");
                    try {
                        int dropIdx = Integer.parseInt(sc.nextLine());
                        if (dropIdx > 0 && dropIdx <= mhs.matkulDiambil.size()) {
                            MataKuliah mkDrop = mhs.matkulDiambil.get(dropIdx - 1);
                            mhs.dropMatkul(mkDrop.namaMK); 
                        } else {
                            System.out.println("Pilihan tidak valid!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                    }
                    break;

                case 4:
                    mhs.tampilkanFRS();
                    break;

                case 0:
                    System.out.println("Keluar dari sistem FRS. Sampai jumpa!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Silakan masukkan nomor menu yang benar.");
            }
        } while (pilihan != 0);
        sc.close();
    }
}
