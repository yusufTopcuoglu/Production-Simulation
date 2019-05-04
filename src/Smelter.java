import java.util.concurrent.Semaphore;

class Smelter implements Runnable{

    private int id, waitInterval;
    private IngotType ingotType;

    /**
     * Empty and Full semaphore for Producer-Consumer problem.
     * This smelter is the producer in this scenario.
     */
    private Semaphore storageSpaceSemaphoreEmpty;
    private Semaphore storageSpaceSemaphoreFull;

    /**
     * Only this smelter will check this semaphore.
     * Initial value is totalProducibleAmount.
     * If can acquire this semaphore, smelter stays in while loop and produce one more ore
     */
    private Semaphore canProduceSemaphore;

    /**
     * Transporters of this smelter will check this semaphore.
     * Initial value is totalProducibleAmount.
     * If a transporter can acquire this semaphore, it will understand that
     * the smelter will produce one more ore, so it will stay in it's while loop and
     * wait the smelter to produce the ore.
     */
    private Semaphore willProduceSemaphore;

    private final Object storageLock = new Object();


    Smelter(int id, int waitInterval, int storageCapacity, int totalProducibleAmount, IngotType ingotType){
        this.id = id;
        this.ingotType = ingotType;
        this.waitInterval = waitInterval;
        storageSpaceSemaphoreEmpty = new Semaphore(storageCapacity);
        canProduceSemaphore = new Semaphore(totalProducibleAmount);
        willProduceSemaphore = new Semaphore(totalProducibleAmount);
        storageSpaceSemaphoreFull = new Semaphore(0);
    }

    @Override
    public void run() {
        print(Action.SMELTER_CREATED);
        while (canProduce()){
            waitCanProduce();
            print(Action.SMELTER_STARTED);
            sleep();
            ingotProduced();
            print(Action.SMELTER_FINISHED);
            sleep();
        }
        print(Action.SMELTER_STOPPED);
    }

    private void waitCanProduce(){
        try {
            storageSpaceSemaphoreEmpty.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void acquireStorageSemaphoreFull(){
        try {
            storageSpaceSemaphoreFull.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void ingotProduced(){
        storageSpaceSemaphoreFull.release();
    }

    void releaseStorageSemaphoreEmpty(){
        storageSpaceSemaphoreEmpty.release();
    }

    private boolean canProduce(){
        return canProduceSemaphore.tryAcquire();
    }

    boolean willProduce(){
        return willProduceSemaphore.tryAcquire();
    }

    private void print(Action action){
        HW2Logger.WriteOutput(id, 0, 0,  action);
    }

    private void sleep(){
        Utils.sleep(waitInterval);
    }

    public Object getStorageLock() {
        return storageLock;
    }
}
