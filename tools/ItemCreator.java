import library.Library;
import library.items.*;
import library.exceptions.*;
import org.apache.commons.cli.*;
import java.io.*;
import java.util.ArrayList;
public class ItemCreator{
    public static void main(String[] args){
	Options options = new Options();
	CommandLineParser parser = new DefaultParser();
	CommandLine cmd = null;
	HelpFormatter formatter = new HelpFormatter();
	options.addOption(Option.builder("type").required(true).required().argName("movie/book").hasArg().desc("sets the type of object to be created").build());
	options.addOption(Option.builder("filename")
			  .required(true)
			  .argName("file path")
			  .hasArg()
			  .desc("sets the location of the item")
			  .build());
	options.addOption(Option.builder("title")
			  .required(true)
			  .argName("title")
			  .hasArg()
			  .desc("sets the title of the item")
			  .build());
        options.addOption(Option.builder("copies")
			  .argName("num copies")
			  .hasArg()
			  .desc("sets the number of copies")
			  .build());
	options.addOption(Option.builder("description")
			  .argName("description")
			  .hasArg()
			  .desc("sets the description of the item")
			  .build());
	options.addOption(Option.builder("subjects")
			  .argName("subjects")
			  .hasArg()
			  .desc("sets the subjects of the item (separated by commas)")
			  .build());
	options.addOption(Option.builder("genre")
			  .argName("genre")
			  .hasArg()
			  .desc("sets the genre of the item")
			  .build());
	options.addOption(Option.builder("length")
			  .argName("length")
			  .hasArg()
			  .desc("sets the page count/duration of the item")
			  .build());
	options.addOption(Option.builder("artist")
			  .argName("artist id")
			  .hasArg()
			  .desc("sets the artist of the item")
			  .build());
	try{
	    cmd = parser.parse(options, args);
	
	    if(cmd.hasOption("help")){
		formatter.printHelp("ItemCreator", options, true);
		System.exit(0);
	    }
	    switch(cmd.getOptionValue("type")){
	    case "book": (new ObjectOutputStream(new FileOutputStream(new File(cmd.getOptionValue("filename"))))).writeObject(bookCreator(cmd));
		break;
	    case "movie": (new ObjectOutputStream(new FileOutputStream(new File(cmd.getOptionValue("filename"))))).writeObject(movieCreator(cmd));
		break;
	    default: throw new Exception();
	    }      	
	}catch(Exception e){
	    formatter.printHelp("ItemCreator", options, true);
	}
    }

    public static Item.Genre parseGenre(String genreString){
	    return switch(genreString){
		    case "SCI_FI" -> Item.Genre.SCI_FI;
		    case "FANTASY" -> Item.Genre.FANTASY;
		    case "DRAMA" -> Item.Genre.DRAMA;
		    case "HORROR" -> Item.Genre.HORROR;
		    case "CRIME" -> Item.Genre.CRIME;
		    case "COMEDY" -> Item.Genre.COMEDY;
		    case "NONFICTION" -> Item.Genre.NONFICTION;
		    default -> null;
	    };
    }

    public static Book bookCreator(CommandLine cmd) throws InvalidItemException{
	String title = cmd.getOptionValue("title");
	System.out.println(title);
        Book.BookBuilder bb = new Book.BookBuilder(title);
	if(cmd.hasOption("copies")){
	    bb = bb.setCopies(Integer.parseInt(cmd.getOptionValue("copies")));
	}
	if(cmd.hasOption("description")){
	    bb = bb.setDescription(cmd.getOptionValue("description"));
	}
	if(cmd.hasOption("subjects")){
	    bb = bb.setSubjects(cmd.getOptionValue("subjects").split(","));
	}
	if(cmd.hasOption("genre")){
	    bb = bb.setGenre(parseGenre(cmd.getOptionValue("genre")));
	}
	if(cmd.hasOption("artist")){
	    bb = bb.setAuthor(cmd.getOptionValue("artist"));
	}
	if(cmd.hasOption("length")){
	    bb = bb.setPages(Integer.parseInt(cmd.getOptionValue("length")));
	}
	return bb.build();
    }

    

    public static Movie movieCreator(CommandLine cmd) throws InvalidItemException{
	String title = cmd.getOptionValue("title");
        Movie.MovieBuilder mb = new Movie.MovieBuilder(title);
	if(cmd.hasOption("copies")){
	    mb = mb.setCopies(Integer.parseInt(cmd.getOptionValue("copies")));
	}
	if(cmd.hasOption("description")){
	    mb = mb.setDescription(cmd.getOptionValue("description"));
	}
	if(cmd.hasOption("subjects")){
	    mb = mb.setSubjects(cmd.getOptionValue("subjects").split(","));
	}

	if(cmd.hasOption("genre")){
	    mb = mb.setGenre(parseGenre(cmd.getOptionValue("genre")));
	}
	if(cmd.hasOption("artist")){
	    mb = mb.setDirector(cmd.getOptionValue("artist"));
	}
	if(cmd.hasOption("length")){
	    mb = mb.setDuration(Integer.parseInt(cmd.getOptionValue("length")));
	}
	return mb.build();
    }

}
