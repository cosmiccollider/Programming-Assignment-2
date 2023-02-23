import java.util.Random;

public class vaseThread extends Thread {

    vaseRoom room;
    int threadNum;
    volatile boolean inQueue = false;

    public vaseThread(vaseRoom room, int threadNum)
    {
        this.room = room;
        this.threadNum = threadNum;
    }

    @Override
    public void run()   // tell thread what to do
    {
        // first decide if the guest wants to go see the vase
        Random rand = new Random(); 
        while(true)
        {
            //System.out.println("thread "+threadNum+" inQueue "+inQueue);
            if(!inQueue)
            {
                int seeVase = rand.nextInt(2); 
                if(seeVase == 1)
                {
                    System.out.println("Guest "+this.threadNum+" has entered the queue");
                    inQueue = true;
                    room.addThread(this);
                }
            }
            
        }
    }
}
