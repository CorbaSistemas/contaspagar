CREATE TABLE usuario_roles (
    usuario_id INT,
    role_id INT,
    PRIMARY KEY (usuario_id, role_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (role_id) REFERENCES Role(id)
);