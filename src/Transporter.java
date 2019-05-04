
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
        print(Action.TRANSPORTER_STOPPED);
    }

    private void transporterSmelterRoutine(){
        synchronized (targetSmelter.getStorageLock()){
            targetSmelter.acquireStorageSemaphoreFull();
            printWithSmelter(Action.TRANSPORTER_TRAVEL);
            sleep();
            printWithSmelter(Action.TRANSPORTER_TAKE_INGOT);
            sleep();
            targetSmelter.releaseStorageSemaphoreEmpty();
        }
    }

    private void transporterConstructerRoutine(){

    }

    private void print(Action action){
        HW2Logger.WriteOutput(0, id, 0,  action);
    }

    private void printWithSmelter(Action action){
        HW2Logger.WriteOutput(targetSmelter.getId(), id, 0,  action);
    }

    private void printWithConstructor(Action action){
        HW2Logger.WriteOutput(0, id, targetConstructor.getId(),  action);
    }

    private void sleep(){
        Utils.sleep(waitInterval);
    }

}
