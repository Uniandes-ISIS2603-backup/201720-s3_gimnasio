
# Tabla de contenidos
 -[Recurso Gimnasio](#recurso-gimnasio)
   - [GET /gimnasios](#get-/gimnasios)
   - [GET /gimnasios/{id}](#get-/gimnasios/{id})
   - [POST /gimnasios](#post-/gimnasios)
   - [PUT /gimnasios/{id}](#put-/gimnasios/{id})
   - [DELETE /gimnasios/{id}](#delete-/gimnasios/{id})

## Recurso Gimnasio
Entidad principal que define el todo el gimnasio	

### Representación Básica
```javascript
{
   nombre : /tipoString/,
   duenio : /tipoString/,
   nit : /tipoLong/
}
```
### Representación Detallada
```javascript
{
   nombre : /tipoString/,
   duenio : /tipoString/,
   nit : /tipoLong/
   clientes: [{ /Representación de usuario 1 en JSON minimum / }
   .
   .
   ],
   entrenadores:[
   {/Representación de entrenador 1 en JSON minimum /}
   .
   .
   ],
   Maquinas:[
   {/Representación de maquina 1 en JSON minimum/}
   ]
}
```
***
#### GET /gimnasios
Retorna una colección de objetos Gimnasio en representación Detail.
#### Parámetros
N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-gimnasio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
#### GET /gimnasios/{id}
Retorna una colección de objetos Gimnasio en representación Detail.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Gimnasio a consultar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|objeto Gimnasio en [representaciones Detail](#recurso-gimnasio)
404|No existe un objeto Gimnasio con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
***
#### POST /gimnasios
Es el encargado de crear objetos Gimnasio.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|objeto Gimnasio que será creado|Sí|[Representación Detail](#recurso-gimnasio)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Gimnasio ha sido creado|[Representación Detail](#recurso-gimnasio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Gimnasio|Mensaje de error
***
#### PUT /gimnasios/{id}
Es el encargado de actualizar objeto Gimnasio
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Gimnasio a actualizar|Sí|Integer
body|body|objeto Gimnasio nuevo|Sí|[Representación Detail](#recurso-gimnasio)
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Gimnasio actualizado|[Representación Detail](#recurso-gimnasio)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Gimnasio|Mensaje de error
***
#### DELETE /gimnasios/{id}
Elimina un objeto Gimnasio.
#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Gimnasio a eliminar|Sí|Integer
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
***
[Volver arriba](#tabla-de-contenidos)


