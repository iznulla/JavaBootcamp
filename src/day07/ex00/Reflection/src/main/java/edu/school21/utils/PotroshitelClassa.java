package edu.school21.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.*;
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

    private static Optional<Object> fieldTypeCast(String clazz, String str) {
        Optional<Object> obj = Optional.empty();
        try {
            if (clazz.toLowerCase().startsWith("str"))
                obj = Optional.of(str);
            else if (clazz.toLowerCase().startsWith("int"))
                obj = Optional.of(Integer.parseInt(str));
            else if (clazz.toLowerCase().startsWith("dou"))
                obj = Optional.of(Double.parseDouble(str));
            else if (clazz.toLowerCase().startsWith("bool"))
                obj = Optional.of(Boolean.parseBoolean(str));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static Object createObject(Class<?> clazz) {
        Object obj = null, setFieldName = null;
        try {
            obj = clazz.getConstructor().newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
                String str = ConsoleUtils.readL();
                field.setAccessible(true);
                if (fieldTypeCast(field.getType().getSimpleName(), str).isPresent()) {
                    setFieldName = fieldTypeCast(field.getType().getSimpleName(), str).get();
                }
                field.set(obj, setFieldName);
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
            throw new ClassNotFoundExcept("Field not found");
        }
    }

    public static void callMethod(Object clazz) {
        System.out.println("Enter name of the method for call:");
        String methodName = ConsoleUtils.readL();
        Optional<Method> methods = Arrays.stream(clazz.getClass().getDeclaredMethods())
                .filter(method -> method.getName()
                .toLowerCase().equals(methodName)).findFirst();
        if (methods.isPresent()) {
            Parameter[] parameters = methods.get().getParameters();
            Object[] args = new Object[parameters.length];
            int i = 0;
            for (Parameter parameter : parameters) {
                System.out.printf("Enter %s value:\n", parameter.getType().getSimpleName());
                args[i] = fieldTypeCast(parameter.getType().getSimpleName(), ConsoleUtils.readL()).orElse("Bad argument");
                i += 1;
            }
            if (!methods.get().getReturnType().getSimpleName().equals("void")) {
                try {
                    System.out.println("Method returned:\n" + methods.get().invoke(clazz, args));
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else
            throw new ClassNotFoundExcept("Bad method");
    }
}

