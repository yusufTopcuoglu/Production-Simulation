import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Constructor implements Runnable{

    private int id, waitInterval, neededIngotCountForOneProduction;

    private final Object storageLock = new Object();

    /**
     * Empty and Full semaphore for Producer-Consumer problem.
     * This constructor is the consumer in this scenario.
     */
    private Semaphore storageSpaceSemaphoreEmpty;
    private Semaphore storageSpaceSemaphoreFull;

    private boolean alive;

    private final Object aliveLock = new Object();


    Constructor(int id, int waitInterval, int storageCapacity, IngotType ingotType) {
        this.id = id;
        this.waitInterval = waitInterval;
        if (ingotType == IngotType.IRON)
            neededIngotCountForOneProduction = 2;
        else
            neededIngotCountForOneProduction = 3;
        storageSpaceSemaphoreEmpty = new Semaphore(storageCapacity);
        storageSpaceSemaphoreFull = new Semaphore(0);
        alive = true;
    }

    @Override
    public void run() {
        print(Action.CONSTRUCTOR_CREATED);
        while (waitIngots()) {
            print(Action.CONSTRUCTOR_STARTED);
            sleep();
            constructorProduced();
            print(Action.CONSTRUCTOR_FINISHED);
        }
        kill();
        print(Action.CONSTRUCTOR_STOPPED);
    }

    private boolean waitIngots(){
        try {
            return storageSpaceSemaphoreFull.tryAcquire(neededIngotCountForOneProduction, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void constructorProduced(){
        for (int i = 0; i < neededIngotCountForOneProduction; i++){
            storageSpaceSemaphoreEmpty.release();
        }
    }

    void acquireStorageSpaceSemaphoreEmpty(){
        try {
            storageSpaceSemaphoreEmpty.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void releaseStorageSpaceSemaphoreFull(){
        storageSpaceSemaphoreFull.release();
    }

    private void print(Action action){
        HW2Logger.WriteOutput(0, 0, id,  action);
    }

    private void sleep(){
        Utils.sleep(waitInterval);
    }

    boolean isAlive(){
        synchronized (aliveLock){
            return alive;
        }
    }

    private void kill(){
        synchronized (aliveLock){
            alive = false;
        }
    }

    int getId() {
        return id;
    }

    Object getStorageLock() {
        return storageLock;
    }
}
