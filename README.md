
# Tabla de contenidos
 - API
    - [\[Recurso Gimnasio\]](#recurso-gimnasio)
       - [GET /gimnasio](#get-gimnasio)
       - [PUT /gimnasio](#put-gimnasio)
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
               - [GET /gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/objetivos](#GET-objetivos)
               - [GET/gimnasio/entrenadores/{id_entrenador}/usuarios/{id_usuario}/objetivos/{id}](#GET-objetivosid)
      - [\[Recurso Usuario\]](#recurso-usuario)
         - [GET /gimnasio/usuarios](#get-usuarios)
         - [GET /gimnasio/usuarios/{id}](#get-usuariosid)
         - [POST /gimnasio/usuarios](#post-usuarios)
         - [PUT /gimnasio/usuarios/{id}](#put-usuariosid)
         - [DELETE /gimnasio/usuarios/{id}](#delete-usuariosid)
         - [\[Recurso objetivos\]](#recurso-objetivos)
            - [GET /gimnasio/usuarios/{id_usuario}/objetivos](#GET-objetivos)
            - [GET /gimnasio/usuarios/{id_usuario}/objetivos/{id}](#GET-objetivosid)
            - [POST /gimnasio/usuarios/{id_usuario}/objetivos](#POST-objetivos)
            - [PUT /gimnasio/usuarios/{id_usuario}/objetivos/{id}](#PUT-objetivosid)
            - [DELETE /gimnasio/usuarios/{id_usuario}/objetivos/{id}](#DELETE-objetivosid)
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
