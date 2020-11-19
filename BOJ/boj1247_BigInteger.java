import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);

        for(int tc=1;tc<=3;tc++){
            int n=sc.nextInt();
            BigInteger val=sc.nextBigInteger();
            for(int i=1;i<n;i++){
                val=val.add(sc.nextBigInteger());
            }
            char first=val.toString().charAt(0);
            if(first=='-') System.out.println("-");
            else if(first=='0') System.out.println("0");
            else System.out.println("+");
        }
    }
}
