package app;

public class Clock {

    private HomeController controller;

    public Clock(HomeController controller) {
        this.controller = controller;
    }

    public void go() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            controller.updateClock(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
