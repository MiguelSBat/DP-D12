
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Config extends DomainEntity {

	private Double				VAT;
	private String				englishWelcomeMessage;
	private String				spanishWelcomeMessage;
	private Integer				userMaxAdvertisements;
	private Integer				businessMaxAdvertisements;
	private Integer				premiumMaxAdvertisements;
	private Integer				advertisementExpirationMonths;
	private Double				premiumPrice;
	private Integer				transactionReportWeight;
	private Integer				reportWeightTreshold;
	private Integer				reputationTreshold;
	private Collection<String>	spamWords;


	@NotNull
	@Range(min = 0, max = 1)
	public Double getVAT() {
		return this.VAT;
	}

	public void setVAT(final Double vAT) {
		this.VAT = vAT;
	}

	@NotNull
	public String getEnglishWelcomeMessage() {
		return this.englishWelcomeMessage;
	}

	public void setEnglishWelcomeMessage(final String englishWelcomeMessage) {
		this.englishWelcomeMessage = englishWelcomeMessage;
	}

	@NotNull
	public String getSpanishWelcomeMessage() {
		return this.spanishWelcomeMessage;
	}

	public void setSpanishWelcomeMessage(final String spanishWelcomeMessage) {
		this.spanishWelcomeMessage = spanishWelcomeMessage;
	}

	@NotNull
	public Integer getUserMaxAdvertisements() {
		return this.userMaxAdvertisements;
	}

	public void setUserMaxAdvertisements(final Integer userMaxAnnouncements) {
		this.userMaxAdvertisements = userMaxAnnouncements;
	}

	@NotNull
	public Integer getBusinessMaxAdvertisements() {
		return this.businessMaxAdvertisements;
	}

	public void setBusinessMaxAdvertisements(final Integer bussinessMaxAnnouncements) {
		this.businessMaxAdvertisements = bussinessMaxAnnouncements;
	}

	@NotNull
	public Integer getPremiumMaxAdvertisements() {
		return this.premiumMaxAdvertisements;
	}

	public void setPremiumMaxAdvertisements(final Integer premiumMaxAnnouncements) {
		this.premiumMaxAdvertisements = premiumMaxAnnouncements;
	}

	@NotNull
	@Range(min = 1, max = 12)
	public Integer getAdvertisementExpirationMonths() {
		return this.advertisementExpirationMonths;
	}

	public void setAdvertisementExpirationMonths(final Integer announcementExpirationMonths) {
		this.advertisementExpirationMonths = announcementExpirationMonths;
	}

	@NotNull
	public Double getPremiumPrice() {
		return this.premiumPrice;
	}

	public void setPremiumPrice(final Double premiumPrice) {
		this.premiumPrice = premiumPrice;
	}

	@NotNull
	public Integer getTransactionReportWeight() {
		return this.transactionReportWeight;
	}

	public void setTransactionReportWeight(final Integer transactionReportWeight) {
		this.transactionReportWeight = transactionReportWeight;
	}

	@NotNull
	public Integer getReportWeightTreshold() {
		return this.reportWeightTreshold;
	}

	public void setReportWeightTreshold(final Integer reportWeightTreshold) {
		this.reportWeightTreshold = reportWeightTreshold;
	}

	@NotNull
	public Integer getReputationTreshold() {
		return this.reputationTreshold;
	}

	public void setReputationTreshold(final Integer reputationTreshold) {
		this.reputationTreshold = reputationTreshold;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final Collection<String> spamWords) {
		this.spamWords = spamWords;
	}

}
