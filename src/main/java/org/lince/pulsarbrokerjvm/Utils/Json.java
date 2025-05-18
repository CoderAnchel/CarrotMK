package org.lince.pulsarbrokerjvm.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Json<T> {

    public void populateFieldsFromJson(T instance, JsonNode jsonNode) {
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Permite acceder a campos privados
            String fieldName = field.getName();

            if (jsonNode.has(fieldName)) {
                try {
                    // Obtiene el valor del campo desde el JsonNode
                    JsonNode valueNode = jsonNode.get(fieldName);

                    // Convierte el valor al tipo del campo
                    Object value = convertValue(valueNode, field.getType());

                    // Asigna el valor al campo del objeto
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    System.out.println("No se pudo asignar el valor al campo: " + fieldName);
                }
            }
        }
    }

    private Object convertValue(JsonNode valueNode, Class<?> targetType) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(valueNode, targetType);
    }
}
