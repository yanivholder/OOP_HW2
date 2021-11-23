package OOP2.Solution;

import OOP2.Provided.Status;
import OOP2.Provided.*;
import java.util.*;


public class StatusImpl implements Status {

	private Integer id;
	private String content;
	private Person publisher;
	private Set<Person> likers = new HashSet<Person> ();


	protected boolean eq(StatusImpl s) {
		if (!(s instanceof StatusImpl)) return false;
		StatusImpl other = (StatusImpl)s;
		return publisher.equals(other.publisher) &&
				id.equals(other.id);
	}
	@Override
	public boolean equals(StatusImpl s) {
		return (this.eq(s) && ((StatusImpl)s).eq(this));
	}

	/*
	 * A constructor that receives the status publisher, the text of the status
	 *  and the id of the status.
	 */
	public StatusImpl(Person publisher, String content, Integer id) {
		id = id; content = content; publisher = publisher;
	}

	@Override
	public Integer getId() {
		return new Integer(id);
	}

	@Override
	public String getContent() {
		return new String(content);
	}

	@Override
	public Person getPublisher() {
		return new Person(publisher);
	}

	@Override
	public void like(Person person) {
		likers.add(person);
	}

	@Override
	public void unlike(Person person) {
		likers.remove(person);
	}

	@Override
	public Integer getLikesCount() {
		return new Integer(likers.size());
	}
}
