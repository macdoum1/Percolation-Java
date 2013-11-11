// Michael MacDougall
// Project 1
public class PercolationStats
{

    private double[] resultArray; //Holds results
    
    private int totalOpened; // How many spaces opened
    private int N; // Size for NxN grid
    private int count; // Count of simulation runs
    
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
        
        // Iterates T times
        for(int i = 0; i < T; i++)
        {
        	// Run Simulation
            int singleResult = simulation(N);
            
            // Store result in resultArray
            resultArray[i] = (double) singleResult/N/N;
            
            // 
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
                simulation.open(i,j); // Open site
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
    	// Prompt user for values for N and T
        StdOut.println("Enter a value for N");
        int N = StdIn.readInt();
        StdOut.println("Enter a value for T");
        int T = StdIn.readInt();
        
        // Time and run simulation
        Stopwatch sw = new Stopwatch();
        PercolationStats monteCarlo = new PercolationStats(N,T);
        double totalTime = sw.elapsedTime();
        
        // Print results of simulation
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