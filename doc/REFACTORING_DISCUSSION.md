NetIDs: ike, anp36, dht9, tc140

# Refactoring into TurtleManager

### Why?

The Model currently holds a list of Turtles and of TurtleListeners; however, this is a fairly low level implementation detail that the model should not handle. By refactoring these data structures into a new class, we can encapsulate how that data is processed and make modifying that behavior easier. This makes model more readable, more closed, and embraces the concept of substitution. 

### Making the Change

Currently the model passes the turtles to the commands one at a time by iterating over the TurtleManager. This change makes it easier to later modeify our code further, and pass the entire TurtleManager into commands. 


# Refactoring TurtleListeners

### Why?

The TurtleView was implementing ImmutableTurtle, which didn't make much sense. One part of the view, the TurtleStateView, was listening to another part of the view, when logically it should listen directly to the Turtle. 

### Making the Change

We made Turtles accept multiple listeners -- later we will have to use this functionality as turtles are constructed, but for now it is used by driver. We added a method to get a second listener from the view, and modified model to take any number of listeners when adding a turtle. 


# Refactoring ScrollPane TextAreas

### Why?

HistoryView, ReferenceView, TurtleStateView, VariableView, UserDefinedCommandView all had very similar constructors and methods. This was a lot of duplicate code. 

### Making the Change

We created a superclass that took care of these processes and extended the superclass into the appropriate components. Each subclass took care of its unique processes. We also removed extraneous, duplicate interfaces.

# Refactoring ChoiceBox Classes

### Why?

BackgroundOptionView, PenColorOptionView, TurtleImageOptionView, LanguageOptionView all have very similar constructors and interface implementations. This was also a lot of duplicate code and interfaces.

### Making the Change

We created another superclass that took care of the interface implementations and other similar processes. We also implemented the feature that when a user selects a color, then a command will be send to the text prompt and sent to the backend. This allows the back end to know of the changes to the pen color, canvas color, etc.


