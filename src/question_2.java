import java.util.ArrayList;

public class question_2 {
    public static void main (String[] args){
        String sequence = fileReader.read();
        String sequences[] = findCodons(sequence);
        System.out.println(sequences[0]);
        System.out.println(sequences[1]);
        System.out.println(sequences[2]);
        System.out.println(sequences[3]);
        System.out.println(sequences[4]);
        System.out.println(sequences[5]);

    }

    public static char[] reverseComplement(char[] sequence) {
        ArrayList<Character> compSequence = new ArrayList<>();

        for(int i = 0; i < sequence.length; i++){
            if(sequence[i] == 'A') compSequence.add(i, 'U');
            else if(sequence[i] == 'U') compSequence.add(i, 'A');
            else if(sequence[i] == 'G') compSequence.add(i, 'C');
            else compSequence.add(i, 'G');
        }
        char compReverse[] = new char[compSequence.size()];

        for(int i = 0; i < compSequence.size(); i++){
            compReverse[compSequence.size()-i-1] = compSequence.get(i);
        }
        return compReverse;
    }

    private static String[] findCodons(String sequence){
        String proteins[] = new String[6];
        String rnaSequence = transcription(sequence);
        proteins[0] = toString(codonToProtein(rnaSequence));

        char sequenceChar[] = rnaSequence.toCharArray();
        sequenceChar = changePosition(sequenceChar);

        proteins[1] = toString(codonToProtein(toString(sequenceChar)));

        proteins[4] = toString(codonToProtein(toString(reverseComplement(sequenceChar))));

        sequenceChar = changePosition(sequenceChar);

        proteins[5] = toString(codonToProtein(toString(reverseComplement(sequenceChar))));

        proteins[2] = toString((codonToProtein(toString(sequenceChar))));

        proteins[3] = toString(codonToProtein(toString(reverseComplement(rnaSequence.toCharArray()))));

        return proteins;

    }

    private static char[] changePosition(char[] sequence){
        char aux = ' ';
        for(int i = 0; i < sequence.length; i++){
            if(i == 0){
                aux = sequence[0];
            }
            else if(i == sequence.length-1){
                sequence[i-1] = sequence[i];
                sequence[i] = aux;
            }
            else sequence[i-1] = sequence[i];
        }
        return sequence;
    }

    public static char[] codonToProtein(String sequence) {
        char proteinString[] = new char[sequence.length()/3];
        char protein;
        String codon;
        int j = 0;
        for(int i = 0; i < sequence.length()-2; i+=3){
            codon = sequence.substring(i, i+3);
            protein = codonMapping.getProtein(codon);
            if(protein != '-') proteinString[j++] = protein;
        }

        return proteinString;
    }

    public static String toString(char[] letters){
        String word = new String(letters);
        return word;
    }

    public static String transcription(String sequence) {
        String newSequence = new String();
        newSequence = sequence.replace('T','U');
        return newSequence;
    }

}
