package ex1;

import java.util.Scanner;

class Person{
    int num;
    String name;
    public Person(){}
    public Person(int number,String name){
        this.num = number;
        this.name = name;
    }
    public String toString(){
        return num +" "+ name;
    }
}
public class Main {
    Main(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Person[] data = new Person[n+1];
        for(int i=1;i<=n;i++){
            int number = scan.nextInt();
            String name = scan.next();
            data[i] = new Person(number,name);
        }
        for(int i=1;i<=n;i++){
            for(int j=2;j<=n;j++){
                if(data[j-1].num < data[j].num){
                    Person tmp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = tmp;
                }
            }
        }
        for(int i=1;i<=n;i++){
            System.out.println(data[i].toString());
        }
    }
    public static void main(String[] args){
        new Main();
    }
}
