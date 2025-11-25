public class Hewan extends MakhlukHidup
{
    public Hewan(String nama) {
        super(nama);
    }

    @Override
    public void makan() {
        System.out.println(getNama() + " makan sesuai jenisnya (herbivora/karnivora/omnivora).");
    }

    @Override
    public void berkembangBiak() {
        System.out.println(getNama() + " berkembang biak secara ovipar / vivipar / ovovivipar.");
    }

    public void bergerak() {
        System.out.println(getNama() + " sedang bergerak.");
    }
}
