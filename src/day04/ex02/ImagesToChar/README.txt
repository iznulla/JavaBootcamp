# download jcomander with command lie
curl -L -o lib/jcommander.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar

# download jcdp with command line
curl -L -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar 

#change directory to target
cd target

# unpacking jcomander to target path
jar -xf ../lib/jcommander.jar

# unpacking jcdp to target path
jar -xf ../lib/JCDP-4.0.2.jar

# copy resources to target
cp -r ../src/resources .

#change directory to ImagesToChar
cd ..

# compile program to directory
javac -d target/ -cp target/ src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java


# crete JAR file
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .


$ run jar file
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
