
# Tabla de contenidos
   -[Recurso Gimnasio](#recurso-gimnasio)

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
