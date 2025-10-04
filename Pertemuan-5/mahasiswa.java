import java.util.ArrayList;

public class Mahasiswa {
    public String nama;
    public String nim;
    
    public ArrayList<MataKuliah> matkulDiambil;
    private final int SKS_MAX = 24; 

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
        this.matkulDiambil = new ArrayList<>();
    }


    public void ambilMatkul(MataKuliah mk) {
        if (matkulDiambil.contains(mk)) {
            System.out.println("Gagal: Mata kuliah " + mk.namaMK + " sudah ada di FRS Anda.");
            return;
        }

        if (mk.kuota <= 0) {
            System.out.println("Gagal: Mata kuliah " + mk.namaMK + " sudah penuh (kuota habis).");
            return;
        }
        
        if (hitungTotalSKS() + mk.sks > SKS_MAX) {
            System.out.println("Gagal: Total SKS akan melebihi batas maksimum (" + SKS_MAX + " SKS).");
            return;
        }

        matkulDiambil.add(mk);
        mk.kurangiKuota();
        System.out.println("Berhasil mengambil: " + mk.namaMK + ". SKS saat ini: " + hitungTotalSKS());
    }

    public void dropMatkul(String namaMK) {
        MataKuliah mkToRemove = null;
        for (MataKuliah mk : matkulDiambil) {
            if (mk.namaMK.equalsIgnoreCase(namaMK)) {
                mkToRemove = mk;
                break;
            }
        }

        if (mkToRemove != null) {
            matkulDiambil.remove(mkToRemove);
            mkToRemove.tambahKuota();
            System.out.println("Berhasil drop mata kuliah: " + mkToRemove.namaMK);
        } else {
            System.out.println("Gagal drop: Mata kuliah " + namaMK + " tidak ditemukan di FRS Anda.");
        }
    }
    
    private int hitungTotalSKS() {
        int totalSKS = 0;
        for (MataKuliah mk : matkulDiambil) {
            totalSKS += mk.sks;
        }
        return totalSKS;
    }

    public void tampilkanFRS() {
        System.out.println("\n==================================");
        System.out.println("  FRS Mahasiswa: " + nama + " (" + nim + ")");
        System.out.println("==================================");
        if (matkulDiambil.isEmpty()) {
            System.out.println("Belum ada mata kuliah yang diambil.");
            System.out.println("----------------------------------");
            return;
        }
        
        int counter = 1;
        for (MataKuliah mk : matkulDiambil) {
            System.out.println(counter++ + ". " + mk);
        }
        System.out.println("----------------------------------");
        System.out.println("Total SKS Diambil: " + hitungTotalSKS());
        System.out.println("==================================");
    }
}
