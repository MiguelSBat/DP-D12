
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	private String				name;
	private String				surname;
	private String				phone;
	private Boolean				premium;
	private Boolean				suspicious;
	private Collection<String>	photosURL;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@NotBlank
	@Pattern(regexp = "^\\+?\\d+$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Boolean isPremium() {
		return this.premium;
	}

	public void setPremium(final Boolean premium) {
		this.premium = premium;
	}

	public Boolean isSuspicious() {
		return this.suspicious;
	}

	public void setSuspicious(final Boolean suspicious) {
		this.suspicious = suspicious;
	}

	@NotNull
	@ElementCollection(targetClass = String.class)
	public Collection<String> getPhotosURL() {
		return this.photosURL;
	}

	public void setPhotosURL(final Collection<String> photosURL) {
		this.photosURL = photosURL;
	}


	//Relationships

	private Collection<SocialIdentity>	socialIdentities;


	@OneToMany
	public Collection<SocialIdentity> getSocialIdentities() {
		return this.socialIdentities;
	}

	public void setSocialIdentities(final Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	public void addSocialIdentity(final SocialIdentity socialIdentity) {
		this.socialIdentities.add(socialIdentity);
	}
	public void removeSocialIdentity(final SocialIdentity socialIdentity) {
		this.socialIdentities.remove(socialIdentity);
	}

}
