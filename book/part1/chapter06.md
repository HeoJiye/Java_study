# chapter06 스택

## 1️⃣ 스택 인터페이스
```java
	public interface StackInterface<E> {
		public void push(E newItem);
		public E pop();
		public E top();
		public boolean isEmpty();
		public void popAll();
	}
```

<br>

## 2️⃣ 배열을 이용한 스택
```java
	public class ArrayStack<E> implements StackInterface<E>{
		private E stack[];
		private int topIndex;	// 스택의 탑 인덱스
		private static final int DEFAULT_CAPACITY = 64;
		private final E ERROR = null;	// 임의의 에러 값
		
		public ArrayStack() {
			stack = (E[]) new Object[DEFAULT_CAPACITY];
			topIndex = -1;
		}
		
		public ArrayStack(int n) {
			stack = (E[]) new Object[n];
			topIndex = -1;
		}
		
		@Override
		public void push(E newItem) {
			if(isFull()) { /*에러 처리*/}
			else stack[++topIndex] = newItem;
		}

		@Override
		public E pop() {
			if(isEmpty()) return ERROR;
			else return stack[topIndex];
		}

		@Override
		public E top() {
			if(isEmpty()) return ERROR;
			else return stack[topIndex];
		}

		@Override
		public boolean isEmpty() {
			return (topIndex < 0);
		}
		
		public boolean isFull() {
			return (topIndex == stack.length-1);
		}

		@Override
		public void popAll() {
			stack = (E[]) new Object[stack.length];
			topIndex = -1;
		}
	}
```

<br>

## 3️⃣ 연결 리스트를 이용한 스택
* [import list.Node;](chapter05.md#노드)
```java
import list.Node;

public class LinkedStack<E> implements StackInterface<E> {
	private Node<E> topNode;
	private final E ERROR = null;

	public LinkedStack() {
		topNode = null;
	}

	public void push(E newItem) {
		topNode = new Node<>(newItem, topNode);
	}

	public E top() {
		if(isEmpty()) return ERROR;
		else {
			Node<E> temp = topNode;
			topNode = topNode.next;
			return temp.item;
		}
	}

	public E top() {
		if(isEmpty()) return ERROR;
		else return topNode.item;
	}

	public boolean isEmpty() {
		return (topNode == null);
	}

	public void popAll() {
		topNode = null;
	}
}
```

<br>

## 4️⃣ 다른 클래스를 재사용한 스택
* [import list.LinkedList;](chapter05.md#연결-리스트)
```java
public class InheritedStack<E> extends LinkedStack<E> implements StackInterface<E> {
	
	public InheritedStack() {
		super();
	}

	public void push(E newItem) {
		add(0, newItem);
	}

	public E pop() {
		if(!isEmpty()) {
			return remove(0);
		} else return null;
	}

	public E top() {
		return get(0);
	}

	public void popAll() {
		clear();
	}
}
```

<br>

## 5️⃣ 스택 응용
#### 1. 문자열 뒤집기
```java
public class ReverseString {

	public static void main(String[] args) {
		String input = "Test Seq 12345";
		String t = reverse(input);
		System.out.println("Input string: " + input);
		System.out.println("Reversed String: " + t);
	}

	private static String reverse(String s) {
		ArrayStack<Character> st = new ArrayStack<>(s.length());
		for(int i = 0; i < s.length(); i++)
			st.push(s.charAt(i));
		String output = "";
		while(!st.isEmpty())
			output = output + st.pop();
		return output;
	}
}
```

#### 2. Postfix 계산
```java
public class PostfixEval {
	public static void main(String[] args) {
		String postfix = "700 3 47 + 6 * - 4 /";
		System.out.println("Input string: " + postfix);

		int answer = evaluate(postfix);
		System.out.println("Answer: " + answer);
	}

	private static int evaluate(String postfix) {
		int A, B;
		LinkedStack<Integer> s = new LinkedStack<>();
		boolean digitPreviously = false;
		for(int i = 0; i < postfix.length(); i++) {
			char ch = postfix.charAt(i);
			if(Character.isDigit(ch)) {
				if(digitPreviously == true) {
					int tmp = s.pop();
					tmp = 10*tmp + (ch - '0');
					s.push(tmp);
				} else s.push(ch - '0');
				digitPreviously = true;
			} else if(isOperator(ch)) {
				A = s.pop();
				B = s.pop();
				int val = operation(A, B, ch);
				s.push(val);
				digitPreviously = false;
			} else digitPreviously = false;
		}
		return s.pop();
;	}

}
```