package Collections;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    private static Map map;
    public static void main (String args[]) {

        map = new WeakHashMap();
        map.put(new String("Maine"), "Augusta");

        Runnable runner = new Runnable() {
            public void run() {
                while (map.containsKey("Maine")) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                    System.out.println("Thread waiting");
                    System.gc();
                }
            }
        };
        Thread t = new Thread(runner);
        t.start();
        System.out.println("Main waiting");
        try {
            t.join();
        } catch (InterruptedException ignored) {
        }
        //add one element to the map...
        Map<Data, String> map2  = new WeakHashMap<Data, String>();
        Data someDataObject = new Data("test");
        map2.put(someDataObject, someDataObject.value);
        System.out.println("map contains someDataObject ? " + map2.containsKey(someDataObject));

        //now make someDataObject eligible for garbage collection...
        someDataObject = null;

        for (int i = 0; i < 1000000; i++){
            if(map2.size()!=0)
            {
                System.out.println("At iteration " + i + " the map still holds the reference on someDataObject");
            }
            else
            {
                System.out.println("someDataObject has finally been garbage collected at iteration " + i + ", so the map is now empty");
                break;
            }

        }
    }

    static class Data{
        String value;
        Data(String value){
            this.value = value;
        }
    }
}
