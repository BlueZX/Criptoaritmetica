# Criptoaritmetica

# OBJETIVO
Implementar algoritmos usando backtracking

# PROBLEMA A RESOLVER
La cantidad de acertijos, pasatiempos y rompecabezas que han ido surgiendo a lo largo de los siglos es inmensa.
Entre ellos, los puzzles matemáticos constituyen una categoría en sí misma, con acertijos sobre aritmética,
combinatoria, topología o probabilidad.
Hoy nos quedaremos con la llamada aritmética verbal, o criptoaritmética. En ella, los puzzles son operaciones
matemáticas en las que los dígitos han sido sustituidos por letras, y hay que encontrar los números originales.

# CONDICIONES DE ENTRADA Y SALIDA
Cada caso de prueba es una operación aritmética (suma o producto) en la que los dígitos han sido sustituidos
por letras minúsculas del alfabeto inglés. Los dos operadores disponibles son ("+" o "*"),
Los operandos no tendrán más de 8 letras minúsculas; además, se garantiza que no habrá más de 10 letras
diferentes en total.
Para cada caso de prueba el programa escribirá, en un archivo XML, la operación asociada tras convertir las
letras a dígitos, de modo que todas las apariciones de la misma letra se conviertan al mismo dígito y viceversa,
y la operación aritmética sea correcta. Se garantiza que la solución será única.
Se debe añadir un espacio antes y después de cada operador. No se considera válida una asignación en la
que cualquiera de los números tenga ceros superfluos a la izquierda, pero se debe tener en cuenta que alguno
de los valores podría ser 0, que sí es válido.
