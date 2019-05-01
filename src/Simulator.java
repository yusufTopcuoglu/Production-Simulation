

public class Simulator {


    public static void main(String[] args) {

        SimulationController simulationController = new SimulationController(args);

        simulationController.runAll();

        System.out.println(args.length);
    }


}
