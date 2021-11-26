package OOP2.Solution;

import OOP2.Provided.Status;
import OOP2.Provided.*;
import java.util.*;


public class StatusImpl implements Status {

	private Integer id;
	private String content;
	private Person publisher;
	private Set<Integer> likers = new HashSet<>();

	public StatusImpl(Person publisher, String content, Integer id) {
		this.id = id;
		this.content = content;
		this.publisher = publisher;
	}

	@Override
	public Integer getId() {
		return new Integer(this.id);
	}

	@Override
	public String getContent() {
		return new String(this.content);
	}

	@Override
	public Person getPublisher() {
		return new PersonImpl(this.publisher.getId(), this.publisher.getName());
	}

	@Override
	public void like(Person person) {
		this.likers.add(person.getId());
	}

	@Override
	public void unlike(Person person) {
		this.likers.remove(person.getId());
	}

	@Override
	public Integer getLikesCount() {
		return this.likers.size();
	}
}
