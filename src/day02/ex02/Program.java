package day02.ex02;


import java.util.Scanner;

public class Program {

  public static void main(String[] args) {

    FileManagerService path = new FileManagerService();

    while (true) {
      Scanner in = new Scanner(System.in);
      String[] data = in.nextLine().split(" ");
      String currentPath = path._path.toString();
      if (data[0].equals("exit")) {
        break;
      }
      if (data[0].equals("cd")) {
        currentPath = path.cdCommand(data[1]);
      } else if (data[0].equals("ls")) {
        path.lsCommand(currentPath);
      } else if (data[0].equals("mv")) {
        if (data[2].startsWith("../") || data[2].startsWith("/")) {
          path.mvMove(currentPath, data[1], data[2]);
        } else {
          path.mvRename(currentPath, data[1], data[2]);

        }
      }
    }
  }
}
