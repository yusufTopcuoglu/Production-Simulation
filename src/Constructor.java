
public class Constructor {

    private int id, waitInterval, storageCapacity;
    private IngotType ingotType;


    Constructor(int id, int waitInterval, int storageCapacity, IngotType ingotType) {
        this.id = id;
        this.waitInterval = waitInterval;
        this.storageCapacity = storageCapacity;
        this.ingotType = ingotType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaitInterval() {
        return waitInterval;
    }

    public void setWaitInterval(int waitInterval) {
        this.waitInterval = waitInterval;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public IngotType getIngotType() {
        return ingotType;
    }

    public void setIngotType(IngotType ingotType) {
        this.ingotType = ingotType;
    }
}
