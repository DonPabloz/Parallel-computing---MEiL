Welcome to readme file!

Here I will describe how to make usage of those files.

Step 1: Create azure account.
Step 2: Create storage account gen1/2.
Step 3: Create batch account and link the storage account to batch account.
Step 4: Add batch account application:
	a) ID: SimpleNavier
	b) version: 0.1
	c) add the PasteToBatchApp.zip file
Step 5: In BatchProject open the BatchDotnetTutorialFfmpeg.sln file as project (for ex. in VisualStudio)
Step 6: Change credential to your own batch and storage account in lines: 24, 25, 26, 29, 30 in Program.cs
Step 7: Put the input files in the BatchCode->BatchDotnetTutorialFfmpeg->InputFiles. The input files should consist of plain .txt files in which the user provides
following data separated by space: number of steps (int), number of timestamps(int), amount of time each "timestamp" covers (double), assumed wave speed (double)(ex. 41 25 0.025 1.0).
Step 8: Run BatchDotnetTutorialFfmpeg.sln

Results: Results of the computation are found in output at storage account. Those are .txt files with speed values in according timestamps.

To change how the application work you need to change code of "ParallelCode" project. After changes are made,
make the .exe file (for example with launch4j program) out of your code (and attach jre with it).

If you want to change anything that is linked to azure settings (eg. quantity of virtual machines, input and output files etc.)then change Program.cs code.