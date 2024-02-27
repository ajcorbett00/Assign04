package assign04;

import java.util.ArrayList;

public class TimerExperiementKthLargest extends TimerTemplate {
    ArrayList<Integer> arr;
    ArrayList<Integer[]> intList;
    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public TimerExperiementKthLargest(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
        arr = new ArrayList<>();
    }

    @Override
    protected void setup(int n) {
        //set up all the paramaters for the tests
        intList = new ArrayList<>();
        for(int j=0; j<n;j++){
            intList.add(new Integer[]{j*3, j, j*2});
        }

    }

    @Override
    protected void timingIteration(int n) {
        //Add method to test here
        LargestNumberSolver.findKthLargest(intList,intList.size()/2);

    }
    @Override
    protected void compensationIteration(int n) {//help compensate extra calculations
        // not related to the method your testing
        }

    public static void main(String[] args){

        ArrayList<Integer> ns = new ArrayList<>();
        for(double n = 1000; n < 100000;n += 1000){
            ns.add((int)n);
        }

        //convert to int[]
        int[] problemSizes = new int[ns.size()];
        for(int i = 0; i < problemSizes.length; i++){
            problemSizes[i] = ns.get(i);
        }

        var timer = new TimerExperiementKthLargest(problemSizes, 20);
        var results = timer.run();
        System.out.println("n, time");
        for(var result: results){
            System.out.println(result.n() + ", " + result.avgNanoSecs());
        }
    }

}
