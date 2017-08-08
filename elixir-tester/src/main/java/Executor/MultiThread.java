package Executor;

public class MultiThread extends Thread {

    private Thread t;
    private String threadName;
    ScenarioExecutor  SC;

    MultiThread( String name,  ScenarioExecutor sc) {
        threadName = name;
        SC = sc;
    }


    public void run() {
        SC.run();
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }


}
