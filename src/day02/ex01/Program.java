package day02.ex01;

public class Program {

  public static void main(String[] args) {
    SimilarFileAnalyze sim = new SimilarFileAnalyze();

    if (args.length < 2)
      System.out.println("few arguments");
    else
      sim.calcSimilar(args[0], args[1]);


  }
}
