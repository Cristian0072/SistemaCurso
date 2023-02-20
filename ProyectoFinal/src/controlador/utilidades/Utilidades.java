package controlador.utilidades;

import java.lang.reflect.Field;

/**
 * fecha: 09/02/2023
 *
 * @author: CEAS
 */
public class Utilidades {

    public static Field obtenerAtributo(Class clase, String nombre) {
        Field atributo = null;
        while (clase != null && atributo == null) {
            for (Field aux : clase.getDeclaredFields()) {
                if (nombre.equalsIgnoreCase(aux.getName())) {
                    atributo = aux;
                    break;
                }
            }
            clase = clase.getSuperclass();
        }
        return atributo;
    }

    public static Boolean isNumber(Class clase) {
        return clase.getSuperclass().getSimpleName().equalsIgnoreCase("Number");
    }

    public static Boolean isString(Class clase) {
        return clase.getSimpleName().equalsIgnoreCase("String");
    }

    public static Boolean isCharacter(Class clase) {
        return clase.getSimpleName().equalsIgnoreCase("Character");
    }

    public static Boolean isBoolean(Class clase) {
        return clase.getSimpleName().equalsIgnoreCase("Boolean");
    }

    public static Boolean isPrimitive(Class clase) {
        return clase.isPrimitive();
    }

    public static Boolean isObject(Class clase) {
        return (!isPrimitive(clase) && !isBoolean(clase) && !isCharacter(clase) && !isString(clase) && !isNumber(clase));
    }

}
