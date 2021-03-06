public class OccurrenceAminoAcids {
    public char[] aminoAcid;
    public int[] occurrence;

    public OccurrenceAminoAcids(){
        this.aminoAcid = new char[18];
        this.aminoAcid[0] = 'A';
        this.aminoAcid[1] = 'C';
        this.aminoAcid[2] = 'F';
        this.aminoAcid[3] = 'G';
        this.aminoAcid[4] = 'H';
        this.aminoAcid[5] = 'I';
        this.aminoAcid[6] = 'K';
        this.aminoAcid[7] = 'L';
        this.aminoAcid[8] = 'M';
        this.aminoAcid[9] = 'N';
        this.aminoAcid[10] = 'P';
        this.aminoAcid[11] = 'Q';
        this.aminoAcid[12] = 'R';
        this.aminoAcid[13] = 'S';
        this.aminoAcid[14] = 'T';
        this.aminoAcid[15] = 'V';
        this.aminoAcid[16] = 'W';
        this.aminoAcid[17] = 'Y';

        this.occurrence = new int[18];
    }

    public int getIndexByAminoAcid(char aA){
        for(int i = 0; i < 18; i++){
            if(aA == this.aminoAcid[i]) return i;
        }
        return 1;
    }

    public static char getProtein(String codon){
        if(codon.equals("UUU")) return 'F';
        else if(codon.equals("UUC")) return 'F';
        else if(codon.equals("UUA")) return 'L';
        else if(codon.equals("UUG")) return 'L';
        else if(codon.equals("UCU")) return 'S';
        else if(codon.equals("UCC")) return 'S';
        else if(codon.equals("UCA")) return 'S';
        else if(codon.equals("UCG")) return 'S';
        else if(codon.equals("UAU")) return 'Y';
        else if(codon.equals("UAC")) return 'Y';
        else if(codon.equals("UAA")) return '-';
        else if(codon.equals("UAG")) return '-';
        else if(codon.equals("UGU")) return 'C';
        else if(codon.equals("UGC")) return 'C';
        else if(codon.equals("UGA")) return '-';
        else if(codon.equals("UGG")) return 'W';
        else if(codon.equals("CUU")) return 'L';
        else if(codon.equals("CUC")) return 'L';
        else if(codon.equals("CUA")) return 'L';
        else if(codon.equals("CUG")) return 'L';
        else if(codon.equals("CCU")) return 'P';
        else if(codon.equals("CCC")) return 'P';
        else if(codon.equals("CCA")) return 'P';
        else if(codon.equals("CCG")) return 'P';
        else if(codon.equals("CAU")) return 'H';
        else if(codon.equals("CAC")) return 'H';
        else if(codon.equals("CAA")) return 'Q';
        else if(codon.equals("CAG")) return 'Q';
        else if(codon.equals("CGU")) return 'R';
        else if(codon.equals("CGC")) return 'R';
        else if(codon.equals("CGA")) return 'R';
        else if(codon.equals("CGG")) return 'R';
        else if(codon.equals("AUU")) return 'I';
        else if(codon.equals("AUC")) return 'I';
        else if(codon.equals("AUA")) return 'I';
        else if(codon.equals("AUG")) return 'M';
        else if(codon.equals("ACU")) return 'T';
        else if(codon.equals("ACC")) return 'T';
        else if(codon.equals("ACA")) return 'T';
        else if(codon.equals("ACG")) return 'T';
        else if(codon.equals("AAU")) return 'N';
        else if(codon.equals("AAC")) return 'N';
        else if(codon.equals("AAA")) return 'K';
        else if(codon.equals("AAG")) return 'K';
        else if(codon.equals("AGU")) return 'S';
        else if(codon.equals("AGC")) return 'S';
        else if(codon.equals("AGA")) return 'R';
        else if(codon.equals("AGG")) return 'R';
        else if(codon.equals("GUU")) return 'V';
        else if(codon.equals("GUC")) return 'V';
        else if(codon.equals("GUA")) return 'V';
        else if(codon.equals("GUG")) return 'V';
        else if(codon.equals("GCU")) return 'A';
        else if(codon.equals("GCC")) return 'A';
        else if(codon.equals("GCA")) return 'A';
        else if(codon.equals("GCG")) return 'A';
        else if(codon.equals("GAU")) return 'D';
        else if(codon.equals("GAC")) return 'D';
        else if(codon.equals("GAA")) return 'E';
        else if(codon.equals("GAG")) return 'E';
        else if(codon.equals("GGU")) return 'G';
        else if(codon.equals("GGC")) return 'G';
        else if(codon.equals("GGA")) return 'G';
        else if(codon.equals("GGG")) return 'G';
        else return '/';
    }
}
