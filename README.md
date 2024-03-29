# Games Management App
This is a project work for the course title: 
SOFTWARE REQUIREMENTS AND MODELLING on Master studies submitted to the 
Department of Software Engineering and Computer Science, 
Faculty of Organisational Sciences, UNIVERSITY OF BELGRADE, SERBIA. 
It is my first project experience fully implemented as a 
DESKTOP application created with java 11, javaFX technology and MySQL Database


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Detailed informations](#detailed-description-of-the-application)

## General info
Games Management System is an application in which every user has their own game libraries.
He can add games to it, in which he played or wants to play.
The user adding the game to the library can evaluate it and write a comment.

This application is available in three languages:
* English
* Serbian
* German


## Technologies
Project is created with:
* Java 11
* JavaFX technology
* MySQL 8.0.29 database 
* OrmLite 6.1


## Detailed description of the application

#DATABASE - CONNECTION PARAMETERS

I use MySQL, for data storage and manipulation,

I have created a database "GamesDB", and you can find the connection in the "DBManager class"

String url = "jdbc:mySQL://localhost:3306/GamesDB";

String user = "root";

String password = "123456@/";

I used mySQL WorkBench 8.0 CE to run a mySQL Server on my localhost.

#STARTING THE APPLICATION:

To start the Games Management application, right-click on the Application Class and then select the
Run 'Application.Main()' and the Main Menu Board will be loaded on your desktop window. You can find the 
appearance of the  main Menu and registration panel below.
During registration, the new user can choose the language in which the application will be displayed.

![](Menu.png)

![](signup.png)


After logging in, the system displayed the main page where the new user can have access to 
rate game page, add category, add new game, view, edit and delete games from list of games 
added to the library, play a game which currently is the minesweeper.

![](mainPage.png)



LICENSE

COPYRIGHT © 2022 ORBANJAMES.

