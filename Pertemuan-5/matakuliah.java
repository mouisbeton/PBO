public class MataKuliah {
    public String namaMK;
    public int sks;
    public Dosen dosen;
    public int kuota = 50; 

    public MataKuliah(String namaMK, int sks, Dosen dosen) {
        this.namaMK = namaMK;
        this.sks = sks;
        this.dosen = dosen;
    }

    public void tambahKuota() {
        this.kuota++;
    }

    public void kurangiKuota() {
        this.kuota--;
    }
    
    public String toString() {
        return namaMK + " (" + sks + " SKS) | Dosen: " + dosen.nama;
    }
}
