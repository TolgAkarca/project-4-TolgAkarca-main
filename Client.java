import library.Library;
import library.people.*;
import library.items.*;
import org.apache.commons.cli.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client{
   
    public static void main(String[] args){
	try{
	    HelpFormatter formatter = new HelpFormatter();
	    CommandLineParser parser = new DefaultParser();
	    Options options = new Options();
	    options.addOption(Option.builder("ip").argName("IP ADDRESS").hasArg().desc("IP used to bind server socket").build());
	    options.addOption(Option.builder("port").argName("PORT").hasArg().desc("PORT used to bind server socket").build());
	    CommandLine cmd = parser.parse(options, args);
	    if(cmd.hasOption("help") || !cmd.hasOption("ip") || !cmd.hasOption("port")){
		formatter.printHelp("Library Server", options);
		System.exit(0);
	    }
	    String ipAddress = cmd.getOptionValue("ip");
	    String port = cmd.getOptionValue("port");
	    //TODO: Create a server socket connection
	    //TODO: Connect a reader to the server socket's inputstream
	    //TODO: Connect an object writer to the server socket's outputstream
	    //TODO: Connect a print writer to the server socket's outputstream
	    //TODO: Use a Scanner to get user input
	    options = new Options();
	    options.addOption(Option.builder("staff").numberOfArgs(2).build());
	    options.addOption(Option.builder("member").numberOfArgs(2).build());
	    //TODO: Log in to the database through the server socket; the server should validate the input the user sends in
	    //TODO: If login succeeds then proceed; otherwise, terminate
	    options = new Options();
	    options.addOption(Option.builder("help").desc("Provides a list of queries").build());
	    options.addOption(Option.builder("addBook").argName("file").hasArg().desc("Adds a book to the database (Staff only)").build());
	    options.addOption(Option.builder("addMovie").argName("file").hasArg().desc("Adds a movie to the database (Staff only)").build());
	    options.addOption(Option.builder("addStaff").numberOfArgs(4).desc("Adds a staff member to the database (Staff only)").build());
	    options.addOption(Option.builder("addMember").numberOfArgs(4).desc("Adds a library member to the database (Staff only)").build());
	    options.addOption(Option.builder("showStaff").desc("Show the entire staff (Staff only)").build());
	    options.addOption(Option.builder("showMembers").desc("Show all members (Staff only)").build());
	    options.addOption(Option.builder("allGenreItems").hasArg().argName("genre").desc("Show all items with specified genre").build());
	    options.addOption(Option.builder("allAvailableItems").desc("Show all available items in catalogue").build());
	    options.addOption(Option.builder("allArtistItems").numberOfArgs(2).argName("artist name").desc("Show all items made by the given artist in the library").build());
	    options.addOption(Option.builder("getDetailedItemInfo").hasArg().argName("uid").desc("Show detailed item info for specified item").build());
	    options.addOption(Option.builder("showCatalogue").desc("Show the entire catalogue").build());
	    options.addOption(Option.builder("showArtists").desc("Show all artists in the library").build());
	    options.addOption(Option.builder("saveDatabase").desc("Save the database").build());
	    
	    while(){//Loop on input; break loop if "exit" is entered
		try{
		    cmd = parser.parse(options, input.split(" "));
		    if(cmd.hasOption("help")){
			formatter.printHelp("queries", options);
			continue;
		    }else if(cmd.hasOption("addBook")){
			//TODO
		    }else if(cmd.hasOption("addMovie")){
			//TODO
		    }else if(cmd.hasOption("addStaff")){
			//TODO
		    }else if(cmd.hasOption("addMember")){
			//TODO
		    }else if(cmd.hasOption("showStaff")){
			//TODO
		    }else if(cmd.hasOption("showMembers")){
			//TODO
		    }else if(cmd.hasOption("allGenreItems")){
			//TODO
		    }else if(cmd.hasOption("allArtistItems")){
			//TODO
		    }else if(cmd.hasOption("allAvailableItems")){
			//TODO
		    }else if(cmd.hasOption("getDetailedItemInfo")){
			//TODO
		    }else if(cmd.hasOption("showCatalogue")){
			//TODO
		    }else if(cmd.hasOption("showArtists")){
		        //TODO
		    }else if(cmd.hasOption("saveDatabase")){
		        //TODO
		    }else{
			System.out.println("Unrecognized command!");
			continue;
		    }		
		    //TODO: Output response
		}catch(ParseException e){
		    System.out.println("Unrecognized command!");
		}
	    }	    
	}catch(Exception e){
	    e.printStackTrace();
	}    
    }
}
