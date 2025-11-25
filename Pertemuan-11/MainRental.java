import java.util.ArrayList;

public class MainRental
{
    private static ArrayList<Kendaraan> daftarKendaraan = new ArrayList<Kendaraan>();
    private static ArrayList<Penyewa> daftarPenyewa = new ArrayList<Penyewa>();

    public static void main(String[] args)
    {
        Mobil mobil1 = new Mobil("Toyota", "Avanza", 2022, 4);
        Mobil mobil2 = new Mobil("Honda", "Civic", 2023, 4);
        Motor motor1 = new Motor("Yamaha", "NMax", 2022, 2);
        Motor motor2 = new Motor("Honda", "PCX", 2021, 2);
        Sepeda sepeda1 = new Sepeda("Giant", "XTC", 2020, "BMX");
        Sepeda sepeda2 = new Sepeda("Trek", "FX", 2023, "Balap");

        tambahKendaraan(mobil1);
        tambahKendaraan(mobil2);
        tambahKendaraan(motor1);
        tambahKendaraan(motor2);
        tambahKendaraan(sepeda1);
        tambahKendaraan(sepeda2);

        Penyewa penyewa1 = new Penyewa("P001", "Reza Firmansyah", "123456789");
        Penyewa penyewa2 = new Penyewa("P002", "Siti Nurhaliza", "987654321");
        Penyewa penyewa3 = new Penyewa("P003", "Budi Santoso", "555666777");

        tambahPenyewa(penyewa1);
        tambahPenyewa(penyewa2);
        tambahPenyewa(penyewa3);

        System.out.println("======== SISTEM MANAJEMEN RENTAL KENDARAAN ========");

        displayDaftarKendaraanTersedia();

        rentalKendaraan("P001", mobil1);
        rentalKendaraan("P002", motor1);
        rentalKendaraan("P003", sepeda1);

        displayDaftarKendaraanTersedia();

        displayDaftarPenyewa();

        displayAllKendaraan();
    }

    public static void tambahKendaraan(Kendaraan kendaraan)
    {
        daftarKendaraan.add(kendaraan);
    }

    public static void tambahPenyewa(Penyewa penyewa)
    {
        daftarPenyewa.add(penyewa);
    }

    public static void rentalKendaraan(String idPenyewa, Kendaraan kendaraan)
    {
        for(Penyewa p : daftarPenyewa) {
            if(p.getIdPenyewa().equals(idPenyewa)) {
                p.setKendaraanDisewa(kendaraan);
                daftarKendaraan.remove(kendaraan);
                System.out.println("Kendaraan berhasil disewa oleh " + p.getNama());
                return;
            }
        }
        System.out.println("Penyewa tidak ditemukan!");
    }

    public static void displayDaftarKendaraanTersedia()
    {
        System.out.println("\n========== DAFTAR KENDARAAN TERSEDIA ==========");
        if(daftarKendaraan.isEmpty()) {
            System.out.println("Tidak ada kendaraan tersedia");
        } else {
            for(int i = 0; i < daftarKendaraan.size(); i++) {
                System.out.println((i + 1) + ". " + daftarKendaraan.get(i).getInfoKendaraan());
            }
        }
        System.out.println("==============================================\n");
    }

    public static void displayDaftarPenyewa()
    {
        System.out.println("\n========== DAFTAR PENYEWA & KENDARAAN ==========");
        if(daftarPenyewa.isEmpty()) {
            System.out.println("Tidak ada data penyewa");
        } else {
            for(Penyewa p : daftarPenyewa) {
                p.displayInfoPenyewa();
            }
        }
        System.out.println("==============================================\n");
    }

    public static void displayAllKendaraan()
    {
        System.out.println("\n========== SEMUA KENDARAAN (Tersedia + Disewa) ==========");
        for(Kendaraan k : daftarKendaraan) {
            System.out.println(k.getInfoKendaraan());
        }
        for(Penyewa p : daftarPenyewa) {
            if(p.getKendaraanDisewa() != null) {
                System.out.println("[DISEWA] " + p.getKendaraanDisewa().getInfoKendaraan());
            }
        }
        System.out.println("========================================================\n");
    }
}
