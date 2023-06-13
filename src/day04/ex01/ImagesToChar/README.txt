# compile program to directory
javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java

# copy resources to target
cp -r src/resources target

# crete JAR file
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .

$ run jar file
java -jar target/images-to-chars-printer.jar . 0

# Run program
java -cp target edu.school21.printer.app.App