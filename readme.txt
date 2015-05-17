This is a sample implementation of the A* Algorithm, written in JavaFX.

The algorithm instructions are from wikipedia:

http://en.wikipedia.org/wiki/A*_search_algorithm

The application demonstrates the finding of the shortest path from a start to an end point while avoiding obstacles in the path.

Usage:

+ drag start and end nodes
+ click/drag primary mouse button to create obstacles
+ click/drag secondary mouse button to remove obstacles
+ use step view to display the single steps in the A* algorithm
+ blue cells = path calculated by A* algorithm
+ step view: top/left = g, top/right = h, center = f = g + h
+ step view: green cells = cells in open set, red cells = cells inclosed set


Build:

+ create a new JavaFX project
+ copy contents src folder into your source folder
+ compile and launch Main.java

