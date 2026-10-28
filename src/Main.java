import java.util.ArrayList;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.2 - CRUD Test ===\n");

        CharacterManager manager = new CharacterManager();

        // 1. Tambah beberapa karakter
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));

        // 2. Coba tambah DUPLIKAT nama - seharusnya DITOLAK
        System.out.println();
        manager.addCharacter(new Character("Arjuna", CharacterType.HEALER, 1));

        // 3. Tampilkan total karakter
        System.out.println("\nTotal karakter saat ini: " + manager.getTotalCharacters());

        // 4. Cari karakter
        System.out.println("\n--- Pencarian ---");
        Character hasil = manager.findByName("Merlin");
        System.out.println(hasil != null ? "Ditemukan: " + hasil : "Tidak ditemukan");

        Character tidakAda = manager.findByName("Zeus");
        System.out.println(tidakAda != null ? "Ditemukan: " + tidakAda : "Zeus tidak ditemukan (sesuai harapan)");

        // 5. Hapus karakter
        System.out.println("\n--- Penghapusan ---");
        manager.removeCharacter("Athena");
        System.out.println("Total setelah hapus: " + manager.getTotalCharacters());

        // 6. Tampilkan semua karakter yang tersisa
        System.out.println("\n--- Daftar Karakter Tersisa ---");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }

        // 7. EKSPERIMEN HALAMAN 7: membuktikan getAllCharacters() mengembalikan SALINAN
        System.out.println("\n--- Eksperimen Salinan (Halaman 7) ---");
        ArrayList<Character> salinan = manager.getAllCharacters();   // ambil salinan list
        salinan.clear();                                             // kosongkan SALINANNYA saja
        System.out.println("Ukuran salinan setelah clear : " + salinan.size());
        System.out.println("Total karakter di manager    : " + manager.getTotalCharacters());

        // 8. MINI-CHALLENGE: uji getAverageLevel() dan findByType()
        System.out.println("\n=== Mini-Challenge (Bagian B) ===");

        // 8a. Uji getAverageLevel() pada manager KOSONG (harus 0, tanpa error)
        CharacterManager managerKosong = new CharacterManager();
        System.out.println("\n--- getAverageLevel() ---");
        System.out.println("Rata-rata level (manager kosong): " + managerKosong.getAverageLevel());

        // 8b. Tambah karakter supaya ada data untuk diuji
        manager.addCharacter(new Character("Gandalf", CharacterType.MAGE, 6));
        manager.addCharacter(new Character("Freya", CharacterType.HEALER, 4));

        System.out.println("Total karakter sekarang        : " + manager.getTotalCharacters());
        System.out.println("Rata-rata level (manager isi)  : " + manager.getAverageLevel());

        // 8c. Uji findByType()
        System.out.println("\n--- findByType() ---");

        ArrayList<Character> daftarMage = manager.findByType(CharacterType.MAGE);
        System.out.println("Karakter bertipe MAGE (" + daftarMage.size() + "):");
        for (Character c : daftarMage) {
            System.out.println("  " + c);
        }

        ArrayList<Character> daftarWarrior = manager.findByType(CharacterType.WARRIOR);
        System.out.println("Karakter bertipe WARRIOR (" + daftarWarrior.size() + "):");
        for (Character c : daftarWarrior) {
            System.out.println("  " + c);
        }

        ArrayList<Character> daftarArcher = manager.findByType(CharacterType.ARCHER);
        System.out.println("Karakter bertipe ARCHER (" + daftarArcher.size() + "):");
        if (daftarArcher.isEmpty()) {
            System.out.println("  (tidak ada, Athena sudah dihapus)");
        }
    }
}