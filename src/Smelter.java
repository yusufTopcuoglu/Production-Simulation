
class Smelter {

    private int id, waitInterval, storageCapacity, totalProducibleAmount;
    private IngotType ingotType;

    Smelter(int id, int waitInterval, int storageCapacity, int totalProducibleAmount, IngotType ingotType){
        this.id = id;
        this.ingotType = ingotType;
        this.storageCapacity = storageCapacity;
        this.waitInterval = waitInterval;
        this.totalProducibleAmount = totalProducibleAmount;
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

    public int getTotalProducibleAmount() {
        return totalProducibleAmount;
    }

    public void setTotalProducibleAmount(int totalProducibleAmount) {
        this.totalProducibleAmount = totalProducibleAmount;
    }

    public IngotType getIngotType() {
        return ingotType;
    }

    public void setIngotType(IngotType ingotType) {
        this.ingotType = ingotType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
