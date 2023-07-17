package edu.school21.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;


public class PotroshitelClassa {

    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        System.out.println("Classes:");
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClassByName(line, packageName))
                .collect(Collectors.toSet());
    }


    private static Class getClassByName(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public static Class classFind(Set<Class> classes, String className) {
        Class<?> clazz = null;
        for (Class<?> clz : classes) {
            if (clz.getSimpleName().equals(className)) {
                clazz = clz;
            }
        }
        if (clazz == null)
            throw new ClassNotFoundExcept("Class not found");
        return clazz;
    }

    private static Object fieldTypeCast(String clazz, String str) {
        Object obj = null;
        try {
            if (clazz.toLowerCase().startsWith("str"))
                obj = str;
            else if (clazz.toLowerCase().startsWith("int"))
                obj = Integer.parseInt(str);
            else if (clazz.toLowerCase().startsWith("dou"))
                obj = Double.parseDouble(str);
            else if (clazz.toLowerCase().startsWith("bool"))
                obj = Boolean.parseBoolean(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static Object createObject(Class<?> clazz) {
        Object obj = null;
        try {
            obj = clazz.getConstructor().newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
                String str = ConsoleUtils.readL();
                field.setAccessible(true);
                field.set(obj, fieldTypeCast(field.getType().getSimpleName(), str));
            }
            return obj;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void changeFields(Object clazz) {
        try {
            System.out.println("Enter name of the field for changing");
            String fieldFind = ConsoleUtils.readL();
            Field field = clazz.getClass().getDeclaredField(fieldFind);
            System.out.printf("Enter %s value\n", field.getType().getSimpleName());
            String value = ConsoleUtils.readL();
            field.setAccessible(true);
            if (field.getType().getSimpleName().toLowerCase().startsWith("bool"))
                field.setBoolean(clazz, Boolean.parseBoolean(value));
            else if (field.getType().getSimpleName().toLowerCase().startsWith("dou") ||
                    field.getType().getSimpleName().toLowerCase().startsWith("flo"))
                field.setDouble(clazz, Double.parseDouble(value));
            else if (field.getType().getSimpleName().toLowerCase().startsWith("int"))
                field.setInt(clazz, Integer.parseInt(value));
            else if (field.getType().getSimpleName().toLowerCase().startsWith("str"))
                field.set(clazz, value);
            else
                throw new ClassNotFoundExcept("Field " + value + "not found");
        } catch (ClassNotFoundExcept | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}

