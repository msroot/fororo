Software Architecture Design And Implementation
Assignment #2 Forum


TEAM MEMBERS

Eduardo Nava S3253064
Victor Nava S3252905
John Kolovos S3289732


MEMBER CONTRIBUTION 

John:

* General design
* Design and Implementation of all database classes and chat class
* Database Test Classes

Eduardo:

* General design
* Design And Implementation of the GUI
    
Victor:

* General design
* Design and Implementation of Forum and ForumClient classes and Interfaces
* Forum Test Classes
    

################################################################################

FUNCTIONALITY ACHIEVED

* The software performs all the basic functionality requested plus:
* Allow message replies
* Allow admins to modify the welcome message

KNOWN ISSUES

* There is an issue with sql: there can not be any quote (') char in the anywhare
Thread, Topic, User, Welcome message.
* The GUI might runs a bit slow if ran from yallara thru ssh


################################################################################

CLASS DESCRIPTIONS AND RELATIONSHIPS

The code for the project can be broken down into 3 main sections

* Client: 
    - ForumClient: is used for accessing the remotes methods on the server
    - GUI: The graphical interface of the client program
    - client.gui.Driver class contains the main method to run the client

* Server: 
    - Forum: has the main logic of the server and provides public methods accessed via RMI
    - DB classes: used to access the DB, each class maps a table on the db
    - Chat: implements the chat functionality 
    - server.Forum class contains the main method to run the server

* Shared: This section provides common interfaces and classes used by both server and client
    - ForumInterface: the RMI interface to call server methods form client
    - ForumClientInterface: the RMI interface to call client methods (chat) form server
    - ForumThread, Topic, User, Config are used as data structure to represent db tables


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

The database should be ready to go with some test data.
In addition the file "export.sql" was provided to recreate the db.


################################################################################

INSTRUCTIONS FOR COMPILING AND RUNNING THE PROGRAM

FROM THE ECLIPSE:

* Create project and import the "forum" dir

* Include the jar files located in the "lib" dir to the buildpath of project

* Set Run Cofigurations > VM arguments for file gui.Driver.java to:
    Djava.security.policy="file:/${workspace_loc}/forum/policy"
    Djava.rmi.server.codebase="file:/${workspace_loc}/forum/bin/"
    
* build the project

* Run the registry from the bin (very important) dir:
    cd path_to_forum_dir/bin    
    rmiregistry [port]

* Run the server first then run the client


FROM THE COMMAND LINE (YALLARA):

* Notes:
    - Make sure the directory forum/bin exists before compiling
    - Make user X11 is enabled when running the GUI from yallara (ssh -X yallara)
    - The database should be ready to go, to recreate the db use export.sql
    - The database should be ready to go, to recreate the db use export.sql

* Compile the server: 
    cd path_to_forum_dir
    mkdir bin
    javac   -sourcepath src \
            -d bin \
            -classpath bin:lib/*.jar \
            src/shared/*.java \
            src/server/*.java \
            src/server/db/*.java \
            src/server/utils/*.java
            
* Compile the client:
    cd path_to_forum_dir
    mkdir bin
    javac   -sourcepath src \
            -d bin \
            -classpath bin:lib/*.jar \
            src/shared/*.java \
            src/client/*.java \
            src/gui/*.java
            
* Run the registry from the bin (very important) dir:
    cd path_to_forum_dir/bin
    rmiregistry [port]

* Run the server:
    cd path_to_forum_dir
    java -cp bin:lib/ojdbc14.jar -Djava.security.policy=policy server.Forum [host] [port]
    
* To run the client:
    cd path_to_forum_folder
    java -cp bin gui.Driver [host] [port]