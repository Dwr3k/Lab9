import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Dog 
{
	private String name;
	private int count;

	public Dog(String dogName, int number)
	{
		name = dogName;
		count = number;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		File file =  new File("Dog_Names.csv");
		Scanner fileReader = new Scanner(file);

		ArrayList<Dog> list = new ArrayList<>();
		fileReader.nextLine();

		while(fileReader.hasNext())
		{
			String line = fileReader.nextLine();
			String values[] = line.split(",");

			String name = values[0];
			int number = Integer.valueOf(values[1]);

			list.add(new Dog(name, number));
		}

		fileReader.close();

		Scanner in = new Scanner(System.in);
		System.out.println("Which part would you like today?");
		int part = in.nextInt();

		if(part == 1)
		{
			System.out.println("Enter a dog's name");
			String answer = in.next();
			
			for(Dog dog : list)
			{
				if(dog.name.equals(answer.toUpperCase()))
				{
					System.out.println(answer + " is registered " + dog.count + " times");
				}
			}
		}
		else if(part == 2)
		{
			Dog.sorter sorter = new Dog.sorter();
			Collections.sort(list, sorter);

			for(Dog dog : list)
			{
				System.out.println(dog.name);
			}
		}
		else if(part == 3)
		{	
			boolean done = false;
			int correct = 0;
			int total = 0;
			while(done == false)
			{
				Dog first = list.get((int)(Math.random() * (list.size() + 1)));
				Dog second = list.get((int)(Math.random() * (list.size() + 1)));
				
				while(first.count == second.count)
				{
					 first = list.get((int)(Math.random() * (list.size() + 1)));
					 second = list.get((int)(Math.random() * (list.size() + 1)));
				}

				System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)");
				System.out.println("1." + first.name + " 2." + second.name);
				
				int number = in.nextInt();
				
				if(number == 1)
				{
					if(first.count > second.count)
					{
						System.out.println("Correct!");
						++correct;
					}
					else
					{
						System.out.println("Nope, the more popular dog name is " + second.name + ".");
						System.out.println("There are " + second.count + " dogs named " + second.name + " and " + first.count + " named " + first.name);
					}
				}
				else if(number == 2)
				{
					if(first.count < second.count)
					{
						System.out.println("Correct!");
						++correct;
					}
					else
					{
						System.out.println("Nope, the more popular dog name is " + first.name + ".");
						System.out.println("There are " + first.count + " dogs named " + first.name + " and " + second.count + " named " + second.name);
					}
				}

				System.out.println("Do you want to play again? (Y/N)?");
				String answer = in.next();
				
				++total;

				if(answer.toUpperCase().equals("N"))
				{
					done = true;
					System.out.println("You guessed correctly " + correct + " out of " + total + " times.");
				}
			}
		}

		in.close();


	}

	public static class sorter implements Comparator<Dog>
	{
		@Override
		public int compare(Dog first, Dog second) 
		{	
			return first.name.compareTo(second.name);
		}	
	}


}