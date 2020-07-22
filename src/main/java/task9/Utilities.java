package task9;

import java.util.Date;

public class Utilities {
    public static String getUniqueString(String someString){
        Date date= new Date();
        return someString+date.getTime();
    }
}
