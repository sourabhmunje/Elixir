package Executor;

import FileHandler.XlsReader;

public class TestRunner {

    public static void main(String args[]){
        String pathOfExcel;
        XlsReader xlsReader = new XlsReader("./test5.xlsx");
        System.out.println(xlsReader.isSheetExist("Environment-Variables"));
        Thread t1 = new Thread(new ScenarioExecutor(xlsReader,"test1"));
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
