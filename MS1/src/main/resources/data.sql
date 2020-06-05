DROP TABLE IF EXISTS conversion;
CREATE TABLE conversion ( Code  VARCHAR(10) PRIMARY KEY, Factor decimal(30,2) NOT NULL);  
INSERT INTO conversion (Code, Factor) VALUES
('USD', 60),
  ('EUR', 85),
  ('SFR', 78.5);