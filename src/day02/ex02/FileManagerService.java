package day02.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagerService {

  public Path _path;

  public FileManagerService() {
    _path = Paths.get("/Users/merylpor/Desktop/JavaButcamp/src/day02");

  }

  public String cdCommand(String data) {
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

  public void lsCommand(String value) {
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


  public void mvRename(String path, String from, String to) {
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

  public void mvMove(String path, String filename, String to) {
    try {
      Path source = Paths.get(path + "/" + filename);
      Path target = Paths.get(path);
      File dir = new File(_path.toString());
      dir  =_path.resolve(path).normalize().toFile();
      target = target.resolve(dir.getPath() + "/" + to + "/" + filename).normalize();
      System.out.println(target);
      Files.move(source, target);

    } catch (IOException e) {
      System.out.printf("Failed to move the file %s\n", filename);
    }
  }

  public boolean checkFolder(String file) {
    File folder = new File(file);
    return folder.exists();
  }
}
