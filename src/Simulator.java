

public class Simulator {


    public static void main(String[] args) {

        SimulationController simulationController = new SimulationController();

        HW2Logger.InitWriteOutput();

        simulationController.runAll();

        simulationController.waitAll();
    }


}
