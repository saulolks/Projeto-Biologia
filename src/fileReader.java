import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class fileReader {
    public static String read () {

        Scanner ler = new Scanner(System.in);

        String sequencia = new String();
        try {
            FileReader arq = new FileReader("sequencia.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            sequencia = lerArq.readLine();

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

       return sequencia;
    }
}
