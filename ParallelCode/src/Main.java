import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Name of the input file with it's extension
        String inputFileNameWithExtension = args[0];
        // Name of the output file with it's extension
        String outputFileNameWithExtension = args[1];
        // Number of iteration in tasks
        String numberOfIteration = args[2];

        // Path that is necessary for this to work. It's a path in Azure pool VM's
        String pathToFile = "D:\\batch\\tasks\\workitems\\WinFFmpegJob\\job-1\\Task" + numberOfIteration + "\\wd\\";

        // Saving results of calculations to a list
        List<Double> result = LinearNavierSolver(pathToFile, inputFileNameWithExtension);

        // Saving results to a file
        File outputFile = new File(outputFileNameWithExtension);
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile + outputFile));
        writer.write(String.valueOf(result));
        writer.close();

    }

    // Program assumes that initial velocity is 2 at 0.5<=x<=1, everywhere else initial velocity is 1
    // nx - number of steps, nt - number of timestamps, dt - amount of time each "timestamp" covers, c - assumed wave speed
    public static List<Double> LinearNavierSolver(String pathToFile, String inputFileNameWithExtension) throws FileNotFoundException {

        // Saving data from inputFile
        // Opening file and scanning it for variables
        File inputFile = new File(pathToFile + inputFileNameWithExtension);
        List<String> data = new ArrayList<>();
        Scanner input = new Scanner(inputFile);
        while (input.hasNext()) {
            data.add(input.next());
        }
        // Saving data for LinearNavierSolver
        // nx - number of steps, nt - number of timestamps, dt - amount of time each "timestamp" covers, c - assumed wave speed
        int nx = Integer.parseInt(data.get(0));
        int nt = Integer.parseInt(data.get(1));
        double dt = Double.parseDouble(data.get(2));
        double c = Double.parseDouble(data.get(3));
        // Data from inputFile saved and ready to use!

        // In equation: delta x
        double dx = 2 / ((double) nx - 1);
        // Creating a list for starting velocity filled with 1.0
        List<Double> u = new ArrayList<Double>(Collections.nCopies(nx, 1.0));
        // Adding starting constrains
        for (int i = 0 ; i < u.size() ; i++) {
            if (i * dx >= 0.5 && i * dx <= 1.0) {
                u.set(i, 2.0);
            }
        }

        // Temporal list for calculating u
        List<Double> un;

        for (int n = 0 ; n < nt ; n++){
            un = u;
            for( int i = 1 ; i < nx ; i++){
                u.set(i, un.get(i) - c * dt / dx * (un.get(i) - un.get(i - 1)));
            }
        }
        return u;
    }
}