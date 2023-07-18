package edu.school21;

import edu.school21.utils.ConsoleUtils;
import edu.school21.utils.PotroshitelClassa;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class Program {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Set<Class> classes = PotroshitelClassa.findAllClassesUsingClassLoader("edu.school21.models");
        classes.stream().map(Class::getSimpleName).forEach(System.out::println);
        System.out.println("-------------------------\nEnter class name");
        String str = ConsoleUtils.readL();
        System.out.println("-------------------------");
        Class<?> findClass = PotroshitelClassa.classFind(classes,str);
        System.out.println("fields:");
        Arrays.stream(findClass.getDeclaredFields()).map(field -> String.format("\t%s %s",
            field.getType().getSimpleName(), field.getName())).forEach(System.out::println);
        System.out.println("methods:");
        Arrays.stream(findClass.getDeclaredMethods()).map(field -> String.format("\t%s %s(%s)",
                field.getReturnType().getSimpleName(), field.getName(), Arrays.stream(field.getParameters())
                        .map(parameter -> parameter.getType().getSimpleName())
                        .collect(Collectors.joining(", ")))).forEach(System.out::println);
        System.out.println("-------------------------\nLet's create an object");
        Object obj = PotroshitelClassa.createObject(findClass);
        System.out.println(obj+"\n"+"-------------------------");
        PotroshitelClassa.changeFields(obj);
        System.out.println(obj);
        System.out.println("-------------------------");
        PotroshitelClassa.callMethod(obj);





    }
}