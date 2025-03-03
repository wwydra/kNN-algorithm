import java.util.*;

public class Knn {
    private final int k;
    public Knn(int k) {
        this.k = k;
    }
    public ArrayList<IrisData> classify(double[] attributes, ArrayList<IrisData> data){
        double distance;
        ArrayList<IrisData> classified = new ArrayList<>();
        Map<IrisData, Double> distanceMap = new HashMap<>();

        for (IrisData iris : data) {
            double part = 0;
            for (int j = 0; j < attributes.length; j++) {
                part += Math.pow(iris.getAttributes()[j] - attributes[j], 2);
            }
            distance = Math.sqrt(part);
            distanceMap.put(iris, distance);
        }

        List<Map.Entry<IrisData, Double>> list = new ArrayList<>(distanceMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        for (int i = 0; i < k; i++) {
            classified.add(list.get(i).getKey());
        }

        return classified;
    }

    public String type(ArrayList<IrisData> classified){
        Map<String, Integer> freqMap = new HashMap<>();
        for (IrisData iris : classified) {
            String decisionAttribute = iris.getDecisionAttribute();
            freqMap.put(decisionAttribute, freqMap.getOrDefault(decisionAttribute, 0) + 1);
        }

        String type = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > max){
                max = entry.getValue();
                type = entry.getKey();
            }
        }

        return type;
    }

    public double testAccuracy(ArrayList<IrisData> testData, ArrayList<IrisData> trainingData){
        int correct = 0;

        for (IrisData test : testData){
            ArrayList<IrisData> classified = classify(test.getAttributes(), trainingData);
            String type = type(classified);
            if (type.trim().equals(test.getDecisionAttribute().trim())) {
                correct++;
            }
        }

        return ((double)correct / (double) testData.size()) * 100;
    }
}
