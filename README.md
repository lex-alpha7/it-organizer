# README #

Organizer for a developer.

## Reason for it ##

This project is for main routine of a developer.

For now, it looks like this:
![Demo screen shot](https://github.com/aleksei-khitev/it-organizer/blob/ui_based_on_spring_mvc/docs/demo_screenshot.JPG)

## Context

### User interface
For implementing UI [Thymeleaf](https://www.thymeleaf.org/), Spring MVC via Spring Boot and JS libraries like [nicEdit](http://nicedit.com/) are used.
More details on ![this wiki page](https://github.com/aleksei-khitev/it-organizer/wiki/it-organizer-web)

### Business Logic
Data transfer objects, converters, services are situated in the it-organizer-business-logic module.
You may read more on ![this wiki page](https://github.com/aleksei-khitev/it-organizer/wiki/it-organizer-business-logic)

### Data Base
All entity, repository classes and classes, connected to the data base layer are situated in the it-organizer-db module.
More details on ![this wiki page](https://github.com/aleksei-khitev/it-organizer/wiki/it-organizer-db)

#### Data Base scheme
![Data Base scheme](https://github.com/aleksei-khitev/it-organizer/blob/ui_based_on_spring_mvc/docs/db_diagram.jpeg)

### Documentation
Any images, text documents and diagrams are situated in the folder /docs

### Infrastructure
In the folder /infrastructure will be add docker or vagrant script in Future.