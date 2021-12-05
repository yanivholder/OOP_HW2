package OOP2.Solution;
import OOP2.Provided.FaceOOP;
import OOP2.Provided.*;

import java.util.*;
import java.util.HashSet;

public class FaceOOPImpl implements FaceOOP, Iterable<Person> {


    public static class StatusIteratorImpl implements StatusIterator {

        private Iterator<Status> it;

        StatusIteratorImpl(Iterator<Status> it) {
            this.it = it;
        }
        public boolean hasNext() {
            return it.hasNext();
        }
        public Status next() {
            return it.next();
        }
        public void remove() { }
    }
    public static class ComparePersonsById implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getId() - p2.getId();
        }
    }
    public static class CompareStatusByRecent implements Comparator<Status> {
        @Override
        public int compare(Status s1, Status s2) {
            int persons_id_diff = s1.getPublisher().getId() - s2.getPublisher().getId();
            if(persons_id_diff != 0) {
                return persons_id_diff;
            }
            else {
                return s2.getId() - s1.getId();
            }
        }
    }
    public static class CompareStatusByPopular implements Comparator<Status> {
        @Override
        public int compare(Status s1, Status s2) {
            int persons_id_diff = s1.getPublisher().getId() - s2.getPublisher().getId();
            if(persons_id_diff != 0) {
                return persons_id_diff;
            }
            else {
                return s2.getLikesCount() - s1.getLikesCount();
            }
        }
    }

	private List<Person> users = new ArrayList<>();

	public FaceOOPImpl()
	{}

	@Override
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException {
		Person new_user = new PersonImpl(id, name);
		if (users.contains(new_user)) {
			throw new PersonAlreadyInSystemException();
		}
		else {
			users.add(new_user);
		}
        return new_user;
	}

	@Override
	public int size() {
		return users.size();
	}
	@Override
	public Person getUser(Integer id) throws PersonNotInSystemException {
        for (Person p : users) {
			if (p.getId().equals(id)){
				return p;
			}
		}
		throw new PersonNotInSystemException();
	}
	@Override
    public void addFriendship(Person p1, Person p2)
            throws PersonNotInSystemException, SamePersonException,
            ConnectionAlreadyExistException {
		if (!users.contains(p1) || !users.contains(p2)){
			throw new PersonNotInSystemException();
		}
		else {
			p1.addFriend(p2);
			p2.addFriend(p1);
		}
	}
    @Override
    public Iterator<Person> iterator() {
        this.users.sort(new ComparePersonsById());
        return this.users.iterator();
    }
    // A function to create a collection with all of p's friends statuses
    private List<Status> CreateFriendStatusesCollection(Person p) {
        List<Status> status_list = new ArrayList<>();
        for(Person tmp_p: p.getFriends()) {
            for(Status s: tmp_p.getStatusesRecent()) {
                status_list.add(s);
            }
        }
        return status_list;
    }
    @Override
    public StatusIterator getFeedByRecent(Person p) throws PersonNotInSystemException {
        if(!this.users.contains(p))
            throw new PersonNotInSystemException();
        List<Status> p_friends_statuses = CreateFriendStatusesCollection(p);
        p_friends_statuses.sort(new CompareStatusByRecent());
        return new StatusIteratorImpl(p_friends_statuses.iterator());
    }
    @Override
    public StatusIterator getFeedByPopular(Person p) throws PersonNotInSystemException {
        if(!this.users.contains(p))
            throw new PersonNotInSystemException();
        List<Status> p_friends_statuses = CreateFriendStatusesCollection(p);
        p_friends_statuses.sort(new CompareStatusByPopular());
        return new StatusIteratorImpl(p_friends_statuses.iterator());
    }

    public Integer rank(Person source, Person target) throws PersonNotInSystemException, ConnectionDoesNotExistException {

        if(!this.users.contains(source) || !this.users.contains(target))
            throw new PersonNotInSystemException();

        //
        Queue<Person> q = new LinkedList<>();
        Hashtable<Integer, Boolean> visited = new Hashtable<>();
        Hashtable<Integer, Integer> d = new Hashtable<>();

        int V = this.size();
        for (Person it : this.users) {
            d.put(it.getId(), V+1);
            visited.put(it.getId(), Boolean.FALSE);
        }

        visited.put(source.getId(), Boolean.TRUE);
        d.put(source.getId(), 0);

        for (Person temp : source.getFriends()) {
            d.put(temp.getId(), 1);
            visited.put(temp.getId(), Boolean.TRUE);
            q.add(temp);
        }

        while (!q.isEmpty()) {
            Person u = q.poll();

            for (Person temp : u.getFriends()) {
                if (visited.get(temp.getId()) == Boolean.FALSE){
                    visited.put(temp.getId(), Boolean.TRUE);
                    d.put(temp.getId(), d.get(u.getId()) + 1);
                    q.add(temp);
                }
            }
        }
        if (d.get(target.getId()) == V+1){
            throw new ConnectionDoesNotExistException();
        }else{
            return d.get(target.getId());
        }
    }






}
