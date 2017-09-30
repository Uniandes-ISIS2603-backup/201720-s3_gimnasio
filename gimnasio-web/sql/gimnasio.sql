delete from EntrenadorEntity_UsuarioEntity;
delete from MAQUINAENTITY_TIPOMEDIDAENTITY;
delete from EJERCICIOENTITY_MAQUINAENTITY;
delete from EJERCICIOENTITY_OBJETIVOENTITY;
delete from OBJETIVOENTITY_USUARIOENTITY;
delete from GimnasioEntity;
delete from AtributoDeCalidadEntity;
ALTER TABLE AtributoDeCalidadEntity ALTER COLUMN Id RESTART WITH 1;
delete from EjercicioEntity;
ALTER TABLE EjercicioEntity ALTER COLUMN Id RESTART WITH 1;
delete from EjercicioHechoEntity;
delete from EstadoEntity;
delete from MaquinaEntity;
delete from MedicionMAquinaEntity;
delete from MedidaEntity;
delete from ObjetivoEntity;
ALTER TABLE ObjetivoEntity ALTER COLUMN Id RESTART WITH 1;
delete from RutinaEntity;
delete from TipoMedidaEntity;
ALTER TABLE TipoMedidaEntity ALTER COLUMN Id RESTART WITH 1;
delete from EntrenadorEntity;
delete from UsuarioEntity;

insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (1, 'Howard', '3/28/2017', 'PT56 9444 5274 7597 0900 1170 8');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (2, 'Brendon', '10/9/2016', 'FR17 1250 1257 37ON Z1SY EEHQ V28');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (3, 'Thekla', '11/17/2016', 'LB12 4684 FRED PGBB AQ87 AHN1 ADED');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (4, 'Orsa', '8/18/2017', 'GE87 FQ92 1751 3216 0909 06');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (5, 'Kean', '3/21/2017', 'IL41 6474 9866 1901 2622 145');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (6, 'Obadiah', '11/21/2016', 'ME10 4134 5662 0098 6010 65');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (7, 'Royce', '11/4/2016', 'LT03 4132 4171 4081 2897');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (8, 'Bax', '9/27/2017', 'LT39 6567 4333 0132 7581');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (9, 'Silva', '7/16/2017', 'FI63 7905 7976 1739 26');
insert into EntrenadorEntity (id, name, fechaNacimiento, documento) values (10, 'Lesya', '7/21/2017', 'DK76 5822 3265 0158 51');

insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (1, 'Herc', '11/18/2016', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (2, 'Dolph', '10/21/2016', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (3, 'Nata', '12/22/2016', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (4, 'Sherrie', '8/15/2017', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (5, 'Nealy', '11/2/2016', 0);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (6, 'Rica', '12/6/2016', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (7, 'Gene', '3/6/2017', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (8, 'Jasun', '2/2/2017', 0);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (9, 'Dalston', '1/9/2017', 1);
insert into UsuarioEntity (id, nombre, fechaDeNacimiento, genero) values (10, 'Wolf', '7/4/2017', 0);

insert into MaquinaEntity (id, descripcion) values (1, 'Frontier');
insert into MaquinaEntity (id, descripcion) values (2, 'SC');
insert into MaquinaEntity (id, descripcion) values (3, 'Sidekick');
insert into MaquinaEntity (id, descripcion) values (4, 'Rally Wagon 3500');
insert into MaquinaEntity (id, descripcion) values (5, 'Ascender');
insert into MaquinaEntity (id, descripcion) values (6, 'Skyhawk');
insert into MaquinaEntity (id, descripcion) values (7, 'Odyssey');
insert into MaquinaEntity (id, descripcion) values (8, 'GranTurismo');
insert into MaquinaEntity (id, descripcion) values (9, 'TrailBlazer');
insert into MaquinaEntity (id, descripcion) values (10, 'E-350 Super Duty');

insert into RutinaEntity (id,fechainicio,fenchafinal,usuario_id) values (1,'11/18/2016','11/18/2017',1);

insert into TipoMedidaEntity (descripcion,unidad,automatico) values ('brazo','cm',0);
insert into TipoMedidaEntity (descripcion,unidad,automatico) values ('abdomen','cm',0);
insert into TipoMedidaEntity (descripcion,unidad,automatico) values ('piernas','cm',0);
insert into TipoMedidaEntity (descripcion,unidad,automatico) values ('peso','kg',0);
insert into TipoMedidaEntity (descripcion,unidad,automatico) values ('presionSanginea','mmHg',0);

insert into ObjetivoEntity (descripcion,tipo) values ('bajar el peso','pérdida de peso');
insert into ObjetivoEntity (descripcion,tipo) values ('aumnetar la musculatura','tonificacion');
insert into ObjetivoEntity (descripcion,tipo) values ('mejorar la velocidad','atletismo');

insert into AtributoDeCalidadEntity (regresion,objetivo_id,tipomedida_id)values(1,1,1);
insert into AtributoDeCalidadEntity (regresion,objetivo_id,tipomedida_id)values(1,1,2);
