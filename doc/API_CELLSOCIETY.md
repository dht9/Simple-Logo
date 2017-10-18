# Methods Marked Internal, External, or Should not be in API. 
netid: ike,anp36,dht9,tc140


```code
package cellsociety_Cells;
public abstract class Cell { 
  	public Cell(String s) INT
	public String getStatus() INT
	public Color getColor() EXT
	public void setStatus(String s) INT
}
 
package cellsociety_Cells;
public class FireCell extends Cell { 
  	public FireCell(String s) 
	public void setStatus(String s) 
}
 
package cellsociety_Cells;
public class LifeCell extends Cell { 
  	public LifeCell(String s) 
	public void setStatus(String s) 
}
 
package cellsociety_Cells;
public class NullCell extends Cell { 
  	public NullCell() 
}
 
package cellsociety_Cells;
public class RPSCell extends Cell { 
  	public RPSCell(String s) 
	public void setStatus(String s) 
}
 
package cellsociety_Cells;
public class SegCell extends Cell{ 
  	public SegCell(String s) 
	public void setStatus(String s) 
}
 
package cellsociety_Cells;
public class WaTorCell extends Cell { 
  	public WaTorCell(String s) 
	public WaTorCell(String s, int sEnergy) 
	public void setStatus(String s) 
	public int getLifeCount() INT
	public void setLifeCount(int l) INT
	public void incrementLifeCount() INT
	public void resetLifeCount() INT
	public int getEnergy() INT
	public void setEnergy(int e) INT
	public void increaseEnergy(int a) INT
	public void decrementEnergy() INT
	public boolean getEaten() INT
	public void setEaten() INT
	public void resetEaten() INT
}
 
package cellsociety_Simulations;
public abstract class CardinalSim extends CellManager { 
  	public CardinalSim(double n, String shape, boolean toroidal, List<String> initialStatuses) 
	public CardinalSim(double n, String shape, boolean toroidal) 
	public List<Integer> getNeighborLocationNums(Cell c) 
}
 
package cellsociety_Simulations;
public abstract class CellManager { 
  	public CellManager(double n, String shape, boolean toroidal, List<String> initialStatuses) EXT
	public CellManager(double n, String shape, boolean toroidal) EXT
	public List<Cell> getCurrentCells() EXT
	public double getSize() EXT
	public void updateCurrentCells() EXT
	public String getShape() EXT
	public boolean getToroidal() EXT
}
 
package cellsociety_Simulations;
public class Fire extends CardinalSim { 
  	public Fire(double probCatch, double n, String shape, boolean toroidal, List<String> initialStatuses) 
	public Fire(double probCatch, double n, String shape) 
	public double getPCatch() EXT
	public void setPCatch(double newPCatch) EXT
}
 
package cellsociety_Simulations;
public class GameOfLife extends CellManager{ 
  	public GameOfLife(double a, double n, String shape, boolean toroidal, List<String> initialStatuses) 
	public GameOfLife(double a, double n, String shape) 
	public double getAliveRatio() EXT
}
 
package cellsociety_Simulations;
public class RPS extends CellManager { 
  	public RPS(double rockPercent, double paperPercent, double scissorsPercent, double n, String shape, boolean toroidal, List<String> initialStatuses) 
	public RPS(double rockPercent, double paperPercent, double scissorsPercent, double n, String shape) 
	public double getRockPercent() EXT
	public double getPaperPercent() EXT
	public double getScissorsPercent() EXT
}
 
package cellsociety_Simulations;
public class Segregation extends CellManager{ 
  	public Segregation(double t, double r, double empty, double n, String shape, boolean toroidal, List<String> initialStatuses) 
	public Segregation(double t, double r, double empty, double n, String shape) 
	public List<Cell> setParamCells() Should not be in API
	public double getRedToBlue() EXT
	public double getEmptyRatio() EXT
	public double getMinSimilar() EXT
}
 
package cellsociety_Simulations;
public class WaTor extends CardinalSim { 
  	public WaTor(double sharksPercent, double fishPercent, double n, int initialEnergy, int sharkBreed, int fishBreed, int fishEnergy, String shape, boolean toroidal, List<String> initialStatuses) 
	public void initializeCurrentCells() EXT
	public void initializeCurrentCells(List<String> statuses) EXT
	public void updateCurrentCells() EXT
	public double getSharkRatio() EXT
	public double getFishRatio() EXT
	public int getInitialSharkEnergy() EXT
	public void setInitialSharkEnergy(int newEnergy) EXT
	public int getSharkBreedCount() EXT
	public void setSharkBreedCount(int newSharkBreed) EXT
	public int getFishBreedCount() EXT
	public void setFishBreedCount(int newFishBreed) EXT
	public int getFishEnergyContent() EXT
	public void setFishEnergyContent(int newFishEnergy) EXT
}
 
package cellsociety_team04;
public class Driver { 
  	public Driver(Stage stage) EXT
	public void setup(Stage stage) Should not be in API
	public void determineSim(CellManager simChoice) EXT
	public void handle(WindowEvent we) Should not be in API
	public void runSimulation() EXT
}
 
package cellsociety_team04;
public class GridDisplay { 
  	public GridDisplay(int nCells, int size, String shape) 
	public void updateGridDisplay(List<Cell> currentCells, GridPane grid) INT
}
 
package cellsociety_team04;
public class XMLException extends RuntimeException { 
      public XMLException (String message, Object ... values) 
    public XMLException (Throwable cause, String message, Object ... values) 
    public XMLException (Throwable cause) 
}

package cellsociety_team04;
public class XMLParser { 
  	public XMLParser() EXT
	public CellManager getSimulation() EXT
	public void buttonChooseFile(File file) EXT
	public void createDocForFile(File file) Should not be in API
}
 
package cellsociety_UIUX;
public class FireWindow extends SimulationWindow { 
  	public FireWindow(Stage s, CellManager sim) 
}
 
package cellsociety_UIUX;
public class GameOfLifeWindow extends SimulationWindow { 
  	public GameOfLifeWindow(Stage s, CellManager sim) 
}
 
package cellsociety_UIUX;
public class MenuWindow extends Window { 
  	public MenuWindow(Stage s) 
	public void setupScene() INT
	public boolean getNewSim() INT
	public void setNewSim(boolean b) INT
}
 
package cellsociety_UIUX;
public class RPSWindow extends SimulationWindow { 
  	public RPSWindow(Stage s, CellManager sim) 
}
 
package cellsociety_UIUX;
public class SegregationWindow extends SimulationWindow { 
  	public SegregationWindow(Stage s, CellManager sim) 
}
 
package cellsociety_UIUX;
public abstract class SimulationWindow extends Window { 
  	public SimulationWindow(Stage s, CellManager sim) 
	public static double getWidth() INT
	public static double getOffset() INT
	public void setRowSize(CellManager c) INT
	public static void setCellShape(String cellShape) INT
            public String toString(Double n) INT
            public Double fromString(String s) INT
	public void buttonClick() INT
	public void displayGrid(List<Cell> currentCellStatuses) INT
	public void stopRunning() INT
	public void setErrorText(String error) INT
}
 
package cellsociety_UIUX;
public class WatorWindow extends SimulationWindow { 
  	public WatorWindow(Stage s, CellManager sim) 
}
 
package cellsociety_UIUX;
public abstract class Window { 
  	public Window(Stage s) 
	public void gameLoop(CellManager simType, double simSpeed) EXT
	public Scene getScene() INT
}
 
public class main extends Application { 
  	public void start(Stage stage) 
}
```

# API’s

## Simulation

### Internal

The internal API for simulations sets the statuses of the Cells and sets the color of each Cell accordingly.

### External

The external API for simulations initializes and updates the statuses of the Cells in the simulation based on neighboring statuses for each Cell, as well as other rules as needed. The API’s for visualization and configuration use this external API to set the initial data of the Cells, update the data, and manipulate the simulation’s parameters in the back end, such as setting different neighbor rules based on shape and edge types.

## Visualization

### Internal

All API for Visualization are internal except for ‘gameLoop()’. APIs in classes SimulationWindow, Window, and MenuWindow (other than ‘gameLoop() ) are intended to help other classes visualize the simulations. 

### External

The external API involves calling `gameLoop(...)` in Main.java allowing the simulation to be run and displayed in a Window. This method is intended for outside use since it will take in a set of external information to run the simulation.

## Configuration

### Internal

There is no internal API. All features of the XML Parser are intended for outside use. Since there is only one class for this section, all methods that would be internal are private. 

### External

buttonChooseFile(File file) selects the file to be loaded, then getSimulation() can be called to return the simulation last selected. 
