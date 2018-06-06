
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Business extends Actor {

	private String				name;
	private String				paypalEmail;
	private String				VATNumber;
	private Boolean				premium;
	private Boolean				suspicious;
	private Collection<String>	photosURL;
	private Boolean				verified;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getPaypalEmail() {
		return this.paypalEmail;
	}

	public void setPaypalEmail(final String paypalEmail) {
		this.paypalEmail = paypalEmail;
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

	public Boolean isSuspicious() {
		return this.suspicious;
	}

	public void setSuspicious(final Boolean suspicious) {
		this.suspicious = suspicious;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getPhotosURL() {
		return this.photosURL;
	}

	public void setPhotosURL(final Collection<String> photosURL) {
		this.photosURL = photosURL;
	}

	public Boolean isVerified() {
		return this.verified;
	}

	public void setVerified(final Boolean verified) {
		this.verified = verified;
	}

	public Boolean getPremium() {
		return this.premium;
	}

	public Boolean getSuspicious() {
		return this.suspicious;
	}

	public Boolean getVerified() {
		return this.verified;
	}


	//Relationships

	private Collection<Answer>			answers;
	private Collection<BusinessInfo>	businessInfos;


	@OneToMany
	public Collection<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(final Collection<Answer> answers) {
		this.answers = answers;
	}

	@OneToMany
	public Collection<BusinessInfo> getBusinessInfos() {
		return this.businessInfos;
	}

	public void setBusinessInfos(final Collection<BusinessInfo> businessInfos) {
		this.businessInfos = businessInfos;
	}

	public void addAnswer(final Answer answer) {
		this.answers.add(answer);
	}

	public void removeAnswer(final Answer answer) {
		this.answers.remove(answer);
	}

}
