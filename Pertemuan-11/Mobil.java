public class Mobil extends Kendaraan
{
    private int jumlahRoda;

    public Mobil(String merk, String model, int tahunProduksi, int jumlahRoda)
    {
        super(merk, model, tahunProduksi);
        this.jumlahRoda = jumlahRoda;
    }    public int getJumlahRoda()
    {
        return jumlahRoda;
    }

    public String getInfoKendaraan()
    {
        return "MOBIL | Merk: " + merk + " | Model: " + model + 
               " | Tahun: " + tahunProduksi + " | Roda: " + jumlahRoda;
    }
}
