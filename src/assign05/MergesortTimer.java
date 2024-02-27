package assign05;



import java.util.ArrayList;
import java.util.Collections;

public class MergesortTimer extends TimerTemplate {
    private ArrayList<Integer> intArr1;
    private ArrayList<Integer> intArr2;
    private int[] threshold;
    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public MergesortTimer(int[] problemSizes, int timesToLoop) {
        super(problemSizes,timesToLoop);
        intArr1 = new ArrayList<>();
    }

    @Override
    protected void setup(int n) {
        //set up all the paramaters for the tests
        intArr1 = new ArrayList<Integer>();
        for(int j=n; j>0;j--){
            intArr1.add(j);
        }
        Collections.shuffle(intArr1);

    }

    @Override
    protected void timingIteration(int n) {
        //Add method to test here
        ArrayListSorter.mergesort(intArr1);

    }
    @Override
    protected void compensationIteration(int n) {//help compensate extra calculations
        // not related to the method your testing
    }

    public static void main(String[] args){
        //
        ArrayList<Integer> ns = new ArrayList<>();
        for(double n = 1000; n < 100000;n += 1000){
            ns.add((int)n);
        }

        //convert to int[]
        int[] problemSizes = new int[ns.size()];
        for(int i = 0; i < problemSizes.length; i++){
            problemSizes[i] = ns.get(i);
        }

        var timer = new MergesortTimer(problemSizes, 20);
        var thres0Results = timer.run();
        System.out.println("n, time");
        for(var result: thres0Results){
            System.out.println(result.n() + ", " + result.avgNanoSecs());
        }
    }
}
