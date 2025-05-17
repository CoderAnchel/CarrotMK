package org.lince.pulsarbrokerjvm.Utils;

import java.lang.reflect.Field;

public class Json<T> {

    public T FullFillAllFields(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            System.out.println("Prop: "+field.getName());
        }

        return clazz.newInstance();
    }
}
