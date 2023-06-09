package day02.ex02;


import java.util.Scanner;

public class Program {

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("a few arguments");
    } else {
      String[] argument = args[0].split("=");
      FileManagerService fileManagerService = new FileManagerService(
          argument[1]);
      fileManagerService.runFileManager();
    }
  }
}
