# API’s

## Model

### External

*The external API for the model includes the Model class and the ImmutableTurtle interface. Model is given codes entered by the user through the view, which are parsed and executed on the turtle by the model’s internal API. The ImmutableTurtle interface passes the updated status values of the turtle to the view so that the display of the turtle may be updated accordingly.*

**Model**
* Public void addTurtleListener(TurtleListener tL)
* Public void addVariableListener(VariableListener sL)
* Public void addCommandListener(StringListener sL)
* Public void execute(String code)

**Interface ImmutableTurtle**
* Public double getX()
* Public double getY()
* Public double getHeading()
* Public boolean getPenDown()
* Public boolean isVisible()
* Public Color getPenColor()

### Internal

*The internal API for the model includes the Turtle class and the Command interface. The Turtle class provides an object for the model to update as it executes commands. Within the setters of Turtle, TurtleListener’s change methods are also called so that the respective turtle in the view may be updated accordingly. Classes that implement Command have an execute method that updates a Turtle parameter or modifies a Collection of commands or variables. These parameters are passed into Command from Model so that these data structures are updated upon the execution of commands. In order to add a new command to the SLogo language, a programmer should add a new class that implements the Command interface and define the execute method so that in enacts the desired changes on the desired data structures. Similar to TurtleListener, StringListener and CommandListener allow the View to see the changes to Model’s command and variable Collections enacted by Command’s execute method.*

**Turtle implements ImmutableTurtle**
* Public Turtle()
* Public Turtle(TurtleListener)
* Public void addTurtleListener(TurtleListener tL)
* Public double getX()
* Public double getY()
* Public double getHeading()
* Public boolean getPenDown()
* Public boolean isVisible()
* Public Color getPenColor()
* Public void setXY(double x, double y)
* Public void setHeading(double heading)
* Public boolean setPenDown(boolean down)
* Public void setVisible(boolean visible)
* Public Color setPenColor(Color color)
* Public void clearScreen()
	
**Interface Command  (SETXY, SETH, etc)**
* Public double execute(Turtle t, Collection commands, Collection variables)


## View

### External

*The external API for the view includes the View class and the TurtleListener, StringListener, and VariableListener interfaces. The View uses a Consumer of Strings to use Command’s execute method whenever a command is entered by the user. View also uses the listener interfaces to detect changes to the data structure in Model that need to be updated in the display, including the turtle image, the list of commands, and the list of variables and their values.*

**View** 
* Public View(Consumer<String> consumer)
* Public TurtleListener getTurtleListener()
* public VariableListener getVariableListener()
* public StringListener getCommandListener()
	
**Interface TurtleListener**
* Public void setTurtle(ImmutableTurtle)
* Public void locationChange(double dx, double dy)
* Public void headingChange(double dtheta)
* Public void penChange()
* Public void visibilityChange()
* Public void clearScreen()
	
**Interface StringListener**
* Public void newString(String s)
	
**Interface VariableListener**
* Public void changedMap(Map<String, Double> vars)

### Internal

*The internal API for the view includes several classes that represent the windows and sections of windows that are displayed on the screen. They all extend Pane and implement the appropriate listener to detect changes in the model. Adding a new window or part of the user interface requires a programmer to create a new class that extends Pane. If the new window or UI component needs to be updated with changes to parts of the model, a new listener interface should be created to detect the changes to those parts of the model and enact those changes on the window or UI component.*

**TurtleView extends Pane implements TurtleListener**
* Public void setBackgroundColor(Color c)
	
**TextPrompt extends Pane**
* Public TextPrompt(Consumer<String> consumer)
* Public void enter()
* Public void clear()
	
**ReferenceDisplay extends Pane**
* Public ReferenceDisplay()
	
**HistoryDisplay extends Pane**
* Public HistoryDisplay()
* Public void updateHistory(String name)
* Public void clearHistory()
	
**VariableDisplay implements VariableListener extends Pane**
* Public VariableDisplay()
* Public void clearVariables()
	
**UserDefinedCommandsDisplay implements StringListener extends Pane**
* Public void clearCommands()

## Controller

**Driver**
* Public Driver()
* Public void run()
