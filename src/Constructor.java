
public class Constructor implements Runnable{

    private int id, waitInterval, storageCapacity;
    private IngotType ingotType;
    private final Object storageLock = new Object();


    Constructor(int id, int waitInterval, int storageCapacity, IngotType ingotType) {
        this.id = id;
        this.waitInterval = waitInterval;
        this.storageCapacity = storageCapacity;
        this.ingotType = ingotType;
    }

    @Override
    public void run() {

    }

    public void putOreToStorage(){
        synchronized (storageLock){
            storageCapacity++;
        }
    }

    public void takeOreFromStorage(){
        synchronized (storageLock){
            storageCapacity--;
        }
    }

}
