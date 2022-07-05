# chapter02 재귀와 귀납적 사고

**재귀 구조 예시**

1. 수열
	- 등차수열
		``` java
		int seq(int n) {
			if(n == 1) return 1;
			else return seq(n-1) + 3;
		}
		```
	- 피보나치 수열
		+ 재귀 버전
			``` java
			int fib(int n) {
				if (n == 1 or n == 2) return 1;
				else return fib(n-1) + fib(n-2);
			}
			```
		+ 반복 버전
			``` java
			int fib_fast(int n) {
				int[] f = new int[n];
				
				f[0] = f[1] = 1;
				for(int i = 3; i < n; i++) {
					f[i] = f[i-1] + f[i-2];
				}
				return f[n-1];
			}
			```
1. 하노이 탑
	``` java
		void move(int n, char a, char b, char c) {
			if(n > 0) {
				move(n-1, a, c, b);
				System.out.println(a + "에 있는 원판을 " + b + "로 옮긴다.");
				move(n-1, c, b, a);
			}
		}
	```
1. 선택 정렬
	``` java
		
	```