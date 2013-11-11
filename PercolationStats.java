// Michael MacDougall
// Project 1
public class PercolationStats
{

    private double[] resultArray; //Holds results
    
    private int totalOpened;
    private int N;
    private int count;
    
    public PercolationStats(int N, int T) // Constructor
    {
        if(N <= 0 || T <= 0)
        {
            throw new java.lang.IllegalArgumentException();
        }

        resultArray = new double[T];
        totalOpened = 0;
        count = 0;
        this.N = N;
        
        for(int i = 0; i < T; i++)
        {
            int singleResult = simulation(N);
            resultArray[i] = (double) singleResult/N/N;
            totalOpened = totalOpened + singleResult;
            count++;
        }
        
    }
    
    private int simulation(int N) // Runs simulation on grid size N*N
    {
        int x = 0;
        Percolation simulation = new Percolation(N);
        while(!simulation.percolates()) // Runs until grid percolates
        {
            int i = StdRandom.uniform(N) + 1; // Random i
            int j = StdRandom.uniform(N) + 1; // Random j
            if(!simulation.isOpen(i,j)) // Checks if not open
            {
                x++;
                simulation.open(i,j);
            }
        }
        return x;
    }
    
    public double getStdDev() //Returns Standard Deviation
    {
        if(count != 1)
        {
            return StdStats.stddev(resultArray); //Finds standard deviation of results
        }
        else
        {
            return Double.NaN; //Return NaN if count is equal to 1
        }
            
    }
    
    public double getMean() // Returns mean
    {
        return (double) totalOpened/count/N/N;
    }
    
    public static void main(String[] args) // Simple Driver
    {
        StdOut.println("Enter a value for N");
        int N = StdIn.readInt();
        StdOut.println("Enter a value for T");
        int T = StdIn.readInt();
        
        Stopwatch sw = new Stopwatch();
        PercolationStats monteCarlo = new PercolationStats(N,T);
        double totalTime = sw.elapsedTime();
        
        double mean = monteCarlo.getMean();
        double stdDev = monteCarlo.getStdDev();
        double bottom = (mean - (1.96*stdDev)/Math.sqrt(T));
        double top = (mean + (1.96*stdDev)/Math.sqrt(T));
        StdOut.println("Mean Percolation Threshold = " + mean);
        StdOut.println("Standard Deviation         = " + stdDev);
        StdOut.println("95% Confidence Interval    = [" + bottom + "],[" + top + "]");
        StdOut.println("Total Time = " + totalTime);
        
    }
}