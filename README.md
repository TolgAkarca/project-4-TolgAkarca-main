[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/wPew6Tal)
# Project 4 - Server/Client Library Database
## Contents
1. [Introduction](#introduction)
2. [Source Code](#source-code)
3. [Given Code](#given-code)
4. [Reading The Javadoc](#reading-the-javadoc)
5. [Your Task](#your-task)
6. [Submit Your Work](#submit-your-work)
7. [Grading](#grading)
8. [Rubric](#rubric)
9. [Tips](#tips)

## Introduction
In this project you will build a networking layer on top of the library database we've been constructing this semester. The goal of this project is to get you stretching your multithreading talents a bit more as well as have you build a simple networking application.

This is an individual project and is expected to be completed without any code-sharing between yourself and your classmates.

This project contributes 6.25% toward the final grade.

## Source Code
For this project, you will be working on the baseline code that needs to be
downloaded from this Github repo. Furthermore, all projects will include a corresponding `javadoc` which you can read to aid you in your implementations. **Please make sure you read this whole section, as well as the [grading guidelines](#grading), before cloning the repo on Github.**

## Given Code
If you take a look at the current repo you’ll see that you’ve been given quite a bit of skeleton code to get you started on completing the project. This section is dedicated to giving a brief overview of the different classes that you’ll be using.

### Dependency/Tool Jars
Since we won't be making any changes to the library this time around we can abstract away the need to compile our networking program with the raw source files. I've handled this step for you by packaging my solution to Project 3 in a jar file which can be found in `dependencies/library.jar`. I didn't discuss `.jar` files in class much since I choose to focus on language/runtime features moreso than build tools, but they are an essential part of the Java ecosystem. You can think of them as `.zip` files for Java classes. Alongside the `library.jar` you've also been given `commons-cli-1.7.0.jar`: a jar file for the [Apache Commons CLI library](https://commons.apache.org/proper/commons-cli/) which provides an API for simple command line argument parsing---more on that [here](#apache-client-cli). The [makefile](#the-makefile) and [bash scripts](#bash-scripts) utilize the `-cp` flag to tell `java`/`javac` where to look to resolve unfamiliar class names.

The tool jar, `ItemCreator.jar`, has been given to allow for the quick creation of valid Book and Movie objects. You can view the help message for the tool by running `./create-item.sh -help` when in the main repo directory. If I wanted to create a new book that represented John Barth's epic novel **The Sot-Weed Factor** I would type:
```bash
./create-item.sh -artist "John Barth" -copies 2 \
		 -description "Set in the late 1600s, The Sot-Weed Factor recounts the wildly chaotic odyssey of hapless, ungainly Ebenezer Cooke, sent to the New World to look after his father's tobacco business and to record the struggles of the Maryland colony in an epic poem." \
		 -filename "./sot-weed.bk" -genre "COMEDY" \
		 -length 756 -subjects "maryland,pirates,tobacco" \
		 -title "The Sot-Weed Factor" -type "book"
```
I have also provided the source code for ItemCreator, `ItemCreator.java` so you can see how simple it is to use the Apache Commons CLI API.

### Bash Scripts
Since we are using jars to wrap up our old APIs and a new dependency we need to provide classpaths to java and javac as explained [above](#dependency-tool-jars). In order to avoid any confusion, I have provided scripts which you can use to run your Server/Client code which automatically handles that for you. *I strongly suggest you do not alter the scripts.* Running the scripts should be rather easy. For example, if I want to run my server implementation all I need to do is type:
```bash
./run-server.sh -ip 0.0.0.0 -library input/tiny_library.lb
```
and for the Client:
```bash
./run-client.sh -ip 0.0.0.0 -port <PORT GIVEN BY SERVER>
```
If you'd like to know more about bash scripting, feel free to come by office hours. If you'd like to learn some more on your own, then I highly recommend reading through the manual page which can be found by typing `man bash` in the terminal.

(I explain create-item.sh [here](#dependency-tool-jars).)

### Server Class
The Server class acts as the hub through which all users must connect. A decent amount of setup code is provided for you already; however, listening for, starting up, and managing connections is left as an exercise for you. 

#### RequestServicer Class
The RequestServicer class should act like a worker thread for handling the logic of processing requests from clients. This is where you'll need to decode queries, look up/update information in the database, and return responses to clients.

### Client Class
The Client class is how a user interacts with a database. Once connected to the Server socket, a user will need to login to begin providing queries/altering the database. A client should be allowed to exit the query loop by typing "exit" in the command line.

### The Makefile
You've been provided a makefile that always compiles `Client.java` and `Server.java`. When you are in the base directory, you can compile your files with a call to `make`; I have provided the `-g` flag so you can use jdb to debug any object and `-d` so all of the class files end up in a directory called "build". You can call `make clean -k` to remove all `class` files and temporary emacs files from your directories. Since you may want to work on the Server/Client independently you may use `make server` or `make client` which will compile `Server.java` or `Client.java`, respectively. 

You may alter the makefile however you like, but there is no need to do so.

## Reading The Javadoc
Using `javadoc`, I have compiled a set of documentation that you can read to help provide you with direction on how certain methods should behave. Once you clone this repo you can easily read through the documentation by clicking on `index.html` and opening it in your browser of choice. Once you've done so, you can freely click around on the hyperlinks to view in finer and finer detail all of the packages, classes, and methods that should be implemented for the finished project.

## Your Task
- Build a fully functioning Client/Server application
  - The Server establishes a server socket and loads a given database
    - The Server should be able to handle multiple Client connections and process queries sent in by the Client
  - The Client connects to the server, logs in to the database, and is able to perform queries on it
    - Both staff and members can login to the database, but only staff may use queries marked *STAFF ONLY*
      - Staff members should be able to login using `-staff <userID> <passkey>`
      - Members should be able to login using `-member <userID> <passkey>`

### Queries
Below is a list of queries you will need to implement:
- addBook: Add a book to the library using an absolute path to a Book binary file. *STAFF ONLY*
  - ex: `-addBook $PWD/input/books/fate.bk`
- addMovie: Add a movie to the library using an absolute path to a Movie binary file. *STAFF ONLY*
  - ex: `-addMovie $PWD/input/movies/walker.mv`
- addStaff: Add a staff member to the library with the format <first name> <last name> <userID> <passkey>. *STAFF ONLY*
  - ex: `-addStaff Kurt Godel kgodel INCOMPLETENESS`
- addMember: Add a member to the library with the format <first name> <last name> <userID> <passkey>. *STAFF ONLY*
  - ex: `-addMember Brian Kernighan unix AWK`
- showStaff: Displays every staff member. *STAFF ONLY*
- showMembers: Displays every member. *STAFF ONLY*
- allGenreItems: Displays every item matching the given genre.
  - ex: `-allGenreItems HORROR`
- allAvailableItems: Displays every item with 1 or more available copies.
  - ex: `-allAvailableItems`
- allArtistItems: Displays every item matching the given artist.
  - ex: `-allArtistItems Ingmar Bergman`
- getDetailedItemInfo: Display detailed item information for item matching the given hex ID.
  - ex: `-getDetailedItemInfo 0x2dbf9a39`
- showCatalogue: Displays the entire library catalogue.
  - ex: '-showCatalogue'
- showArtists: Displays all of the artists in the catalogue.
  - ex: '-showArtists'
- saveDatabase: Saves the database into the file provided by the login.
  - ex: '-saveDatabase'

## Submit Your Work
Once your code is ready for grading, make sure you commit it to the Github Classroom Repo. **Any commits pushed after the deadline will be considered late submissions.**

Please also include a `PROJ4.txt` file explaining your experience working on this project.
- The status of your implementation (especially, if not fully complete).
- A description of how your code works, if that is not completely clear by reading the code (note that this should not be necessary, ideally your code should be self-documenting).
- Possibly a log of test cases which work and which don't work.
- Any other material you believe is relevant to the grading of your project.
**Suggestion**: Test your code thoroughly on a CS machine before submitting.

## Grading
The following are the general grading guidelines for this and all future projects.
- Late penalty is 30\% of the points scored for each of the first two days late, and will not be graded thereafter.
- If the submitted code cannot be successfully patched to the baseline source code, or the patched code does not compile:
  ```
  1	TA will try to fix the problem (for no more than 3 minutes);
  2	if (problem solved)
  3	  1%-10% points off (based on how complex the fix is, TA's discretion);
  4	else
  5	  TA may contact the student by email or schedule a demo to fix the problem;
  6	  if (problem solved)
  7	    11%-20% points off (based on how complex the fix is, TA's discretion);
  8	  else
  9	    All points off;

  ```
So in the case that TA contacts you to fix a problem, please respond to TA's email promptly or show up at the demo appointment on time; otherwise the line 9 above will be effective.
- If the code is not working as required in the project spec, the TA should take points based on the assigned full points of the task and the actual problem.
- Lastly but not the least, stick to the collaboration policy stated in the syllabus: you may discuss with your fellow students, but code should absolutely be kept private. You may not use any kind of generative AI to assist you. Any kind of cheating will result in zero points on the project, and further reporting.

## Rubric
- Code Compiles (20 Points)
- Server cannot manage more than 1 client connection (-20 points)
- A baseline of 80 points is granted. An assortment of the listed queries will be tested. Each failed query will result in the forfeiture of a certain number of points.
- **BONUS**: Server lets a maximum of 3 improper logins before exiting (+10 points)
- **BONUS**: The terminal interface is made to look nice. "Nice" is at the discretion of the TAs. (+15 points)
- **BONUS**: You may add up to 5 unique queries. Each query will grant you 5 points for a total of 25 points. Uniqueness will be at the discretion of the TAs. (+25 points)
- *NOTE*: Ideally, there shouldn't be any sudden connection timeouts when the code is tested; however, if one happens during final testing then it will not immediately lead to a total loss of points. Any more than that and the number of points removed will be at the TAs discretion.

## Tips
### Parsing Queries
I am giving you free reign to determine exactly how you would like to implement the query parsing. You'll need some way of transmitting a request from the client to the server and having the server take that request and figure out what to do. Since we've serialized many of our Library objects you can easily pass them back and forth through the use of an ObjectOutput/InputStream. Text can be sent using PrintWriters and read using BufferedReaders. It's left as an exercise to make this idea work in practice.

It's important to keep in mind that if a message is sent over a socket it must be waited on by the opposite thread. This means that you'll need to map out how the client should wait for and receive messages from the Server and how the Server will wait for and receive messages from the client. Once that mental model is crystallized for a single query, that process should be easily mapped for the remaining queries.

### Apache Client CLI
It can be a bit of a pain to write bespoke argument parsers for each and every CLI application you write, so I've opted to introduce the Apache Client CLI API to show how easy it can be to load in a pre-existing solution to a common problem. Oftentimes, it doesn't really pay to reinvent the wheel for no reason. The documentation for the API can be found [here](https://commons.apache.org/proper/commons-cli/apidocs/org/apache/commons/cli/package-summary.html) and a getting started guide can be found [here](https://commons.apache.org/proper/commons-cli/introduction.html). The idea is quite simple: set up some `Options`, provide those `Options` and the system arguments to a `CommandLineParser`, and extract the `CommandLine` output. You can see this in practice in `tools/ItemCreator.java` and in the skeleton code for `Server.java` and `Client.java`. 