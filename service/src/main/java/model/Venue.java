package model;

public class Venue {
	private String id, name, city, country, countrycode;

	public Venue(String pId) {
		id = pId;
	}
	public String getId() {
		return id.replace("sr:venue:", "");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.replace("'", " ");
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
