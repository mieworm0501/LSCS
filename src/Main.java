
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int T;
		int t;
		int position;
		int attack;
		int health;
		int attacker;
		int defender;
		String doWhat;
		T = scan.nextInt();
		hero []a = new hero [2];
		a[0] = new hero();
		a[1] = new hero();
		int TIME=0;
		for(t = 0; t<T ; t++)
		{
			doWhat = scan.next();
			if(doWhat.equals("summon"))
			{
				position = scan.nextInt();
				attack = scan.nextInt();
				health = scan.nextInt();
				a[TIME].addmem(position, attack, health);
			}
			if(doWhat.equals("end"))
			{
				TIME++;
				if(TIME==2)
				{
					TIME=0;
				}
			}
			if(doWhat.equals("attack"))
			{
				attacker = scan.nextInt();
				defender = scan.nextInt();
				if(defender>0)
				{
					a[TIME].member[attacker-1][1] = a[TIME].member[attacker-1][1] - a[(TIME+1)%2].member[defender-1][0];
					a[(TIME+1)%2].member[defender-1][1] = a[(TIME+1)%2].member[defender-1][1] - a[TIME].member[attacker-1][0];
					a[TIME].iflive(attacker);
					a[(TIME+1)%2].iflive(defender);
				}
				else
				{
					a[(TIME+1)%2].hp = a[(TIME+1)%2].hp - a[TIME].member[attacker-1][0];
				}
			}
			if(t>1)
			{
				if(a[(TIME+1)%2].hp<=0)
				{
					break;
				}
			}
		}
		if(a[0].hp<=0)
			System.out.println(-1);
		else
		{
			if(a[1].hp<=0)
			System.out.println(1);
			else
			System.out.println(0);
		}
		System.out.println(a[0].hp);
		System.out.print(a[0].mem);
		for(int i=0;i<a[0].mem;i++)
		{
			System.out.print(' ');
			System.out.print(a[0].member[i][1]);
		}
		
		System.out.print('\n');
		System.out.println(a[1].hp);
		System.out.print(a[1].mem);
		for(int i=0;i<a[1].mem;i++)
		{
			System.out.print(' ');
			System.out.print(a[1].member[i][1]);
		}
		
		scan.close();
	}
}

class hero
{
	int hp=30;
	int mem=0;
	int [][]member = new int [7][2];
	void addmem(int position,int attack,int health)
	{
		mem++;
		if(member[position-1][1]==0)
		{
			member[position-1][0]=attack;
			member[position-1][1]=health;
		}
		else
		{
			for(int i=mem-1;i>position-1;i--)
			{
				member[i][0]=member[i-1][0];
				member[i][1]=member[i-1][1];
			}
			member[position-1][0]=attack;
			member[position-1][1]=health;
		}
	}
	void iflive(int position)
	{
		if(member[position-1][1]<=0)
		{
			mem--;
			for(int i=position-1;i<mem;i++)
			{
				member[i][0]=member[i+1][0];
				member[i][1]=member[i+1][1];
			}
			member[mem][0]=0;
			member[mem][1]=0;
		}
	}
}

