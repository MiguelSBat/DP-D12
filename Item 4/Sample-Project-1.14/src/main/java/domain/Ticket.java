
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Ticket extends DomainEntity {

	private Date	date;
	private String	status;


	@NotNull
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}
	@NotNull
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships

	private User		user;
	private Business	bussiness;


	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public Business getBussiness() {
		return this.bussiness;
	}

	public void setBussiness(final Business bussiness) {
		this.bussiness = bussiness;
	}

}
