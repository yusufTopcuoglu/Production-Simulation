import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.DoubleStream;

class Utils {

    private Utils(){}

    static void sleep(int waitInterval){
        Random random = new Random(System.currentTimeMillis());
        DoubleStream stream;
        stream = random.doubles(1, waitInterval-waitInterval*0.01, waitInterval+waitInterval*0.02);
        try {
            OptionalDouble optionalDouble = stream.findFirst();
            if (optionalDouble.isPresent())
                Thread.sleep((long) optionalDouble.getAsDouble());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
