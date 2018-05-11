
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {

	private String	website;
	private String	nick;
	private String	accountURL;


	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(final String website) {
		this.website = website;
	}

	@NotBlank
	public String getNick() {
		return this.nick;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public String getAccountURL() {
		return this.accountURL;
	}

	public void setAccountURL(final String accountURL) {
		this.accountURL = accountURL;
	}


	//Relationships

	private User	user;


	@OneToMany
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
