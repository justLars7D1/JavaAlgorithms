# Genetic Algorithm - Java
An implementation of the concept of a genetic algorithm in Java

## Author
The author of this software is Lars Quaedvlieg 
* lcpm.quaedvlieg@student.maastrichtuniversity.nl

## License
This project is licensed under the GNU GPL V3 License - see the LICENSE.md file for details.

## Getting started
### Prerequisites
In order to use this program, you need to have a working version of the java JDK (11+). You can install it over [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).


## Source code files
Everything is commented with javadoc, so we'll only give a brief description of the classes.
### Agent.java
This class represents a single agent within a certain environment.
It contains an id, weights and a score.

### Algorithms.GeneticAlgorithm.java
This class represents the genetic algorithm strategy. It takes a pool of agents and with a score function it can create better generations.

_We recommend creating a new file for scoring the agent's performance and updating it within that file_

