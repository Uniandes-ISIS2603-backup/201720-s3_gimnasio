DELETE FROM ENTRENADORENTITY_USUARIOENTITY;
DELETE FROM MAQUINAENTITY_TIPOMEDIDAENTITY;
DELETE FROM EJERCICIOENTITY_MAQUINAENTITY;
DELETE FROM EJERCICIOENTITY_OBJETIVOENTITY;
DELETE FROM EJERCICIOENTITY_TIPOMEDIDAENTITY;
DELETE FROM USUARIOENTITY_OBJETIVOENTITY;
DELETE FROM MEDICIONMAQUINAENTITY;

DELETE FROM REGRECIONENTITY;
ALTER TABLE REGRECIONENTITY ALTER COLUMN ID RESTART WITH 1;

DELETE FROM EJERCICIOHECHOENTITY;
ALTER TABLE EJERCICIOHECHOENTITY ALTER COLUMN ID RESTART WITH 1;

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

INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('HOWARD', '3/28/1998', '100712578');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('BRENDON', '10/9/1978', '988785786');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('THEKLA', '11/17/1989', '1234789598');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('ORSA', '8/18/1997', '998856148');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('KEAN', '3/21/1980', '965789582');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('OBADIAH', '11/21/1993', '1282357442');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('ROYCE', '11/4/1978', '1257896358');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('BAX', '9/27/1958', '456850369');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('SILVA', '7/16/1999', '789581123');
INSERT INTO ENTRENADORENTITY (NOMBRE, FECHANACIMIENTO, DOCUMENTO) VALUES ('LESYA', '7/21/1989', '369785123');

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

INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('TROTADORA');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('PRENSA DE PIERNAS');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('PARALELAS');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('ASCENDER');
INSERT INTO MAQUINAENTITY (INFORMACION) VALUES ('BARRAS');

