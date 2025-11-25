public class Kendaraan
{
    protected String merk;
    protected String model;
    protected int tahunProduksi;

    public Kendaraan(String merk, String model, int tahunProduksi)
    {
        this.merk = merk;
        this.model = model;
        this.tahunProduksi = tahunProduksi;
    }

    public String getMerk()
    {
        return merk;
    }

    public String getModel()
    {
        return model;
    }

    public int getTahunProduksi()
    {
        return tahunProduksi;
    }

    public String getInfoKendaraan()
    {
        return "KENDARAAN | Merk: " + merk + " | Model: " + model + 
               " | Tahun: " + tahunProduksi;
    }

    public void displayInfo()
    {
        System.out.println(getInfoKendaraan());
    }
}
