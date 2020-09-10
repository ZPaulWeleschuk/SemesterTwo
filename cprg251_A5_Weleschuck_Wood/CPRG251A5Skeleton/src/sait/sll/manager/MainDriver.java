package sait.sll.manager;

import java.io.*;

import sait.sll.problemdomain.Node;
import sait.sll.problemdomain.User;
/**
 * 
 * @author Zennon Weleschuck and Joel Wood
 * @version 1.1 July 21 2020
 * Tests methods implemented in the SLL class
 *
 */
public class MainDriver implements Serializable {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//for testing code
		User alice = new User(1234, "Alice", "email@gmail.com", "PaSsWord");
		User joel = new User(4321, "Joel", "JW@gmail.com", "wordPass");
		User zen = new User(69420, "zen", "zw@gmail.com", "drowssap");
		User ali = new User(9876, "Ali", "ALi@sait.ca", "password123");
		
		/*serialize(alice);
		/serialize(joel);
		serialize(zen);
		serialize(ali);
		
		deserialize(alice);
		deserialize(joel);
		deserialize(zen);
		deserialize(ali);*/
		
		SLL sll = new SLL();
		sll.append(alice); // append works
		//sll.print();
		sll.append(joel);
		sll.prepend(ali); // prepend is  works 
		
		//serialize(sll);
		//deserialize(sll);
		
		sll.print();
		
		//sll.print();
		
		//sll.insert(zen, 2); //insert works
		//sll.replace(zen, 1); //replace works
		//sll.append(zen);
		 
		//System.out.println(sll.isEmpty()); //isempty works
		//System.out.println(sll.size()); // size works
		//sll.clear(); // clear works)
		//System.out.println(sll.isEmpty());
		//System.out.println(sll.size());
		//System.out.println(sll.retrieve(3)); // retrieve is working
		//System.out.println(sll.indexOf(alice)); // indexOf is working
		//System.out.println(sll.contains(ali));
		//System.out.println(sll.contains(zen));//contains is working
		//sll.delete(1); // delete is working now
		//sll.print();

	}
	/*public static void serialize(SLL u) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		for (int i = 0; i < u.size(); i++) {
			oos.writeObject(u.retrieve(i));
		}
		
		oos.close();
		baos.close();
	}
	
	public static void deserialize(SLL u) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bais = new ByteArrayInputStream();
		ObjectInputStream ois = new ObjectInputStream(bais);
		for (int i = 0; i < u.size(); i++) {
			ois.readObject();
			
		}
		ois.close();
		bais.close();
	}*/
	

}
