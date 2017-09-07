# Tabla de contenidos
  - [Recurso Book](#recurso-book)
    - [GET /books](#GET-/books)
    - [GET /books/{id}](#GET-/books/{id})
    - [POST /books](#POST-/books)
    - [PUT /books/{id}](#PUT-/books/{id})
    - [DELETE /books/{id}](#DELETE-/books/{id})
    

# Recurso Book
El objeto Book tiene 2 representaciones JSON:	

## Representación Básica
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    isbn: '' /*Tipo String*/,
    description: '' /*Tipo String*/,
    image: '' /*Tipo String*/,
    publishingdate: '' /*Tipo Date*/,
   editorial: {
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/    }
   }
```

##Ejemplo Representación Básica
```javascript
{
"id" : 1, 
    
"name" : "Cien años de soledad", 
    
"description": "El libro se compone de 20 capítulos no titulados, en los cuales se narra una historia con una estructura cíclica temporal, 
                puesto que los acontecimientos del pueblo y de la familia Buendía, así como los nombres de los personajes, se repiten una y otra vez, 
                fusionando la fantasía con la realidad.",
    
 "isbn" : "0307474720",
    
 "image" : "http://goo.gl/IWNdCX",
    
 "publishDate" : "01071967", 
    
 "editorial": {"id" : 1, 
               "name" : "Plaza y Janes"}

}
```
## Representación Detallada
```javascript
{
    // todo lo de la representación Minimum más las colecciones de objetos básicos relacionados. 
   
    authors: [ { id: '' /*Tipo Long*/,
                  name: '' /*Tipo String*/,
                birthday: '' /*Tipo Date*/ },
             ],
    reviews: [ { id: '' /*Tipo Long*/,
                 name: '' /*Tipo String*/,
                 source: '' /*Tipo String*/
                 description: '' /*Tipo String*/
               },
             ]

}
```

##Ejemplo Representación Detallada
```javascript
{
"id" : 1, 
    
"name" : "Cien años de soledad", 
    
"description": "El libro se compone de 20 capítulos no titulados, en los cuales se narra una historia con una estructura cíclica temporal, 
                puesto que los acontecimientos del pueblo y de la familia Buendía, así como los nombres de los personajes, se repiten una y otra vez, 
                fusionando la fantasía con la realidad.",
    
 "isbn" : "0307474720",
    
 "image" : "http://goo.gl/IWNdCX",
    
 "publishDate" : "01071967", 
    
 "editorial": {"id" : 1, 
               "name" : "Plaza y Janes"}

 "authors":[{
 "id": 200,
 
	      "name": "Gabriel José de la Concordia García Márquez"
              "birthDate": "1927-03-03T00:00:00-05:00",
        
              "description": "fue un escritor, guionista, editor y periodista colombiano. En 1982 recibió el Premio Nobel de Literatura.",
               
             "image": "https://commons.wikimedia.org/wiki/File:Gabriel_Garcia_Marquez.jpg",
        

           }],
 "reviews":[{
 "id": 123,
              "name": "Primera edición",
              "source":"El Tiempo",
	      "description":"Magnifico inigualable"
            },
            {
 "id": 456,
              "name": "Realismo",
              "source":"Arcadia",
	      "description":"Realismo mágico al extremo. No puede dejar de leerlo."
            }]
  
}
```

## GET /books

Retorna una colección de objetos Book en representación Detallada.
Cada Book en la colección tiene embebidos los siguientes objetos: Editorial.

## Parámetros

 N/A

## Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detallada](#recurso-book)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

## GET /books/{id}

Retorna una colección de objetos Book en representación Detallada.
Cada Book en la colección tiene los siguientes objetos: Editorial.

## Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

## Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representaciones Detallada](#recurso-book)
404|No existe un objeto Book con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /books

Es el encargado de crear objetos Book.

## Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Book que será creado|Sí|[Representación Detallada](#recurso-book)

## Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book ha sido creado|[Representación Detallada](#recurso-book)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Book|Mensaje de error

#### PUT /books/{id}

Es el encargado de actualizar objetos Book.

## Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a actualizar|Sí|Integer
body|body|Objeto Book nuevo|Sí|[Representación Detallada](#recurso-book)

##Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book actualizado|[Representación Detallada](#recurso-book)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Book|Mensaje de error

## DELETE /books/{id}

Elimina un objeto Book.

## Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a eliminar|Sí|Integer

## Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error





[Volver arriba](#tabla-de-contenidos)
