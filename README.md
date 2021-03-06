[![Gradle Build](https://github.com/AlecCoates/Dragon-Boat-Z/workflows/Gradle%20Build/badge.svg?branch=main)](https://github.com/AlecCoates/Dragon-Boat-Z/actions?query=workflow%3A%22Gradle+Build%22) 
[![CodeQL](https://github.com/AlecCoates/Dragon-Boat-Z/workflows/CodeQL/badge.svg)](https://github.com/AlecCoates/Dragon-Boat-Z/actions?query=workflow%3ACodeQL) 
[![Branch Coverage (Tested classes)](https://raw.githubusercontent.com/AlecCoates/Dragon-Boat-Z/main/.github/badges/jacocoBranchesTested.svg)](https://aleccoates.github.io/Dragon-Boat-Z/docs/tests/Coverage%20Details/Coverage%20Report/index.html) 
[![Coverage (Tested classes)](https://raw.githubusercontent.com/AlecCoates/Dragon-Boat-Z/main/.github/badges/jacocoTested.svg)](https://aleccoates.github.io/Dragon-Boat-Z/docs/tests/Coverage%20Details/Coverage%20Report/index.html) 
[![Pull Requests](https://img.shields.io/github/issues-pr/AlecCoates/Dragon-Boat-Z)](https://github.com/AlecCoates/Dragon-Boat-Z/pulls) 
[![Website aleccoates.github.io/Dragon-Boat-Z](https://img.shields.io/website-up-down-green-red/https/aleccoates.github.io/Dragon-Boat-Z.svg)](https://aleccoates.github.io/Dragon-Boat-Z/)

# Contents
<details open><summary></summary>

- [About Us](#about-us)
- [Game Description](#game-description)
- [Assessment 1 Content](#assessment-1-content)
- [Assessment 2 Content](#assessment-2-content)

</details>


# About Us
<details open><summary></summary>
<img src="core/assets/dragonboatz Logo.png">

Ever wanted to race Dragon boats down the River Ouse?
Of course, the answer is yes and we have just the game for you.
DragonBoat Z! 

Our Team (Quackthulu) consists of:

* Aaron Price
* Alec Coates
* Charlie Curedale-Rayner
* Eleanor Griffin-Smith
</details>


# Game Description
<details open><summary></summary>
<img src="core/assets/example screen for website.png">

Dragon Boat Z is a single-player Boat Racing game based on the annual Dragon Boat Race held in York along the
River Ouse.

In Dragon Boat Z, the player competes against 6 AI opponents, racing their dragon boats across 3 legs to achieve the fastest time to cross the finish line.

Upon starting the game, the player selects 1 of 7 boats as their boat that they would like to race with.
Every boat has 4 statistics with each boat having a different distribution of these statistics.

## Boat Statistics

- <strong>Robustness</strong>
  - Determines how much damage a boat can take.
  - A boat with higher robustness will lose a smaller percentage damage to the durability upon collision with an obstacle.
- <strong>Manoeuvrability</strong>
  - Determines how fast the boat can avoid obstacles.
  - A boat with higher manoeuvrability will be able to move side to side better without losing speed.
- <strong>Max Speed</strong>
  - Determines how fast a boat can go.
  - A boat with higher max speed will be able to go faster than other boats.
- <strong>Acceleration</strong>
  - Determines how quickly a boat can achieve its max speed.
  - A boat with higher acceleration will achieve its max speed before other boats.

## Obstacles and Penalties

During the races, there are a series of obstacles that will be floating down the Ouse. The player must avoid these obstacles in order to not damage their boat.
If the boat's durability is reduced to 0 at any point within the game, the game ends and the player loses. So, watch out for those geese!!
Whilst navigating the obstacles, the player must make sure to stay in their lane to avoid incurring a time penalty.

## Winning the Game

If the player manages to achieve one of the 3 fastest times across the 3 legs, they will qualify for the final race where they will compete against the other 2 fastest boats.
Upon completing the final race, the player will be awarded a medal, bronze, silver, or gold respective to their finishing position.
</details>


# Assessment 1 Content
<details><summary></summary>

## Deliverables

* <a href="docs/deliverables/Req1.pdf">Requirements</a>
* <a href="docs/deliverables/Arch1.pdf">Architecture</a>
* <a href="docs/deliverables/Plan1.pdf">Method Selection and Planning</a>
* <a href="docs/deliverables/Risk1.pdf">Risk Assessment and Mitigation</a>
* <a href="docs/deliverables/Impl1.pdf">Implementation</a>

## Executables

* <a href="releases/download/v1.0/DragonBoat.jar">Dragon Boat Z Game</a>

## Javadocs

* <a href="docs/javadocs/javadoc-1.0/index.html">Javadocs HTML Export</a>

## Weekly Snapshots

### Project Gantt Chart

<img src="docs/gantt chart/gantt chart.png">

### Sprint Dropdowns

<details>
<summary><strong> Sprint 1 - 08/10/2020 </strong></summary>

Having completed the task of setting up Jira and other resources needed for the project, the focus was on preparatory
work for the upcoming week. This involved adding to the existing set of Customer Questions constructed and develop an
understanding of how GitHub Pages works.
<img src="docs/sprints/Sprint 1.png">

<br>
<br>
<a href="docs/sprints/Sprint 1.png">Sprint 1 Jira Board</a>
<br>
</details>


<details>
<summary><strong> Sprint 2 - 15/10/2020 </strong></summary>

Having completed the Customer meeting during the last Sprint, other tasks and deliverables could now be started. The priorities
are the Architecture Abstract diagram that will be reviewed at the second meeting of this Sprint, as this will allow us to make decisions such as what game library would be used. Deliverables such as Method Selection and Planning, Requirements and Risk Assessment will be built up using the now known information from the Customer Meeting alongside the starting of Sprite Design.
<img src="docs/sprints/Sprint 2.png">

<br>
<br>
<a href="docs/sprints/Sprint 2.png">Sprint 2 Jira Board</a>
<br>
</details>

<details>
<summary><strong> Sprint 3 - 22/10/2020 </strong></summary>

Having completed the Abstract Architecture Diagram, a focus was put onto the Concrete Architecture Diagram development so
it would be ready for when initial classes are constructed, based off the Gantt Chart. This also meant a decision
was made on the library used for this project: LibGDX. Thus, each team member was assigned the task of completing
the tutorial found in the documentation, of LibGDX, and further research. Continued deliverable work was assigned a low priority
as this was considered an iterative process throughout the course of the project.
<img src="docs/sprints/Sprint 3.png">

<br>
<br>
<a href="docs/sprints/Sprint 3.png">Sprint 3 Jira Board</a>
<br>
</details>

<details>
<summary><strong> Sprint 4 - 29/10/2020 </strong></summary>

In Sprint 3, the Concrete Architecture was created and allowed for critical tasks to begin: the initial classes. This
was a high priority as delays would impact the production of the prototype on time. Further deliverable work was
assigned medium/low priority. To maintain a clear separation between work, the initial one board was separated into
two: Deliverables and Implementation. It was decided that second session of the Sprint would focus on the progression
of these tasks and discuss if any would need to be reassigned.

<br>
<br>
<strong> Deliverables Board </strong>
<img src="docs/sprints/Sprint 4 - Deliverables.png">
<br>
<a href="docs/sprints/Sprint 4 - Deliverables.png">Sprint 4 Delievrables Jira Board</a>
<br>
<strong> Implementation Board </strong>
<img src="docs/sprints/Sprint 4 - Implementation.png">
<br>
<a href="docs/sprints/Sprint 4 - Implementation.png">Sprint 4 Implementation Jira Board</a>
<br>
</details>

<details> 
<summary><strong> Sprint 5 - 05/11/2020 </strong></summary>

The backlog of tasks T14 and T11, from the previous Sprint, were assigned the highest priority, as continued programming work depended on their completion. With the aim of reaching the milestone M4, at the end of this Sprint, the prototype development was a high focus. The specific tasks related to the prototype were stated within the assignee's task description. Method Selection and Planning were a focus within the Deliverables board to continue to append changes that had occurred during the project lifecycle.

<br>
<br>
<strong> Deliverables Board </strong>
<img src="docs/sprints/Sprint_5_-_Deliverables.png">
<br>
<a href="Sprint_5_-_Deliverables.png">Sprint 5 Jira Deliverables Board</a>
<br>
<strong> Implementation Board </strong>
<img src="docs/sprints/Sprint_5_-_Implementation.png">
<br>
<a href="docs/sprints/Sprint_5_-_Implementation.png">Sprint 5 Jira Implementation Board</a>
<br>
</details>

<details>
<summary><strong> Sprint 6 - 12/11/2020 </strong></summary>

Due to some minor issues with the construction of the legs of the game in the prototype, this task was focused on. Once completed,
we envision that the final tasks to have a functioning game will be finished on time. There will be a high focus on the programming elements needed for the final product. During the second meeting of this sprint, if there is extra time to implement additional functionality of the game the task, Animations, will be attempted and potentially additional features.
The Deliverables board focused on the finalising of the Implementation document, reflecting features that we have unsuccessfully
managed to implement and any additional features we may include.

<br>
<br>
<strong> Deliverables Board </strong>
<img src="docs/sprints/Sprint 6 - Deliverables.png">
<br>
<a href="docs/sprints/Sprint 6-Deliverables.png">Sprint 6 Jira Deliverables Board</a>
<br>
<strong> Implementation Board </strong>
<img src="docs/sprints/Sprint 6 - Implementation.png">
<br>
<a href="docs/sprints/Sprint6-Implementation.png">Sprint 6 Jira Implementation Board</a>
<br>
</details>

<details>
<summary><strong> Sprint 7 - 19/11/2020 </strong></summary>

Having reached the completion of the game, a focus on the game testing and peer review of finalised deliverables was
focused on. This was to ensure a successful completion of the entire project.
<img src="docs/sprints/Sprint 7.png">

<br>
<br>
<a href="docs/sprints/Sprint 7.png">Sprint 7 Jira Board</a>
<br>
</details>

</details>


# Assessment 2 Content
<details open><summary></summary>
  
## Deliverables
* <a href="docs/deliverables/Req2.pdf">Requirements</a>
* <a href="docs/deliverables/Arch2.pdf">Architecture</a>
* <a href="docs/deliverables/Plan2.pdf">Method Selection and Planning</a>
* <a href="docs/deliverables/Risk2.pdf">Risk Assessment and Mitigation</a>
* <a href="docs/deliverables/Impl2.pdf">Implementation</a>
* <a href="docs/deliverables/Test2.pdf">Testing</a>
* <a href="docs/deliverables/CI2.pdf">Continuous Integration Report</a>
* <a href="docs/deliverables/Change2.pdf">Change Report</a>
* <a href="docs/deliverables/SelfAss2.pdf">Self assessment</a>

## Executables

* <a href="https://github.com/AlecCoates/Dragon-Boat-Z/releases/download/v1.1.0/Dragon-Boat-Z.zip">Dragon Boat Z Game</a>

## Javadocs

* <a href="docs/javadocs/javadoc-1.1.0/index.html">Javadocs HTML Export</a>

## Weekly Snapshots

<summary><strong> Week 1 </strong></summary>

<img src="docs/sprints/w1m1.PNG">
<img src="docs/sprints/w1m2.PNG">

<summary><strong> Week 2 </strong></summary>

<img src="docs/sprints/w2m1.PNG">
<img src="docs/sprints/w2m2.PNG">

<summary><strong> Week 3 </strong></summary>

<img src="docs/sprints/w3m1.PNG">


### Project Gantt Chart

<img src="docs/gantt chart/ganttchart2.PNG">




## Testing
<details><summary>Test Designs</summary>

* <a href="docs/tests/Test Designs/Unit Tests.pdf">Unit Tests</a>
* <a href="docs/tests/Test Designs/Integration Tests.pdf">Integration Tests</a>
* <a href="docs/tests/Test Designs/System Tests.pdf">System Tests</a>

</details>

<details><summary>Test Results</summary>
  <div style="text-indent:50px;">
  <details><summary>Inherited Code</summary>

  * <a href="docs/tests/Test Results/00 - Inherited Code/Unit Test Results.html">Unit Test Results</a>
  * <a href="docs/tests/Test Results/00 - Inherited Code/Unit Test Explanation.pdf">Unit Test Explanation</a>
  * <a href="docs/tests/Test Results/00 - Inherited Code/System Test Results.pdf">System Test Results</a>

  </details>
  <details><summary>Fixed Inherited Code</summary>

  * <a href="docs/tests/Test Results/01 - Fixed Inherited Code/Unit Test Results.html">Unit Test Results</a>
  * <a href="docs/tests/Test Results/01 - Fixed Inherited Code/System Test Results.pdf">System Test Results</a>

  </details>
  <details><summary>Expanded Code</summary>

  * <a href="docs/tests/Test Results/02 - Expanded Code/Unit Test Results.html">Unit Test Results</a>
  * <a href="docs/tests/Test Results/02 - Expanded Code/Integration Test Results.html">Integration Test Results</a>
  * <a href="docs/tests/Test Results/02 - Expanded Code/System Test Results.pdf">System Test Results</a>
  * <a href="docs/tests/Test Results/02 - Expanded Code/Traceability Matrix.pdf">Traceability Matrix</a>

  </details>
  </div>
</details>

<details><summary>Coverage Details</summary>

* <a href="docs/tests/Coverage Details/Coverage Report/index.html">Coverage Report</a>
* <a href="docs/tests/Coverage Details/Coverage Explanation.pdf">Coverage Explanation</a>

</details>

</details>
