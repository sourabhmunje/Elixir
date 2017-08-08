package Executor;

import BrowserInteractions.SeleniumFunctions;
import FileHandler.FreemarkerUtil;
import FileHandler.XlsReader;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioExecutor implements Runnable {
//public class ScenarioExecutor {

    ArrayList tags = new ArrayList();
    HashMap<String,String> localDataMap= new HashMap<>();
    FreemarkerUtil freemarkerUtil = new FreemarkerUtil();

//    SeleniumFunctions seleniumFunctions = new SeleniumFunctions();
    HelperFunctions helperFunctions = new HelperFunctions();

   // static int x=0;
//    static XlsReader xlsReader;
//    static String nameOfTab;
    XlsReader xlsReader;
    String nameOfTab;
    HashMap<String,ArrayList> dataSet = new HashMap<>();
    public ScenarioExecutor(XlsReader xlsReader, String nameOfTab){
        this.xlsReader = xlsReader;
        this.nameOfTab=nameOfTab;
        tags.add("~run");
    }

    public synchronized void run(){
  // public void run(){
        SeleniumFunctions seleniumFunctions = new SeleniumFunctions();
        for (int i = 1; i <= xlsReader.getRowCount(nameOfTab); i++) {
            if (tags.contains(xlsReader.getCellData(nameOfTab, 0, i))) {
                i++;
                System.out.println(i);
                if (xlsReader.getCellData(nameOfTab, 0, i).contentEquals("~data")) {
                    dataSet=xlsReader.dataSetProvider(nameOfTab,i);
                }
                while (!xlsReader.getCellData(nameOfTab, 0, i).contentEquals("~test")){
                    i++;
                }
                //outer:
                if (xlsReader.getCellData(nameOfTab, 0, i).contentEquals("~test")) {
                    //INNER:
                    int datasetSize = dataSet.get(dataSet.keySet().toArray()[0]).size();
                    for (int lengthOfDataSet = 0; lengthOfDataSet < datasetSize; lengthOfDataSet++) {
                        localDataMap=helperFunctions.mapOfData(lengthOfDataSet,dataSet);
                    //  int j = i;
                    for (int j = i; j < xlsReader.getRowCount(nameOfTab); j++) {
                        if (xlsReader.getCellData(nameOfTab, 0, j + 2).contentEquals("~end")) {
                            j = i - 1;
                            break;
                        }
                        Method[] methods = SeleniumFunctions.class.getMethods();
                        for (Method method : methods) {
                            int k = j + 2;
                            System.out.println("-----------" + xlsReader.getCellData(nameOfTab, "ACTION", k, i));
                            if (method.getName().equals(xlsReader.getCellData(nameOfTab, "ACTION", k, i))) {
                                try {
                                    String paramString = xlsReader.getCellData(nameOfTab, "PARAMS", k, i);
                                    if (paramString.contentEquals("")) {
                                        method.invoke(seleniumFunctions);
                                    } else {

                                        method.invoke(seleniumFunctions,helperFunctions.arraOfParameters(freemarkerUtil.replace(xlsReader.getCellData(nameOfTab, "PARAMS", k, i), localDataMap)));
                                        //method.invoke(seleniumFunctions, helperFunctions.arraOfParameters(xlsReader.getCellData(nameOfTab, "PARAMS", k, i)));
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                } catch (TemplateException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                }
            }
        }


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
