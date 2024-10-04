
CREATE TABLE IF NOT EXISTS recipe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    preparation_method TEXT,
    image_url VARCHAR(255)
    );


-- INSERT INTO recipe (title, description, preparation_method, image_url) VALUES ('Spaghetti Bolognese', 'Delicious pasta with meat sauce', 'Boil pasta and prepare meat sauce', 'spaghetti.jpg');
-- INSERT INTO recipe (title, description, preparation_method, image_url) VALUES ('Tacos', 'Mexican dish with beef, cheese, and vegetables', 'Prepare beef and assemble tacos', 'tacos.jpg');
-- INSERT INTO recipe (title, description, preparation_method, image_url) VALUES ('Pancakes', 'Fluffy pancakes with maple syrup', 'Mix ingredients and cook pancakes', 'pancakes.jpg');
