# Tank War Game (JavaFX)

A 2D Tank War game developed using JavaFX and OOP Design patterns. The game allows the player to control a tank, fight against AI tanks, and navigate through dynamic maps. The map is made of walls and obstacles, and players/AI Tanks can use missile burst firing and health packs to win by destroying the other tanks, before losing all their lives.

## Demo
https://github.com/user-attachments/assets/5fe837ee-f8d4-4104-9255-1084e4bf137d

## Features

- **Tank Movement**: Tanks can be controlled by user using the keyboard, enemy tanks have player seeking algorithm to move towards the player tank.
- **Combat System**: The player can shoot projectiles to damage enemy tanks, projectiles cannot pass through immovable walls or after crashing into enemy tanks.
- **Dynamic Map**: The game map is created using a 2D array, dynamically adjusted to fit the pane size.
- **Walls and Obstacles**: Walls are represented as GIFs and are integrated into the game map, they act as immovable objects that cannot be passed through.
- **Respawning and Lives**: If the player tank takes too much damage, it will respawn until all 3 lives are exhausted.

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
