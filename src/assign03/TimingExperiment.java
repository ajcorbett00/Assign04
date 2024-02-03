package assign03;

import assign03.TimerTemplate;
import java.util.ArrayList;

public class TimingExperiement extends TimerTemplate{
        ArrayList<Integer> arr;


        /**
         * Create a timer
         *
         * @param problemSizes array of N's to use
         * @param timesToLoop  number of times to repeat the tests
         */
        public TimingExperiement(int[] problemSizes, int timesToLoop) {
            super(problemSizes, timesToLoop);
        }

        @Override
        protected void setup(int n) {
            arr = new ArrayList<>();
            for(int i = 0;i<n;i++)
                arr.add(0);
        }

        @Override
        protected void timingIteration(int n) {
            arr.remove(0);
            arr.add(0);

        }

        @Override
        protected void compensationIteration(int n) {
            arr.set(arr.size()-1,0);
        }
        public static void main(String[] args){

            ArrayList<Integer> ns = new ArrayList<>();
            for(double n = 100; n < 1000000; n *= 1.5){
                ns.add((int)n);
            }

            //convert to int[]
            int[] problemSizes = new int[ns.size()];
            for(int i = 0; i < problemSizes.length; i++){
                problemSizes[i] = ns.get(i);
            }

            var timer = new lab02.TimerExperiement09(problemSizes, 10);
            var results = timer.run();
            System.out.println("n, time");
            for(var result: results){
                System.out.println(result.n() + ", " + result.avgNanoSecs());
            }
        }


}
