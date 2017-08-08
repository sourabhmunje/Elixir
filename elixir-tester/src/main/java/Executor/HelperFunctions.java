package Executor;

import java.util.ArrayList;
import java.util.HashMap;

public class HelperFunctions {

    public String[] arraOfParameters(String params){

        String[] arrayOfParams=params.split(",");
        return arrayOfParams;
    }


    public HashMap<String,String> mapOfData(int i, HashMap<String,ArrayList> hMap){

        HashMap<String,String> temp = new HashMap<>();
        Object[] keySet = hMap.keySet().toArray();
       // String[] keySet = (String[]) hMap.keySet().toArray();
        for(Object key: keySet){
            temp.put(key.toString(),hMap.get(key).get(i).toString());
        }
        return temp;
    }
}
