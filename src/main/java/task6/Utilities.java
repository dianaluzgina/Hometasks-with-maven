package task6;

import java.util.Date;

public class Utilities {
    public static String getUniqueSubject(String subject){
        Date date= new Date();
        return subject+date.getTime();
    }
}
