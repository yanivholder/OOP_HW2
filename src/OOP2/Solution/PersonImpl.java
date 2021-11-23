package OOP2.Solution;

import OOP2.Provided.*;

import java.util.Collection;
import java.util.TreeSet;
import java.util.Set;
import java.util.Comparator;
//import java.util.stream.Collectors;

public class PersonImpl implements Person, Comparable<PersonImpl> {

	private Integer id = 0;
	private String name = "";
	private Integer num_of_statuses = 0;
	private Set<Status> statuses;
	private Set<Person> friends;

	public PersonImpl(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.statuses = new TreeSet<>();
		this.friends = new TreeSet<>();
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
		return friends;
	}
	@Override
	public Iterable<Status> getStatusesRecent()
	{
		// TODO: maybe need to change the order here
		Comparator<Status> CompareStatusByRecent = (s1, s2) -> s1.getId() - s2.getId();
		(this.statuses.stream()
				.sorted(CompareStatusByRecent)
				.collect(Collectors.toList())).iterator();
	}
	@Override
	public Iterable<Status> getStatusesPopular()
	{
		// TODO: maybe need to change the order here
		Comparator<Status> CompareStatusByLikesThenRecent = (s1, s2) ->
				(Integer likes = s1.getLikesCount() - s2.getLikesCount();
		if(likes != 0) {
			return likes;
		}
		else {
			return s1.getId() - s2.getId();
		});

		(this.statuses.stream()
				.sorted(CompareStatusByLikesThenRecent)
				.collect(Collectors.toList())).iterator();
	}
	@Override
	public int compareTo(PersonImpl p) {
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
	public static void main (String[] args) {
		System.out.println("Hello World!");
	}
}


