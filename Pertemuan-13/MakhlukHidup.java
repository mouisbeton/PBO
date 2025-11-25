public abstract class MakhlukHidup
{
    private String nama;

    public MakhlukHidup(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void bernafas() {
        System.out.println(nama + " sedang bernafas.");
    }

    public abstract void makan();

    public abstract void berkembangBiak();
}
