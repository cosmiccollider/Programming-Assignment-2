import java.util.InputMismatchException;
import java.util.Scanner;

public class problem1_runner {

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

    public static void main(String[] args) throws InterruptedException
    {
        int N = getGuestNumber();
        plateRoom room = new plateRoom();
        cupcakeThread[] threadArr = new cupcakeThread[N];
 
        for(int i = 0; i < N; i++)  // initializes each thread and tells it to start 
        {
            // our first thread is the one responsible for tracking how many people have visisted at least once
            if(i == 0)
                threadArr[i] = new cupcakeThread(room, N, i); 
            else
                threadArr[i] = new cupcakeThread(room, i);

            threadArr[i].start();
        }
        threadArr[0].join(); // we should only stop once the tracker knows everyone has visited at least once
        
        for(int i = 0; i < N; i++)
        {
            if(threadArr[i].isAlive()) // kills all the threads
                threadArr[i].stop();
        }
    }
}
