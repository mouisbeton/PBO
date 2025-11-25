public class Penyewa
{
    private String idPenyewa;
    private String nama;
    private String nomorIdentitas;
    private Kendaraan kendaraanDisewa;

    public Penyewa(String idPenyewa, String nama, String nomorIdentitas)
    {
        this.idPenyewa = idPenyewa;
        this.nama = nama;
        this.nomorIdentitas = nomorIdentitas;
        this.kendaraanDisewa = null;
    }

    public String getIdPenyewa()
    {
        return idPenyewa;
    }

    public String getNama()
    {
        return nama;
    }

    public String getNomorIdentitas()
    {
        return nomorIdentitas;
    }

    public Kendaraan getKendaraanDisewa()
    {
        return kendaraanDisewa;
    }

    public void setKendaraanDisewa(Kendaraan kendaraan)
    {
        this.kendaraanDisewa = kendaraan;
    }

    public void displayInfoPenyewa()
    {
        System.out.println("\nID Penyewa: " + idPenyewa);
        System.out.println("Nama: " + nama);
        System.out.println("No. Identitas: " + nomorIdentitas);
        if(kendaraanDisewa != null) {
            System.out.println("Kendaraan Disewa: " + kendaraanDisewa.getInfoKendaraan());
        } else {
            System.out.println("Kendaraan Disewa: Tidak ada");
        }
    }
}
