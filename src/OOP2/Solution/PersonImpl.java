package OOP2.Solution;

import OOP2.Provided.*;

import java.util.*;

public class PersonImpl implements Comparable<Person>, Person {

	private Integer id;
	private String name;
	private List<Status> statuses = new ArrayList<>();
	private Set<Person> friends = new HashSet<>();

	public static class CompareStatusByRecent implements Comparator<Status> {
		@Override
		public int compare(Status s1, Status s2) {
			return s2.getId() - s1.getId();
		}
	}

	public static class CompareStatusByLikesThenRecent implements Comparator<Status> {
		@Override
		public int compare(Status s1, Status s2) {
			int likes = s2.getLikesCount() - s1.getLikesCount();
			if(likes != 0) {
				return likes;
			}
			else {
				return s2.getId() - s1.getId();
			}
		}
	}

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
		Status new_status = new StatusImpl(this, content, this.statuses.size());
		statuses.add(new_status);
		return new_status;
	}
	@Override
	public void addFriend(Person p) throws SamePersonException, ConnectionAlreadyExistException {
		if(p.getId().equals(this.id))
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
		this.statuses.sort(new CompareStatusByRecent());
		return this.statuses;
	}
	@Override
	public Iterable<Status> getStatusesPopular()
	{
		this.statuses.sort(new CompareStatusByLikesThenRecent());
		return this.statuses;
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
		return ((PersonImpl)o).id.equals(this.id);
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


