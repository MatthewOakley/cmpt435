import java.util.Scanner;

public class stuff{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    int n = 0;
    System.out.print("Input a number: ");
    n = input.nextInt();
    double start = System.nanoTime();
    
    // input testing here
    
    double end = System.nanoTime();
    System.out.print(end - start);
    System.out.println(" nano seconds");
  }
}
  
/* 
// (1)        // COST TIME
sum = 0;      // c1   1
  for(i = 0;  // c2   1
    i < n;    // c3   n + 1
      i++)    // c4   n + 1
    sum++;    // c5   n
// n + (n + 1) + (n + 1) => (n)

  
// (2)            // COST TIME
sum = 0;          // c1   1
for(i = 0;        // c2   1
  i < n;          // c3   n + 1
    i++)          // c4   n + 1
      for(j = 0;  // c5   n + 1
        j < n;    // c6   n * n + 1
          j++)    // c7   n * n + 1
            sum++;// c8   n * n
// (n + 1) + (n + 1) + (n*n) + (n*n + 1) + (n*n + 1) + (n*n) => n^2


// (3)              // COST TIME
sum = 0;            // c1   1
for(i = 0;          // c2   1
  i < n;            // c3   n + 1
    i++)            // c4   n + 1
      for(j = 0;    // c5   n + 1
        j < n * n;  // c6   n * n^2 + 1
          j++)      // c7   n * n^2 + 1
            sum++;  // c8   n * n^2
// (n + 1) + (n + 1) + (n + 1) + (n * n^2 + 1) + (n * n^2 + 1) + (n * n^2) => n^3


// (4)              // COST TIME
sum = 0;            // c1   1
for(i = 0;          // c2   1
  i < n;            // c3   n + 1
    i++)            // c4   n + 1
      for(j = 0;    // c5   n + 1
        j < i;      // c6   n * (n + 1) / 2
          j++)      // c7   n * (n + 1) / 2
            sum++;  // c8   n * (n + 1) / 2
// (n + 1) + (n + 1) + (n + 1) + (n(n + 1) / 2) + (n(n + 1) / 2)  +  (n(n + 1) / 2) => (n^n / 2 )         


// (5)                      // COST TIME
sum = 0;                    // c1   1
for(i = 0;                  // c2   1
  i < n;                    // c3   n + 1
    i++)                    // c4   n + 1
      for(j = 0;            // c5   n + 1
        j < i * i;          // c6   n (n^n) + 1
          j++)              // c7   n (n^n) + 1
            for(k = 0;      // c8   n (n^n) + 1 
              k < j;        // c9   n (n^n) * n + 1
                k++)        // c10  n (n^n) * n + 1
                  sum++;    // c11  n (n^n) * n
// n^n


// (6)                        // COST TIME
sum = 0                       // c1   1
  for(i = 1;                  // c2   1
    i < n;                    // c3   n + 1
      i++)                    // c4   n + 1
        for(j = 1;            // c5   n + 1
          j < i * i;          // c6   n (n*n) + 1
            j++)              // c7   n (n*n) + 1
              if(j % i == 0)  // c8   n (n*n) + 1
                for(k = 0;    // c9   n idk
                  k < j;      // c10  n idk
                    k++)      // c11  n idk
                      sum++;  // c12  n idk
// n^n
*/