# download jcomander with command lie
curl -L -o lib/jcommander.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar

# download jcdp with command line
curl -L -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar 


# compile program to directory
javac -d target -cp lib/jcommander.jar:lib/JCDP-4.0.2.jar src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java


# copy resources to target
cp -r src/resources target


# crete JAR file
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .


$ run jar file
java -jar images-to-chars.printer.jar 0 .


# Run program
java -cp target edu.school21.printer.app.App