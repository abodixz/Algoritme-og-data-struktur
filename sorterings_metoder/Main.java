import java.util.*;
public class Main {

    private static int[] fyllArray(int length, int highestVal) {
        Random ran = new Random();
        int[] array = new int[length];
        Arrays.setAll(array, i -> ran.nextInt(highestVal));
        return array;
    }

    //-------------------------------------------------------------------

    public static void main(String[] args) {

        // les inn antall tall, sorteringsmetode og testtype fra brukeren
        Scanner scanner = new Scanner(System.in);
        System.out.print("Antall tall som skal sorteres: ");
        int antallTall = scanner.nextInt();
        System.out.print("Hvilken sorteringsmetode som skal brukes? (1 = insertion, 2 = quicksort, 3 = mergesort, 4 = radixsort): ");
        int sorteringsmetode = scanner.nextInt();
        System.out.print("Hvilken test av sorteringsmetoden som skal utføres? (1 = kjøretid, 2 = estimering): ");
        int testtype = scanner.nextInt();


        //-------------------------------------------------------------------


        // Utfører sorteringen og gir tiden den bruker.
        ArrayList<Float> C_save = null;
        if (testtype == 1) {

            // oppretter en array med tilfeldige tall
            int[] randomNummbers = fyllArray(antallTall, 2 * antallTall);

            long startTime = System.currentTimeMillis();

            // velger sorteringsalgoritme og sorterer arrayen
            if (sorteringsmetode == 1) {
                InsertionSort.sort(randomNummbers);
            } else if (sorteringsmetode == 2) {
                QuickSort.sort(randomNummbers, 0, randomNummbers.length - 1);
            } else if (sorteringsmetode == 3) {
                MergeSort.sort(randomNummbers, 0, randomNummbers.length - 1);
            } else if (sorteringsmetode == 4) {
                RadixSort.sort(randomNummbers, 2 * antallTall);
            } else {
                System.out.println("Error! Parameter nr.2 må være mellom 1-4");
                return;
            }

            // regner og skriver ut tidsbruket til sorteringsalgoritmen.
            long endTime = System.currentTimeMillis();
            long timeUsed = endTime - startTime;
            System.out.println("Sorteringen tar: " + timeUsed + " milisek");

        }



        // Estimering av verdien C
        else if (testtype == 2) {

            C_save = new ArrayList<>();

            for (int n = 10000; n <= 40000; n += 3000) {

                int[] a = fyllArray(n, 2 * n);
                float C = 0;
                long time = System.currentTimeMillis();

                // valg av sorterings algoritme
                if (sorteringsmetode == 1) {
                    InsertionSort.sort(a);
                    time = System.currentTimeMillis() - time;
                    C = ((float) (time) / (n * n));
                    C_save.add(C);
                } else if (sorteringsmetode == 2) {
                    QuickSort.sort(a, 0, a.length - 1);
                    time = System.currentTimeMillis() - time;
                    C = ((float) (time) / (float) (n * Math.log((double) (n))));
                    C = ((float) (2.0 * C) / (float) (Math.log((double) (n))));
                    C_save.add(C);
                } else if (sorteringsmetode == 3) {
                    MergeSort.sort(a, 0, a.length - 1);
                    time = System.currentTimeMillis() - time;
                    C = ((float) (time) / (float) (n * Math.log((double) (n))));
                    C_save.add(C);
                } else if (sorteringsmetode == 4) {
                    RadixSort.sort(a, 2 * n);
                    time = System.currentTimeMillis() - time;
                    C = ((float) (time) / (float) (n * Math.log((double) (n))));
                    C_save.add(C);
                } else {
                    System.out.println("Feil! Parameter nr.2 må være mellom 1-4");
                    return;
                }
                System.out.println("n: " + n + "\tt: " + time + "ms  \tC: " + C);
            }

            // regner gjennomsnittlig C verdi
            float C_avg = 0;
            for (float nr : C_save) {
                C_avg += nr;
            }
            C_avg /= C_save.size();

            System.out.println("\nC average: " + C_avg + "\n");

        }
        else {
            System.out.println("Feil! Parameter nr.3 må være enten 1 eller 2");
        }

    }
}