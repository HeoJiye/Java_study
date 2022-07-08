# chapter05 리스트

## 1️⃣  리스트

#### 리스트란?
* 줄 세워져 있는 데이터, 쭉 눌어선 데이터

#### ADT 리스트
* i번째 자리에 원소 x를 삽입한다.
* i번째 원소를 삭제한다.
* 원소 x를 삭제한다.
* i번째 원소를 알려준다.
* 원소 x가 몇 번째 원소인지 알려준다.
* 리스트의 사이즈를 알려준다.

#### 리스트 인터페이스
```java
public interface ListInterface<E> {
	public void add(int i, E x);
	public void append(E x);
	public E remove(int i);
	public boolean removeItem(E x);
	public E get(int i);
	public void set(int i, E x);
	public int indexOf(E x);
	public int len();
	public boolean isEmpty();
	public void clear();
}
```

<br>

## 2️⃣  배열로 구현한 리스트

```java
public class ArrayList<E> implements ListInterface<E> {
	private E item[];
	private int numItems;
	private static final int DEFAULT_CAPACITY = 64;

	public ArrayList() {
		item = (E[]) new Object[DEFAULT_CAPACITY];
		numItems = 0;
	}

	public ArrayList(int n) {
		item = (E[]) new Object[n];
		numItems = 0;
	}

	public void add(int index, E x) {
		if(numItems >= item.length || index < 0 || index > numItems) { /*예외 처리*/ }
		else {
			for(int i = numItems - 1; i >= index; i--)
				item[i + 1] = item[i];
			item[index] = x;
			numItems++;
		}
	}

	public void append(E x) {
		if(numItems >= item.length) { /*예외 처리*/ }
		else {
			item[numItems++] = x;
		}
	}

	public E remove(int index) {
		if(isEmpty() || index < 0 || index > numItems - 1) return null;
		else {
			E tem = item[index];
			for(int i = index; i <= numItems - 2; i++)
				item[i] = item[i + 1];
			numItems--;
			return tmp;
		}
	}

	public boolean removeItem(E x) {
		int k = 0;
		while(k < numItems && ((Comparable)items[k]).compareTo(x) != 0) 
			k++;
		if(k == numItems) return false;
		else {
			for(int i = k; i <= numItems - 2; i++)
				item[i] = item[i+1];
			numItems--;
			return true;
		}
	}

	public E get(int index) {
		if(index >= 0 && index <= numItems-1)
			item[index] = x;
		else return null;
	}

	public void set(int index, E x) {
		if(index >= 0 && index <= numItems-1)
			item[index] = x;
		else { /*에러 처리*/ }
	}

	private final int NOT_FOUND = -12345;
	public int indexOf(E x) {
		int i = 0;
		for(i = 0; i < numItems; i++) {
			if(((Comparable)item[i]).compareTo(x) == 0)
				return i;
		}
		return NOT_FOUND;
	}

	public int len() {
		return numItems;
	}

	public boolean isEmpty() {
		returm numItems == 0;
	}

	public void clear() {
		item = (E[]) new Object[item.length];
		numItems = 0;
	}
}
```

<br>

## 3️⃣  연결 리스트

```java
public class LinkedList<E> implements ListInterface<E> {
	private Node<E> head;
	private int numItems;

	public LinkedList() {
		numItems = 0;
		head = new Node<>(null, null);
	}

	public void add(int index, E item) {
		if(index >= 0 && index <= numItems) {
			Node<E> prevNode = getNode(index - 1);
			Node<E> newNode = new Node<>(item, preNode.next);
			
			prevNode.next = newNode;
			numItems++;
		}
	}

	public void append(E item) {
		Node<E> prevNode = head;
		
		while(prevNode.next != null) {
			prevNode = prevNode.next;
		}
		
		Node<E> newNode = new Node<>(item, null);
		prevNode.next = newNode;
		numItems++;
	}

	public E remove(int index) {
		if(index >= 0 && index < numItems) {
			Node<E> prevNode = getNode(index - 1);
			Node<E> currNode = prevNode.next;
			prevNode.next = currNode.next;
			numItems--;
			return currNode.item;
		} else 
			return null;
	}

	public boolean removeItem(E x) {
		Node<E> currNode = head;
		Node<E> prevNode;
		for(int i = 0; i < numItems; i++) {
			prevNode = currNode;
			currNode = currNode.next;
			if(((Comparable)(currNode.item)).compareTo(x) == 0) {
				prevNode.next = currNode.next;
				numItems--;
				return true;
			}
		}
		return false;
	}

	public E get(int index) {
		if(index >= 0 && index <= numItems - 1) {
			return getNode(index).item;
		} else 
			return null;
	}

	public Node<E> getNode(int index) {
		if(index >= -1 && index <= numItems - 1) {
			Node<E> currNode = head;
			for(int i = 0; i <= index; i++) {
				currNode = currNode.next;
			}
			return currNode;
		} else { 
			return null;
		}
	}

	public void set(int index, E x) {
		if(index >= 0 && index <= numItems - 1) {
			getNode(index).item = x;
		} else { /*에러 처리*/ }
	}

	public final int NOT_FOUND = -12345;
	public int indexOf(E x) {
		Node<E> currNode = head;
		int i;
		for(i = 0; i < numItems; i++) {
			currNode = currNode.next;
			if(((Comparable)(currNode.item)).compareTo(x) == 0)
				return i;
		}
		return NOT_FOUND;
	}

	public int len() {
		return numItems;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public void clear() {
		numItems = 0;
		head = new Node<>(null, null);
	}
}
```

<br>

## 4️⃣  배열 리스트와 연결 리스트의 비교

#### 배열 리스트
* **정적**: 시작부터 고정된 크기를 지정
* 연속된 공간에 원소를 저장
	- 정렬/인덱스 검색 작업이 비교적 빠름
* 원소 하나당 필요한 공간이 비교적 작음

#### 연결 리스트
* **동적**: 원소가 들어오는 대로 공간을 할당
* 공간의 연속성이 없음
	- 정렬/인덱스 검색 작업이 비교적 느림
* 원소 하나당 필요한 공간이 비교적 큼 (공간 낭비)

#### 공통점
* 검색 작업은 똑같은 시간이 걸림
	- 크기 순으로 정렬된 경우에는 
		+ 배열 O(log n) -> 이진 탐색 알고리즘
		+ 연결 O(n)
	- 