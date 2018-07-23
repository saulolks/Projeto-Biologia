import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class questao_3 {
	
	static File file = new File("sequencia.txt");
	static ArrayList<String> dados = new ArrayList<>();
	static ArrayList<String> ProtStr = new ArrayList<String>();
   
	public static void main (String[] args) throws IOException{
			
	 FileReader fileReader = new FileReader(file);
	 BufferedReader reader = new BufferedReader(fileReader);
	 int t = 0, n = 0;
	 
		 while(reader.ready()){
			
			 String str = reader.readLine();
				if(str.charAt(0) != '>'){
					dados.add(str);
				}	
		 }	
		 fileReader.close();
		 reader.close();
		 
		 t = dados.size();
		 n = dados.get(0).length();
		 char[][] amostra = dados.toArray(new char[t][n]);
		 for(int i =0; i< t; i++){
			 for(int j = 0; j<n; j++){
				 System.out.print(amostra[i][j]);
			 }
			 System.out.println("");
		 }
		 
	}
}