
package forms;

import java.util.List;

public class PaymentResponse {

	private String			id;
	private String			state;
	private List<String>	errors;


	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public List<String> getErrors() {
		return this.errors;
	}

	public void setErrors(final List<String> errors) {
		this.errors = errors;
	}

}
