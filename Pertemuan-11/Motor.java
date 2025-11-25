public class Motor extends Kendaraan
{
    private int jumlahRoda;

    public Motor(String merk, String model, int tahunProduksi, int jumlahRoda)
    {
        super(merk, model, tahunProduksi);
        this.jumlahRoda = jumlahRoda;
    }    public int getJumlahRoda()
    {
        return jumlahRoda;
    }

    public String getInfoKendaraan()
    {
        return "MOTOR | Merk: " + merk + " | Model: " + model + 
               " | Tahun: " + tahunProduksi + " | Roda: " + jumlahRoda;
    }
}
