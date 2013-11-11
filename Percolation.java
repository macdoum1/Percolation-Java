// Michael MacDougall
// Project 1
public class Percolation {

    // Current Status Array
    private int[][]currentStatus;
 
    // QuickUnion Data Model
    private QuickFindUF unionFind;
 
    // Grid size N * N
    private int N;
 
    // Current Count
    private int currentCount;

    public Percolation(int N)              // create N-by-N grid, with all sites blocked
    {
        currentStatus = new int[N][N];
        unionFind = new QuickFindUF((N*N)+2);
        this.N = N;
        currentCount = 0;
  
        for(int i=0; i < N; i++)
        {
            for(int j=0; j < N; j++)
            {
                currentStatus[i][j] = 1;
                currentCount++;
                if(i == 0) // Check if first row
                {
                    unionFind.union(currentCount,0); //Connect first row together to ease percolation problem
                }
                else if(i == N - 1)
                {
                    unionFind.union(currentCount,(N*N)+ 1);
                } 
            }
        }
    }
 
    public void open(int i, int j)         // open site (row i, column j) if it is not already
    {
        int x,y;
        x = i - 1;
        y = j - 1;
        currentStatus[x][y] = 0;
     
        for(int k = x - 1; k <= x + 1; k+=2)
        {
            if(k >= 0 && k < N && y >= 0 && y < N)
            {
                if(isOpen(k+1,y+1))
                {
                    unionFind.union((k*N)+(y+1),(x*N)+j);
                }
            }
        }
     
        for(int l = y - 1; l <= y + 1; l += 2)
        {
            if(x >=0 && x < N && l >= 0 && l < N)
            {
                if(isOpen(x+1,l+1))
                {
                    unionFind.union((x*N)+(l+1),(x*N)+j);
                }
            }
        }
     
    }
    
    public boolean isOpen(int i, int j)    // is site (row i, column j) open?
    {
        if(checkBounds(i,j))
        {
            if(currentStatus[i-1][j-1] == 0)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFull(int i, int j)    // is site (row i, column j) full?
    {
        if(checkBounds(i,j))
        {
            if(unionFind.connected(((i-1)*N) + j, 0))
            {
                if(currentStatus[i-1][j-1] == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean percolates()            // does the system percolate?
    {
        return unionFind.connected(0,(N*N)+1);
    }
    
    private boolean checkBounds(int i, int j) // Checks bounds of i and j, throws exception if invalid
    {
        if(i > 0 && j > 0 && i <= N && j <= N)
        {
            return true;
        }
        else
        {
            throw new RuntimeException("Coordinates are out of bounds, please check input");
        }
   
    }
}