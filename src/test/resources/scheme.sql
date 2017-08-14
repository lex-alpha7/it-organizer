CREATE TABLE Project (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Reference_Link (
    id INTEGER NOT NULL PRIMARY KEY,
    project_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    link VARCHAR(100) NOT NULL,
    FOREIGN KEY(project_id) REFERENCES Project(id)
);

CREATE TABLE Note (
    id INTEGER NOT NULL PRIMARY KEY,
    project_id INTEGER NOT NULL,
    title VARCHAR(100) NOT NULL,
    note VARCHAR(100) NOT NULL,
    FOREIGN KEY(project_id) REFERENCES Project(id)
);

CREATE TABLE Ticket (
    id INTEGER NOT NULL PRIMARY KEY,
    project_id INTEGER NOT NULL,
    ticket_key VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    workspace VARCHAR(100),
    status VARCHAR(100) NOT NULL,
    FOREIGN KEY(project_id) REFERENCES Project(id)
);

CREATE TABLE Ticket_Link (
    id INTEGER NOT NULL PRIMARY KEY,
    ticket_id INTEGER NOT NULL,
    type INTEGER NOT NULL,
    name VARCHAR(100),
    link VARCHAR(100) NOT NULL,
    FOREIGN KEY(ticket_id) REFERENCES Ticket(id)
);

CREATE TABLE Progress (
    id INTEGER NOT NULL PRIMARY KEY,
    ticket_id INTEGER NOT NULL,
    status_date date,
    status VARCHAR(100) NOT NULL,
    FOREIGN KEY(ticket_id) REFERENCES Ticket(id)
);