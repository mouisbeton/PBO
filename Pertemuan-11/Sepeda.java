public class Sepeda extends Kendaraan
{
    private String jenisSepeda;

    public Sepeda(String merk, String model, int tahunProduksi, String jenisSepeda)
    {
        super(merk, model, tahunProduksi);
        this.jenisSepeda = jenisSepeda;
    }    public String getJenisSepeda()
    {
        return jenisSepeda;
    }

    public String getInfoKendaraan()
    {
        return "SEPEDA | Merk: " + merk + " | Model: " + model + 
               " | Tahun: " + tahunProduksi + " | Jenis: " + jenisSepeda;
    }
}
