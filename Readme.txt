Michael MacDougall
How to Compile/Run
	VERY IMPORTANT:
		During testing the textbook libraries were installed using the all-in-one installer
		and all code was compiled and ran using the DrJava provided in the all-in-one installer.
	Percolation Stats
		Ensure that Textbook Libraries are installed
		Compile in DrJava
		Run PercolationStats and Provide N and T values when prompted
	Percolation Visualizer
		Ensure that Textbook Libraries are installed
		Compile in DrJava
		Run PercolationVisualizer providing the input file as an argument 	
		(visualizerTest.txt has been provided)

Project 1 Analysis

Quick-find
	Base Run: 50x50 Grid with 50 repetitions
		Time:	0.09 seconds
		
	Doubling N
		Time:	1.209 seconds
	
	Doubling T
		Time: 	0.174 seconds
		
	Time Formula: T(n) ~ N^2*T
	
	Comments: Was not able to get 200x200 grid with 200 repetitions 
	to complete within a short period of time.

Weighted Quick Union
	Base Run: 50x50 Grid with 50 repetitions
		Time:	0.011 seconds
		
	Doubling N: 
		Time: 	0.035 seconds
		
	Doubling T: 
		Time:	0.02 seconds
		
	Time Formula: T(n) ~ N + T log* N
	
	Comments: Was able to get 500x500 grid with 500 repetitions to 
	complete within 10 seconds.
	
p*
	Using Weighted Quick Union with a 500x500 grid with 500 repetitions,
	a p* value of 0.5925026 was found.
	
Memory Usage

	Quick-Union
		2 int arrays		~ 8N bytes
		1 int 				~ 4 bytes
	Percolation
		1 2-d in array		~ 4N^2 bytes
		2 ints				~ 8 bytes
	PercolationStats
		1 double array		~ 8N bytes	
		3 ints				~ 12 bytes
	Driver
		2 ints				~ 8 bytes
		5 doubles			~ 40 bytes
	Stopwatch
		1 long				~ 8 bytes
		
	Total: 4N^2 + 18N + 80 bytes
