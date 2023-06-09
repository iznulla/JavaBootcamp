package day02.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManagerService {

  private Path _path;

  public FileManagerService(String mainPath) {
    _path = Paths.get(mainPath);

  }

  private String cdCommand(String data) {
    String resultPath = _path.toString();
    File dir = new File(_path.toString());
    dir = _path.resolve(data).normalize().toFile();
    if (dir.exists()) {
      _path = _path.resolve(dir.getPath());
      resultPath = dir.getPath();
      System.out.println(resultPath);
    } else {
      System.out.println("File not found");
    }

    return resultPath;
  }

  private void lsCommand(String value) {
    File folder = new File(value);
    try {
      for (File file : folder.listFiles()) {
        double size = file.length();
        size = ((int) (size * 100)) / 100.0;
        System.out.println(file.getName() + " " + size + " Kb");
      }
    } catch (RuntimeException e) {
      System.out.println("File not found");
    }

  }


  private void mvRename(String path, String from, String to) {
    File file = new File(path + "/" + from);
    File newFile = new File(file.getParent(), to);
    if (file.exists()) {
      if (file.renameTo(newFile)) {
        System.out.println("File renamed successfully.");
      } else {
        System.out.println("Failed to rename the file.");
      }
    } else {
      System.out.println("File does not exist.");
    }
  }

  private void mvMove(String path, String filename, String to) {
    try {
      Path source = Paths.get(path + "/" + filename);
      Path target = Paths.get(path);
      File dir = new File(_path.toString());

      dir  =_path.resolve(path).normalize().toFile();
      target = target.resolve(dir.getPath() + "/" + to + "/" + filename).normalize();

      Files.move(source, target);
    } catch (IOException e) {
      System.out.printf("Failed to move the file %s\n", filename);
    }
  }

  public void runFileManager() {
    while (true) {
      Scanner in = new Scanner(System.in);
      String[] data = in.nextLine().split(" ");
      String currentPath = _path.toString();
      if (data[0].equals("exit")) {
        break;
      }
      if (data[0].equals("cd")) {
        currentPath = this.cdCommand(data[1]);
      } else if (data[0].equals("ls")) {
        this.lsCommand(currentPath);
      } else if (data[0].equals("mv")) {
        if (data[2].startsWith("../") || data[2].startsWith("/")) {
          this.mvMove(currentPath, data[1], data[2]);
        } else {
          this.mvRename(currentPath, data[1], data[2]);

        }
      }
    }
  }

}
