public class cupcakeThread extends Thread
{
    plateRoom room;
    boolean tracker;
    int guestNum, count = 0;
    
    int threadNum;

    /*  lets us know if this guest has visited. ONLY this thread should have access to this value.
        The purpose is to help the thread make its own choice, NOT to let us know when to stop */
    private boolean hasVisited = false; 

    public cupcakeThread(plateRoom room, int threadNum) // normal guest constructor
    {
        this.room = room;
        this.tracker = false;

        this.threadNum = threadNum;
    }

    public cupcakeThread(plateRoom room, int N, int threadNum) // tracker constructor 
    {
        this.room = room;
        this.tracker = true;
        this.guestNum = N;
        
        this.threadNum = threadNum;
    }

    // I am a normal guest thread who has entered the room
    public void normalGuest()
    {
        System.out.println("thread number "+threadNum+" has entered the room");
        if(room.viewPlate() && hasVisited == false) // I have yet to eat the cupcake and no one has eaten it yet
        {
            System.out.println("thread number "+threadNum+" has ate the cake");
            room.eat();
            hasVisited = true;
        }
    }
    public void trackerGuest()
    {
        System.out.println("tracker thread number "+threadNum+" has entered the room");
        if(hasVisited == false) // Im the tracker, I need to include myself but theirs no point in eating the cupcake
        {
            System.out.println("tracker thread number "+threadNum+" added himself to his own count");
            hasVisited = true;
            count++;
        }

        if(room.viewPlate() == false) // someone had entered the room for the first time and ate a cupcake
        {
            System.out.println("tracker thread number "+threadNum+" has noted that someone ate the cake");
            room.refill();
            count++;
        }
    }

    @Override
    public void run()   // tell thread what to do
    {
        while(true)
        {
            if(room.enter()) // if the room was empty we just entered
            {
                if(tracker == false) // am I the tracker?
                    normalGuest();
                else
                {
                    trackerGuest();
                    System.out.println("count = "+count);
                    if(count == guestNum) // if all guests have visited the room at least once
                        break; 
                }
                System.out.println("thread number "+threadNum+" has left the room");
                room.leave();
            }
        }
    }
}
