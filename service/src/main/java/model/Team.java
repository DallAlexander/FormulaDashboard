package model;

public class Team {
	private String id, name, gender, country, countrycode;

	public Team(String pId) {
		id = pId;
	}
	public String getId() {
		return id.replace("sr:competitor:", "");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.replace("'", " ");
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
}
