import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Time Complexity: O(N)
//Auxiliary Space: O(1)

public class StoredWater {
    public static int maxStoredWater(int arr[], int n)
    {
        int size = n - 1;

        // Array'in ilk elemanını prev'e atadık.
        // Çünkü loop'u 1. index'ten başlatacağız.
        int prev = arr[0];

        // Önceki duvarın index'ini saklamak için.
        // Çünkü sonradan array'i sondan başlayarak
        // tekrar loop edeceğiz.
        int prev_index = 0;
        int total_water = 0;

        // Daha yüksek bir duvar bulana kadar
        // toplam suyu burada tutacağız. Eğer
        // array'in son elemanı en yüksek duvar
        // olmazsa su dökülecek demektir.
        // O durumda array'i sondan bir daha döneceğiz.
        int water_without_border = 0;
        for (int i = 1; i <= size; i++) {

            // Eğer şu anki duvar, prev değerinden
            // fazla veya eşitse, prev değerlerini
            // güncelle.
            if (arr[i] >= prev) {
                prev = arr[i];
                prev_index = i;

                // Daha yüksek duvar bulduğumuzdan
                // bu noktaya kadar tutulan suyun
                // dökülmeyeceğini biliyoruz. O yüzden
                // değeri sıfırladık.
                water_without_border = 0;
            }
            else {

                // Şu anki duvar değeri prev'den
                // küçük olduğundan, aradaki fark
                // kadar birim su depolanacak demektir.
                total_water += prev - arr[i];

                // Dökülme ihtimaline karşın aynı değeri
                // buraya da verdik.
                water_without_border += prev - arr[i];
            }
        }

        // Eğer en yüksek duvar sondaysa
        // array'i tekrar loop'lamaya gerek yok
        // çünkü hiç su dökülmemiş demek. Diğer
        // durumda prev_index'deki duvar son index'ten
        // küçük demek.
        if (prev_index < size) {

            // Son duvar kısa kaldığından su dökülmesi olacak.
            // Dökülecek suyu içerdiğini bildiğimiz değeri
            // toplam sudan çıkarıyoruz.
            total_water -= water_without_border;

            // Çıkardığımız değerdeki suda dökülmeyecek
            // kısmı hesaplamak için array'i sondan
            // en yüksek değerde tuttuğumuz duvara(prev_index)
            // kadar tekrar döneceğiz. prev'i de array'in sonuna eşitliyoruz
            // çünkü sondan başa dolaşacağız.
            prev = arr[size];

            // prev_index sağındaki her duvardan uzun. Sağındaki
            // duvarları birbiriyle karşılaştırıp biriken suyu
            // eklemek için hesaplıyoruz.
            for (int i = size; i >= prev_index; i--) {

                // Duvar sağındakinden uzunsa veya eşitse
                // su dökülecek o yüzden sadece
                // prev'i en uzun bulunan duvara
                // güncelliyoruz.
                if (arr[i] >= prev) {
                    prev = arr[i];
                }
                // Sağındakinden kısa duvar bulduk
                // yani su burada depolanacak.
                else {
                    total_water += prev - arr[i];
                }
            }
        }

        // Biriken toplam suyu döndürüyoruz.
        return total_water;
    }

    public static void main(String[] args) throws IOException {
        // Kullanıcıdan array input'u alıp arkasına yukarıdaki fonksiyonumuza hesaplaması için verdik.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the size of the array: ");
        int arr_size = 0;
        try {
            arr_size = Integer.parseInt(br.readLine());
        } catch (NumberFormatException | IOException  e) {
            System.out.println("Invalid input for array size. Please enter a valid integer.");
            return;
        }
        int[] arr = new int[arr_size];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < arr_size; i++) {
            try {
                arr[i] = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input for array element. Please enter a valid integer.");
                return;
            }
        }
        System.out.println("The elements of the array are: ");
        for (int i = 0; i < arr_size; i++) {
            System.out.print(arr[i] + " ");
        }
        int N = arr.length;
        System.out.println();
        System.out.print("Total units of water that can be stored in this array is: " + maxStoredWater(arr, N));
        br.close();
    }
}
