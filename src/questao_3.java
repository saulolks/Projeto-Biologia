import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class questao_3 {
	
	static ArrayList<char[]> dados = new ArrayList<>();
	static ArrayList<String> ProtStr = new ArrayList<String>();
   
	public static void main (String[] args) throws IOException{
			
	 FileReader fileReader = new FileReader("sequencia.txt");
	 BufferedReader reader = new BufferedReader(fileReader);
	 int t = 0, n = 0;
	 
		 while(reader.ready()){
			
			 String str = reader.readLine();
				if(str.charAt(0) != '>'){
					
					dados.add(str.toCharArray());
				}	
		 }	
		 fileReader.close();
		 reader.close();
		 
		 t = dados.size();
		 n = dados.get(0).length;
		 char[][] amostra = dados.toArray(new char[t][n]);
		 
/*		 for(int i =0; i<t; i++){
			 for(int j = 0; j<amostra[i].length; j++){
				 System.out.print(amostra[i][j]);
			 }
			 System.out.println("");
		 }
*/		 	 
 }
	
	public String BranchAndBoundMotifSearch(char[][] DNA, int t, int n,int l){
		int[] s = new int[t];
		for(int j=0; j<t; j++){
			s[j] = 1;
		}
		int bestDistance = 99999;
		String bestWord = new String();
		int i = 3;
	
		while (i>0){
			if (i<l){
				String prefix = new String();
				for(int j=0; j<i; j++){
					if(s[j]==1) prefix+="A";
					else if(s[j]==2) prefix+="C";
					else if(s[j]==3) prefix+="G";
					else if(s[j]==4) prefix+="T";
				}
				int optimisticDistance = totaldistance(prefix, DNA);
				if(optimisticDistance>bestDistance){
					Info retorno = bypass(s, i, l, 4);
					s = retorno.getA();
					i = retorno.getI();	
				}else{
					Info retorno = nextvertex(s, i, l, 4);
					s = retorno.getA();
					i = retorno.getI();
				}
			}else{
				String word = new String();
				for(int j=0; j<l; j++){
					if(s[j]==1) word+="A";
					else if(s[j]==2) word+="C";
					else if(s[j]==3) word+="G";
					else if(s[j]==4) word+="T";
				}
				if(totaldistance(word, DNA)<bestDistance){
					bestDistance = totaldistance(word, DNA);
					bestWord = word;
				}
				Info retorno = nextvertex(s, i, l, 4);
				s = retorno.getA();
				i = retorno.getI();
			}
		}		
		return bestWord;
	}
	
	public int totaldistance(String prefix, char DNA[][]){
		int totaldistance = 0;
		
		return 0;
	}
	
	public Info bypass(int[] s, int i, int l, int k){
		for(int j = 1; j<=i; j++){
			if(s[j]<k){
				s[j]++;
				Info retorno = new Info(s,j);
				return retorno;
			}
		}
		Info retorno = new Info(s,0);
		return retorno;
	}
	
	public Info nextvertex(int[] s, int i, int l, int k){
		if(i<l){
			s[i+1] = 1;
			Info retorno = new Info(s,i+1);
			return retorno;
		}else{
			for(int j = 1; j<=l; j++){
				if(s[j]<k){
					s[j]++;
					Info retorno = new Info(s,j);
					return retorno;
				}
			}
		}
		Info retorno = new Info(s,0);
		return retorno;
	}
	
	
	
} 