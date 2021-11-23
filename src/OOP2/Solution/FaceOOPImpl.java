package OOP2.Solution;
import OOP2.Provided.FaceOOP;
import OOP2.Provided.*;

import java.util.List;

public class FaceOOPImpl implements FaceOOP {

	private List<Person> users = new List<Person>;


	/**
	 * Constructor - receives no parameters and initializes the system.
	 */
	public FaceOOPImpl()
	{}

	@Override
	public void joinFaceOOP(Integer id, String name) {
		Person new_user = new PersonImpl(id, name);
		if (users.contains(new_user)) {
			throw PersonAlreadyInSystemException;
		}
		else {
			users.add(new_user);
		}
	}

	@Override
	public Integer size() {
		return new Integer(users.size());
	}

	@Override
	public getUser(Integer id) {
		for (Person p : users) {
			if (p.id.equals(id)){
				return new p;
			}
		}
		throw PersonNotInSystemException;
	}

	@Override
	public void addFriendship(Person p1, Person p2){
		if (!user.contains(p1) || !(!user.contains(p1))){
			throw PersonNotInSystemException;
		}
		else {
			p1.addFriend(p2);
			p2.addFriend(p1);
		}
	}

	@Override
	public void getFeedByRecent(Person p){

	}







	class MyListIterator implements Iterator<T>{
		boolean hasNext() {

		}
		T next() {

		}
		void remove() {}
	}
	public Iterator<T> iterator(){
		return new MyListIterator();
	}
	
}
