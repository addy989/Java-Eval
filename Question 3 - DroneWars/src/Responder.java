import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Responder implements Runnable {
    private final timeSharing clock;
    private final InstructionQueue queue;
    private boolean isProcessing = false;

    public Responder(timeSharing clock, InstructionQueue queue) {
        this.clock = clock;
        this.queue = queue;
    }
    private double getArrakisAngle(int time) {
        return (360.0 / 12) * (time % 12);  // 1 Arrakis
    }
    private double getGiediPrimeAngle(int time) {
        return (360.0 / 60) * (time % 60);  //  Giedi Prime
    }
    private boolean isAligned(double arrakisAngle, double giediPrimeAngle) {
        double diff = Math.abs(arrakisAngle - giediPrimeAngle);
        return diff <= 10;
    }


    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("recvrs.mxt"))) {
            while (true){
                int time = clock.getTime();
                double arrakisAngle = getArrakisAngle(time);
                double giediPrimeAngle = getGiediPrimeAngle(time);
                if (!isAligned(arrakisAngle, giediPrimeAngle)) {
                    System.out.println("Responder: Planets not aligned, turning off...");
                    Thread.sleep(500);  // Wait for alignment
                    continue;
                }
                System.out.println("Planets are aligned...");
                // Process the instruction if the receiver is not already processing
                if (!isProcessing) {
                    String instruction = queue.getInstruction();
                    System.out.println("Responder: Received instruction - " + instruction);
                    isProcessing = true;
                    // Simulate instruction processing (1 LTU processing time given in th question itselg)
                    Thread.sleep(1000);
                    clock.incrementTime();
                    String acknowledgment = instruction.replace(">", ".").replace("<", ">");
                    writer.write(acknowledgment + "\n");
                    writer.flush();
                    System.out.println("Responder: Sent acknowledgment - " + acknowledgment);

                    isProcessing = false;  // Ready for the next instruction
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("something went wrong in Responder.java");
        }
    }
}