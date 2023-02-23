public class plateRoom
{
    private boolean isCupcake = true, isOccupied = false;

    public boolean enter() 
    {
        synchronized(this)
        {
            if(!isOccupied)
            {
                isOccupied = true;
                return true;
            }
            return false;
        }
    }
    public void leave() 
    {
        synchronized(this)
        {
            isOccupied = false;
        }
    }
    public boolean viewPlate() 
    {
        return isCupcake;
    }
    public void eat() 
    {
        synchronized(this)
        {
            isCupcake = false;
        }
    }
    public void refill() 
    {
        synchronized(this)
        {
            isCupcake = true;
        }
    }
  }