public class Manusia extends MakhlukHidup
{
    public Manusia(String nama) {
        super(nama);
    }

    @Override
    public void makan() {
        System.out.println(getNama() + " makan dengan menggunakan tangan/alat makan.");
    }

    @Override
    public void berkembangBiak() {
        System.out.println(getNama() + " berkembang biak secara vivipar (melahirkan).");
    }

    public void bekerja() {
        System.out.println(getNama() + " sedang bekerja.");
    }
}
