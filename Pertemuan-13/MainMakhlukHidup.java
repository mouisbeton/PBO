public class MainMakhlukHidup
{
    public static void main(String[] args) {
        MakhlukHidup m1 = new Manusia("Andi");
        MakhlukHidup m2 = new Hewan("Kucing");
        MakhlukHidup m3 = new Tumbuhan("Pohon Mangga");

        m1.bernafas();
        m1.makan();
        m1.berkembangBiak();

        m2.bernafas();
        m2.makan();
        m2.berkembangBiak();

        m3.bernafas();
        m3.makan();
        m3.berkembangBiak();
    }
}
