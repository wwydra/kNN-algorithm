import java.io.*;
import java.util.ArrayList;

public class IrisData {
    private String decisionAttribute;
    private double[] attributes;

    public IrisData(String decisionAttribute, double[] attributes) {
        this.decisionAttribute = decisionAttribute;
        this.attributes = attributes;
    }

    public static ArrayList<IrisData> read(String path){
        ArrayList<IrisData> data = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.replace(',', '.').trim().split("\t");
                String decisionAttribute = parts[parts.length - 1];
                double[] attributes = new double[parts.length - 1];
                for (int i = 0; i < parts.length - 1; i++) {
                    attributes[i] = Double.parseDouble(parts[i]);
                }
                data.add(new IrisData(decisionAttribute, attributes));
            }
        }catch (IOException e){
            System.out.println("Blad odczytu pliku: " + e.getMessage());
        }

        return data;
    }

    public String getDecisionAttribute() {
        return decisionAttribute;
    }

    public double[] getAttributes() {
        return attributes;
    }

    public void setDecisionAttribute(String decisionAttribute) {
        this.decisionAttribute = decisionAttribute;
    }

    public void setAttributes(double[] attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return decisionAttribute;
    }
}
