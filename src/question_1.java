public class question_1 {
    public static void main (String[] args){
        String sequence;
        sequence = fileReader.read();

        System.out.println(occurrenceGC(sequence) + "%");
    }

    private static double occurrenceGC(String sequence) {
        int ocrA = 0, ocrT = 0, ocrG = 0, ocrC = 0;
        int size = sequence.length();
        for(int i = 0; i < size; i++){
            if(sequence.charAt(i) == 'A') ocrA++;
            else if(sequence.charAt(i) == 'T') ocrT++;
            else if(sequence.charAt(i) == 'G') ocrG++;
            else ocrC++;
        }
        double percentual  = (ocrG*100+ocrC*100)/size;
        return percentual;
    }

}
