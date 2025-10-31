-- Insert topics
INSERT INTO themes (name, description) VALUES
('JAVA', 'Términos relacionados con Java'),
('SPRING', 'Términos del framework Spring'),
('DEVOPS', 'Términos de DevOps y CI/CD'),
('DATABASE', 'Términos de bases de datos');

-- Insert words for JAVA (theme_id = 1)
INSERT INTO words (word, theme_id) VALUES
('CLASS', 1), ('OBJECT', 1), ('METHOD', 1), 
('INTERFACE', 1), ('ABSTRACT', 1), ('STATIC', 1),
('FINAL', 1), ('EXTENDS', 1), ('IMPLEMENTS', 1),
('PACKAGE', 1);

-- Insert words for SPRING (theme_id = 2)
INSERT INTO words (word, theme_id) VALUES
('BEAN', 2), ('AUTOWIRED', 2), ('COMPONENT', 2),
('SERVICE', 2), ('REPOSITORY', 2), ('CONTROLLER', 2),
('RESTFUL', 2), ('INJECTION', 2), ('CONTEXT', 2);

-- Insert words for DEVOPS (theme_id = 3)
INSERT INTO words (word, theme_id) VALUES
('DOCKER', 3), ('JENKINS', 3), ('PIPELINE', 3),
('DEPLOY', 3), ('BUILD', 3), ('TESTING', 3),
('RELEASE', 3), ('CONTAINER', 3);

-- Insert words for DATABASE (theme_id = 4)
INSERT INTO words (word, theme_id) VALUES
('SELECT', 4), ('INSERT', 4), ('UPDATE', 4),
('DELETE', 4), ('TABLE', 4), ('INDEX', 4),
('QUERY', 4), ('JOIN', 4), ('TRANSACTION', 4);