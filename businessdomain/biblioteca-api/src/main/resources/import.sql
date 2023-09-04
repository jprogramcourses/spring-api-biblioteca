insert into autores (id, nombre, apellidos, created_at, last_modified, nacionalidad) values (1, 'Tom', 'Sharpe', now(), now(), 'Inglaterra');
insert into autores (id, nombre, apellidos, created_at, last_modified, nacionalidad) values (2, 'Eduardo', 'Mendoza', now(), now(), 'España');
insert into autores (id, nombre, apellidos, created_at, last_modified, nacionalidad) values (3, 'Isaac', 'Asimov', now(), now(), 'Estados Unidos');
insert into autores (id, nombre, apellidos, created_at, last_modified, nacionalidad) values (4, 'Isabel', 'Allende', now(), now(), 'Chile');

insert into libros (id, titulo, genero, anho_edicion, created_at, last_modified, paginas) values (1, 'Sin noticias de Gurb', 'NOVELA', '1990', now(), now(), 180);
insert into libros (id, titulo, genero, anho_edicion, created_at, last_modified, paginas) values (2, 'Wildt', 'NOVELA', '1975', now(), now(), 223);
insert into libros (id, titulo, genero, anho_edicion, created_at, last_modified, paginas) values (3, 'Fundación', 'CIENCIA_FICCION', '1953', now(), now(), 263);
insert into libros (id, titulo, genero, anho_edicion, created_at, last_modified, paginas) values (4, 'Fundación e Imperio', 'CIENCIA_FICCION', '1957', now(), now(), 276);
insert into libros (id, titulo, genero, anho_edicion, created_at, last_modified, paginas) values (5, 'Eva Luna', 'NOVELA', '1986', now(), now(), 221);

insert into autor_libro (autor_id, libro_id) values (1, 2);