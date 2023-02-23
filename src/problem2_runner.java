import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;


public class problem2_runner {

    public static int getGuestNumber()
    {
        int num; // number of guests at the party
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the number of guests: ");
        while(true)
        {
            try
            {
                num = scan.nextInt();  // Read user input
                scan.close();
                return num;
            }
            catch (InputMismatchException e) 
            {
                System.out.print("Invalid response, please try and enter a number: ");
                scan.nextLine();
            }
        }
    }

    public static void admireVase(vaseThread t)
    {
        Random rand = new Random(); 
        while(true)
        {
            int decision = rand.nextInt(2); // 0 = leave room, 1 = stay a little longer
            if(decision == 1)
            {
                System.out.println("lets admire the vase for a little longer...");
                synchronized (t) {
                    try {
                        t.wait(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else
                return;
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        int N = getGuestNumber();
        vaseRoom room = new vaseRoom();
        vaseThread[] threadArr = new vaseThread[N];

 
        for(int i = 0; i < N; i++)  // initializes each thread and tells it to start 
        {
            threadArr[i] = new vaseThread(room, i);
            threadArr[i].start();
        }

        long temp = System.currentTimeMillis();
        while(System.currentTimeMillis() - temp < 15000) // stops running the code after 15 seconds
        {
            if(room.getSize() > 0 && room.isOccupied() == false)
            {
                vaseThread t = room.enterRoom(); // guest currently in room
                admireVase(t);
                System.out.println("Guest "+t.threadNum+" is leaving the room...");
                threadArr[t.threadNum].inQueue = false;
                room.leaveRoom();
            }
        }

        for(int i = 0; i < N; i++)
        {
            if(threadArr[i].isAlive()) // kill the threads
                threadArr[i].stop();
        }
    }
}
