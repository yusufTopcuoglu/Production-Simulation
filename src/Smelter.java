
class Smelter implements Runnable{

    private int id, waitInterval, storageCapacity, totalProducibleAmount;
    private IngotType ingotType;
    private final Object storageLock = new Object();


    Smelter(int id, int waitInterval, int storageCapacity, int totalProducibleAmount, IngotType ingotType){
        this.id = id;
        this.ingotType = ingotType;
        this.storageCapacity = storageCapacity;
        this.waitInterval = waitInterval;
        this.totalProducibleAmount = totalProducibleAmount;
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
