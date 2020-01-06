package ex3;

import javafx.collections.ObservableList;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    String mapToJsonString(Map map){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        boolean flag = true;
        for(Object x : map.keySet()){

            if(flag){flag = false;}
            else{
                stringBuilder.append(",");
            }
            stringBuilder.append("\""+x.toString()+"\":");
            Object cur = map.get(x);
            if(cur instanceof List){
                //stringBuilder.append(cur.toString()); 这样少了引号
                stringBuilder.append("[");
                boolean inner_flag = true;
                for(Object tmp : (List)cur){
                    if(inner_flag){inner_flag = false;}
                    else{stringBuilder.append(",");}
                    stringBuilder.append("\""+tmp.toString()+"\"");
                }
                stringBuilder.append("]");
            }else if(cur instanceof Map){
                stringBuilder.append(mapToJsonString((Map)cur));
            }else{
                stringBuilder.append("\""+cur.toString()+"\"");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    Main(){
        Map<String, Object> map = new HashMap<String, Object>();
        List list =new ArrayList();
        list.add("l1");list.add("l2");
        map.put("username", "yaomy");map.put("password", "123");
        Map m = new HashMap();m.put("da1","d1");m.put("da2","d2");
        map.put("data", m);map.put("list",list);
        System.out.println(mapToJsonString(map));
    }
    public static void main(String[] args){
        new Main();
    }
}
