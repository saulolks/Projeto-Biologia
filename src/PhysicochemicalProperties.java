import java.util.ArrayList;

public class PhysicochemicalProperties {
    public static ArrayList<String> phChemicalMap(char codon){
        ArrayList<String> result;

        switch(codon){
            case 'A':
                result.add("Small");
                result.add("Hydrophobic");
                break;
            case 'G':
                result.add("Small");
                result.add("Hydrophobic");
                break;
            case 'V':
                result.add("Small");
                result.add("Hydrophobic");
                break;
            case 'D':
                result.add("Small");
                result.add("Polar");
                break;
            case 'S':
                result.add("Small");
                result.add("Polar");
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
                break;
            case 'E':
                result.add("Polar");
                break;
            case 'K':
                result.add("Polar");
                result.add("Hydrophobic");
                break;
            case 'H':
                result.add("Polar");
                result.add("Hydrophobic");
                break;
            case 'Y':
                result.add("Polar");
                result.add("Hydrophobic");
                break;
            case 'W':
                result.add("Polar");
                result.add("Hydrophobic");
                break;
            case 'F':
                result.add("Hydrophobic");
                break;
            case 'M':
                result.add("Hydrophobic");
                break;
            case 'L':
                result.add("Hydrophobic");
                break;
            case 'I':
                result.add("Hydrophobic");
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
