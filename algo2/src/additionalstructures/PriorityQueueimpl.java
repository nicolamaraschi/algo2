package additionalstructures;

import upo.greedy.HuffmanNode;

import java.util.HashMap;
import java.util.Set;

public class PriorityQueueimpl implements PriorityQueue{

	private HashMap<Character,Integer> characterIntegerHashMap = new HashMap<>(); 
	
	@Override
	public void enqueue(HuffmanNode h) {
		characterIntegerHashMap.put(h.carattere,h.dato);
	}


	@Override
	public int dequeue() {
		Integer min = 100000;
		Character carattere =null;
		Set<Character> listaCaratteri = characterIntegerHashMap.keySet();
		for(Character iteratoreCaratteri: listaCaratteri) {
			if(min > characterIntegerHashMap.get(iteratoreCaratteri)) {
				min = characterIntegerHashMap.get(iteratoreCaratteri);
				carattere = iteratoreCaratteri;
			}
		}
		characterIntegerHashMap.remove(carattere);
		return min;
	}

	@Override
	public void modify_priority(HuffmanNode h, int newPriority) {
		// TODO Auto-generated method stub
		characterIntegerHashMap.replace(h.carattere, newPriority);
	}

	public int size() {
		// TODO Auto-generated method stub
		return characterIntegerHashMap.size();
	}
	

}
