DELETE FROM ENTRENADORENTITY_USUARIOENTITY;
DELETE FROM MAQUINAENTITY_TIPOMEDIDAENTITY;
DELETE FROM EJERCICIOENTITY_MAQUINAENTITY;
DELETE FROM EJERCICIOENTITY_OBJETIVOENTITY;
DELETE FROM EJERCICIOENTITY_TIPOMEDIDAENTITY;
DELETE FROM USUARIOENTITY_OBJETIVOENTITY;
DELETE FROM GIMNASIOENTITY;

DELETE FROM EJERCICIOINSTANCIAENTITY;
ALTER TABLE EJERCICIOINSTANCIAENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM ATRIBUTODECALIDADENTITY;
ALTER TABLE ATRIBUTODECALIDADENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM EJERCICIOENTITY;
ALTER TABLE EJERCICIOENTITY ALTER COLUMN ID RESTART WITH 1;


DELETE FROM MEDIDAENTITY;
ALTER TABLE MEDIDAENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM ESTADOENTITY;
ALTER TABLE ESTADOENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM MAQUINAENTITY;
ALTER TABLE MAQUINAENTITY ALTER COLUMN ID RESTART WITH 1;


DELETE FROM OBJETIVOENTITY;
ALTER TABLE OBJETIVOENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM RUTINAENTITY;
ALTER TABLE RUTINAENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM TIPOMEDIDAENTITY;
ALTER TABLE TIPOMEDIDAENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM ENTRENADORENTITY;
ALTER TABLE ENTRENADORENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM USUARIOENTITY;
ALTER TABLE USUARIOENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM EJERCICIOHECHOENTITY;
ALTER TABLE EJERCICIOHECHOENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM MEDICIONMAQUINAENTITY;
ALTER TABLE MEDICIONMAQUINAENTITY ALTER COLUMN ID RESTART WITH 1;


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

INSERT INTO ESTADOENTITY (FECHA,USUARIO_ID) VALUES ('7/4/2017',1);
INSERT INTO ESTADOENTITY (FECHA,USUARIO_ID) VALUES ('2/2/2017',1);
INSERT INTO ESTADOENTITY (FECHA,USUARIO_ID) VALUES ('3/6/2017',1);
INSERT INTO ESTADOENTITY (FECHA,USUARIO_ID) VALUES ('7/4/2017',2);
INSERT INTO ESTADOENTITY (FECHA,USUARIO_ID) VALUES ('8/15/2017',2);

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

INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('TROTADORA');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('PRENSA DE PIERNAS');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('PARALELAS');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('RALLY WAGON 3500');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('ASCENDER');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('SKYHAWK');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('ODYSSEY');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('GRANTURISMO');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('TRAILBLAZER');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('E-350 SUPER DUTY');

INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (1,'11/18/2016','11/18/2017',1);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (2,'11/18/2016','11/18/2017',2);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (3,'11/18/2016','11/18/2017',3);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (4,'11/18/2016','11/18/2017',4);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (5,'11/18/2016','11/18/2017',5);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (6,'11/18/2016','11/18/2017',6);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (7,'11/18/2016','11/18/2017',7);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (8,'11/18/2016','11/18/2017',8);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (9,'11/18/2016','11/18/2017',9);
INSERT INTO RUTINAENTITY (ID,FECHAINICIO,FECHAFINAL,USUARIO_ID) VALUES (10,'11/18/2016','11/18/2017',10);

INSERT INTO EJERCICIOENTITY (DESCRIPCION,EXPLICACION,TIPO) VALUES ('ABDOMINALES','EMHH ABDOMINALES','AEROBICOS');
INSERT INTO EJERCICIOENTITY (DESCRIPCION,EXPLICACION,TIPO) VALUES ('TROTADORA','USAR LA TROTADORA','AEROBICOS');
INSERT INTO EJERCICIOENTITY (DESCRIPCION,EXPLICACION,TIPO) VALUES ('PRENSA DE PIERNAS','USAR LA PRENSA DE PIERNAS','AEROBICOS');

INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES (15,20,1,7,1,1);
INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES (25,0,1,7,2,1);
INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES(20,30,3,7,3,1);
INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES(15,15,1,7,1,2);
INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES (15,20,2,7,1,3);
INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES (15,30,1,7,1,4);
INSERT INTO EJERCICIOINSTANCIAENTITY (DURACION,SERIES,REPETICIONESPORPARTICION,TAMANIOPARTICIONES,EJERCICIO_ID,RUTINA_ID) VALUES (15,20,3,7,1,5);

INSERT INTO EJERCICIOENTITY_MAQUINAENTITY (EJERCICIOS_ID,MAQUINAS_ID) VALUES (2,1);
INSERT INTO EJERCICIOENTITY_MAQUINAENTITY (EJERCICIOS_ID,MAQUINAS_ID) VALUES (3,2);
INSERT INTO EJERCICIOENTITY_MAQUINAENTITY (EJERCICIOS_ID,MAQUINAS_ID) VALUES (1,1);
INSERT INTO EJERCICIOENTITY_MAQUINAENTITY (EJERCICIOS_ID,MAQUINAS_ID) VALUES (1,2);
INSERT INTO EJERCICIOENTITY_MAQUINAENTITY (EJERCICIOS_ID,MAQUINAS_ID) VALUES (1,3);
INSERT INTO EJERCICIOENTITY_MAQUINAENTITY (EJERCICIOS_ID,MAQUINAS_ID) VALUES (1,4);

INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('BRAZO','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('ABDOMEN','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PIERNAS','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PESO','KG',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('VELOCIDAD PROMEDIO','KM/S',1);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('CALORIAS QUEMADAS','CAL',1);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('KILOMETROS REOCRIDOS','KM',1);

INSERT INTO MAQUINAENTITY_TIPOMEDIDAENTITY (MAQUINAS_ID,TIPOMEDIDA_ID) VALUES (1,5);
INSERT INTO MAQUINAENTITY_TIPOMEDIDAENTITY (MAQUINAS_ID,TIPOMEDIDA_ID) VALUES (1,6);
INSERT INTO MAQUINAENTITY_TIPOMEDIDAENTITY (MAQUINAS_ID,TIPOMEDIDA_ID) VALUES (1,7);

INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (12,1,1);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (13,2,1);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (13,3,1);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (15,4,1);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (16,5,1);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (37,1,2);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (36,2,2);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (37,3,2);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (27,1,3);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (26,2,3);
INSERT INTO MEDIDAENTITY (MEDIDA,ESTADO_ID,PARTE_ID) VALUES (28,3,3);

INSERT INTO OBJETIVOENTITY (TIPO,DESCRIPCION) VALUES ('ADELGAZAR','es una reducción de la masa corporal de un individuo, por razón de una pérdida promedio de líquidos, grasa o de tejidos como el músculo, tendón o tejido conjuntivo.');
INSERT INTO OBJETIVOENTITY (TIPO,DESCRIPCION) VALUES ('AUMNETAR LA MASA MUSCULAR','Los doctores afirman que puedes aumentar tu masa muscular de manera notoria en unas seis semanas después de empezar una rutina de ejercicios regular.');
INSERT INTO OBJETIVOENTITY (TIPO,DESCRIPCION) VALUES ('GANAR RESISTENCIA','La resistencia es una cualidad psicofísica que nos permite prolongar un ejercicio o una actividad.');
INSERT INTO OBJETIVOENTITY (TIPO,DESCRIPCION) VALUES ('TONIFICAR','Tonificar es sinónimo de endurecer, fortalecer, sin necesariamente aumentar la masa muscular en forma exagerada. Se puede estar delgado, pero tonificado.');

INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,1);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,2);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,3);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (1,4);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (2,3);
INSERT INTO USUARIOENTITY_OBJETIVOENTITY (OBJETIVOS_ID,USUARIOS_ID) VALUES (2,5);

INSERT INTO EJERCICIOENTITY_OBJETIVOENTITY (EJERCICIOS_ID,OBJETIVOSEJERCICIO_ID) VALUES (1,1);
INSERT INTO EJERCICIOENTITY_OBJETIVOENTITY (EJERCICIOS_ID,OBJETIVOSEJERCICIO_ID) VALUES (2,1);
INSERT INTO EJERCICIOENTITY_OBJETIVOENTITY (EJERCICIOS_ID,OBJETIVOSEJERCICIO_ID) VALUES (2,3);
INSERT INTO EJERCICIOENTITY_OBJETIVOENTITY (EJERCICIOS_ID,OBJETIVOSEJERCICIO_ID) VALUES (3,1);
INSERT INTO EJERCICIOENTITY_OBJETIVOENTITY (EJERCICIOS_ID,OBJETIVOSEJERCICIO_ID) VALUES (3,2);
INSERT INTO EJERCICIOENTITY_OBJETIVOENTITY (EJERCICIOS_ID,OBJETIVOSEJERCICIO_ID) VALUES (3,3);

INSERT INTO EJERCICIOENTITY_TIPOMEDIDAENTITY (EJERCICIOS_ID,TIPOSMEDIDAS_ID) VALUES (1,1);
INSERT INTO EJERCICIOENTITY_TIPOMEDIDAENTITY (EJERCICIOS_ID,TIPOSMEDIDAS_ID) VALUES (1,2);

INSERT INTO ATRIBUTODECALIDADENTITY (REGRESION,OBJETIVO_ID,TIPOMEDIDA_ID)VALUES(1,1,1);
INSERT INTO ATRIBUTODECALIDADENTITY (REGRESION,OBJETIVO_ID,TIPOMEDIDA_ID)VALUES(1,1,2);

/*Robles*/
INSERT INTO EJERCICIOHECHOENTITY (ID,FECHA , SERIESREALES ,EJERCICIOINSTANCIAENTITY_ID) VALUES (1,'11/18/2016',10,1);
INSERT INTO EJERCICIOHECHOENTITY (ID,FECHA , SERIESREALES ,EJERCICIOINSTANCIAENTITY) VALUES (2,'11/19/2016',10,1);
INSERT INTO EJERCICIOHECHOENTITY (ID,FECHA , SERIESREALES ,EJERCICIOINSTANCIAENTITY_ID) VALUES (3,'11/20/2016',10,1);

INSERT INTO MEDICIONMAQUINAENTITY (ID,MEDICIONMAQUINA ,EJERCICIOHECHOENTITY_ID ) VALUES (1, 10,1);
INSERT INTO MEDICIONMAQUINAENTITY (ID,MEDICIONMAQUINA ,EJERCICIOHECHOENTITY_ID  ) VALUES (2,10,1);
INSERT INTO MEDICIONMAQUINAENTITY (ID,MEDICIONMAQUINA ,EJERCICIOHECHOENTITY_ID) VALUES (3,10, 1);
