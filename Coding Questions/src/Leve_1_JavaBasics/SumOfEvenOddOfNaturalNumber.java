package Leve_1_JavaBasics;

	import java.util.Scanner;
public class SumOfEvenOddOfNaturalNumber {

		public static void main(String args []){
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter The Nummber : ");
			int num = sc.nextInt();
			int oddres = 0;
			int everes = 0;
			for(int i = 1; i <= num; i++){
				if(i % 2 == 0){
					everes = everes + i;
				}
				else{
					oddres = oddres + i;
				}
			}
			System.out.println("The Sum of ODD of " + num + " Natural number is : " + oddres);
			System.out.print("The Sum of EVEN of " + num + " Natural number is : " + everes);
			sc.close();
		}
	}

