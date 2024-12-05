import library.Library;
import library.people.*;
import library.items.*;
import library.exceptions.*;
import library.structures.SkipList;
import org.apache.commons.cli.*;
import java.net.*;
import java.io.*;
public class Server{
    static String serverName = "Library";
    static String databaseFileName = null;
    static Library lib;
    public static void main(String[] args){
	try{
	    CommandLineParser parser = new DefaultParser();
	    Options options = new Options();
	    HelpFormatter formatter = new HelpFormatter();
	    options.addOption(Option.builder("ip").argName("IP ADDRESS").hasArg().desc("IP used to bind server socket").build());
	    options.addOption(Option.builder("library").argName("library bin path").hasArg().desc("Path to serialized binary library file.").build());
	    options.addOption(Option.builder("help").argName("help").desc("explains how to use this Server").required(false).build());
	    options.addOption(Option.builder("name").argName("name").desc("gives a name to the Server").required(false).build());
	    CommandLine cmd = parser.parse(options, args);

	    if(cmd.hasOption("help") || !cmd.hasOption("ip") || !cmd.hasOption("library")){
		formatter.printHelp("Library Server", options);
		System.exit(0);
	    }

	    if(cmd.hasOption("name")){
		serverName = cmd.getOptionValue("name");
	    }
	    
	    String ipAddress = cmd.getOptionValue("ip");
	    databaseFileName = cmd.getOptionValue("library");
	    lib = Library.loadDatabase(databaseFileName);
	    //TODO: Create a socket and listen in for new connections --- remember, you need to be able to accomodate multiple client connections!
	    
	}catch(Exception e){
	    e.printStackTrace();
	}
    
    }
}

class RequestServicer extends Thread{
    private Socket connection;
    
    public void run(){
	try{
	    //Set up an stream to take in objects
	    //Set up an reader to take in commands
	    //Set up a writer to provide output
	    //Provide a welcome message to the client (include the server name and instructions for logging in)

	    //Handle the login process. (Bonus points will be given to those who allow for a bounded number of tries...see README for more...)

	    //Process queries given by the client. Remember that the client may send more than one query once connected, so don't exit out prematurely!
	    
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    RequestServicer(Socket connection){
	this.connection = connection;
    }
}
