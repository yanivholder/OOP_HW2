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
		return this.id;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public Person getPublisher() {
		return this.publisher;
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

	protected boolean eq(Object o) {
		if (!(o instanceof StatusImpl)) return false;
		return publisher.equals(((StatusImpl)o).publisher) &&
				id.equals(((StatusImpl)o).id);
	}
	@Override
	public boolean equals(Object o) {
		return (this.eq(o) && ((StatusImpl)o).eq(this));
	}
}
