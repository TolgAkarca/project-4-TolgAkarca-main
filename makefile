JC = javac
JFLAGS = -g -cp ".:$(PWD)/dependencies/library.jar:$(PWD)/dependencies/commons-cli-1.7.0.jar" -d build

default:
	$(JC) $(JFLAGS) Client.java Server.java

client: Client.java 
	$(JC) $(JFLAGS) Client.java

server: Server.java
	$(JC) $(JFLAGS) Server.java

clean: clean-java clean-temps

clean-java:
	rm -r build;

#The \ works as a line delimiter. It will concatenate the strings on each line so the commands must be separated by semicolons
clean-temps:
	rm *~;
