# compile program to directory
javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java


# Run program
java -classpath target edu.school21.printer.app.App ../it.bmp . 0