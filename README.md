# README #

Organizer for a developer.

## Reason for it ##

This project is for main routine of a developer.

## Context

### Documentation
Any images, text documents and diagrams are situated in the folder /docs

### Infrastructure
Any vagrant, ansible, sh/cmd scripts will be situated in the folder /infrastructure

### Data Base
All entity, repository classes and classes, connected to the data base layer are situated in the it-organizer-db module.

#### Data Base scheme
![Data Base scheme](https://github.com/aleksei-khitev/it-organizer/blob/ui_based_on_spring_mvc/docs/db_diagram.jpeg)

### Business Logic
Data transfer objects, converters, services are situated in the it-organizer-business-logic module.
You may read more on ![this wiki page](https://github.com/aleksei-khitev/it-organizer/wiki/it-organizer-business-logic)

### Spring MVC
All controllers, view files, css, js and other files, connected to the web based user interface layer are situated in the it-organizer-web module
There is no logic. Only controller, what use servicies from business logic submodule.

#### Aspects
For now there is only one aspect, made for forming navigation bar on every controller operation with model.

#### User interface
For implementing UI thymleaf is used.
Text editors are transformed to rich editors by nicEdit java script library