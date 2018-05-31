
package forms;

import org.hibernate.validator.constraints.NotBlank;

public class PaymentForm {

	private String	shipmentCountry;
	private String	shipmentCity;
	private String	shipmentPostalCode;
	private String	shipmentAdress;

	private String	name;
	private String	surname;
	private String	country;
	private String	city;
	private String	postalCode;
	private String	IDNumber;


	@NotBlank
	public String getShipmentCountry() {
		return this.shipmentCountry;
	}

	public void setShipmentCountry(final String shipmentCountry) {
		this.shipmentCountry = shipmentCountry;
	}

	@NotBlank
	public String getShipmentCity() {
		return this.shipmentCity;
	}

	public void setShipmentCity(final String shipmentCity) {
		this.shipmentCity = shipmentCity;
	}

	@NotBlank
	public String getShipmentPostalCode() {
		return this.shipmentPostalCode;
	}

	public void setShipmentPostalCode(final String shipmentPostalCode) {
		this.shipmentPostalCode = shipmentPostalCode;
	}

	@NotBlank
	public String getShipmentAdress() {
		return this.shipmentAdress;
	}

	public void setShipmentAdress(final String shipmentAdress) {
		this.shipmentAdress = shipmentAdress;
	}

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
	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	@NotBlank
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotBlank
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	@NotBlank
	public String getIDNumber() {
		return this.IDNumber;
	}

	public void setIDNumber(final String iDNumber) {
		this.IDNumber = iDNumber;
	}

}
