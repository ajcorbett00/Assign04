package lab07;
import java.util.Random;
public class BetterRandomNumberGenerator implements RandomNumberGenerator{
    private long seed;
    private long const1;
    private long const2;


    @Override
    public int nextInt(int max) {
        var rand1 = new Random();
        var rand2 = new Random();
        return 1 ;
    }

    @Override
    public void setSeed(long seed) {this.seed = seed;}

    @Override
    public void setConstants(long const1, long const2) {
        this.const1 = const1;
        this.const2 = const2;

    }
}
