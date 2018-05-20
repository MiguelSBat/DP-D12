
package forms;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ActorForm {

	private String	username;
	private String	password;
	private String	email;
	private String	authority;
	private String	password2;
	private Boolean	agree;
	private String	name;
	private String	paypalEmail;
	private String	VATNumber;
	private String	phone;
	private String	surname;


	@Size(min = 5, max = 32)
	@Column(unique = true)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(final String authority) {
		this.authority = authority;
	}

	public String getPassword2() {
		return this.password2;
	}

	public void setPassword2(final String password2) {
		this.password2 = password2;
	}

	@AssertTrue
	public Boolean getAgree() {
		return this.agree;
	}

	public void setAgree(final Boolean agree) {
		this.agree = agree;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPaypalEmail() {
		return this.paypalEmail;
	}

	public void setPaypalEmail(final String paypalEmail) {
		this.paypalEmail = paypalEmail;
	}

	public String getVATNumber() {
		return this.VATNumber;
	}

	public void setVATNumber(final String vATNumber) {
		this.VATNumber = vATNumber;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}
}
