
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {

	private String	website;
	private String	nick;
	private String	accountURL;


	@NotBlank
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

	@URL
	@NotBlank
	public String getAccountURL() {
		return this.accountURL;
	}

	public void setAccountURL(final String accountURL) {
		this.accountURL = accountURL;
	}

}
