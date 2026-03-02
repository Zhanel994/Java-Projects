package application.com.utils;

import java.util.ArrayList;
import java.util.List;

public class IterableToList {

    public static <T> List<T> convert(Iterable<T> iterable){
        List<T> tList = new ArrayList<>();
        iterable.forEach(tList::add);
        return tList;
    }
}
