
import java.util.*;


// This class finds the optimal disaster relief path, maximizing the people
//  helped and minimizing costs. This class also explores many possibilities
//  to account for different scenarios.
public class Client {
    private static final Random RAND = new Random();

    public static void main(String[] args) throws Exception {
        List<Region> scenario = createRandomScenario(5, 100, 1000, 100, 1000);
        System.out.println(scenario);
        Path allocation = findPath(scenario);
        if (allocation != null) {
            printResult(allocation);
        } else {
            System.out.println("No valid path found. :-(");
        }
    }

    // Behavior: 
    //   - This method finds the most optimal path through a list of regions to maximize
    //  the people helped and lower travel costs.
    // Parameters:
    //   - sites: list of unvisited regions.
    // Returns:
    //   - Path: the optimal best path for the current region.
    //   - null: returned if the list of sites do not contain any regions. 
    // Exceptions:
    //   - If the given sites is negative, an IllegalArgumentException is thrown.
    public static Path findPath(List<Region> sites) {
        if(sites == null){
            throw new IllegalArgumentException();
        }
        if(sites.isEmpty()){
            return null;
        }
        Region site = sites.get(0);
        sites.remove(site);
        return findPath(sites, new Path().extend(site));
    }

    // Behavior: 
    //   - This method explores all possible paths from the current region and selects.
    // the best region depending on total population and travel costs.
    // Parameters:
    //   - sites: list of unvisited regions.
    //   - currentPath: the current path made.
    // Returns:
    //   - Path: the best path found from the current path onward.
    //   - currentPath: if the list of sites are empty, the current path that was made
    //  is returned.
    // Exceptions:
    //   - If the given sites is null, an IllegalArgumentException is thrown.
    private static Path findPath(List<Region> sites, Path currentPath){
        if(sites == null){
            throw new IllegalArgumentException();
        }
        if(sites.isEmpty()){
            return currentPath;
        }
        Path bestPath = currentPath;
        for (int i = 0; i < sites.size(); i++){
            if(currentPath.getEnd().canReach(sites.get(i))){
                Path path = currentPath.extend(sites.get(i));
                Region removed = sites.remove(i);
                Path result = findPath(sites, path);
                sites.add(i, removed);
                if (bestPath == null || bestPath.totalPeople() < result.totalPeople() ||
                    (bestPath.totalPeople() == result.totalPeople() && bestPath.totalCost()
                     > result.totalCost())){
                        bestPath = result;
                }
            } 

        }
        return bestPath;
    }



    ///////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
    * Prints each path in the provided set. Useful for getting a quick overview
    * of all path currently in the system.
    * @param paths Set of paths to print
    */
    public static void printPaths(Set<Path> paths) {
        System.out.println("All Allocations:");
        for (Path a : paths) {
            System.out.println("  " + a);
        }
    }

    /**
    * Prints details about a specific path result, including the total people
    * helped and total cost.
    * @param path The path to print
    */
    public static void printResult(Path path) {
        System.out.println("Result: ");
        List<Region> regions = path.getRegions();
        System.out.print(regions.get(0).getName());
        for (int i = 1; i < regions.size(); i++) {
            System.out.print(" --($" + regions.get(i - 1).getCostTo(regions.get(i)) + ")-> " + regions.get(i).getName());
        }
        System.out.println();
        System.out.println("  People helped: " + path.totalPeople());
        System.out.printf("  Cost: $%.2f\n", path.totalCost());
    }

    /**
    * Creates a scenario with numRegions regions by randomly choosing the population 
    * and travel costs for each region.
    * @param numRegions Number of regions to create
    * @param minPop Minimum population per region
    * @param maxPop Maximum population per region
    * @param minCost Minimum cost of travel between regions
    * @param maxCost Maximum cost of travel between regions
    * @return A list of randomly generated regions
    */
    public static List<Region> createRandomScenario(int numRegions, int minPop, int maxPop,
                                                    double minCost, double maxCost) {
        List<Region> result = new ArrayList<>();

        // ranomly create regions
        for (int i = 0; i < numRegions; i++) {
            // int pop = RAND.nextInt(minPop, maxPop + 1);
            int pop = RAND.nextInt(maxPop - minPop + 1) + minPop;
            result.add(new Region("Region #" + i, pop));
        }

        // randomly create connections between regions
        for (int i = 0; i < numRegions; i++) {
            // int numConnections = RAND.nextInt(1, numLocs - i);
            Region site = result.get(i);
            for (int j = i + 1; j < numRegions; j++) {
                // flip a coin to decide whether or not to add each connection
                if (RAND.nextBoolean()) {
                    Region other = result.get(j);
                    // double cost = round2(RAND.nextDouble(minCostPer, maxCostPer));
                    double cost = round2(RAND.nextDouble() * (maxCost - minCost) + maxCost);
                    site.addConnection(other, cost);
                    other.addConnection(site, cost);
                }
            }
        }

        return result;
    }

    /**
    * Manually creates a simple list of regions to represent a known scenario.
    * @return A simple list of regions
    */
    public static List<Region> createSimpleScenario() {
        List<Region> result = new ArrayList<>();
        Region regionOne = new Region("Region #1", 100);
        Region regionTwo = new Region("Region #2", 50);
        Region regionThree = new Region("Region #3", 100);

        regionOne.addConnection(regionTwo, 200);
        regionOne.addConnection(regionThree, 300);

        regionTwo.addConnection(regionOne, 200);

        regionThree.addConnection(regionOne, 300);
        
        result.add(regionOne);   // Region #1: pop. 100 - [Region #2 (200.0), Region #3 (300.0)]
        result.add(regionTwo);   // Region #2: pop. 50 - [Region #1 (200.0)]
        result.add(regionThree); // Region #3: pop. 100 - [Region #1 (300.0)]

        return result;
    }   

    /**
    * Rounds a number to two decimal places.
    * @param num The number to round
    * @return The number rounded to two decimal places
    */
    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}