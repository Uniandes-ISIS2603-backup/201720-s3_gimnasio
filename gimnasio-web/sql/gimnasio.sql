DELETE FROM ENTRENADORENTITY_USUARIOENTITY;
DELETE FROM MAQUINAENTITY_TIPOMEDIDAENTITY;
DELETE FROM EJERCICIOENTITY_MAQUINAENTITY;
DELETE FROM EJERCICIOENTITY_OBJETIVOENTITY;
DELETE FROM USUARIOENTITY_OBJETIVOENTITY;
DELETE FROM GIMNASIOENTITY;
DELETE FROM ATRIBUTODECALIDADENTITY;
ALTER TABLE ATRIBUTODECALIDADENTITY ALTER COLUMN ID RESTART WITH 1;
DELETE FROM EJERCICIOENTITY;
ALTER TABLE EJERCICIOENTITY ALTER COLUMN ID RESTART WITH 1;
DELETE FROM EJERCICIOHECHOENTITY;
DELETE FROM ESTADOENTITY;
DELETE FROM MAQUINAENTITY;
DELETE FROM MEDICIONMAQUINAENTITY;
DELETE FROM MEDIDAENTITY;
DELETE FROM OBJETIVOENTITY;
ALTER TABLE OBJETIVOENTITY ALTER COLUMN ID RESTART WITH 1;
DELETE FROM RUTINAENTITY;
DELETE FROM TIPOMEDIDAENTITY;
ALTER TABLE TIPOMEDIDAENTITY ALTER COLUMN ID RESTART WITH 1;
DELETE FROM ENTRENADORENTITY;
ALTER TABLE ENTRENADORENTITY ALTER COLUMN ID RESTART WITH 1;
DELETE FROM USUARIOENTITY;
ALTER TABLE USUARIOENTITY ALTER COLUMN ID RESTART WITH 1;

INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('HOWARD', '3/28/2017', 'PT56 9444 5274 7597 0900 1170 8');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('BRENDON', '10/9/2016', 'FR17 1250 1257 37ON Z1SY EEHQ V28');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('THEKLA', '11/17/2016', 'LB12 4684 FRED PGBB AQ87 AHN1 ADED');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('ORSA', '8/18/2017', 'GE87 FQ92 1751 3216 0909 06');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('KEAN', '3/21/2017', 'IL41 6474 9866 1901 2622 145');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('OBADIAH', '11/21/2016', 'ME10 4134 5662 0098 6010 65');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('ROYCE', '11/4/2016', 'LT03 4132 4171 4081 2897');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('BAX', '9/27/2017', 'LT39 6567 4333 0132 7581');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('SILVA', '7/16/2017', 'FI63 7905 7976 1739 26');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('LESYA', '7/21/2017', 'DK76 5822 3265 0158 51');

INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('HERC', '11/18/2016', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('DOLPH', '10/21/2016', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('NATA', '12/22/2016', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('SHERRIE', '8/15/2017', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('NEALY', '11/2/2016', 0);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('RICA', '12/6/2016', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('GENE', '3/6/2017', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('JASUN', '2/2/2017', 0);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('DALSTON', '1/9/2017', 1);
INSERT INTO USUARIOENTITY (NOMBRE, FECHADENACIMIENTO, GENERO) VALUES ('WOLF', '7/4/2017', 0);

INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (1,1);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (1,2);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (1,3);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (1,4);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (2,2);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (3,3);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (4,4);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (5,5);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (6,6);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (7,7);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (2,4);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (2,7);
INSERT INTO ENTRENADORENTITY_USUARIOENTITY (ENTRENADORES_ID,USUARIOS_ID) VALUES (2,5);


INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (1, 'FRONTIER');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (2, 'SC');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (3, 'SIDEKICK');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (4, 'RALLY WAGON 3500');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (5, 'ASCENDER');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (6, 'SKYHAWK');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (7, 'ODYSSEY');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (8, 'GRANTURISMO');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (9, 'TRAILBLAZER');
INSERT INTO MAQUINAENTITY (ID, INFORMACION) VALUES (10, 'E-350 SUPER DUTY');

INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (1,'11/18/2016','11/18/2017',1);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (2,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (3,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (4,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (5,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (6,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (7,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (8,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (9,'11/18/2016','11/18/2017');
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL) VALUES (10,'11/18/2016','11/18/2017');

INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('BRAZO','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('ABDOMEN','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PIERNAS','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PESO','KG',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PRESIONSANGINEA','MMHG',0);

INSERT INTO OBJETIVOENTITY (DESCRIPCION,TIPO) VALUES ('BAJAR EL PESO','PÉRDIDA DE PESO');
INSERT INTO OBJETIVOENTITY (DESCRIPCION,TIPO) VALUES ('AUMNETAR LA MUSCULATURA','TONIFICACION');
INSERT INTO OBJETIVOENTITY (DESCRIPCION,TIPO) VALUES ('MEJORAR LA VELOCIDAD','ATLETISMO');

INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,1);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,2);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,3);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,4);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (2,3);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (2,5);

INSERT INTO ATRIBUTODECALIDADENTITY (REGRESION,OBJETIVO_ID,TIPOMEDIDA_ID)VALUES(1,1,1);
INSERT INTO ATRIBUTODECALIDADENTITY (REGRESION,OBJETIVO_ID,TIPOMEDIDA_ID)VALUES(1,1,2);
