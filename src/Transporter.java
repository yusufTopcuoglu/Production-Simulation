
public class Transporter {

    private int id, waitInterval, targetSmelterId, targetConstructorId;

    Transporter(int id, int waitInterval, int targetSmelterId, int targetConstructorId) {
        this.id = id;
        this.waitInterval = waitInterval;
        this.targetSmelterId = targetSmelterId;
        this.targetConstructorId = targetConstructorId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTargetSmelterId() {
        return targetSmelterId;
    }

    public void setTargetSmelterId(int targetSmelterId) {
        this.targetSmelterId = targetSmelterId;
    }

    public int getTargetConstructorId() {
        return targetConstructorId;
    }

    public void setTargetConstructorId(int targetConstructorId) {
        this.targetConstructorId = targetConstructorId;
    }

    public int getWaitInterval() {
        return waitInterval;
    }

    public void setWaitInterval(int waitInterval) {
        this.waitInterval = waitInterval;
    }
}
