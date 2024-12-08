import java.util.*;
class InstructionQueue {
    public Queue<String> instructions = new LinkedList<>();
    public synchronized void addInstruction(String instruction) {
        instructions.add(instruction);
        notifyAll();
    }
    public synchronized String getInstruction() throws InterruptedException {
        while (instructions.isEmpty()) {
            System.out.println("there is no instruction , waiting for it!!!");
            wait();
        }
        return instructions.poll();
    }
}