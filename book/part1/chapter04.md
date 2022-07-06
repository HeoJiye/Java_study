# chapter04 자바 기초
2022.07.01 학습

### 01. 클래스

* 클래스란?
	- 클래스는 '객체'를 만드는 수단이고 객체지향언어인 자바는 모든 것이 클래스를 중심으로 구성됨
	- 클래스는 어떤 대상을 추상화하는 의미 단위이기도 함

* 클래스의 전형적인 구조 = Field + Constructor + Method

```java
class Heap {
	// Field
	private int[] A;
	private int numItems;

	// Constructor
	public Heap(int n) {
		A = new int[n];
		numItems = 0;
	}
	// Method
	public boolean isEmpty() {
		return numItem == 0;
	}
	public void insert(int newItem) {
		...
	}
	private void percolateUp(int i) {
		...
	}
	public int delete() {
		...
	}
	public static void main(String[] args) {
		...
	}
}
```

* 생성자의 역할
	- 생성자는 해당 클래스의 객체가 하나 만들어질 때 자동으로 수행되어 객체를 초기화하는 역할을 함

* main() 메소드
	- main()는 프로그램의 수행을 시작하는 메서드
	- main()는 반드시 어떤 클래스 안에서 정의되어야 함

<br>

### 02. 자바 기초 문법

#### a. 변수의 타입과 상수

* **기초 타입 변수 8가지**
	- 정수
		+ byte (4bit)
		+ short (16bit): 64K
		+ int (4byte) : 4G
		+ long (8byte)
	- 실수
		+ float (32bit) 
		+ double (52bit)
	- 문자
		+ char (16bit): Unicode
	- 부울
		+ boolean (1byte)
		
**기초 타입 이외의 타입은 모두 클래스이다 (배열도 포함) -> 레퍼런스 변수**

* Object 클래스란?
	- 모든 클래스의 상위 클래스
	- import할 필요없이 사용
	- **8가지 기초 타입을 객체로 만들어주는 클래스**
		+ Byte, Short, Integer, Long, Float, Double, Character, Boolean

* 타입 앞에 **final** 키워드를 투가하면 상수로 선언 (값 변경 불가능)

#### b. 프린트 
#### c. 제어

#### d. 메소드에 변수나 배열 넘기기: 복사호출과 참조호출
* **복사 호출**
	- 변수 x의 값을 넘김
	- <u>값 변형 -> 원본에 영향 X</u>
* **참조 호출**
	- 변수 x의 레퍼런스(식별자, 포인터 또는 주소값)를 넘김
	- <u>값 변형 -> 원본에 영향 O</u>
	
**<u>자바는 파라미터 값을 복사호출 방식으로 전달함</u>**  
**배열은 참조호출 (포인터 전달)**

<br>

### 04. 제네릭
```java
	public interface InterfaceA {
		public void insert(String x);
		public Object search(String x);
		public void remove(String x);
	}
```
```java
	public interface InterfaceB<E> {
		public void insert(E x);
		public Object search(E x);
		public void remove(E x);
	}
```
```java
	public interface InterfaceC<E, T> {
		public void insert(E x);
		public T search(E x);
		public void remove(E x);
	}
```

```java
	public class ClassA<E> implements InterfaceB<E> {

		@Override
		public void insert(E x) {
			
		}

		@Override
		public Object search(E x) {
			
			return null;
		}

		@Override
		public void remove(E x) {
			
		}
	}
```
```java
	public class ClassB implements InterfaceB<Integer>{
		@Override
		public void insert(Integer x) {
			
		}

		@Override
		public Object search(Integer x) {
			
			return null;
		}

		@Override
		public void remove(Integer x) {
			
		}
	}
```

<br>

### 05. 패키지

* 패키지란?
	- 클래스와 인터페이스들을 모아놓은 것
	- 패키지속 패키지 계층적 구조 가능

* java.lang: import 하지 않아도 사용 가능함
	- Object
	- String
	- Wrapper Class (ex. Integer)
	- Stream: 입출력 장치 연결

<br>

### 06. 프로그램 수행

* main(): 수행 시작점
	- main()이 있는 클래스 / 시작을 위한 클래스 / main()가 없는 클래스
	- 반드시 **public static void**가 앞에 붙음

* 에러 처리 구조
```java
	// 에러 클래스 설계
	class HeapException extends Exception { 
		public HeapException (String msg) {
			super(msg);
		}
	}

	public void insert(int newItem) throws HeapException {
		if (size == maxHeap) {
			throws new HeapException("Overflow in insert()");
		} else {
			/* 정상 수행 */
		}
	}

	public static void main(String[] args) {
		Heap h = new Heap(3);

		try {
			h.insert(1);
			h.insert(10);
			h.insert(20);
			h.insert(30);
			// 이 시점에서 Exception 객체가 날라와 catch로 넘어감
			// 뒷 부분 수행 X
			h.insert(40);
		} catch (HeapException ex) {
			System.out.println("HeapException: " + ex.getMessage());
		}
	} 
```

* try-catch-finally 구조
```java
	try {
		...
	} catch (...) {
		...
	} catch (...) {
		...
	} finally {
		// 임의의 catch 처리 후 마지막으로 처리할 일
	}
```

* 메모리 사용 관행
	- 정적/동적 영역
		+ 동적 영역: 힙/스택
		+ 정적 영역: 데이터/코드
	- 가비지 컬렉션
		+ 동적 영역에서 할당 받았다가 더 이상 쓰지 않는 메모리 공간을 회수하는 것
		+ C에서는 사용자의 영역이지만, 자바는 가상머신에서 알아서 처리 
	
레퍼런스 변수는 소속 함수의 스택 영역에 저장됨. 해당 함수가 끝나면 해당 레퍼런스 변수의 메모리는 회수됨

<br>

### +) 혼자 공부하는 자바 참고

#### 06-5. 인스턴스 멤버와 정적 멤버

##### **인스턴스 멤버**
: 객체마다 가지고 있는 멤버

* **인스턴스 메소드**
	- 객체에 소속된 멤버지만, 객체 내부에 존재하지 않고 메소드 영역에 저장되고 공유됨
	- 메소드는 코드 블록이기 때문에
	- <u>인스터스 메소드는 객체 생성없이 사용할 수 없음</u>
* **this**
	- 인스턴스 멤버인 필드를 명시하고자 사용

##### **정적 멤버**
: 클래스에 위치시키고 객체들이 공유하는 멤버

* **static**을 사용해서 선언
* 정적 멤버와 메소드는 클래스가 메모리로 로딩되면 바로 사용가능
	- 클래스.필드;
	- 클래스.메소드( ... );
* 인스턴스 필드나 인스턴스 메소드 사용 불가, this 키워드도 사용 불가
