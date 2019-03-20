import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class Dog implements Comparator<String>
{
	private String name;
	private int count;

	public Dog(String dogName, int number)
	{
		name = dogName;
		count = number;
	}

	public static void main() throws FileNotFoundException
	{
		File file =  new File("Dog_Names.csv");
		Scanner fileReader = new Scanner(file);

		ArrayList<Dog> list = new ArrayList<>();

		while(fileReader.hasNext())
		{
			String line = fileReader.nextLine();
			String values[] = line.split(",");

			String name = values[0];
			int number = Integer.valueOf(values[1]);

			list.add(new Dog(name, number));
		}
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a Dog name");
		
		
		String input = in.next();
		
		for(Dog dog : list)
		{
			if(dog.name.equals(input.toUpperCase()))
			{
				System.out.println(input + " is registered " + dog.count + " times");
			}
		}
		
	}

	@Override
	public int compare(String o1, String o2) 
	{
		return o1.compareTo(o2);
	}

	
}
