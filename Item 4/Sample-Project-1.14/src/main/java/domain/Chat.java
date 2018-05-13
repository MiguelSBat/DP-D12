
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Chat extends DomainEntity {

	private String	text;
	private Date	date;


	@NotNull
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@NotNull
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}


	//Relationships

	private Actor	sender;
	private Actor	receiver;


	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@ManyToOne(optional = false)
	public Actor getReceiver() {
		return this.receiver;
	}

	public void setReceiver(final Actor receiver) {
		this.receiver = receiver;
	}
}