Software Architecture Design And Implementation
Assignment #2 Battleships Game


TEAM MEMBERS

Eduardo Nava S3253064
Victor Nava S3252905
John Kolovos S3289732


MEMBER CONTRIBUTION 

John:

- General Software Design
- Design and Implementation of all database classes and chat class
- Database Test Classes

Eduardo:
- General Software Design
- Design And Implementation of the GUI
- Documentation
    
Victor:
- General Software Design
- Design and Implementation of Forum and ForumClient Classes and Interfaces
- Forum Test Classes
    

FUNCTIONALITY ACHIEVED

- The software performs all the basic functionality requested
- Achieved the optional goal of allowing message replies

KNOWN BUGS

# TODO


CLASS DESCRIPTIONS

# TODO

################################################################################

CLASS DESCRIPTIONS AND RELATIONSHIPS

The code for the project can be broken down into 3 main sections

* Client: 
    - ForumClient: is used as an interface for accessing the remotes methods on the server
    - GUI: The graphical interface of the client program

* Server: 
    - Forum: has the main logic of the server and provides public methods accessed via RMI
    - DB classes: used to access the DB, each class maps a table on the db
    - Chat: implements the chat functionality 

* Shared: This section provides common interfaces and classes used by both server and client
    - ForumInterface: the RMI interface to call server methods form client
    - ForumClientInterface: the RMI interface to call client methods (chat) form server
    - ForumThread, Topic, User, Config are used as data structure to represent a db table
    

################################################################################

INFORMATION ABOUT DATABASE SCHEMA

* The Users:

    FUSER (ID, NAME, PASSWORD, ISACTIVE, TYPE)

* The discussion topics (groups):

    FTOPIC (ID, NAME, DESCRIPTION, ISACTIVE, USERID)

* The Threads (messages) and Replies:

    FTHREAD (ID, TITLE, DESCRIPTION, TOPICID, USERID, PARENTID)
    

* The welcome message:

    FCONFIG (ID, WELCOME)
    
    
The are currently this users in the db:

User    Password    Type
admin   admin       admin
vic     abcd1234    admin
john    abcd1234    admin


################################################################################

INSTRUCTIONS FOR COMPILING AND RUNNING THE PROGRAM

FROM THE ECLIPSE:

* Run the registry from the bin (very important) dir:
    $ cd path_to_forum_dir/bin    
    $ rmiregistry [port]
        
* Include the jar files located in the lib dir to the buildpath

* Set to running VM arguments to:
    Djava.security.policy="file:/${workspace_loc}/forum/policy"
    Djava.rmi.server.codebase="file:/${workspace_loc}/forum/bin/"

* Run the server first then run the client


FROM THE COMMAND LINE:

* Run the registry from the bin (very important) dir:
    $ cd path_to_forum_dir/bin
    $ rmiregistry [port]
    
* Compile the server:
    $ cd path_to_forum_dir
    $ javac -sourcepath src \
            -classpath bin:lib/*.jar \
            -d bin \
            src/shared/*.java \
            src/server/*.java \
            src/server/db/*.java \
            src/server/utils/*.java
            
* Compile the client:
    $ cd path_to_forum_dir
    $ javac -sourcepath src \
            -classpath bin:lib/*.jar \
            -d bin \
            src/shared/*.java \        
            src/client/*.java \
            src/gui/*.java
            
* Run the server (leave host and port black to use defaults):
    $ cd path_to_forum_dir
    $ java -cp bin:lib/ojdbc14.jar -Djava.security.policy=policy server.Forum [host] [port]
    
* To run the client (leave host and port black to use defaults):
    $ cd path_to_forum_folder
    $ java -cp bin gui.Driver [host] [port]
    



                                        


