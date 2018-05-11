
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	@Email
	@NotBlank
	private String	emailAddress;


	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}


	//Relationships

	private UserAccount			userAccount;
	private Collection<Folder>	folders;
	private Collection<Message>	messagesSent;
	private Collection<Message>	messagesReceived;
	private Collection<Report>	reports;
	private Valoration			valoration;


	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@OneToMany
	@NotNull
	public Collection<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(final Collection<Folder> folders) {
		this.folders = folders;
	}

	@OneToMany(mappedBy = "sender")
	@NotNull
	public Collection<Message> getMessagesSent() {
		return this.messagesSent;
	}

	@OneToMany(mappedBy = "recipient")
	@NotNull
	public Collection<Message> getMessagesReceived() {
		return this.messagesReceived;
	}

	public void setMessagesSent(final Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public void setMessagesReceived(final Collection<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public Collection<Report> getReports() {
		return this.reports;
	}

	public void setReports(final Collection<Report> reports) {
		this.reports = reports;
	}

	@OneToMany
	public Valoration getValoration() {
		return this.valoration;
	}

	public void setValoration(final Valoration valoration) {
		this.valoration = valoration;
	}

}
