import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;

public class questao_3 {
	
	static ArrayList<char[]> dados = new ArrayList<>();
	static ArrayList<String> ProtStr = new ArrayList<String>();
    static ArrayList<ArrayList<Motif>> motifS = new ArrayList<ArrayList<Motif>>();
    static int bestMotifDistance;
	public static void main (String[] args) throws IOException{
			
	 FileReader fileReader = new FileReader("sequencia.txt");
	 BufferedReader reader = new BufferedReader(fileReader);
	 int t = 0, n = 0;
	 String sequence = "";
		 while(reader.ready()){
			 String str = reader.readLine();
				if(str.charAt(0) != '>'){
					sequence+=str;				
				}	
		 }
		 dados.add(sequence.toCharArray());
		 
		 fileReader.close();
		 reader.close();
		 
		 
		char[][] amostra = dados.toArray(new char[t][n]);
		
		for (int i=0; i<amostra.length;i++){
		    for(int j=0; j<amostra[i].length; j++) {
		    	if(amostra[i][j] == 'T'){
			    	amostra[i][j] = 'U';
			    }
		    }
		}
		
		char cAmostra[] = new char[amostra[0].length];
		int y = amostra[0].length;
		y--;
	    for(int j=0; j<amostra[0].length;j++) {
	    	if(amostra[0][j]== 'U'){
		    	cAmostra[y] = 'A';
		    }else if(amostra[0][j]=='C'){
		    	cAmostra[y] = 'G';
		    }else if(amostra[0][j]=='G'){
		    	cAmostra[y] = 'C';
		    }else if(amostra[0][j]=='A'){
		    	cAmostra[y] = 'U';
		    }
			y--;
	    }
	    dados.add(cAmostra);
	    amostra = dados.toArray(new char[t][n]);	 
		 Scanner entrada = new Scanner(System.in);
		 int tMotif = 0;
		 t = dados.size();
		 n = dados.get(0).length;
		 System.out.print("Digite o tamanho do Motif: ");
		 tMotif = entrada.nextInt();
		 entrada.close();
		 for(int i = 0; i<amostra.length; i++) {
			 motifS.add(new ArrayList<Motif>());
		 }
		 System.out.println("Número de Sequências: " + amostra.length);
		 long tempoInicio = System.currentTimeMillis();
		 
		 ArrayList<Motif> bestMotifS = BranchAndBoundMotifSearch(amostra,t,n,tMotif);
		 for(int i = 0; i<bestMotifS.size(); i++) {
			 System.out.println("Motif " + (i+1) + " = " + bestMotifS.get(i).toString());
		 }
		 long tempoFinal = System.currentTimeMillis();
		 System.out.println("\nTempo de Busca(s): " + (tempoFinal - tempoInicio)/1000);
 }
	
	public static ArrayList<Motif> BranchAndBoundMotifSearch(char[][] DNA, int t, int n,int l){
		int[] v = new int[l];
		for(int j=0; j<l; j++){
			v[j] = 1;
		}
		int bestDistance = 99999;
		int i = 1;
		ArrayList<Motif> bestMotifS = new ArrayList<Motif>();
	  
		while (i>0){
			if (i<l){
				String prefix = new String();
				for(int j=0; j<i; j++){
					if(v[j]==1) prefix+="A";
					else if(v[j]==2) prefix+="C";
					else if(v[j]==3) prefix+="G";
					else if(v[j]==4) prefix+="U";
				}
				int optimisticDistance = totaldistance(prefix, DNA, l);
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
					else if(v[j]==4) word+="U";
				}
				int minDistance = totaldistance(word, DNA, l);
				if(minDistance < bestDistance){
					bestMotifS = new ArrayList<Motif>();
					bestDistance = totaldistance(word, DNA, l);
					bestMotifDistance = bestDistance;
					bestMotifS.add(new Motif(bestDistance,0,word));
				}
				
				else if(totaldistance(word, DNA, l) == bestDistance){	
					bestDistance = totaldistance(word, DNA, l);
					bestMotifS.add(new Motif(bestDistance,0,word));
					bestMotifDistance = bestDistance;										
				}
				Info retorno = nextvertex(v, i, l, 4);
				v = retorno.getA();
				i = retorno.getI();
			}
		}		
		return bestMotifS;
	}
	
	public static int totaldistance(String prefix, char DNA[][], int l){
		int totaldistance = 0;
		int distance = 0;
		Motif[] motifs = new Motif[DNA.length];
		
		for(int k = 0; k<DNA.length; k++){
			int min = 9999;
			int posinic = 0;
			for(int i = 0; i<DNA[0].length - prefix.length(); i++) {
				distance = 0;
				for(int j = 0; j<prefix.length(); j++) {
					if(prefix.charAt(j)!= DNA[k][i+j]) {
						distance++;
					}
				}
				if(distance < min) {
					min = distance;
					posinic = i+1;
				}
				
				Motif aux = new Motif(distance, posinic, prefix);
				
				if(prefix.length()==l && distance <= prefix.length()/3) {
					if(motifS.get(k).contains(aux) == false) {
						motifS.get(k).add(aux);
					}
				}		
		
			 }
			motifs[k] = new Motif(min,posinic, prefix);		
		}	
		for(int i = 0; i<DNA.length; i++) {
			totaldistance += motifs[i].getHamdis();
		}
		
		return totaldistance;
	}
	
	public static Info bypass(int[] s, int i, int l, int k){
		for(int j = i; j>=1; j--){
			if(s[j-1]<k){
				s[j-1]++;
				Info retorno = new Info(s,j);
				return retorno;
			}
		}
		Info retorno = new Info(s,0);
		return retorno;
	}
	
	public static Info nextvertex(int[] s, int i, int l, int k){
		if(i<l){
			s[i] = 1;
			Info retorno = new Info(s,i+1);
			return retorno;
		}else{
			for(int j = l; j>=1; j--){
				if(s[j-1]<k){
					s[j-1]++;
					Info retorno = new Info(s,j);
					return retorno;
				}
			}
		}
		Info retorno = new Info(s,0);
		return retorno;
	}
	
	
	
} 