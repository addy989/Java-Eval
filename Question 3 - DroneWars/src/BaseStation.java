import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class BaseStation implements Runnable {
    private final timeSharing clock;
    private final InstructionQueue queue;

    public BaseStation(timeSharing clock, InstructionQueue queue) {
        this.clock = clock;
        this.queue = queue;
    }
    private double getArrakisAngle(int time){
        return (360.0 / 12) * (time % 12);  //arrakis
    }
    private double getGiediPrimeAngle(int time) {
        return (360.0 / 60) * (time % 60);  //  Giedi Prime
    }
    private boolean isAligned(double arrakisAngle, double giediPrimeAngle) {
        double diff = Math.abs(arrakisAngle - giediPrimeAngle);
        System.out.println("Arrakis Angle : " + arrakisAngle);
        System.out.println("Giedi Prime Angle: " + giediPrimeAngle);
        System.out.println("Angle Difference: " + diff);
        return diff <= 10;
    }
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\AdityaPahuja\\IdeaProjects\\Java Eval\\Question 3 - DroneWars\\trans.mxt"))) {
            String instruction = reader.readLine();
            while (instruction != null){
                clock.incrementTime();
                int time = clock.getTime();
                double arrakisAngle = getArrakisAngle(time);
                double giediPrimeAngle = getGiediPrimeAngle(time);
                if (isAligned(arrakisAngle, giediPrimeAngle)) {
                    queue.addInstruction(instruction);
                    System.out.println("BaseStation:Sent instruction-" + instruction);
                    instruction = reader.readLine();
                } else {
                    System.out.println("BaseStation: Planets not aligned, waiting for alignment...");
                }
            }
        }
        catch (IOException e) {
            System.out.println("something went wrong in Basestation.java during input reading ");
        }
    }
}