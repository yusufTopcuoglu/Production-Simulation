import java.util.ArrayList;
import java.util.Scanner;

class SimulationController {

    private ArrayList<Smelter> smelters;
    private ArrayList<Transporter> transporters;
    private ArrayList<Constructor> constructors;
    private ArrayList<Thread> threads;
    private Scanner scanner ;

    SimulationController() {
        smelters = new ArrayList<>();
        transporters = new ArrayList<>();
        constructors = new ArrayList<>();
        threads = new ArrayList<>();
        scanner = new Scanner(System.in);
        readAndInitialize();
    }

    void runAll(){
        runSmelters();
        runTransporters();
        runConstructors();
    }

    void waitAll(){
        for (Thread thread : threads ) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readAndInitialize(){
        readSmelters();
        readConstructors();
        readTransporters();
        scanner.close();
    }

    private void runSmelters(){
        for (Smelter smelter : smelters ) {
            Thread thread = new Thread(smelter);
            thread.start();
            threads.add(thread);
        }
    }

    private void runTransporters(){
        for (Transporter transporter: transporters) {
            Thread thread = new Thread(transporter);
            thread.start();
            threads.add(thread);
        }
    }

    private void runConstructors(){
        for (Constructor constructor: constructors) {
            Thread thread = new Thread(constructor);
            thread.start();
            threads.add(thread);
        }
    }

    private void readSmelters(){
        int numberOfSmelter = getNextArg();
        for (int j = 0; j < numberOfSmelter; j++){
            int waitInterval = getNextArg();
            int storageCapacity = getNextArg();
            getNextArg();
            int totalProducibleAmount = getNextArg();
            Smelter smelter = new Smelter(j+1, waitInterval, storageCapacity, totalProducibleAmount);
            smelters.add(smelter);
        }
    }

    private void readConstructors(){
        int numberOfConstructor = getNextArg();
        for (int j = 0; j < numberOfConstructor; j++){
            int waitInterval = getNextArg();
            int storageCapacity = getNextArg();
            IngotType ingotType = intToIngotType(getNextArg());
            Constructor constructor = new Constructor(j+1, waitInterval, storageCapacity, ingotType);
            constructors.add(constructor);
        }
    }

    private void readTransporters(){
        int numberOfTransporter = getNextArg();
        for (int j = 0; j < numberOfTransporter; j++){
            int waitInterval = getNextArg();
            int targetSmelterId = getNextArg();
            int targetConstructorId = getNextArg();
            Transporter transporter= new Transporter(j+1, waitInterval, smelters.get(targetSmelterId-1), constructors.get(targetConstructorId-1));
            transporters.add(transporter);
        }
    }

    private int getNextArg(){
        return scanner.nextInt();
    }

    private IngotType intToIngotType(int ingotTypeInt){
        if (ingotTypeInt == 0 )
            return IngotType.IRON;
        else
            return IngotType.COPPER;
    }

}
