
public class Transporter implements Runnable {

    private int id, waitInterval;
    private Smelter targetSmelter;
    private Constructor targetConstructor;

    Transporter(int id, int waitInterval, Smelter targetSmelter, Constructor targetConstructor) {
        this.id = id;
        this.waitInterval = waitInterval;
        this.targetSmelter = targetSmelter;
        this.targetConstructor = targetConstructor;
    }

    @Override
    public void run() {

    }

}
