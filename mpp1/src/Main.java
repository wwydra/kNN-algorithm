import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean runnig = true;
        Scanner scanner = new Scanner(System.in);

        String path1 = "../iris_training.txt";
        String path2 = "../iris_test.txt";

        ArrayList<IrisData> trainingList = IrisData.read(path1);
        ArrayList<IrisData> testList = IrisData.read(path2);
        int attrCount = testList.get(0).getAttributes().length;

        while (runnig){
            double[] attributes = new double[attrCount];
            for (int i = 1; i <= attrCount; i++){
                System.out.println("Podaj wartosc atrybutu " + i + ". :");
                attributes[i - 1] = scanner.nextDouble();
            }

            System.out.println("Podaj wartosc k:");
            int k = scanner.nextInt();

            System.out.println();
            Knn knn = new Knn(k);
            ArrayList<IrisData> classified = knn.classify(attributes, testList);
            System.out.println("Zaklasyfikowane przyklady:");
            for (IrisData iris : classified){
                System.out.println(iris);
            }
            System.out.println();
            System.out.println("Typ: " + knn.type(classified));
            System.out.println();

            System.out.println("Dokladnosc eksperymentu: " + knn.testAccuracy(testList, trainingList) + "%");
            System.out.println();

            System.out.println("Czy chcesz kontynuowac? tak/nie");
            String answer = scanner.next();
            if (answer.equals("nie")){
                runnig = false;
            }
        }

        scanner.close();
    }
}
