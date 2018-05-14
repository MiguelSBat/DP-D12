
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
	private Integer				userMaxAnnouncements;
	private Integer				businessMaxAnnouncements;
	private Integer				premiumMaxAnnouncements;
	private Integer				announcementExpirationMonths;
	private Double				premiumPrice;
	private Integer				paymentHoldDays;
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
	public Integer getUserMaxAnnouncements() {
		return this.userMaxAnnouncements;
	}

	public void setUserMaxAnnouncements(final Integer userMaxAnnouncements) {
		this.userMaxAnnouncements = userMaxAnnouncements;
	}

	@NotNull
	public Integer getBusinessMaxAnnouncements() {
		return this.businessMaxAnnouncements;
	}

	public void setBusinessMaxAnnouncements(final Integer bussinessMaxAnnouncements) {
		this.businessMaxAnnouncements = bussinessMaxAnnouncements;
	}

	@NotNull
	public Integer getPremiumMaxAnnouncements() {
		return this.premiumMaxAnnouncements;
	}

	public void setPremiumMaxAnnouncements(final Integer premiumMaxAnnouncements) {
		this.premiumMaxAnnouncements = premiumMaxAnnouncements;
	}

	@NotNull
	@Range(min = 1, max = 12)
	public Integer getAnnouncementExpirationMonths() {
		return this.announcementExpirationMonths;
	}

	public void setAnnouncementExpirationMonths(final Integer announcementExpirationMonths) {
		this.announcementExpirationMonths = announcementExpirationMonths;
	}

	@NotNull
	public Double getPremiumPrice() {
		return this.premiumPrice;
	}

	public void setPremiumPrice(final Double premiumPrice) {
		this.premiumPrice = premiumPrice;
	}

	@NotNull
	public Integer getPaymentHoldDays() {
		return this.paymentHoldDays;
	}

	public void setPaymentHoldDays(final Integer paymentHoldDays) {
		this.paymentHoldDays = paymentHoldDays;
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
