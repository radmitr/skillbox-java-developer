// Task 2.3.2
public class Main {
    
    public static void main(String[] args) {
        int vasyaAge = 104;
        int mashaAge = 32;
        int mishaAge = 40;

        int min = 0;
        int middle = 0;
        int max = 0;

        if (vasyaAge >= mashaAge) {
            if (mishaAge <= mashaAge){
                min = mishaAge;
                middle = mashaAge;
                max = vasyaAge;
            } else if (mishaAge >= vasyaAge) {
                min = mashaAge;
                middle = vasyaAge;
                max = mishaAge;
            } else {
                min = mashaAge;
                middle = mishaAge;
                max = vasyaAge;
            }
        } if (vasyaAge <= mashaAge) {
            if (mishaAge <= vasyaAge) {
                min = mishaAge;
                middle = vasyaAge;
                max = mashaAge;
            } else if (mishaAge >= mashaAge) {
                min = vasyaAge;
                middle = mashaAge;
                max = mishaAge;
            } else {
                min = vasyaAge;
                middle = mishaAge;
                max = mashaAge;
            }
        }

        System.out.println("Minimal age: " + min);
        System.out.println("Middle age: " + middle);
        System.out.println("Maximum age: " + max);
    }
}