INSERT INTO EJERCICIOENTITY (DESCRIPCION,video,EXPLICACION,TIPO) 
VALUES ('Remo horizontal con mancuerna','n1Xb8ispJbs','Para empezar, hay que posicionarse, tomando una mancuerna con la mano izquierda o derecha, y apoyando la palma de la mano contraria, al igual que la rodilla sobre el banco. La espalda debe permanecer fija y el brazo con la mancuerna extendido hacia abajo, al costado del cuerpo.
Inspirar y elevar la mancuerna hasta la espalda, de forma que el codo quede por encima de la misma para imitar el movimiento de remo.
Regresar a la posición original en un movimiento controlado y lento, y espirar al final de recorrido.','AEROBICOS');
INSERT INTO APP.EJERCICIOENTITY (DESCRIPCION, EXPLICACION, TIPO, VIDEO) 
	VALUES ('Remo al cuello con polea baja', 'Uno de los ejercicios que vale la pena llevar a cabo en las rutinas de volumen muscular es el remo al cuello con polea baja, ya que ayuda a trabajar el tren superior, específicamente los músculos de los hombros.', 'ANEAROBICOS', 'SyX9QF76-c8');
INSERT INTO APP.EJERCICIOENTITY (DESCRIPCION, EXPLICACION, TIPO, VIDEO) 
	VALUES ('Encogimiento de hombros con mancuernas', 'Para su ejecución será necesario de un par de mancuernas de peso ajustado, según tus capacidades de levantamiento.', 'NO_PERTENECE', 'qlIv6nW5kHU');
INSERT INTO APP.EJERCICIOENTITY (DESCRIPCION, EXPLICACION, TIPO, VIDEO) 
	VALUES ('Mountain Climbers', 'un ejercicio que involucra los músculos de la cintura y que nos permiten elevar las piernas a la hora de saltar, golpear un balón o soltar una patada.', 'AEROBICOS', 'lvaQcFaxL00');
INSERT INTO APP.EJERCICIOENTITY (DESCRIPCION, EXPLICACION, TIPO, VIDEO) 
	VALUES ('Abdominales en barra', 'para hacerlo bien es necesario tener un perfecto control abdominal y una amplia concentración en la zona que estamos trabajando.', 'EQUILIBRIO', 'hCRrA-EAiBU');

INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('ESPALDA','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('ABDOMEN','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PECHO','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('ANTEBRAZO','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('HOMBROS','C;',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('GLUTEOS','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('BICEP','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('OBLICUOS','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('TRICEP','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('TRAPECIOS','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('TELDOiDES','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PIERNAS','CM',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('PESO','KG',0);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('VELOCIDAD PROMEDIO','KM/S',1);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('CALORIAS QUEMADAS','CAL',1);
INSERT INTO TIPOMEDIDAENTITY (TIPOMEDIDA,UNIDAD,AUTOMATICO) VALUES ('KILOMETROS REOCRIDOS','KM',1);

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

INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-01', 1);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-14', 1);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-01', 1);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-01', 3);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-16', 3);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-01', 2);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-05', 2);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-12', 2);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-13', 2);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-14', 2);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-15', 2);
INSERT INTO APP.ESTADOENTITY (FECHA, USUARIO_ID) 
	VALUES ('2017-11-16', 2);

INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (60.0, 1, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (35.0, 1, 6);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (50.0, 4, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (100.0, 6, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (40.0, 6, 2);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (110.0, 7, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (120.0, 8, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (108.0, 9, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (107.0, 10, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (106.0, 11, 13);
INSERT INTO APP.MEDIDAENTITY (MEDIDA, ESTADO_ID, PARTE_ID) 
	VALUES (107.0, 12, 13);


INSERT INTO APP.RUTINAENTITY (CUMPLIMIENTO, FECHAFINAL, FECHAINICIO, USUARIO_ID) 
	VALUES (166.11111111111111, '2016-07-12', '2016-05-12', 1);

INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (2, 1);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (3, 1);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (4, 1);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (5, 2);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (5, 3);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (6, 4);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (8, 4);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (9, 4);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (13, 2);
INSERT INTO APP.EJERCICIOENTITY_TIPOMEDIDAENTITY (TIPOSMEDIDAS_ID, EJERCICIOS_ID) 
	VALUES (15, 5);

INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (1, 1);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (1, 2);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (1, 5);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (2, 3);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (3, 2);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (3, 4);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (4, 4);
INSERT INTO APP.EJERCICIOENTITY_OBJETIVOENTITY (OBJETIVOSEJERCICIO_ID, EJERCICIOS_ID) 
	VALUES (4, 5);

INSERT INTO APP.EJERCICIOINSTANCIAENTITY (CUMPLIMIENTO, DURACION, EFECTIVIDAD, REPETICIONESPORPARTICION, SERIES, TAMANIOPARTICIONES, EJERCICIO_ID, RUTINA_ID) 
	VALUES (72.22222222222221, 20, 0.0, 2, 50, 7, 5, 1);
INSERT INTO APP.EJERCICIOINSTANCIAENTITY (CUMPLIMIENTO, DURACION, EFECTIVIDAD, REPETICIONESPORPARTICION, SERIES, TAMANIOPARTICIONES, EJERCICIO_ID, RUTINA_ID) 
	VALUES (260.0, 20, 0.0, 1, 15, 14, 4, 1);

INSERT INTO APP.REGRECIONENTITY (REGRESION, EJERCICIO_ID, TIPOMEDIDA_ID) 
	VALUES (0, 1, 15);
INSERT INTO APP.REGRECIONENTITY (REGRESION, EJERCICIO_ID, TIPOMEDIDA_ID) 
	VALUES (0, 2, 6);
INSERT INTO APP.REGRECIONENTITY (REGRESION, EJERCICIO_ID, TIPOMEDIDA_ID) 
	VALUES (0, 2, 8);
INSERT INTO APP.REGRECIONENTITY (REGRESION, EJERCICIO_ID, TIPOMEDIDA_ID) 
	VALUES (0, 2, 9);

INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-07-07', 50, 2);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-19', 50, 2);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-11', 50, 2);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-02', 50, 2);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-25', 50, 2);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-12', 50, 2);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-12', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-13', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-19', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-21', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-27', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-05-29', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-15', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-15', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-16', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-23', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-26', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-06-29', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-07-05', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-07-09', 50, 1);
INSERT INTO APP.EJERCICIOHECHOENTITY (FECHA, SERIESREALES, EJERCICIO_ID) 
	VALUES ('2016-07-11', 50, 1);


