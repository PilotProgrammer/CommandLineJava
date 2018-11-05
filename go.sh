#  ./go.sh -asdf=qwer
cp target/CommandLineJava-1.0.0-jar-with-dependencies.jar .
java -cp CommandLineJava-1.0.0-jar-with-dependencies.jar com.pilotprogrammer.app.Start "$1" "$2" "$PWD"
# cd ..

config.properties