# Pentominoes Dancing Links

## User manual
To use our implementation of Dancing Links, head over to the folder ./Algorithms/Dancing Links/bin/. This map contains all previously compiled java-files. The application can be executed by heading to that map in the command prompt and, when in the directory, entering “java Main”. This will start the program.

The code has been saved to ./Algorithms/Dancing Links/src/. This folder contains the source code of the program.

Pentomino.csv contains all possible mutations of the different pentomino pieces.

## Documentation
We have provided a lot of comments within the java-files, so that everything is clear. For some reason Javadoc has been giving us errors and we haven’t been able to fix it, so we apologize for that.

However, we have made a flowchart which represents a very high-level intuition of what the code does.

![HIGH LEVEL IMAGE](https://i.imgur.com/hZ3iMv0.png)


We will now specify what the different files do.

### Node.java and ColumnNode.java
These are self-made classes to represent a 4-way linked list (pointers to up, down, left and right), since we didn’t find any Java classes that allowed us to create this. We use both classes for the transformation of the problem into an exact cover problem later in the main.

### DLAlgorithmX.java
This class is basically the Dancing Links implementation of Algorithm X. It gets a header pointer to the exact cover “matrix” made of nodes and the number of solutions it should try to find. Then, a user can start the search.
The class also allows us to transform the results of the search algorithm back to the playing field. This will then be used later to print the result of the application.

### Main.java
This is basically the “front-end” of the application. The user will run this file and it will ask for inputs. Then it transforms the inputs into an exact cover version of the game, and it initializes and runs the search. Finally, it also prints the results of the game to the screen.

### Other files
The remaining files are the completed files from the template, which we use for
-	Creating Pentominos.csv
-	Loading data from Pentominos.csv
-	Having a GUI
