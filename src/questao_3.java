import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;

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
		 System.out.println(BranchAndBoundMotifSearch(amostra,t,n,7));
 }
	
	public static String BranchAndBoundMotifSearch(char[][] DNA, int t, int n,int l){
		int[] v = new int[l+1];
		for(int j=0; j<l; j++){
			v[j] = 1;
		}
		int bestDistance = 99999;
		String bestWord = new String();
		int i = 1;
	  
		while (i>0){
			if (i<l){
				String prefix = new String();
				for(int j=0; j<i; j++){
					if(v[j]==1) prefix+="A";
					else if(v[j]==2) prefix+="C";
					else if(v[j]==3) prefix+="G";
					else if(v[j]==4) prefix+="T";
				}
				int optimisticDistance = totaldistance(prefix, DNA);
				if(optimisticDistance>bestDistance){
					Info retorno = bypass(v, i, l, 4);
					v = retorno.getA();
					i = retorno.getI();	
				}else{
					Info retorno = nextvertex(v, i, l, 4);
					v = retorno.getA();
					i = retorno.getI();
				}
			}else{
				String word = new String();
				for(int j=0; j<l; j++){
					if(v[j]==1) word+="A";
					else if(v[j]==2) word+="C";
					else if(v[j]==3) word+="G";
					else if(v[j]==4) word+="T";
				}
				if(totaldistance(word, DNA)<bestDistance){
					bestDistance = totaldistance(word, DNA);
					bestWord = word;
				}
				Info retorno = nextvertex(v, i, l, 4);
				v = retorno.getA();
				i = retorno.getI();
			}
		}		
		return bestWord;
	}
	
	public static int totaldistance(String prefix, char DNA[][]){
		int totaldistance = 0;
		int distance = 1;
		Motif[] motifs = new Motif[DNA.length];
		
		for(int k = 0; k<DNA.length - 1; k++){
			int min = 9999;
			int posinic = 0;
			for(int i = 0; i<DNA[0].length - prefix.length(); i++) {
				distance = 1;
				for(int j = 0; j<prefix.length(); j++) {
//					System.out.println(k + " " + i + " " + j);
					if(prefix.charAt(j)!= DNA[k][i+j]) {
						distance++;
					}
				}
				if(distance < min) {
					min = distance;
					posinic = i+1;
				}		
			}
			motifs[k] = new Motif(min,posinic);	
		}
		
		for(int i = 0; i<DNA.length-1; i++) {
			totaldistance += motifs[i].getHamdis();
		}
		
		return totaldistance;
	}
	
	public static Info bypass(int[] s, int i, int l, int k){
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
	
	public static Info nextvertex(int[] s, int i, int l, int k){
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