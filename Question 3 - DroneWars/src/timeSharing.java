class timeSharing {
    private int time = 0;
    public synchronized int getTime() {
        return time;
    }
    public synchronized void incrementTime() {
        time++;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("SOMETHING WENT WRONG while INCREMENTING TIME in timeSharing.java");
        }
    }
}
