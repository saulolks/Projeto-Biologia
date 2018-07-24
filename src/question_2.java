import java.text.DecimalFormat;
import java.util.ArrayList;

public class question_2 {
    public static void main (String[] args){
        String sequence = fileReader.read();
        //String sequences[] = countCodons(sequence);
        //System.out.println(sequences[0]);
        //System.out.println(sequences[1]);
        //System.out.println(sequences[2]);
        //System.out.println(sequences[3]);
        //System.out.println(sequences[4]);
        //System.out.println(sequences[5]);

        occurrenceCodons count = countCodons(sequence);
        DecimalFormat dc = new DecimalFormat("0.00");
        int totalSum = 0; double percentual;
        char aminoacid;

        for(int i = 0; i < 64; i++){
            totalSum+=count.occurrence[i];
            percentual = (count.occurrence[i] * 100);
            percentual/=(sequence.length()*4/3);
            aminoacid = codonMapping.getProtein(count.codons[i]);

            System.out.println(count.codons[i] + " | " + dc.format(percentual) + "% | " + PhysicochemicalProperties.phChemicalMap(aminoacid));
            System.out.println("----------------------------------------------");
        }

        System.out.println("Total codons counted: " + totalSum);



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

    private static occurrenceCodons countCodons(String sequence){
        String proteins[] = new String[6];
        String sequences[] = new String[6];
        sequences[0] = transcription(sequence);
        sequences[1] = toString(changePosition(sequences[0].toCharArray()));
        sequences[2] = toString(changePosition(sequences[1].toCharArray()));
        sequences[3] = toString(reverseComplement(sequences[0].toCharArray()));
        sequences[4] = toString(reverseComplement(sequences[1].toCharArray()));
        sequences[5] = toString(reverseComplement(sequences[2].toCharArray()));

        int index;
        occurrenceCodons count = new occurrenceCodons();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < sequences[i].length()-2; j+=3){
                index = occurrenceCodons.getIndexByCodon(sequences[i].substring(j,j+3));
                count.occurrence[index]++;
            }
        }



        return count;
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

    public static char[] codonToAminoAcid(String sequence) {
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
