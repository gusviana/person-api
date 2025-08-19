CREATE TABLE IF NOT EXISTS tb_person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    cpf VARCHAR(20),
    gender_enum INT,
    data_nascimento DATE
);

INSERT INTO tb_person (data_nascimento, gender_enum, cpf, name) VALUES
('1990-01-01', 1, '12345678901', 'Gustavo'),
('1992-02-02', 2, '23456789012', 'Ana'),
('1985-03-03', 1, '34567890123', 'Carlos'),
('1995-04-04', 2, '45678901234', 'Bianca'),
('1988-05-05', 1, '56789012345', 'Diego'),
('1991-06-06', 2, '67890123456', 'Fernanda'),
('1993-07-07', 1, '78901234567', 'Guilherme'),
('1994-08-08', 2, '89012345678', 'Helena'),
('1989-09-09', 1, '90123456789', 'Igor'),
('1996-10-10', 2, '01234567890', 'Juliana');

