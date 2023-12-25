DROP table grad;
DROP table drzava;

CREATE TABLE drzava(id INTEGER,
					naziv TEXT(50),
					glavni_grad INTEGER,
					CONSTRAINT c_pk_drzava PRIMARY KEY(id),
					CONSTRAINT c_fk_drz_gg FOREIGN KEY(glavni_grad) REFERENCES grad(id));
				
INSERT INTO drzava(naziv) values("Francuska");
INSERT INTO drzava(naziv) values("Velika Britanija");
INSERT INTO drzava(naziv) values("Austrija");

CREATE TABLE grad (	id INTEGER,
					naziv TEXT(50),
					broj_stanovnika INTEGER,
					drzava INTEGER,
					CONSTRAINT c_pk_grad PRIMARY KEY(id),
					constraint c_fk_grad_drz FOREIGN KEY(drzava) REFERENCES drzava(id));
				
INSERT INTO grad(naziv, broj_stanovnika, drzava) VALUES('Pariz', 2161000, 1);
INSERT INTO grad(naziv, broj_stanovnika, drzava) VALUES('London', 8982000, 2);
INSERT INTO grad(naziv, broj_stanovnika, drzava) VALUES('Bec', 1897000 ,3);
INSERT INTO grad(naziv, broj_stanovnika, drzava) VALUES('Manchester', 441200, 2);
INSERT INTO grad(naziv, broj_stanovnika, drzava) VALUES('Graz', 283869, 3);

UPDATE drzava SET glavni_grad=id;

