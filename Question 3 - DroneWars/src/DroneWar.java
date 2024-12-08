public class DroneWar {
    public static void main(String[] args) {
        timeSharing sharedtime = new timeSharing();
        InstructionQueue instructionsQ = new InstructionQueue();

        BaseStation baseStation1 = new BaseStation(sharedtime, instructionsQ);
        Thread baseStation = new Thread(baseStation1);

        Responder responder1 = new Responder(sharedtime, instructionsQ);
        Thread responder = new Thread(responder1);
        baseStation.start();
        responder.start();
    }
}

