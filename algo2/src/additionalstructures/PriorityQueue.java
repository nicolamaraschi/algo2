package additionalstructures;

import upo.greedy.HuffmanNode;

/** ATTENZIONE: chi avesse bisogno delle priority queue per il proprio algoritmo le deve implementare seguendo
 * questa interfaccia. Non � per� necessario che siano implementazioni efficienti. Anche l'implementazione con un array da 0 a n,
 * dove la posizione i-esima rappresenta la priorità del verice i-esimo, come spiegato nel video, va benissimo
 * (anzi, abbiamo anche spiegato essere la più efficiente in alcuni casi).
 * NB: questa PriorityQueue pu� essere usata anche per Huffman. 
 * NB2: in caso di implementazioni come descritto sopra, per indicare che un elemento non è contenuto nella
 * priority queue, potete usare un valore di priorità fuori range (ad es. -1 se tutte le priorità sono non negative).
 * 
 * @author Luca Piovesan
 *
 */
public interface PriorityQueue {
	

	public void enqueue(HuffmanNode h);

	public int dequeue();

	public void modify_priority(HuffmanNode h, int newPriority);
	

}
