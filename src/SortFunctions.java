public class SortFunctions {
    private static void merge(occurrenceCodons count, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
        String L2[] = new String[n1];
        String R2[] = new String[n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i){
            L[i] = count.occurrence[l + i];
            L2[i] = count.codons[l + i];
        }

        for (int j=0; j<n2; ++j) {
            R[j] = count.occurrence[m + 1 + j];
            R2[j] = count.codons[m + 1 + j];
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] >= R[j])
            {
                count.occurrence[k] = L[i];
                count.codons[k] = L2[i];
                i++;
            }
            else
            {
                count.occurrence[k] = R[j];
                count.codons[k] = R2[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            count.occurrence[k] = L[i];
            count.codons[k] = L2[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            count.occurrence[k] = R[j];
            count.codons[k] = R2[j];
            j++;
            k++;
        }
    }

    public static void sort(occurrenceCodons count, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(count, l, m);
            sort(count, m + 1, r);

            // Merge the sorted halves
            merge(count, l, m, r);
        }
    }

    private static void merge(OccurrenceAminoAcids count, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
        char L2[] = new char[n1];
        char R2[] = new char[n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i){
            L[i] = count.occurrence[l + i];
            L2[i] = count.aminoAcid[l + i];
        }

        for (int j=0; j<n2; ++j) {
            R[j] = count.occurrence[m + 1 + j];
            R2[j] = count.aminoAcid[m + 1 + j];
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] >= R[j])
            {
                count.occurrence[k] = L[i];
                count.aminoAcid[k] = L2[i];
                i++;
            }
            else
            {
                count.occurrence[k] = R[j];
                count.aminoAcid[k] = R2[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            count.occurrence[k] = L[i];
            count.aminoAcid[k] = L2[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            count.occurrence[k] = R[j];
            count.aminoAcid[k] = R2[j];
            j++;
            k++;
        }
    }

    public static void sort(OccurrenceAminoAcids count, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(count, l, m);
            sort(count, m + 1, r);

            // Merge the sorted halves
            merge(count, l, m, r);
        }
    }
}
