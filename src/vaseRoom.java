import java.util.LinkedList;
import java.util.Queue;

public class vaseRoom {
    
    private Queue<vaseThread> vase_queue = new LinkedList<>(); // Queue of guests waiting to see the vase
    boolean hasGuest = false;

    public boolean inQueue(int n)
    {
        for(vaseThread element : vase_queue) 
        {
            if(element.threadNum == n)
                return true;
        }
        return false;        
    }
    public void addThread(vaseThread t)
    {
        synchronized(this)
        {
            vase_queue.add(t);
        }
    }
    public int getSize()
    {
        synchronized(this)
        {
            return vase_queue.size();
        }
    }
    public vaseThread enterRoom()
    {
        synchronized(this)
        {
            System.out.println("Guest "+vase_queue.peek().threadNum+" has entered the room");
            hasGuest = true;
            return vase_queue.remove();
        }
    }
    public void leaveRoom()
    {
        synchronized(this)
        {
            hasGuest = false;
        }
    }
    public boolean isOccupied()
    {
        synchronized(this)
        {
            return hasGuest; 
        }
    }
}
