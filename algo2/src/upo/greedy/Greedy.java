package upo.greedy;


import additionalstructures.PriorityQueueimpl;

import java.util.HashMap;
import java.util.Map;

public class Greedy {
	
	/** Calcola una codifica di Huffman per i caratteri contenuti nel vettore characters, date le frequenze 
	 * contenute in f. f[i] contiene la frequenza (in percentuale, 0<=f[i]<=100) del carattere characters[i] 
	 * nel testo per il quale si vuole calcolare la codifica.
	 * </br>CONSIGLIO: potete estendere o usare un vostro grafo non pesato non orientato per rappresentare la 
	 * foresta di Huffman.
	 * </br>CONSIGLIO2: potete implementate una PriorityQueue dall'interfaccia in upo.additionalstructures,
	 * oppure aggiungere al grafo del primo consiglio delle priorità.
	 * 
	 * @param characters i caratteri dell'alfabeto per i quali calcolare la codifica.
	 * @param f le frequenze dei caratteri in characters nel dato testo.
	 * @return una Map che mappa ciascun carattere in una stringa che rappresenta la sua codifica secondo 
	 * l'algoritmo visto a lezione.
	 */
	public static Map<Character,String> getHuffmanCodes(Character[] characters, int[] f) {
		Map<Character,String> codificaCarattereCodice 	= new HashMap<>();
		PriorityQueueimpl listaCodaPriorita = new PriorityQueueimpl();

		// scorro l'array di caratteri
		for (int i = 0; i < characters.length; i++) {
			// creo l'oggetto
			HuffmanNode huffmanNode 	= new HuffmanNode();
			huffmanNode.carattere 		= characters[i];
			huffmanNode.dato 			= (Integer)f[i];
			huffmanNode.left 			= null;
			huffmanNode.right 			= null;
			listaCodaPriorita.enqueue(huffmanNode);
		}
		HuffmanNode root = new HuffmanNode();
		while(listaCodaPriorita.size() > 1){
			int frequenzaMinima1 		= listaCodaPriorita.dequeue(); //estraggo le due frequenze minime
			int frequenzaMinima2 		= listaCodaPriorita.dequeue();
			HuffmanNode nodoRadice 		= new HuffmanNode(); //nodo root
			nodoRadice.dato	 			=  frequenzaMinima1 + frequenzaMinima2;
			nodoRadice.carattere		= '-';
			HuffmanNode nodoSinistro 	= new HuffmanNode(); //figlio
			nodoSinistro.dato 			= frequenzaMinima1;
			nodoRadice.left 			= nodoSinistro;
			HuffmanNode nodoDestra 		= new HuffmanNode(); //figlio
			nodoDestra.dato 			= frequenzaMinima2;
			nodoRadice.right 			= nodoDestra;
			root 						= nodoRadice;
			listaCodaPriorita.enqueue(nodoRadice);
		}
		stampaCodice(root, "", codificaCarattereCodice);
		return codificaCarattereCodice;
	}


	public static void stampaCodice(HuffmanNode root, String s, Map<Character, String> codificaCarattereCodice) {
		if (root.left == null && root.right == null && Character.isLetter(root.carattere)) {
			codificaCarattereCodice.put(root.carattere, s);
			System.out.println(root.carattere + ":" + s);
			return;
		}
		stampaCodice(root.left,s + "0", codificaCarattereCodice);
		stampaCodice(root.right,s + "1", codificaCarattereCodice);
	}

}
