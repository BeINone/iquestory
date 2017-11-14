package kr.co.iquest.beinone.iquestory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-11-14.
 */

public class Utils {

    public static <T> List<T> createList(T... objects) {
        List<T> list = new ArrayList<>();
        for (int index = 0; index < objects.length; index++) {
            list.add(objects[index]);
        }

        return list;
    }

    public static String toPriceString(int price) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(price);
    }
}
