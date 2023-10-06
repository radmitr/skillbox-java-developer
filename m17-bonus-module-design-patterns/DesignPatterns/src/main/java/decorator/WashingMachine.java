package decorator;

// Client
public class WashingMachine {

    public static void main(String[] args) {
        // 1 - BasicWashingProgram
        System.out.println("--------------------------------- BasicWashingProgram ---------------------------------");
        BasicWashingProgram basicProgram = new BasicWashingProgram();
        basicProgram.executeProgram();

        // 2 - BasicWashingProgram + DoubleSpinningProgram + BoilingWashingProgram
        System.out.println("\n--------- BasicWashingProgram + DoubleSpinningProgram + BoilingWashingProgram ---------");
        BasicWashingProgram program = new BasicWashingProgram();
        DoubleSpinningProgram spinningProgram = new DoubleSpinningProgram(program);
        BoilingWashingProgram boilingSpinningProgram = new BoilingWashingProgram(spinningProgram);
        boilingSpinningProgram.executeProgram();
    }
}
