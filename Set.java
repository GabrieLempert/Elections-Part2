package id_206215311_id_207497561;

import java.util.ArrayList;

public class Set<T> {

	private ArrayList<T> arr;
	private int currentSize;

	public Set() {
		this.arr = new ArrayList<T>();
		this.currentSize = 0;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.currentSize; i++) {
			sb.append(arr.toString() + "\n");
		}
		return sb.toString();
	}

	public boolean add(T newObj) {
		for (int i = 0; i < this.currentSize; i++) {
			if (arr.contains(newObj)) {
				System.out.println("Id already exists Cant add citizen");
				return false;
			}
		}
		arr.add(newObj);
		this.currentSize++;
		return true;
	}

	public int getCapacity() {
		return arr.size();
	}

	public T get(int index) {
		for (int i = 0; i < getCurrentSize(); i++) {
			if (arr.get(i).equals(arr.get(index))) {
				index = i;
			}
		}
		return arr.get(index);
	}

	public boolean contains(T object) {
		for (int i = 0; i < getCurrentSize(); i++) {
			if (arr.get(i).equals(object)) {
				return true;
			}
		}
		return false;
	}

	public int getIndex(int index) {
		for (int i = 0; i < getCurrentSize(); i++) {
			if (arr.get(i).equals(arr.get(index))) {
				index = i;
			}
		}
		return index;
	}

	public int getCurrentSize() {
		return this.currentSize;
	}

}
