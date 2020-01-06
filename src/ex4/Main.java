package ex4;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final String LOCATION = "d:/SupnuevoPrice/UploadData/";
    int id[] = {101,122,149,166,176,207,270,320,340,539,573,747,776};
    HashMap<Integer,HashMap<String,Double>> value = new HashMap<>();
  //  int cnt = 0;
    void personSolver(File x) throws IOException, ClassNotFoundException {
        System.out.println("solving "+x.getAbsolutePath());
        int curId = Integer.parseInt(x.getName());
        File[] files = x.listFiles();
        x = files[0];
         //   cnt ++;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(x));
        String[] codigos = (String[]) objectInputStream.readObject();
        int[] cods = (int[])objectInputStream.readObject();
        double[] prices = (double[]) objectInputStream.readObject();
        for(int i=0;i<codigos.length;i++){
            if(codigos[i].equals("7793890251661")){
                System.out.println(prices[i]);
            }
            value.get(curId).put(codigos[i],prices[i]);
        }
    }
    void daySolver(File x) throws IOException, ClassNotFoundException {
        File[] files = x.listFiles();
        for(File cur : files){
            personSolver(cur);
        }
    }
    Main() throws IOException, ClassNotFoundException {
        for(int x:id){
            value.put(x,new HashMap<>());
        }
        File file = new File(LOCATION);
        File[] files = file.listFiles();
        Arrays.sort(files,(a,b)-> a.getName().compareToIgnoreCase(b.getName()));
        for(File x : files){
            if(x.isDirectory()){
                daySolver(x);
            }
        }
        file = new File(LOCATION+"codigo.txt");
        File fileOut = new File(LOCATION + "hhh.txt");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileOut));
        printWriter.println("codigo	suggestPrice");
        Scanner scan = new Scanner(file);
        while(scan.hasNext()){
            String cur = scan.next();
            int cnt = 0;
            double sum = 0;
            for(HashMap<String,Double> x : value.values()){
                if(x.containsKey(cur)){
                    cnt ++;
                    sum +=  x.get(cur);
                }
            }

            printWriter.println(cur+"\t"+sum/cnt + "\t" + sum + "\t" + cnt);
        }
        scan.close();
        printWriter.close();
      //  System.out.println(cnt);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Main();
    }
}
