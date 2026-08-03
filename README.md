# Dining Philosophers – JavaFX

A visual JavaFX simulation of the classic Dining Philosophers concurrency problem.

The application demonstrates how multiple threads compete for shared resources while avoiding deadlock.

## Features

- Five philosophers running as separate threads
- Five shared chopstick resources
- Random eating, thinking, and idle durations
- Thread synchronization using `synchronized`
- Thread coordination using `wait()` and `notifyAll()`
- Deadlock prevention through ordered resource acquisition
- JavaFX graphical interface displaying each philosopher's state

## Deadlock Prevention

Each philosopher always picks up the lower-numbered chopstick first.

Using a consistent resource-acquisition order prevents the circular-wait condition that can cause deadlock.

## Technologies

- Java
- JavaFX
- FXML
- Multithreading
- Object-Oriented Programming
- Synchronization

## Project Structure

```text
src/
├── Chopsticks.java
├── Philosopher.java
├── PhilosophersProblem.java
├── PhilosophersProblemController.java
├── PhilosophersProblem.fxml
└── pictures/
