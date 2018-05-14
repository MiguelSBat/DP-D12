
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Business extends Actor {

	private String				name;
	private String				surname;
	private String				VATNumber;
	private Boolean				premium;
	private Integer				reputation;
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
	public String getVATNumber() {
		return this.VATNumber;
	}

	public void setVATNumber(final String VATNumber) {
		this.VATNumber = VATNumber;
	}

	public Boolean isPremium() {
		return this.premium;
	}

	public void setPremium(final Boolean premium) {
		this.premium = premium;
	}

	@NotNull
	@Range(min = 1, max = 5)
	public Integer getReputation() {
		return this.reputation;
	}

	public void setReputation(final Integer reputation) {
		this.reputation = reputation;
	}

	public Boolean isSuspicious() {
		return this.suspicious;
	}

	public void setSuspicious(final Boolean suspicious) {
		this.suspicious = suspicious;
	}

	public Collection<String> getPhotosURL() {
		return this.photosURL;
	}

	public void setPhotosURL(final Collection<String> photosURL) {
		this.photosURL = photosURL;
	}


	//Relationships

	private Collection<Answer>	answers;
	private BusinessInfo		businessInfo;


	@OneToMany
	public Collection<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(final Collection<Answer> answers) {
		this.answers = answers;
	}

	@OneToMany
	public BusinessInfo getBusinessInfo() {
		return this.businessInfo;
	}

	public void setBusinessInfo(final BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

}
