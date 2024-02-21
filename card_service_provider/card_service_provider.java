// put conditions as given in the question to determine what card the user holds
// use nested classes
import java.util.*;
public class card_service_provider
{
	static String ac;
	static long ac1;
	static int ac2, ac3, ac4;
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your 16-digit account number:");
		ac = sc.nextLine();

		if(ac.length() != 16)
		{
			System.out.println("Invalid input. System will terminate.");
			System.exit(0);
		}

		try
		{
			ac1 = Long.parseLong(ac);
		}
		catch(Exception e)
		{
			System.out.println("Invalid input. System will terminate.");
			System.exit(0);
		}

		card_service_provider obMain = new card_service_provider();
		obMain.operate();
	}

	void operate()
	{
		ac4 = (int)(ac1 % 10000);
		ac1 = ac1 / 10000;
		ac3 = (int)(ac1 % 10000);
		ac1 = ac1 / 10000;
		ac2 = (int)(ac1 % 10000);
		ac1 = ac1 / 10000;

		visa obVisa = new visa();
		master obMaster = new master();
		rupay obRupay = new rupay();
		amex obAmex = new amex();

		if(obVisa.check())
			System.out.println("VISA");
		else if(obMaster.check())
			System.out.println("Mastercard");
		else if(obRupay.check())
			System.out.println("RuPay");
		else if(obAmex.check())
			System.out.println("Amex");
		else 
			System.out.println("Not found");
	}

	int sum_of_digits(int n)
	{
		if(n > 0)
		{
			return (n % 10) + sum_of_digits(n / 10);
		}
		return 0;
	}

	class visa
	{
		boolean check()
		{
			int n1 = (sum_of_digits((int)(ac1)) + sum_of_digits(ac2)) % (ac2 % 100);
			int n2 = (sum_of_digits(ac3) + sum_of_digits(ac4)) % (ac4 % 100);

			if(n1 < 5 && n2 < 5)
				return true;
			else 
			{	
				// System.out.println(ac1 + " " + ac2 + " " + ac3 + " " + ac4);
				// System.out.println(n1 + " " + n2);
				return false;
			}
		}
	}

	class master
	{
		boolean check()
		{
			int n1 = sum_of_digits((int)(ac1)) * sum_of_digits(ac2) % (ac2 / 100);
			int n2 = sum_of_digits(ac3) * sum_of_digits(ac4) % (ac3 / 100);

			if(n1 > 20 && n1 < 40 && n2 < 40 && n2 > 20)
				return true;
			else 
				return false;
		}
	}

	class rupay
	{
		boolean check()
		{
			int n1 = sum_of_digits((int)(ac1)) * sum_of_digits(ac2) % (ac2 / 100);
			int n2 = sum_of_digits(ac3) * sum_of_digits(ac4) % (ac3 / 100);

			if(n1 / n2 > 10)
				return true;
			else 
				return false;
		}
	}

	class amex
	{
		boolean check()
		{
			int n1 = sum_of_digits((int)(ac1)) * sum_of_digits(ac2) % (ac2 / 100);
			int n2 = sum_of_digits(ac3) * sum_of_digits(ac4) % (ac3 / 100);

			if(n2 / n1 > 20 && n2 / n1 < 50)
				return true;
			else 
				return false;
		}
	}
}