public class HumanitarianAid {

    private int numberOfBoxes;
    private int numberOfContainers;
    private int numberOfTrucks;

    public static final int NUMBER_OF_BOXES_IN_CONTAINER = 27;
    public static final int NUMBER_OF_CONTAINERS_IN_TRUCK = 12;

    public HumanitarianAid() {
    }
    
    public HumanitarianAid(int numberOfBoxes) {
        if (numberOfBoxes > 0) {
            this.numberOfBoxes = numberOfBoxes;
            calculateContainers();
            calculateTrucks();
        }
        else {
            new HumanitarianAid();
        }
    }

    public void setNumberOfBoxes(int numberOfBoxes) {
        this.numberOfBoxes = numberOfBoxes;
        calculateContainers();
        calculateTrucks();
    }

    public int getNumberOfBoxes() {
        return numberOfBoxes;
    }

    private void calculateContainers() {
        numberOfContainers = (int) Math.ceil((double) numberOfBoxes / NUMBER_OF_BOXES_IN_CONTAINER);
    }

    private void calculateTrucks() {
        numberOfTrucks = (int) Math.ceil((double) numberOfContainers / NUMBER_OF_CONTAINERS_IN_TRUCK);
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public int getNumberOfTrucks() {
        return numberOfTrucks;
    }

    public void printNumberOfBoxes() {
        if (numberOfBoxes < 1) {
            return;
        }
        int countBoxes = 1;
        int countContainers = 1;
        int countTrucks = 1;

        System.out.println("Грузовик: " + countTrucks);
        System.out.println("\tКонтейнер: " + countContainers);
        System.out.println("\t\tЯщик " + countBoxes);
        while (countBoxes < numberOfBoxes) {
            countBoxes++;
            System.out.println("\t\tЯщик " + countBoxes);
            if (countBoxes == numberOfBoxes) {
                break;
            }
            if (isContainerFull(countBoxes)) {
                if (isTruckFull(countContainers)) {
                    countTrucks++;
                    System.out.println("Грузовик: " + countTrucks);
                }
                countContainers++;
                System.out.println("\tКонтейнер: " + countContainers);
            }
        }
    }

    private boolean isContainerFull(int countCrates) {
        return countCrates % NUMBER_OF_BOXES_IN_CONTAINER == 0;

    }
    private boolean isTruckFull(int countContainers) {
        return countContainers % NUMBER_OF_CONTAINERS_IN_TRUCK == 0;
    }
}
