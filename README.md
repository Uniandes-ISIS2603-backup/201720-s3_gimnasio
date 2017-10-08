
# Tabla de contenidos
 - API
    - [\[Recurso Gimnasio\]](#recurso-gimnasio)
       - [GET /gimnasio](#get-gimnasio)
       - [PUT /gimnasio](#put-gimnasio)
       - [\[Recurso Maquina\]](#recurso-maquina)
          - [GET /maquinas](#get-maquinas)
          - [GET /maquinas/{id}](#get-maquinasid)
          - [POST /maquinas](#post-maquinas)
          - [PUT /maquinas/{id}](#put-maquinasid)
          - [DELETE /maquinas/{id}](#delete-maquinasid)
          - [\[Recurso TipoMedida\]](#recurso-tipomedida)
             - [GET /maquinas/{id_maquina}/tiposmedidas](#get-tiposmedidas)
             - [GET /maquinas/{id_maquina}/tiposmedidas/{id}](#get-tiposmedidasid)
             - [POST /maquinas/{id_maquina}/tiposmedidas](#post-tiposmedidas)
             - [PUT /maquinas/{id_maquina}/tiposmedidas/{id}](#put-tiposmedidasid)
             - [DELETE /maquinas/{id_maquina}/tiposmedidas/{id}](#delete-tiposmedidasid)
          - [\[Recurso MedidaUsuario\]](#recurso-medidausuario)
             - [GET /maquinas/{id_maquina}/medidasusuarios](#get-medidasusuarios)
             - [GET /maquinas/{id_maquina}/medidasusuarios/{id}](#get-medidasusuariosid)
             - [POST /maquinas/{id_maquina}/medidasusuarios](#post-medidasusuarios)
             - [PUT /maquinas/{id_maquina}/medidasusuarios/{id}](#put-medidasusuariosid)
             - [DELETE /maquinas/{id_maquina}/medidasusuarios/{id}](#delete-medidasusuariosid)
             - [\[Recurso Medicion\]](#recurso-medicion)
                - [GET /maquinas/{id_maquina}/medidasusuarios/{id_medidausuario}/mediciones](#get-mediciones)
                - [GET /maquinas/{id_maquina}/medidasusuarios/{id_medidausuario}/mediciones/{id}](#get-medicionesid)
                - [POST /maquinas/{id_maquina}/medidasusuarios/{id_medidausuario}/mediciones](#post-mediciones)
                - [PUT /maquinas/{id_maquina}/medidasusuarios/{id_medidausuario}/mediciones/{id}](#put-medicionesid)
                - [DELETE /maquinas/{id_maquina}/medidasusuarios/{id_medidausuario}/mediciones/{id}](#delete-medicionesid)
       - [\[Recurso Entrenador\]](#recurso-entrenador)
          - [GET /gimnasio/entrenadores](#get-entrenadores)
          - [GET /gimnasio/entrenadores/{id}](#get-entrenadoresid)
          - [POST /gimnasio/entrenadores](#post-entrenadores)
          - [PUT /gimnasio/entrenadores/{id}](#put-entrenadoresid)
          - [DELETE /gimnasio/entrenadores/{id}](#delete-entrenadoresid)
          - [\[Recurso Usuario\]](#recurso-usuario)
             - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios](#get-usuarios)
             - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id}](#get-usuariosid)
             - [POST /gimnasio/entrenadores/{id_entrenador}/usuarios](#post-usuarios)
             - [PUT /gimnasio/entrenadores/{id_entrenador}/usuarios/{id}](#put-usuariosid)
             - [DELETE /gimnasio/entrenadores/{id_entrenador}/usuarios/{id}](#delete-usuariosid)
             - [\[Recurso objetivos\]](#recurso-objetivo)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/objetivos](#get-objetivos)
                - [GET/gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/objetivos/{id}](#get-objetivosid)
             - [\[Recurso jornada\]](#recurso-jornada)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas](#get-jornadas)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/{id}](#get-jornadasid)
                - [\[Recurso ejercicioHecho\]](#recurso-ejerciciohecho)
                   - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/
                     {id_jornada}/ejercicioshechos](#get-ejercicioshechos)
                   - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/
                     {id_jornada}/ejercicioshechos/{id}](#get-ejercicioshechosid)
                    - [\[Recurso Ejercicio\]](#recurso-ejercicio)
                      - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/
                        {id_jornada}/ejercicioshechos/{id_ejercicioHecho}/ejercicio](#get-ejercicio)
                      - [\[Recurso Maquina\]](#recurso-maquina)
                         - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/
                           {id_jornada}/ejercicioshechos/{id_ejercicioHecho}/maquinas](#get-maquinas)
                         - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/
                           {id_jornada}/ejercicioshechos/{id_ejercicioHecho}/maquinas/{id}](#get-maquinasid)
                         - [\[Recurso MedidaUsuario\]](#recurso-medidausuario)
                            - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/jornadas/
                              {id_jornada}/ejercicioshechos/{id_ejercicioHecho}/maquinas/
                              {id_maquina}/medidasusuarios/mismedidas](#get-medidasusuariosmismedidas)
             - [\[Recurso Estado\]](#recurso-estado)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/estados](#get-estados)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/estados/{id}](#get-estadosid)
                - [\[Recurso Medida\]](#recurso-medida)
                   - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/estados/
                     {id_estado}/medidas](#get-medidas)
                   - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/estados/
                     {id_estado}/medidas/{id}](#get-medidasid)
             - [\[Recurso Rutina\]](#recurso-rutina)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas](#get-rutinas)
                - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/{id}](#get-rutinasid)
                - [POST /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas](#post-rutinas)
                - [PUT /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/{id}](#put-rutinasid)
                - [DELETE /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/{id}](#delete-rutinasid)
                - [\[Recurso EjercicioAsignado\]](#recurso-ejercicioasignado)
                   - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                     {id_rutina}/ejerciciosasignados](#get-ejerciciosasignados)
                   - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                     {id_rutina}/ejerciciosasignados/{id}](#get-ejerciciosasignadosid)
                   - [POST /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                     {id_rutina}/ejerciciosasignados](#post-ejerciciosasignados)
                   - [PUT /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                     {id_rutina}/ejerciciosasignados/{id}](#put-ejerciciosasignadosid)
                   - [DELETE /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                     {id_rutina}/ejerciciosasignados/{id}](#delete-ejerciciosasignadosid)
                    - [\[Recurso Ejercicio\]](#recurso-ejercicio)
                      - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                        {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio](#get-ejercicio)
                      - [POST /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                        {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio](#post-ejercicio)
                      - [PUT /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                        {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio](#put-ejercicio)
                      - [DELETE /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                        {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio](#delete-ejercicio)
                      - [\[Recurso Maquina\]](#recurso-maquina)
                         - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                           {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio/maquinas](#get-maquinas)
                         - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                           {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio/maquinas/{id}](#get-maquinasid)
                         - [POST /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                           {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio/maquinas](#post-maquinas)
                         - [PUT /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                           {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio/maquinas/{id}](#put-maquinasid)
                         - [DELETE /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/rutinas/
                           {id_rutina}/ejerciciosasignados/{id_ejercicioAsignado}/ejercicio/maquinas/{id}](#delete-maquinasid)
       - [\[Recurso Usuario\]](#recurso-usuario)
          - [GET /gimnasio/usuarios](#get-usuarios)
          - [GET /gimnasio/usuarios/{id}](#get-usuariosid)
          - [POST /gimnasio/usuarios](#post-usuarios)
          - [PUT /gimnasio/usuarios/{id}](#put-usuariosid)
          - [DELETE /gimnasio/usuarios/{id}](#delete-usuariosid)
          - [\[Recurso objetivos\]](#recurso-objetivo)
             - [GET /gimnasio/usuarios/{id_usuario}/objetivos](#get-objetivos)
             - [GET /gimnasio/usuarios/{id_usuario}/objetivos/{id}](#get-objetivosid)
             - [POST /gimnasio/usuarios/{id_usuario}/objetivos](#post-objetivos)
             - [PUT /gimnasio/usuarios/{id_usuario}/objetivos/{id}](#put-objetivosid)
             - [DELETE /gimnasio/usuarios/{id_usuario}/objetivos/{id}](#delete-objetivosid)
          - [\[Recurso jornada\]](#recurso-jornada)
             - [GET /gimnasio/usuarios/{id_usuario}/jornadas](#get-jornadas)
             - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id}](#get-jornadasid)
             - [POST /gimnasio/usuarios/{id_usuario}/jornadas](#post-jornadas)
             - [PUT /gimnasio/usuarios/{id_usuario}/jornadas/{id}](#put-jornadasid)
             - [DELETE /gimnasio/usuarios/{id_usuario}/jornadas/{id}](#delete-jornadasid)
             - [\[Recurso ejercicioHecho\]](#recurso-ejerciciohecho)
                - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos](#get-ejercicioshechos)
                - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/{id}](#get-ejercicioshechosid)
                - [POST /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos](#post-ejercicioshechos)
                - [PUT /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/{id}](#put-ejercicioshechosid)
                - [DELETE /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/{id}](#delete-ejercicioshechosid)
                - [\[Recurso Ejercicio\]](#recurso-ejercicio)
                   - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/
                     {id_ejercicioHecho}/ejercicio](#get-ejercicio)
                   - [POST /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/
                     {id_ejercicioHecho}/ejercicio](#post-ejercicio)
                   - [PUT /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/
                     {id_ejercicioHecho}/ejercicio](#put-ejercicio)
                   - [DELETE /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/
                     {id_ejercicioHecho}/ejercicio](#delete-ejercicio)
                   - [\[Recurso Maquina\]](#recurso-maquina)
                      - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/
                        ejerciciosHechos/{id_ejercicioHecho}/ejercicio/maquinas](#get-maquinas)
                      - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/
                         ejerciciosHechos/{id_ejercicioHecho}/ejercicio/maquinas/{id}](#get-maquinasid)
                      - [\[Recurso MedidaUsuario\]](#recurso-medidausuario)
                         - [GET /gimnasio/usuarios/{id_usuario}/jornadas/{id_jornada}/ejerciciosHechos/
                           {id_ejercicioHecho}/ejercicio/maquinas/{id_maquina}/medidasusuarios/mismedidas](#get-medidasusuariosmismedidas)
          - [\[Recurso Estado\]](#recurso-estado)
             - [GET /gimnasio/usuarios/{id_usuario}/estados](#get-estados)
             - [GET /gimnasio/usuarios/{id_usuario}/estados/{id}](#get-estadosid)
             - [POST /gimnasio/usuarios/{id_usuario}/estados](#post-estados)
             - [PUT /gimnasio/usuarios/{id_usuario}/estados/{id}](#put-estadosid)
             - [DELETE /gimnasio/usuarios/{id_usuario}/estados/{id}](#delete-estadosid)
             - [\[Recurso Medida\]](#recurso-medida)
                - [GET /gimnasio/usuarios/{id_usuario}/estados/{id_estado}/medidas](#get-medidas)
                - [GET /gimnasio/usuarios/{id_usuario}/estados/{id_estado}/medidas/{id}](#get-medidasid)
                - [POST /gimnasio/usuarios/{id_usuario}/estados/{id_estado}/medidas](#post-medidas)
                - [PUT /gimnasio/usuarios/{id_usuario}/estados/{id_estado}/medidas/{id}](#put-medidasid)
                - [DELETE /gimnasio/usuarios/{id_usuario}/estados/{id_estado}/medidas/{id}](#delete-medidasid)
          - [\[Recurso Rutina\]](#recurso-rutina)
             - [GET /gimnasio/usuarios/{id_usuario}/rutinas](#get-rutinas)
             - [GET /gimnasio/usuarios/{id_usuario}/rutinas/{id}](#get-rutinasid)
             - [DELETE /gimnasio/usuarios/{id_usuario}/rutinas/{id}](#delete-rutinasid)
             - [\[Recurso /gimnasio/usuarios/{id_usuario}/rutinas/{id_rutina}/EjercicioAsignado\]](#recurso-ejercicioasignado)
                - [GET /gimnasio/usuarios/{id_usuario}/rutinas/{id_rutina}/ejerciciosAsignados](#get-ejerciciosasignados)
                - [GET /gimnasio/usuarios/{id_usuario}/rutinas/{id_rutina}/ejerciciosAsignados/{id}](#get-ejerciciosasignadosid)
                - [\[Recurso Ejercicio\]](#recurso-ejercicio)
                   - [GET /gimnasio/usuarios/{id_usuario}/rutinas/{id_rutina}/
                     ejerciciosAsignados/{id_ejercicioAsignado}/ejercicio](#get-ejercicio)
                   - [\[Recurso Maquina\]](#recurso-maquina)
                      - [GET /gimnasio/usuarios/{id_usuario}/rutinas/{id_rutina}/
                        ejerciciosAsignados/{id_ejercicioAsignado}/ejercicio/maquinas](#get-maquinas)
                      - [GET /gimnasio/usuarios/{id_usuario}/rutinas/{id_rutina}/
                        ejerciciosAsignados/{id_ejercicioAsignado}/ejercicio/maquinas/{id}](#get-maquinasid)
*** 
# Recurso Gimnasio
Entidad principal que define el todo el gimnasio	

## Representación Básica
```javascript
{
   nombre : /tipoString/,
   duenio : /tipoString/,
   nit : /tipoLong/
}
```
## Representación Detallada
```javascript
{
   nombre : /tipoString/,
   duenio : /tipoString/,
   nit : /tipoLong/
   clientes: [{ /Representación de usuario en JSON minimum / }
   .
   .
   ],
   entrenadores:[
   {/Representación de entrenador en JSON minimum /}
   .
   .
   ],
   Maquinas:[
   {/Representación de maquina en JSON minimum/}
   ]
}
```
***
### GET /gimnasio
Retorna una colección de objetos Gimnasio en representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detallada](#recurso-gimnasio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
### PUT /gimnasio
Es el encargado de actualizar objeto Gimnasio
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Gimnasio nuevo|Sí|[Representación basica](#recurso-gimnasio)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Gimnasio actualizado|[Representación Detallada](#recurso-gimnasio)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Gimnasio|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Entrenador
Los entrenadores que se encuentran en el Entrenador

## Representación Básica
```javascript
{
  nombre : /tipoString/,
  FechaNacimiento: /tipoString/,
  cedula : /tipoLong/
}
```
## Representación Detallada
```javascript
{
  nombre : /tipoString/,
  FechaNacimiento: /tipoString/,
  cedula : /tipoLong/
  usuarios: [{ /Representación de usuario 1 en JSON minimum / }
  .
  .
  ]
}
```
***
### GET /entrenadores
Retorna una colección de objetos Entrenador en representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-entrenador)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /entrenadores/{id}
Retorna una colección de objetos Entrenador en representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Entrenador a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Entrenador en [representaciones Detalladas](#recurso-entrenador)
404|No existe un objeto Entrenador con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /entrenadores
Es el encargado de crear objetos Entrenador.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Entrenador que será creado|Sí|[representación Detallada](#recurso-entrenador)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Entrenador ha sido creado|[representación Detallada](#recurso-entrenador)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Entrenador|Mensaje de error
***
### PUT /entrenadores/{id}
Es el encargado de actualizar objeto Entrenador
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Entrenador a actualizar|Sí|Integer
body|body|objeto Entrenador nuevo|Sí|[representación Detallada](#recurso-entrenador)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Entrenador actualizado|[representación Detallada](#recurso-entrenador)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Entrenador|Mensaje de error
***
### DELETE /entrenadores/{id}
Elimina un objeto Entrenador.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Entrenador a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Usuario
Los usuarios del gimnasio

## Representación Básica
```javascript
{
  nombre : /tipoString/,
  FechaNacimiento: /tipoString/,
  genero: /tipoBoolean/
}
```
## Representación Detallada
```javascript
{
  nombre : /tipoString/,
  FechaNacimiento: /tipoString/,
  genero: /tipoBoolean/,
  Objetivos: [{ /Representación de 1 obejtivo en JSON minimum / }
  .
  .
  ],Estado:[{/Representación de estado 1 en JSON minimum /}
  .
  .
  ],Rutinas:[{/Representación de rutina 1 en JSON minimum /}
  .
  .
  ],
  Jornadas:[{/Representación de jornada 1 en JSON minimum /}
  .
  .
  ]
}
```
***
### GET /usuarios
Retorna una colección de objetos Usuario en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-usuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /usuarios/{id}
Retorna una colección de objetos Usuario en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Usuario en [representaciones Detalladas](#recurso-usuario)
404|No existe un objeto Usuario con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /usuarios
Es el encargado de crear objetos Usuario.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Usuario que será creado|Sí|[Representación Detallada](#recurso-usuario)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Usuario ha sido creado|[Representación Detallada](#recurso-usuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Usuario|Mensaje de error
***
### PUT /usuarios/{id}
Es el encargado de actualizar objeto Usuario
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a actualizar|Sí|Integer
body|body|objeto Usuario nuevo|Sí|[Representación Detallada](#recurso-usuario)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Usuario actualizado|[Representación Detallada](#recurso-usuario)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Usuario|Mensaje de error
***
### DELETE /usuarios/{id}
Elimina un objeto Usuario.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Objetivo
es el / los objetivos que tiene el usuario.

## Representación Minimum
```javascript
{
    tipo: '' /*Tipo String*/,
    descripcion: '' /*Tipo String*/
}
```

## Representación Detail
```javascript
{
    tipo: '' /*Tipo String*/,
    descripcion: '' /*Tipo String*/
}
```
***
### GET /objetivos
Retorna una colección de objetos Objetivos en representación Detail.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-objetivos)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /objetivos/{id}
Retorna una colección de objetos Objetivo en representación Detail.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Objetivo a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Objetivo en [representaciones Detail](#recurso-objetivo)
404|No existe un objeto objetivo con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /objetivos
Es el encargado de crear objetos Objetivo.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Objetivo que será creado|Sí|[Representación Detail](#recurso-objetivo)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Objetivo ha sido creado|[Representación Detail](#recurso-objetivo)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Objetivo|Mensaje de error
***
### PUT /objetivos/{id}
Es el encargado de actualizar objetos Objetivo
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Objetivo a actualizar|Sí|Integer
body|body|Objeto Objetivo nuevo|Sí|[Representación Detail](#recurso-objetivo)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Objetivo actualizado|[Representación Detail](#recurso-objetivo)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Objetivo|Mensaje de error
***
### DELETE /objetivos/{id}
Elimina un objeto Objetivo.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Objetivo a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Jornada
es la rutina que hace el usuario, independientemente de lo que dice la rutina

## Representación Minima
```javascript
{
    fechaInicio: '' /*Tipo Long*/,
    fechaFin: '' /*Tipo Long*/
}
```

## Representación Detail
```javascript
{
    fechaInicio: '' /*Tipo Long*/,
    fechaFin: '' /*Tipo Long*/
    ejerciciosHechos:[{ /Representación de EjericicioHecho en JSON minimum / }
    .
    .
    ]
}
```
***
### GET /jornadas
Retorna una colección de objetos Jornada en representación Detail.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-jornadas)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /jornadas/{id}
Retorna una colección de objetos Jornada en representación Detail.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Jornada a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Jornada en [representaciones Detail](#recurso-jornada)
404|No existe un objeto jornada con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /jornadas
Es el encargado de crear objetos Jornada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Jornada que será creado|Sí|[Representación Detail](#recurso-jornada)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Jornada ha sido creado|[Representación Detail](#recurso-jornada)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Jornada|Mensaje de error
***
### PUT /jornadas/{id}
Es el encargado de actualizar objetos Jornada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Jornada a actualizar|Sí|Integer
body|body|Objeto Jornada nuevo|Sí|[Representación Detail](#recurso-jornada)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Jornada actualizado|[Representación Detail](#recurso-jornada)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Jornada|Mensaje de error
***
### DELETE /jornadas/{id}
Elimina un objeto Jornada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Jornada a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso EjercicioHecho
es el ejercicio que se hace con la serie

## Representación Minimum
```javascript
{
    fechaInicio: '' /*Tipo Long*/,
    series: '' /*Tipo Integer*/
}
```

#### Representación Detail
```javascript
{
    fechaInicio: '' /*Tipo Long*/,
    series: '' /*Tipo Integer*/
    ejercicio: { /Representación de Ejercicio en JSON minimum / }
}
```
***
#### GET /ejerciciosHechos
Retorna una colección de objetos EjercicioHecho en representación Detail.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-ejerciciosHechos)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
#### GET /ejerciciosHechos/{id}
Retorna una colección de objetos EjercicioHecho en representación Detail.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto EjercicioHecho a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto EjercicioHecho en [representaciones Detail](#recurso-ejercicioHecho)
404|No existe un objeto EjercicioHecho con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
#### POST /ejerciciosHechos
Es el encargado de crear objetos EjercicioHecho.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto EjercicioHecho que será creado|Sí|[Representación Detail](#recurso-ejercicioHecho)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto EjercicioHecho ha sido creado|[Representación Detail](#recurso-ejercicioHecho)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto EjercicioHecho|Mensaje de error
***
#### PUT /ejerciciosHechos/{id}
Es el encargado de actualizar objetos EjercicioHecho.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto EjercicioHecho a actualizar|Sí|Integer
body|body|objeto EjercicioHecho nuevo|Sí|[Representación Detail](#recurso-ejercicioHecho)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto EjercicioHecho actualizado|[Representación Detail](#recurso-ejercicioHecho)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto EjercicioHecho|Mensaje de error
***
#### DELETE /ejerciciosHechos/{id}
Elimina un objeto EjercicioHecho.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto EjercicioHecho a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Estado
es el estado actual de las medidas físicas del usuario
## Representación Básica
```javascript
{
  nombre : /tipoString/,
  FechaNacimiento: /tipoString/,
  genero: /tipoBoolean/
}
```
## Representación Detallada
```javascript
{
   fecha: /tipolong/
   peso : /tipoDouble/
   PrecionSanguinia: /tipoDouble/
   ritmoCardiaco: /tipoInteger/
   Medida: [{/Reprecentacion de Medidas en Json minimun}  
   .
   .
   ]
}
```
***
### GET /estados
Retorna una colección de objetos Estado en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-estado)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /estados/{id}
Retorna una colección de objetos Estado en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Estado a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Estado en [representaciones Detalladas](#recurso-estado)
404|No existe un objeto Estado con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /estados
Es el encargado de crear objetos Estado.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Estado que será creado|Sí|[Representación Detallada](#recurso-estado)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Estado ha sido creado|[Representación Detallada](#recurso-estado)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Estado|Mensaje de error
***
### PUT /estados/{id}
Es el encargado de actualizar objeto Estado
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Estado a actualizar|Sí|Integer
body|body|objeto Estado nuevo|Sí|[Representación Detallada](#recurso-estado)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Estado actualizado|[Representación Detallada](#recurso-estado)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Estado|Mensaje de error
***
### DELETE /estados/{id}
Elimina un objeto Estado.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Estado a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Medida
es la medida de una parte del cuerpo
## Representación Básica
```javascript
{
 Medida: /Tipo Doubel/
 parteDelCuerpo : / Tipo String/
}
```
## Representación Detallada
```javascript
{
 Medida: /Tipo Doubel/
 parteDelCuerpo : / Tipo String/
}
```
***
### GET /medidas
Retorna una colección de objetos Medida en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-medida)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /medidas/{id}
Retorna una colección de objetos Medida en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Medida a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Medida en [representaciones Detalladas](#recurso-medida)
404|No existe un objeto Medida con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /medidas
Es el encargado de crear objetos Medida.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Medida que será creado|Sí|[Representación Detallada](#recurso-medida)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Medida ha sido creado|[Representación Detallada](#recurso-medida)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Medida|Mensaje de error
***
### PUT /medidas/{id}
Es el encargado de actualizar objeto Medida
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Medida a actualizar|Sí|Integer
body|body|objeto Medida nuevo|Sí|[Representación Detallada](#recurso-medida)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Medida actualizado|[Representación Detallada](#recurso-medida)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Medida|Mensaje de error
***
### DELETE /medidas/{id}
Elimina un objeto Medida.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Medida a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Rutina
son las rutinas que tiene un usuario que fueron consolidadas por un entrenador

## Representación Básica
```javascript
{
  tipo: / tipoString /,
  fechaInicio: / tipoDate /,
  fechaFinal: / tipoDate /
}
```
## Representación Detallada
```javascript
{
  tipo: / tipoString /,
  fechaInicio: / tipoDate /,
  fechaFinal: / tipoDate /
  EjercicioAsignado: [ {/ Representación de ejercicio asignado 1 en JSON mínimo /}
  .
  . 
  ] 
}
```
***
### GET /rutinas
Retorna una colección de objetos Rutina en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-rutina)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /rutinas/{id}
Retorna una colección de objetos Rutina en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Rutina a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Rutina en [representaciones Detalladas](#recurso-rutina)
404|No existe un objeto Rutina con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /rutinas
Es el encargado de crear objetos Rutina.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Rutina que será creado|Sí|[Representación Detallada](#recurso-rutina)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Rutina ha sido creado|[Representación Detallada](#recurso-rutina)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Rutina|Mensaje de error
***
### PUT /rutinas/{id}
Es el encargado de actualizar objeto Rutina
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Rutina a actualizar|Sí|Integer
body|body|objeto Rutina nuevo|Sí|[Representación Detallada](#recurso-rutina)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Rutina actualizado|[Representación Detallada](#recurso-rutina)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Rutina|Mensaje de error
***
### DELETE /rutinas/{id}
Elimina un objeto Rutina.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Rutina a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso EjercicioAsignado
es el ejercicio que tiene la rutina y cuantas series se deberían hacer

## Representación Básica
```javascript
{
  series : /tipoint/,
}
```
## Representación Detallada
```javascript
{
 series : /tipoint/,
  Ejercicio:[{/Representación de ejercicio 1 en JSON minimum/}
  .
  .
  ]
}
```
***
### GET /ejerciciosAsignados
Retorna una colección de objetos EjercicioAsignado en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-ejercicioAsignado)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /ejerciciosAsignados/{id}
Retorna una colección de objetos EjercicioAsignado en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto EjercicioAsignado a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto EjercicioAsignado en [representaciones Detalladas](#recurso-ejercicioAsignado)
404|No existe un objeto EjercicioAsignado con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /ejerciciosAsignados
Es el encargado de crear objetos EjercicioAsignado.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto EjercicioAsignado que será creado|Sí|[Representación Detallada](#recurso-ejercicioAsignado)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto EjercicioAsignado ha sido creado|[Representación Detallada](#recurso-ejercicioAsignado)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto EjercicioAsignado|Mensaje de error
***
### PUT /ejerciciosAsignados/{id}
Es el encargado de actualizar objeto EjercicioAsignado
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto EjercicioAsignado a actualizar|Sí|Integer
body|body|objeto EjercicioAsignado nuevo|Sí|[Representación Detallada](#recurso-ejercicioAsignado)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto EjercicioAsignado actualizado|[Representación Detallada](#recurso-ejercicioAsignado)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto EjercicioAsignado|Mensaje de error
***
### DELETE /ejerciciosAsignados/{id}
Elimina un objeto EjercicioAsignado.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto EjercicioAsignado a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Ejercicio
es la actividad física de como se debe hacer y el tipo

## Representación Básica
```javascript
{
  descripcion: '' /*Tipo String*/,
  tipo: '' /*Tipo String*/
}
```
## Representación Detallada
```javascript
{
  descripcion: '' /*Tipo String*/,  
  tipo: '' /*Tipo String*/,
  maquinas:[{representacion de maquinas en JSON minimum}
  .
  .
  ]
}
```
***
### GET /ejercicio
Retorna una colección de objetos Ejercicio en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-ejercicio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /ejercicio
Es el encargado de crear objetos Ejercicio.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Ejercicio que será creado|Sí|[Representación Detallada](#recurso-ejercicio)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Ejercicio ha sido creado|[Representación Detallada](#recurso-ejercicio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Ejercicio|Mensaje de error
***
### PUT /ejercicio
Es el encargado de actualizar objeto Ejercicio
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Ejercicio nuevo|Sí|[Representación Detallada](#recurso-ejercicio)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Ejercicio actualizado|[Representación Detallada](#recurso-ejercicio)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Ejercicio|Mensaje de error
***
### DELETE /ejercicio
Elimina un objeto Ejercicio.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Maquina
la maquina con la que se hace el ejercicio

## Representación Básica
```javascript
{
    tipo: '' /*Tipo Long*/,
    descripcion: '' /*Tipo String*/,
}
```
## Representación Detallada
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/    
    tiposMedida: [{representacion de medida en JSON minimum}
    .
    .
    ]
}
```
***
### GET /maquinas
Retorna una colección de objetos Maquina en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-maquina)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /maquinas/{id}
Retorna una colección de objetos Maquina en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Maquina a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Maquina en [representaciones Detalladas](#recurso-maquina)
404|No existe un objeto Maquina con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /maquinas
Es el encargado de crear objetos Maquina.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Maquina que será creado|Sí|[Representación Detallada](#recurso-maquina)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Maquina ha sido creado|[Representación Detallada](#recurso-maquina)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Maquina|Mensaje de error
***
### PUT /maquinas/{id}
Es el encargado de actualizar objeto Maquina
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Maquina a actualizar|Sí|Integer
body|body|objeto Maquina nuevo|Sí|[Representación Detallada](#recurso-maquina)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Maquina actualizado|[Representación Detallada](#recurso-maquina)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Maquina|Mensaje de error
***
### DELETE /maquinas/{id}
Elimina un objeto Maquina.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Maquina a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso TipoMedida
son el tipo de medida que la maquina puede registrar

## Representación Básica
```javascript
{
    medida: '' /*Tipo String*/
}
```
## Representación Detallada
```javascript
{
    medida: '' /*Tipo String*/
}
```
***
### GET /tiposmedidas
Retorna una colección de objetos TipoMedida en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-tipomedida)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /tiposmedidas/{id}
Retorna una colección de objetos TipoMedida en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto TipoMedida a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto TipoMedida en [representaciones Detalladas](#recurso-tipomedida)
404|No existe un objeto TipoMedida con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /tiposmedidas
Es el encargado de crear objetos TipoMedida.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto TipoMedida que será creado|Sí|[Representación Detallada](#recurso-tipomedida)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto TipoMedida ha sido creado|[Representación Detallada](#recurso-tipomedida)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto TipoMedida|Mensaje de error
***
### PUT /tiposmedidas/{id}
Es el encargado de actualizar objeto TipoMedida
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto TipoMedida a actualizar|Sí|Integer
body|body|objeto TipoMedida nuevo|Sí|[Representación Detallada](#recurso-tipomedida)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto TipoMedida actualizado|[Representación Detallada](#recurso-tipomedida)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto TipoMedida|Mensaje de error
***
### DELETE /tiposmedidas/{id}
Elimina un objeto TipoMedida.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto TipoMedida a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso MedidaUsuario
es un registro de que usuarios usaron la maquina

## Representación Básica
```javascript
{
    fecha: '' /*Tipo Date*/
}
```
## Representación Detallada
```javascript
{
    fecha: '' /*Tipo Date*/
    mediciones:
      [
	{/Representacion de TipoMedida en Json minimum/}
      ]
}
```
***
### GET /medidasusuarios
Retorna una colección de objetos MedidaUsuario en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-medidausuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /medidasusuarios/{id}
Retorna una colección de objetos MedidaUsuario en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto MedidaUsuario a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto MedidaUsuario en [representaciones Detalladas](#recurso-medidausuario)
404|No existe un objeto MedidaUsuario con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /medidasusuarios
Es el encargado de crear objetos MedidaUsuario.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto MedidaUsuario que será creado|Sí|[Representación Detallada](#recurso-medidausuario)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto MedidaUsuario ha sido creado|[Representación Detallada](#recurso-medidausuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto MedidaUsuario|Mensaje de error
***
### PUT /medidasusuarios/{id}
Es el encargado de actualizar objeto MedidaUsuario
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto MedidaUsuario a actualizar|Sí|Integer
body|body|objeto MedidaUsuario nuevo|Sí|[Representación Detallada](#recurso-medidausuario)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto MedidaUsuario actualizado|[Representación Detallada](#recurso-medidausuario)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto MedidaUsuario|Mensaje de error
***
### DELETE /medidasusuarios/{id}
Elimina un objeto MedidaUsuario.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto MedidaUsuario a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
### GET /medidasusuarios/mismedidas
retorna el conjunto de medidas de un mismo  usuario
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-medidausuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
***
# Recurso Medicion
es el dato de la medida arrojada de la maquina

## Representación Básica
```javascript
{
  medida: '' /*Tipo String*/
}
```
## Representación Detallada
```javascript
{
  medida: '' /*Tipo String*/
  tipo: {/Representación de rutina 1 en JSON minimum /}
}
```
***
### GET /mediciones
Retorna una colección de objetos Medicion en Representación Detallada.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detalladas](#recurso-medicion)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### GET /mediciones/{id}
Retorna una colección de objetos Medicion en Representación Detallada.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Medicion a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Medicion en [representaciones Detalladas](#recurso-medicion)
404|No existe un objeto Medicion con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
### POST /mediciones
Es el encargado de crear objetos Medicion.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Medicion que será creado|Sí|[Representación Detallada](#recurso-medicion)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Medicion ha sido creado|[Representación Detallada](#recurso-medicion)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Medicion|Mensaje de error
***
### PUT /mediciones/{id}
Es el encargado de actualizar objeto Medicion
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Medicion a actualizar|Sí|Integer
body|body|objeto Medicion nuevo|Sí|[Representación Detallada](#recurso-medicion)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Medicion actualizado|[Representación Detallada](#recurso-medicion)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Medicion|Mensaje de error
***
### DELETE /mediciones/{id}
Elimina un objeto Medicion.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Medicion a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)
