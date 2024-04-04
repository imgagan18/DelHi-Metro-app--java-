import java.util.ArrayList;//A Binary min heap
import java.util.HashMap;

public class Heap <T extends Comparable<T>> 
{
	ArrayList<T> data = new ArrayList<>();
	HashMap<T, Integer> map = new HashMap<>();//Maps each element to its index in the data list. This is used for efficient retrieval of an element's position in the heap.

	public void add(T item) //Adds an item to the heap. It places the item at the end of the data list and then calls upheapify to restore the heap property.
	{
		data.add(item);   
		map.put(item, this.data.size() - 1);
		upheapify(data.size() - 1);
	}

	private void upheapify(int ci) //Moves an item up the heap to its correct position. It compares the item with its parent and swaps them if the item is smaller. This process is repeated until the item is in its correct position.
	{
		int pi = (ci - 1) / 2;
		if (isLarger(data.get(ci), data.get(pi)) > 0) 
		{
			swap(pi, ci);
			upheapify(pi);
		}
	}

	private void swap(int i, int j) // Swaps two elements in the heap. It also updates the map to reflect the new positions of the swapped elements.
	{
		T ith = data.get(i);
		T jth = data.get(j);
		
		data.set(i, jth);
		data.set(j, ith);
		map.put(ith, j);
		map.put(jth, i);
	}

	public void display() // Prints the contents of the heap.
	{
		System.out.println(data);
	}

	public int size() //size of the heap
	{
		return this.data.size();
	}

	public boolean isEmpty() //: Checks if the heap is empty.
	{
		return this.size() == 0;
	}

	public T remove() //Removes and returns the root element of the heap. It swaps the root with the last element, removes the last element, and then calls downheapify to restore the heap property.
	{
		swap(0, this.data.size() - 1);
		T rv = this.data.remove(this.data.size() - 1);
		downheapify(0);

		map.remove(rv);
		return rv;
	}

	private void downheapify(int pi) //Moves an item down the heap to its correct position. It compares the item with its children and swaps it with the smaller child if the item is larger. This process is repeated until the item is in its correct position.
	{
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		int mini = pi;

		if (lci < this.data.size() && isLarger(data.get(lci), data.get(mini)) > 0)
		{
			mini = lci;
		}
		
		if (rci < this.data.size() && isLarger(data.get(rci), data.get(mini)) > 0) 
		{
			mini = rci;
		}
		
		if (mini != pi)
		{
			swap(mini, pi);
			downheapify(mini);
		}
	}

	public T get() //Returns the root element of the heap without removing it.
	{
		return this.data.get(0);
	}

	public int isLarger(T t, T o) //Compares two elements and returns a positive value if the first is larger, indicating a violation of the heap property.
	{
		return t.compareTo(o);
	}

	public void updatePriority(T pair) //Updates the position of an element in the heap to maintain the heap property. It finds the element's index using the map and then calls upheapify to move the element up or down as necessary.
	{
		int index = map.get(pair);
		upheapify(index);
	}
}


// basicailly forms a tree using the heap data froms a tree using the values of the gvertex and then we use bfs and dfs to find the cost and time to reach the stations 