// Michael MacDougall
// Project 1
public class PercolationVisualizer
{
    //Delay for StdDraw display
    private final static int guiDelay = 50;
    public static void main(String[] args)
    {
        // Checks for arguments
        if(args.length == 1)
        {
            fromFilename(args[0]);
        }
        else
        {
            throw new RuntimeException("Please provide a valid filename"); // Throws exception if no arguments
        }
    }
    
    private static void fromFilename(String file)
    {
        // Read in file and set up percolation simulation
        In input = new In(file);
        int N = input.readInt();
        Percolation simulation = new Percolation(N);
        
        StdDraw.show(0); // Show Window
        draw(simulation,N);
        StdDraw.show(guiDelay); // Delay
        while(!input.isEmpty())
        {
            int x = input.readInt();
            int y = input.readInt();
            simulation.open(x,y); // Open site x,y
            draw(simulation,N); // Draw Simulation
            StdDraw.show(guiDelay); // Delay
        }
    }
    public static void draw(Percolation simulation, int N)
    {
        StdDraw.clear();
        // Pen Color
        StdDraw.setPenColor(StdDraw.BLACK);
        
        // Set Scale
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N);
        
        // Filled Square Parameters
        StdDraw.filledSquare(N/2.0,N/2.0,N/2.0);
        
        for (int r = 1; r <= N; r++)
        {
            for(int c = 1; c <= N; c++)
            {
                if(simulation.isFull(r,c))
                {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE); // Color Light Blue if full
                }
                else 
                {
                    if(simulation.isOpen(r,c))
                    {
                        StdDraw.setPenColor(StdDraw.WHITE); // Color White if open
                    }
                    else
                    {
                        StdDraw.setPenColor(StdDraw.BLACK); // Color Baclk if not open or full
                    }
                }
                StdDraw.filledSquare(c - 0.5, N + 0.5 - r, 0.45); // Fill Square & Scale
            }
        }
        
    }
}