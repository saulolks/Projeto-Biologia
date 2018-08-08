import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class question_2 {
    public static void main (String[] args){
        String sequence = fileReader.read();

        String sequences[] = countCodons(sequence);
        createAminoAcidTable(sequences);
    }

    //Essa função gera a tabela de aminoácidos de acordo com as sequências obtidas nos métodos abaixo
    private static void createAminoAcidTable(String[] sequences) {
        String aminoAcidsSequences[] = new String[6];

        // percorre o array com as 6 strings (original, variação de start, variação de start 2,
        // complemento reverso, variação de start do complemento reverso e variação de star do complemento reverso 2)
        // e converte cada string numa cadeia de aminoácidos.
        for(int i = 0; i < sequences.length; i++){
            aminoAcidsSequences[i] = toString(codonToAminoAcid(sequences[i]));
        }

        // É inicializado todas as instâncias do Array que vai contar a ocorrência dos aminácidos na sequência
        OccurrenceAminoAcids count[] = new OccurrenceAminoAcids[6];
        for(int i = 0; i < 6; i++) count[i] = new OccurrenceAminoAcids();

        // Cada uma das 6 sequências é lida por completo e cada aminácido é contado na instância de OccurrenceAminoAcids
        // que possui um array com os aminoácidos e suas ocorrências
        int index;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < aminoAcidsSequences[i].length(); j++){
                index = count[i].getIndexByAminoAcid(aminoAcidsSequences[i].charAt(j));
                count[i].occurrence[index]++;
            }
        }

        DecimalFormat dc = new DecimalFormat("0.00");
        int totalSum = 0; double percentual;

        // Ordena o Array
        for(int i = 0; i < 6; i++) SortFunctions.sort(count[i], 0, 17);

        // Calcula e imprime a sequência
        try{
            File arquivo = new File("Amino Acid Table.txt");
            arquivo.createNewFile();
            FileWriter fileW = new FileWriter (arquivo);//arquivo para escrita
            BufferedWriter buffW = new BufferedWriter (fileW);
            for(int j = 0; j < 6; j++){
                System.out.println(aminoAcidsSequences[j]);
                buffW.write(aminoAcidsSequences[j]);
                buffW.newLine();
                buffW.newLine();
                for(int i = 0; i < 18; i++){
                    totalSum+=count[j].occurrence[i];
                    percentual = (count[j].occurrence[i] * 100);
                    percentual/=(aminoAcidsSequences[0].length());

                    System.out.println(count[j].aminoAcid[i] + " | " + dc.format(percentual) + "% | " + PhysicochemicalProperties.phChemicalMap(count[j].aminoAcid[i]));
                    buffW.write(count[j].aminoAcid[i] + " | " + dc.format(percentual) + "% | " + PhysicochemicalProperties.phChemicalMap(count[j].aminoAcid[i]));
                    buffW.newLine();
                }

                System.out.println("----------------------------------------------");
                buffW.write("----------------------------------------------");
                buffW.newLine();
                System.out.println("Total amino acids counted: " + totalSum);
                buffW.write("Total amino acids counted: " + totalSum);
                buffW.newLine();
                buffW.newLine();
                System.out.println();
                totalSum = 0;
            }
        }catch(IOException io){
            System.out.println("something wrong happens");
        }
    }

    // Retorna o complemento reverso da sequência
    public static char[] reverseComplement(char[] sequence) {
        ArrayList<Character> compSequence = new ArrayList<>();

        // Mapeia cada caractere na sua base nitrogenada complementar
        for(int i = 0; i < sequence.length; i++){
            if(sequence[i] == 'A') compSequence.add(i, 'U');
            else if(sequence[i] == 'U') compSequence.add(i, 'A');
            else if(sequence[i] == 'G') compSequence.add(i, 'C');
            else compSequence.add(i, 'G');
        }
        char compReverse[] = new char[compSequence.size()];

        // Inverte o array
        for(int i = 0; i < compSequence.size(); i++){
            compReverse[compSequence.size()-i-1] = compSequence.get(i);
        }
        return compReverse;
    }

    private static String[] countCodons(String sequence){

        String proteins[] = new String[6];
        String sequences[] = new String[6];
        sequences[0] = transcription(sequence); // transforma em RNA
        sequences[1] = toString(changePosition(sequences[0].toCharArray())); // varia a posição de início em 1
        sequences[2] = toString(changePosition(sequences[1].toCharArray())); // varia a posição de início em 2
        sequences[3] = toString(reverseComplement(sequences[0].toCharArray())); // complemento reverso
        sequences[4] = toString(reverseComplement(sequences[1].toCharArray())); // varia a posição de início do complemento reverso em 1
        sequences[5] = toString(reverseComplement(sequences[2].toCharArray())); // varia a posição de início do complemento reverso em 2

        int index;
        occurrenceCodons count[] = new occurrenceCodons[6];

        // instancia a classe que armazenará a ocorrência dos códons de cada sequência
        for(int i = 0; i < 6; i++) count[i] = new occurrenceCodons();

        // incrementa a ocorrência de cada códon encontrado
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < sequences[i].length()-2; j+=3){
                index = occurrenceCodons.getIndexByCodon(sequences[i].substring(j,j+3));
                count[i].occurrence[index]++;
            }
        }

        DecimalFormat dc = new DecimalFormat("0.00");
        int totalSum = 0; double percentual;
        char aminoacid;

        // ordena o array de acordo com os codons mais frequentes
        for(int i = 0; i < 6; i++) SortFunctions.sort(count[i], 0, 63);

        // calcula a porcentagem e imprime
        try{
            File arquivo = new File("Codon Table.txt");
            arquivo.createNewFile();
            FileWriter fileW = new FileWriter (arquivo);//arquivo para escrita
            BufferedWriter buffW = new BufferedWriter (fileW);
            for(int j = 0; j < 6; j++){
                System.out.println(sequences[j]);
                buffW.write(sequences[j]);
                buffW.newLine();
                buffW.newLine();
                for(int i = 0; i < 64; i++){
                    totalSum+=count[j].occurrence[i];
                    percentual = (count[j].occurrence[i] * 100);
                    percentual/=(sequence.length()/3);
                    aminoacid = OccurrenceAminoAcids.getProtein(count[j].codons[i]);

                    System.out.println(count[j].codons[i] + " | " + dc.format(percentual) + "% | " + PhysicochemicalProperties.phChemicalMap(aminoacid));
                    buffW.write(count[j].codons[i] + " | " + dc.format(percentual) + "% | " + PhysicochemicalProperties.phChemicalMap(aminoacid));
                    buffW.newLine();
                }
                buffW.write("----------------------------------------------");
                buffW.newLine();

                System.out.println("Total codons counted: " + totalSum);
                buffW.write("Total codons counted: " + totalSum);
                buffW.newLine();
                buffW.newLine();
                System.out.println();
                totalSum = 0;
            }
        }catch(IOException IO){

        }
        return sequences;
    }

    // varia a posição de início da sequência, pegando o primeiro elemento e jogando pro final
    // ex:   AACTG      =>  ACTGA
    //       |     ^
    //       |_____|
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

    // Transforma um códon num aminoácido
    public static char[] codonToAminoAcid(String sequence) {
        char aminoAcidString[] = new char[sequence.length()/3];
        char protein;
        String codon;
        int j = 0;
        for(int i = 0; i < sequence.length()-2; i+=3){
            codon = sequence.substring(i, i+3); // captura uma substring contida entre a posição i e i+3 do vetor
            protein = OccurrenceAminoAcids.getProtein(codon); // mapeia para proteína usando a função getProtein da classe em questão
            if(protein != '-') aminoAcidString[j++] = protein; // como os stop codons estão mapeados para '-', não adicionamos ele na sequência
        }

        return aminoAcidString;
    }

    public static String toString(char[] letters){
        String word = new String(letters);
        return word;
    }

    // Faz a transcrição do DNA em RNA
    public static String transcription(String sequence) {
        String newSequence = new String();
        newSequence = sequence.replace('T','U');
        return newSequence;
    }
}

//O algoritmo de Merge Sort foi retirado do site Geeks for Geeks, disponível em: https://www.geeksforgeeks.org/merge-sort/,
//e adaptado para realizar o trabalho com o objeto OccurenceCodons e de forma decrescente.
