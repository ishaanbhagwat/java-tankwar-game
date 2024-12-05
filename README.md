# Tank War Game (JavaFX)

A 2D Tank War game developed using JavaFX. The game allows players to control tanks, fight against each other, and navigate through dynamic maps. The map is made of walls and obstacles, and players use strategic movement and combat to win.

## Features

- **Tank Movement**: Tanks can be controlled using keyboard inputs to move around the map.
- **Combat System**: Players can shoot projectiles to damage enemy tanks.
- **Dynamic Map**: The game map is created using a 2D array, dynamically adjusted to fit the pane size.
- **Walls and Obstacles**: Walls are represented as GIFs and are integrated into the game map, using design patterns to handle their creation and interaction.
- **Game Loop**: The game is driven by a continuous game loop to ensure smooth gameplay and updates.

## Technologies Used

- **JavaFX**: For building the graphical user interface (GUI) and handling game rendering and animations.
- **Design Patterns**: Various design patterns like Factory, Singleton, and Observer are used to structure the code and ensure scalability and maintainability.
- **Object-Oriented Programming**: Emphasis on OOP principles to create reusable, modular code, and design components like tanks, bullets, and the map.

## Installation

### Prerequisites

- Java 11 or later
- Maven (optional, for dependency management)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/tank-war-game.git
2. Add required JavaFX module:
   ```bash
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml com.example.tankwar.Main
4. Run Main.java file.
   ```bash
   java com.example.tankwar.Main
