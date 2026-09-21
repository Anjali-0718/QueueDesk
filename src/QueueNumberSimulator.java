import java.util.Scanner;

public class QueueNumberSimulator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter starting ticket number: ");
        int startingNumber=sc.nextInt();
        System.out.print("Enter number of customers: ");
        int count=sc.nextInt();

        int served=0;
        int vip=0;
        int priorityRecheck=0;

        for (int i=0;i<count;i++) {
            int ticketNumber=startingNumber+i;
            System.out.println("Now serving ticket #" + ticketNumber);
            int callNumber=i+1;
            if (callNumber%10==0) {
                System.out.println("Priority recheck");
                priorityRecheck++;
            }
            else if (callNumber%5==0) {
                System.out.println("VIP lane");
                vip++;
            }
            served++;
        }
        System.out.println("Total tickets served: "+served);
        System.out.println("Total VIP tickets: "+vip);
        System.out.println("Total Priority Recheck tickets: "+priorityRecheck);
    }
}
