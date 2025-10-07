import java.util.Map;
 import java.util.HashMap;
 import java.util.Random;

 public class TechSupport {
   private Map<String, String> responses;
   private Map<String, String> synonymMap; 
   private Random random;

   public TechSupport() {
     responses = new HashMap<>();
     synonymMap = new HashMap<>();
     random = new Random();

     responses.put("error", "Apakah Anda menerima pesan kesalahan? Coba restart sistem.");
     responses.put("help", "Apa yang bisa saya bantu?");
     responses.put("crash", "Kapan crash terjadi? Apakah ada pesan spesifik?");
     responses.put("slow", "Sudahkah Anda memeriksa penggunaan CPU dan RAM?");
     responses.put("restart", "Sudahkah Anda mencoba mematikan dan menyalakan kembali perangkat?");
     responses.put("update", "Apakah masalah terjadi setelah pembaruan (update)?");
     responses.put("mati", "Apakah perangkat Anda mati total? Coba periksa kabel daya.");
     responses.put("halo", "Halo! adakah yang bisa saya bantu?");

     synonymMap.put("salah", "error");
     synonymMap.put("kesalahan", "error");
     synonymMap.put("bantuan", "help");
     synonymMap.put("tolong", "help");
     synonymMap.put("lambat", "slow"); 
     synonymMap.put("lemot", "slow");  
     synonymMap.put("hang", "crash");
     synonymMap.put("restart", "restart");
     synonymMap.put("nyalakan", "restart");
     synonymMap.put("perbarui", "update");
     synonymMap.put("bug", "error");
     synonymMap.put("hello", "halo");
   }

   public String getResponse(String userInput) {
     if (userInput == null || userInput.trim().isEmpty()) {
         return "Silakan ketik pertanyaan atau masalah Anda.";
     }

     String[] tokens = userInput.toLowerCase().split("\\s+|[.,?!]");

     for (String token : tokens) {
       String cleanToken = token.trim();

       if (responses.containsKey(cleanToken)) {
         return responses.get(cleanToken);
       }
       
       if (synonymMap.containsKey(cleanToken)) {
         String mainKeyword = synonymMap.get(cleanToken);
         return responses.get(mainKeyword); 
       }
     }

     String[] generic = {
       "Coba jelaskan lebih rinci.",
       "Saya belum mengerti, bisa ulangi?",
       "Bisakah Anda memberi detail lebih lanjut?",
       "Itu terdengar rumit. Sudah coba cari di manual?"
     };
     return generic[random.nextInt(generic.length)];
   }
 }
