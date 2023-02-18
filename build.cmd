ECHO "Build Started"
CD src

ECHO "Building Docs"
javadoc Application.java -d ../docs 
javadoc WDFrame/*.java -d ../docs
javadoc WDCore/Core.java -d ../docs
ECHO "Build Docs Successful"

ECHO "Building Class"
javac Application.java -d ../bin
ECHO "Build Class Successful"

ECHO "Excutiong Application"
CD ../ && CD bin
java Application

ECHO "Press Enter To Create Jar"
PAUSE

ECHO "Building Jar"
CD ../
jar cvfm Application.jar resources/manifest.txt -C bin . src docs







