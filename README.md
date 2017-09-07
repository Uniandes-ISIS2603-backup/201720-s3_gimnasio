
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
