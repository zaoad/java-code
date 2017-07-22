import java.util.*;
import java.lang.*;
import java.io.*;
class accout{
    private int  balance ;
    boolean valueset;
    accout(int b)
    {
        this.balance=b;
        valueset=false;
    }
    public synchronized void deposite(int clientno,int balance) throws IOException, InterruptedException {
        if(!valueset)
        {
            wait();
        }
        valueset=false;
        this.balance+=balance;
        FileWriter fout=new FileWriter("balance.txt");
        fout.write(balance);
        fout.close();
        System.out.println("client no "+clientno+": deposite"+balance+" taka");
        System.out.println("total balance :"+this.balance);
        notify();
    }
    public synchronized void retrieve(int clientno,int balance) throws IOException, InterruptedException {
        if(valueset)
            wait();
        valueset=true;
        if(this.balance<balance)
        {
            System.out.println("sry balance insufficient");
        }
        else{
            this.balance-=balance;
            FileWriter fout=new FileWriter("balance.txt");
            fout.write(balance);
            fout.close();
            System.out.println("client no "+clientno+": retrieve"+balance+" taka");
            System.out.println("total balance :"+this.balance);
        }
        notify();
    }
}
class client extends Thread{
    int value,clientno;
    boolean br;
    accout ac;
    client(int clientno,int value,boolean br,accout ac)
    {
        this.clientno=clientno;
        this.value=value;
        this.br=br;
        this.ac=ac;
    }
    public void run()
    {
        while(true)
        {
            if(br)
            {
                try {
                    ac.deposite(clientno,value);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    ac.retrieve(clientno,value);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
/* Name of the class has to be "Main" only if the class is public. */

public class TaskOneSolution23 {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        FileWriter fout=new FileWriter("balance.txt");
        FileReader fin=new FileReader("balance.txt");
        Scanner fsc=new Scanner(fin);
        System.out.println("add initial value");
        String bal=sc.next();
        fout.write(bal);
        fout.close();
        System.out.println("numbers of clients");
        int n=sc.nextInt();
        client cl[]=new client[n];
        boolean br;
        int value,initbal;
        int in=fsc.nextInt();
        System.out.println(in);
        accout ac=new accout(in);
 /// assume that odd no. client deposite money and even no. client retrieve money;
        for(int i=0;i<n;i++) {
            if (i % 2 == 1) {
                br = false;
            } else {
                br = true;
            }
            cl[i] = new client(i, i * 10, br, ac);
            cl[i].start();
        }
    }
}
