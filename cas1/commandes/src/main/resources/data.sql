DROP TABLE IF EXISTS Commande;

CREATE TABLE Commande (
          id INT PRIMARY KEY,
          description VARCHAR(255) NOT NULL,
          quantity INT NOT NULL,
          date_ VARCHAR(255) NOT NULL,
          amount INT NOT NULL
);