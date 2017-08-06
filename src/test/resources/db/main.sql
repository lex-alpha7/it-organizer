INSERT INTO Project(id, name) VALUES (0,'pro1');
INSERT INTO Reference(id) VALUES (0);
INSERT INTO Reference_Link(id, reference_id, name, link) VALUES (0, 0, 'wiki', 'http://www.confluence.com');
INSERT INTO Reference_Link(id, reference_id, name, link) VALUES (1, 0, 'jira', 'http://www.jira.com');
INSERT INTO Note(id, reference_id, title, note) VALUES (0, 0, 'Environment set up', 'You need to install an plugin.');
INSERT INTO Note(id, reference_id, title, note) VALUES (1, 0, 'Deployment', 'Deploy in the Oracle VM.');
INSERT INTO Note(id, reference_id, title, note) VALUES (2, 0, 'Stand Up', 'You need to prepare a report every morning.');
INSERT INTO Project(id, name) VALUES (1,'pro2');