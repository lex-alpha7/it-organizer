# IT Organizer #

Organizer for a IT specialists.

## Reason for it ##

This project is for main routine of a developer.

### Ticket
While you work on your task, you need a place, where you could write your findings, paste log fragments or code fragments. Ideally, if code formatting is kept.
Using the organizer, you have such a place.
You may save steps for reproduce, links to an issue tracking system and your workspace (rich editor, where you might keep you current findings).

### Place for your notes, connected with a project
With this application you don't need to place your notes in some txt/doc/other files and save them somewhere.
You can make a note in this Organizer and you don't loose it.

### Links, connected with a project
I believe it's more convenient to create an external link in this Organizer but not in browser bookmark.
It will keep an order in your browser bookmarks.

#### For now, it looks like this:
![Demo screen shot](https://github.com/aleksei-khitev/it-organizer/blob/ui_based_on_spring_mvc/docs/demo_screenshot.JPG)

## Short Description

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