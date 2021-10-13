
clean:
	rm `find . | grep -E ".*\.class|.*\.jar"`

build-class: 
	javac biblioteca/Main.java

build: build-class
	jar -cfe biblioteca.jar biblioteca/Main `find . | grep -E ".*\.class"`

exec: build
	java -jar biblioteca.jar
	make clean

