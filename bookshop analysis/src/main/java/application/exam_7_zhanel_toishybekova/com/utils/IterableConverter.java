package application.exam_7_zhanel_toishybekova.com.utils;

import java.util.ArrayList;
import java.util.List;

public class IterableConverter
{
    public static <T> List<T> convert(Iterable<T> iterable)
    {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);

        return list;
    }
}
