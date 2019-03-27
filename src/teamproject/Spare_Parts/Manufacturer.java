package teamproject.Spare_Parts;

public class Manufacturer {

	private String name;
	private String email;
	private String telephone;

	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	/**
	 *
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 *
	 * @param name
	 * @param email
	 * @param telephone
	 */
	public static Manufacturer Manufacturer(String name, String email, String telephone) {
		// TODO - implement Manufacturer.Manufacturer
		throw new UnsupportedOperationException();
	}

}
