import java.util.*;
import java.util.Map.Entry;

public class Solucion{

	String res= "";

	//al momento de crear el objeto de esta clase comenzara a llamar el metodo de backtracking  insertando los datos requeridos por el
	public Solucion(ArrayList<String> text){
    	ArrayList<String> res = new ArrayList<String>();

    	//repetimos el proceso de backtrancking dependiendo de la cantidad de puzzles que tenga el xml, el cual se ve reflejado en el size()
    	for(int i=0;i<text.size();i++){
    		System.out.println(text.get(i));
    		//creamos el map el cual conendra la letra y el numero que le corresponde
	    	Map<Character, Integer> map = new HashMap<Character, Integer>();
	    	//generamos la lista de letras que se ocupan en el puzzle, mediante el metodo creado getChar()
			List<Character> letras = getChar(text.get(i));
			
			//llamamos a nuestro metodo de backracking asignando la palabra text, lo interesante es que map conserva los numeros de resultados
			String aux = backtracking(text.get(i), 0, map, new HashMap<Integer, Void>(), letras);

			if(!aux.isEmpty()) {
				//en este metodo pasamos las letras a numeros, ya teniendo el resultado entregado por el backtracking
				res.add(resultadoUnitario(map, aux));
			}
		}

		//generamos una lista de string las cuales almacenaran el numero de la palabra que les corresponda ejemplo o1(operadando)
		ArrayList<String> o1  = new ArrayList<String>();
		ArrayList<String> op  = new ArrayList<String>();
		ArrayList<String> o2  = new ArrayList<String>();
		ArrayList<String> r  = new ArrayList<String>();

		//en este for entregamos los numeros al xml, mediante el metodo estatico de su clase
		for(int i=0;i<res.size();i++){
			//los partimos en tokens los cuales nos importa todo menos el =  y los espacios
			StringTokenizer st = new StringTokenizer(res.get(i), "*+=", true);
			int a = 0;
		    while(st.hasMoreTokens()) {
		    	switch(a){
		    		case 0:
		    			o1.add(st.nextToken().trim());
		    			a++;
		    		break;
		    		case 1:
		    			op.add(st.nextToken().trim());
		    			a++;
		    		break;
		    		case 2:
		    			o2.add(st.nextToken().trim());
		    			a++;
		    		break;
		    		case 3:
		    			st.nextToken().trim();
		    			a++;
		    		break;
		    		case 4:
		    			r.add(st.nextToken().trim());
		    			a = 0;
		    		break;
		    		default:
		    		break;
		    	}
		    }
		}
	    try { 
			CrearXml.generar(o1,op,o2,r);
		} catch (Exception e) {}

	}

	/*
	* Imprime, usando backtracking, la suma o multiplicacion de las palabras transformadas en numeros unicos
	* pueden formar usando n letras
	* @param text cadena completa, contiene los dos operandos, el operador y el resultado
	* @param k número de revisión en la que va
	* @param map contiene una clave (las letras de las palabras) y un numero 
	* @param letras es una lista de caracteres la cual no contiene letras repetidas de las palabras
	* @param map_aux es solo para ver si una letra ya fue asignada con algun numero
	*/
	private String backtracking(String text, int k, Map<Character, Integer> map, Map<Integer, Void> map_aux, List<Character> letras){
		//se compara si el tamaño de nuestra posible solucion es igual al tamaño de todas las letras
		if (map.size() == letras.size()){
			//se separa el strign en 2 , para poder pasarlo a operacion
			String[] aux = text.split(" = ");
	      	
	      	// se tramsforma la suma o multiplicacion de los operandos y el resultado a numeros mediante el metodo operacion
	    	int n1 = operacion(map, aux[0]), n2 = operacion(map, aux[1]);

	    	/* Verifica si se ha encontrado solución, comparando si la suma o multiplicaion es igual al resulado, en caso de serlo, reorna text */
	    	if ((n1 == n2) && (aux[0].charAt(0))!='0'){
	    		return text;
	    	}
	    	else{ 
	    		/* Deshace la decisión, se arrepiente */
	    		return "";
	    	}
		}

		/* Revisa los candidatos */
		for (int i=0;i<10;i++) {
			/* Verifica si una clave está dada de alta en el mapa */
			if (!map_aux.containsKey(i)) {

				/* Marca como usada temporalmente */
				map.put(letras.get(k),i);
				map_aux.put(i,null);

				/* No hay solución aún, así es que continúa buscando, haciendo una llamada recursiva */
				String r = backtracking(text, k+1, map, map_aux, letras);

				/* Verifica si se ha encontrado solución */
				if(r.isEmpty()){
					/* Deshace la decisión, se arrepiente */
					map.remove(letras.get(k));
					map_aux.remove(i);
				}else{
					// se ha enconrado una solucion
					return r;
				}
			}
		}
		return "";
	}

	private String resultadoUnitario(Map<Character, Integer> map, String text){
		String res = "";
		String aux = text.trim();
		
		for(int i=0;i<aux.length();i++){
			if(map.get(aux.charAt(i)) != null){
				res += map.get(aux.charAt(i));
			}
			else{
				res += aux.charAt(i);
			}
		}

		System.out.println(res);
		return res;
	}
	/* este metodo lo unico que hace es pasar de string a una lista de caracteres, con la caractreristica que
	no se repitan los caracteres (esto se hace mediante el HashSet)
	*/
	private List<Character> getChar(String text) {
		List<Character> list = new ArrayList<Character>();
		Set<Character> set = new HashSet<>();

		for(int i=0;i<text.length();i++) {
			//verifica si el caracter es parte del alfabeto
	    	if(Character.isAlphabetic(text.charAt(i))) {
	        	set.add(text.charAt(i));
	    	}
	    }
	    //aqui solo se cambia de estilo de lista, por el tema del retorno
		for(char c: set) {
    		list.add(c);
		}
		return list;
	}

	/* 	En esta funcion hacemos la transformacion de string a numeros enteros
	mediante el map el cual nos entrega el caracter y el numero que le corresponde,
	a su vez limitamos que el primer digito no sea 0, tambien se decide que opracion matematica realizar
	*/
	private int operacion(Map<Character, Integer> map, String text) {
	    int num = 0;
	    StringTokenizer st = new StringTokenizer(text, "*+", true);
	    while(st.hasMoreTokens()) {
		    String next = st.nextToken().trim();
		    if(next.equals("+")) {
		    	String p = st.nextToken().trim();
		    	int n = 0;
		    	for(int i=0;i<p.length();i++){
					n = 10 * n + map.get(p.charAt(i));
				}
				if(map.get(p.charAt(0))!=0){
					num += n;
				}
		    } 
		    else if(next.equals("*")) {
		        String p = st.nextToken().trim();
		        int n = 0;
		    	for(int i=0;i<p.length();i++){
					n = 10 * n + map.get(p.charAt(i));
				}
				if(map.get(p.charAt(0))!=0){
					num *= n;
				}
		    } 
		    else {
		      	for(int i=0;i<next.length();i++){
		      		if(map.get(next.charAt(0))!=0){
						num = 10 * num + map.get(next.charAt(i));
					}
				}
		    }
	    }
	    return num;
	  }
}