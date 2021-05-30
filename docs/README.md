# Final Delivery

## sudo PacMan

A clone of the classic PacMan game played in the arcade machines of the good old days, spiced up with some easter eggs related to the package manager of your favorite Linux distro.
<p align="center">
  <img src="images/pacman-gif.gif" alt="PacMan g50.model.Game">
</p>

Developed by Bruno Mendes (up20212121@fe.up.pt), Fernando Rego (up2123131@fe.up.pt) and Nuno Costa(up201906272@fe.up.pt).

## Implemented Features

- Map Loader from a file 
    - Loads a map from a file in order to increase compatibility, making it possible to load different pacman maps, as long as it sticks to the defined structure
- Game Map Display
    - Custom font for a more precise replica of the original game elements
    - Usage of bitmasks for different Wall generation
    - 
- PacMan movement with the keyboard
- PacDot collection
- Ghost movement and states
- Different ghost strategies  
- Scoring and abilities with collectables
- Keeps track of the highest score  
- Defeat screen (endless game, no winning screen)
- Level transition
- Difficulty adaptation per level  
- Application menus
- Pause state  
- Sound effects
- Package manager easter eggs

## Architectural Design
<p align="center">
  <img src="images/mvc-architecture.png" alt="Model View Controller">
</p>

The architectural design followed in this project was the MVC (particularly the definition represented above).
Each of its components is briefly described below.

### Model
Contains the entities that exist semantically in the game: the pacman, the ghosts, the map and its elements.

### View
Responsible for drawing the game elements (and, later, the menus) in a way that makes sense considering the model and is suited for any terminal-based view framework (in our case, Lanterna).

### Controller
Responsible for the flow of the application:
- Updates the screen at a given frame rate
- Updates the game state based on the input received from the GUI
- Switches between game menus based on game rules logic and user input (later)

## Design Problems

builder para a view
observers na gui
strategy pattern
gui facade 
decorator gamemapviewer
composite de controladores
extract dos passos do ghost controller

## Testing

### Coverage/Mutations
<p align="center">
  <img src="images/pitest.png" alt="PiTest">
</p>

### Particular test highlights
- ElementViewFactory: provided this class’ methods require knowledge from other classes (namely, the ViewProperty and the GUI), stubs were used to ensure the correct decouple of the tests, and dummy parameters were injected where needed (namely, to create a model entity to be drawn).
- GameMapTest: a smoke test was executed on a smaller test map to ensure that the main map load features were working properly.

## Design Problems and solutions (UML)

### Multiple views
Each game element shall be drawn on the screen with the appropriate character and color. While it isn’t feasible to include this data in the model (should we ever switch to a 3D-based viewer framework, that would be highly uncomfortable), it is also lazy to create a different viewer class for each element just to hold different data, and act exactly the same way. To solve this issue, we opted to let a builder create the correct element viewer instance given the class of the given drawable element.

<p align="center">
  <img src="images/view.png" alt="Multiple Views">
</p>

### Polling from the GUI
As seen in the classes, we could poll the input receiver (in our case, the GUI) for the last user action each frame and act upon it. However, with this approach we would lose some precision and possibly some key inputs. We found that the Lanterna framework provides methods for handling interrupts from the keyboard, and used this mechanism to inform any generic GUI observer of an action in “real time”. In our case, the game controller is the observer, acting upon the inputs to move the pacman.

<p align="center">
  <img src="images/controller.png" alt="Polling from the GUI">
</p>

### g50.model.Game Map Builder
To avoid long lines of hard coded insertions in the game map class, it makes sense to have a separate class to do this job in a generic way (read from a file) and return the assembled GameMap object.

<p align="center">
  <img src="images/mapbuilder.png" alt="g50.model.Game Map Builder">
</p>

