package model;

public class Competitor {
	private String id, country, dateOfBirth, debut, firstPointsDate, firstPoleDate, firstVictoryDate, gender,name,nationality,website,placeOfBirth,vehicleNickname,countryCode, birthcode, height,salary,weight,originCountryCode,wCSWon;
	
	public Competitor(String pId) {
		this.id = pId;
		country= dateOfBirth= debut= firstPointsDate= firstPoleDate= firstVictoryDate= gender=name=nationality=website=placeOfBirth=vehicleNickname=countryCode= birthcode= height=salary=weight=originCountryCode=wCSWon = "null";
	}
	
	public String getBirthcode() {
		return birthcode;
	}

	public void setBirthcode(String birthcode) {
		this.birthcode = birthcode;
	}

	public String getId() {
		return id.replace("sr:competitor:", "");
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getFirstPointsDate() {
		return firstPointsDate;
	}
	public void setFirstPointsDate(String firstPointsDate) {
		this.firstPointsDate = firstPointsDate;
	}
	public String getFirstPoleDate() {
		return firstPoleDate;
	}
	public void setFirstPoleDate(String firstPoleDate) {
		this.firstPoleDate = firstPoleDate;
	}
	public String getFirstVictoryDate() {
		return firstVictoryDate;
	}
	public void setFirstVictoryDate(String firstVictoryDate) {
		this.firstVictoryDate = firstVictoryDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getVehicleNickname() {
		return vehicleNickname;
	}
	public void setVehicle(String vehicle) {
		this.vehicleNickname = vehicle;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public String getwCSWon() {
		return wCSWon;
	}

	public void setwCSWon(String wCSWon) {
		this.wCSWon = wCSWon;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVehicleNickname(String vehicleNickname) {
		this.vehicleNickname = vehicleNickname;
	}
	
}