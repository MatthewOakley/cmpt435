import java.util.Scanner;

public class fib{
  
  public static int posEfibonacci(int num){
    if(num <= 3)
      return 1;
    else
      return posEfibonacci(num - 1) + posEfibonacci(num - 2) + posEfibonacci(num - 3);
  }
  public static int negEfibonacci(int num){
    if(num >= -3)
      return -1;
    else
      return negEfibonacci(num + 1) - negEfibonacci(num + 2) - negEfibonacci(num + 3);
  }
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    int num = input.nextInt();
    int output = 0;
    if(num > 0)
      output = posEfibonacci(num);
    else
      output = negEfibonacci(num);
    System.out.println(output);
  }
}
