import java.util.ArrayList;

public class PhysicochemicalProperties {
    public static ArrayList<String> phChemicalMap(char codon){
        ArrayList<String> result = new ArrayList<>();

        switch(codon){
            case 'A':
                result.add("Small");
                result.add("Hydrophobic");
                result.add("Tiny");
                break;
            case 'G':
                result.add("Small");
                result.add("Hydrophobic");
                result.add("Tiny");
                break;
            case 'V':
                result.add("Small");
                result.add("Hydrophobic");
                result.add("Aliphatic");
                break;
            case 'D':
                result.add("Small");
                result.add("Polar");
                result.add("-ve");
                break;
            case 'S':
                result.add("Small");
                result.add("Polar");
                result.add("Tiny");
                break;
            case 'N':
                result.add("Small");
                result.add("Polar");
                break;
            case 'P':
                result.add("Small");
                break;
            case 'Q':
                result.add("Polar");
                break;
            case 'R':
                result.add("Polar");
                result.add("+ve");
                break;
            case 'E':
                result.add("Polar");
                result.add("-ve");
                break;
            case 'K':
                result.add("Polar");
                result.add("Hydrophobic");
                result.add("+ve");
                break;
            case 'H':
                result.add("Polar");
                result.add("Hydrophobic");
                result.add("+ve");
                break;
            case 'Y':
                result.add("Polar");
                result.add("Hydrophobic");
                result.add("Aromatic");
                break;
            case 'W':
                result.add("Polar");
                result.add("Hydrophobic");
                result.add("Aromatic");
                break;
            case 'F':
                result.add("Hydrophobic");
                result.add("Aromatic");
                break;
            case 'M':
                result.add("Hydrophobic");
                break;
            case 'L':
                result.add("Hydrophobic");
                result.add("Aliphatic");
                break;
            case 'I':
                result.add("Hydrophobic");
                result.add("Aliphatic");
                break;
            case 'C':
                result.add("Polar");
                result.add("Hydrophobic");
                result.add("Small");
                break;
            case 'T':
                result.add("Polar");
                result.add("Hydrophobic");
                result.add("Small");
                break;
            default:

        }

        return result;
    }
}
