public class Tumbuhan extends MakhlukHidup
{
    public Tumbuhan(String nama) {
        super(nama);
    }

    @Override
    public void makan() {
        System.out.println(getNama() + " melakukan fotosintesis.");
    }

    @Override
    public void berkembangBiak() {
        System.out.println(getNama() + " berkembang biak dengan biji, spora, atau vegetatif.");
    }

    public void fotosintesis() {
        System.out.println(getNama() + " sedang berfotosintesis.");
    }
}
