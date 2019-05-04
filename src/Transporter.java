
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
        while (targetSmelter.willProduce() && targetConstructor.isAlive()){
            transporterSmelterRoutine();
            transporterConstructorRoutine();
        }
        print(Action.TRANSPORTER_STOPPED);
    }

    private void transporterSmelterRoutine(){
        synchronized (targetSmelter.getStorageLock()){
            targetSmelter.waitUntilOrePresentInStorage();
            printWithSmelter(Action.TRANSPORTER_TRAVEL);
            sleep();
            printWithSmelter(Action.TRANSPORTER_TAKE_INGOT);
            sleep();
            targetSmelter.takeOreFromStorage();
        }
    }

    private void transporterConstructorRoutine(){
        synchronized (targetConstructor.getStorageLock()){
            if(targetConstructor.isAlive()){
                targetConstructor.waitUntilEmptySpaceAvailable();
                printWithConstructor(Action.TRANSPORTER_TRAVEL);
                sleep();
                printWithConstructor(Action.TRANSPORTER_DROP_INGOT);
                sleep();
                targetConstructor.dropOreToStorage();
            }
        }
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
