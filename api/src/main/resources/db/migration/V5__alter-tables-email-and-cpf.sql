ALTER TABLE pacientes
MODIFY COLUMN email varchar(100) not null unique,
MODIFY COLUMN cpf varchar(14) not null unique;