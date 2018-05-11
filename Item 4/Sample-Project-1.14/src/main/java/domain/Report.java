
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	public String	text;
	public Integer	weight;


	@NotNull
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@NotNull
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(final Integer weight) {
		this.weight = weight;
	}


	//Relationships

	private Actor	actor;


	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

}
