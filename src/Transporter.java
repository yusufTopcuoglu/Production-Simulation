
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
        print(Action.TRANSPORTER_CREATED);
        while (targetSmelter.willProduce()){
            transporterSmelterRoutine();



        }
    }

    private void transporterSmelterRoutine(){
        synchronized (targetSmelter.getStorageLock()){
            targetSmelter.acquireStorageSemaphoreFull();
            print(Action.TRANSPORTER_TRAVEL);
            sleep();
            print(Action.TRANSPORTER_TAKE_INGOT);
            sleep();
            targetSmelter.releaseStorageSemaphoreEmpty();
        }
    }

    private void transporterConstructerRoutine(){

    }

    private void print(Action action){
        HW2Logger.WriteOutput(0, id, 0,  action);
    }

    private void sleep(){
        Utils.sleep(waitInterval);
    }

}
