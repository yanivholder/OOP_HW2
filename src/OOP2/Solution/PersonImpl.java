package OOP2.Solution;

import OOP2.Provided.*;

import java.util.*;

class CompareStatusByRecent implements Comparator<Status> {
	@Override
	public int compare(Status s1, Status s2) {
		return s1.getId() - s2.getId();
	}
}

class CompareStatusByLikesThenRecent implements Comparator<Status> {
	@Override
	public int compare(Status s1, Status s2) {
		int likes = s1.getLikesCount() - s2.getLikesCount();
		if(likes != 0) {
			return likes;
		}
		else {
			return s1.getId() - s2.getId();
		}
	}
}

public class PersonImpl implements Comparable<Person>, Person {

	private Integer id;
	private String name;
	private Integer num_of_statuses = 0;
	private Set<Status> statuses = new TreeSet<>();
	private Set<Person> friends = new TreeSet<>();

	public PersonImpl(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public Integer getId() {
		return this.id;
	}
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public Status postStatus(String content)
	{
		Status new_status = new StatusImpl(this, content, this.num_of_statuses);
		this.num_of_statuses++;
		statuses.add(new_status);
		return new_status;
	}
	@Override
	public void addFriend(Person p) throws SamePersonException, ConnectionAlreadyExistException {
		if(p.getId() == this.id)
		{
			throw new SamePersonException();
		}
		if(this.friends.contains(p))
		{
			throw new ConnectionAlreadyExistException();
		}
		this.friends.add(p);
	}
	@Override
	public Collection<Person> getFriends() {
		return this.friends;
	}
	@Override
	public Iterable<Status> getStatusesRecent()
	{
		// TODO: maybe need to change the order here
		List<Status> status_list = new ArrayList<>(this.statuses);
		Collections.sort(status_list, new CompareStatusByRecent());
		return status_list;
	}
	@Override
	public Iterable<Status> getStatusesPopular()
	{
		// TODO: maybe need to change the order here
		List<Status> status_list = new ArrayList<>(this.statuses);
		Collections.sort(status_list, new CompareStatusByLikesThenRecent());
		return status_list;
	}
	@Override
	public int compareTo(Person p) {
		return this.id - p.getId();
	}
	protected boolean eq(Object o)
	{
		if(!(o instanceof PersonImpl)) {
			return false;
		}
		return ((PersonImpl)o).id == this.id;
	}
	@Override
	public boolean equals(Object o) {
		return (this.eq(o) && ((PersonImpl)o).eq(this));
	}
	@Override
	public int hashCode() {
		return this.id;
	}
}


