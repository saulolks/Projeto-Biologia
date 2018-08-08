import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;

public class questao_3 {
	
	static ArrayList<char[]> dados = new ArrayList<>(); //Armazena as sequ�ncias
    static ArrayList<ArrayList<Motif>> motifS = new ArrayList<ArrayList<Motif>>();
    static int bestMotifDistance; //Vari�vel global para totaldistance m�nima encontrada
    
    
	public static void main (String[] args) throws IOException{
	 //Leitura do Arquivo		
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
		
		//Obt�m o complemento reverso da entrada
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
	     
	     amostra = dados.toArray(new char[t][n]); //Converte o ArrayList dados para um array [t][n]
		 Scanner entrada = new Scanner(System.in);
		 int tMotif = 0;
		 t = dados.size();
		 n = dados.get(0).length;
		 System.out.print("Digite o tamanho do Motif: ");
		 tMotif = entrada.nextInt();
		 entrada.close();
		 
		 //Inicializa o ArrayList de ArrayList
		 for(int i = 0; i<amostra.length; i++) {
			 motifS.add(new ArrayList<Motif>());
		 }
	 
		 long tempoInicio = System.currentTimeMillis();
		 
		 ArrayList<Motif> bestMotifS = BranchAndBoundMotifSearch(amostra,t,n,tMotif);
		 
		 FileWriter arq = new FileWriter("Saida Motif 15.txt");
		 PrintWriter gravarArq = new PrintWriter(arq);
		 
		 gravarArq.println("N�mero de Sequ�ncias: " + amostra.length); 
		 gravarArq.println("Melhor Dist�ncia Hamming(min): " + bestMotifDistance + "\n");
		 for(int i = 0; i<bestMotifS.size(); i++) {
			 gravarArq.println("Motif " + (i+1) + " = " + bestMotifS.get(i).toString());
		 }
		 long tempoFinal = System.currentTimeMillis();
		 gravarArq.println("Tempo de Busca(segundos): " + (tempoFinal - tempoInicio)/1000);
		 
		 
		 gravarArq.close();
 }
	//Fun��o implementada usando como base o pseudo-c�digo BranchAndBoundMotifSearch com algumas altera��es.
	//Fun��o alterada para retornar um ArrayList contendo todos os motifs com valor de totaldistance m�nimo.
	//t - N�mero de sequ�ncias, n - Tamanho da sequ�ncia, l - Tamanho do motif que deseja buscar, DNA - array [t][n]
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
				//Se o prefixo j� possui uma totaldistance maior que a m�nima n�o � necessario verificar seus filhos.
				if(optimisticDistance>bestDistance){
					Info retorno = bypass(v, i, l, 4);
					v = retorno.getA();
					i = retorno.getI();	
				}
				//Se o prefixo possui uma totaldistance menor que a m�nima verificamos seus filhos.
				else{
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
				//Se a totaldistance do motif for menor que a m�nima o ArrayList bestMotifS � resetado e o motif � adicionado.
				if(minDistance < bestDistance){
					bestMotifS = new ArrayList<Motif>();
					bestDistance = totaldistance(word, DNA, l);
					bestMotifDistance = bestDistance;
					bestMotifS.add(new Motif(bestDistance,0,word));
				}
				//Se o motif tiver totaldistance igual a minima ele � adicionado ao ArrayList bestMotifS.
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
	
	//Fun��o que retorna a soma das hamming distance minimas de todas as sequencias com base no prefixo.
	public static int totaldistance(String prefix, char DNA[][], int l){
		int totaldistance = 0;
		int distance = 0;
		
		Motif[] motifs = new Motif[DNA.length]; //Armazena as hamming distance minimas de cada sequencia
		
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
				/*
				//Pode ser usado para verificar outras possibilidades de motifs acima do valor minimo encontrado.
				Motif aux = new Motif(distance, posinic, prefix);
				
				if(prefix.length()==l && distance <= prefix.length()/3) {
					if(motifS.get(k).contains(aux) == false) {
						motifS.get(k).add(aux);
					}
				}	
				*/
		
			 }
			motifs[k] = new Motif(min,posinic, prefix);		
		}	
		//Soma todas as hamming distance minimas das sequencias i.
		for(int i = 0; i<DNA.length; i++) {
			totaldistance += motifs[i].getHamdis();
		}
		
		return totaldistance;
	}
	
	//Fun��o que pula os galhos das arvores.
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
	//Fun��o que verifica o pr�ximo v�rtice da �rvore.
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