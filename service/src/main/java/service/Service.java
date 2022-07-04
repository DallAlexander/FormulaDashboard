package service;

import model.Competitor;
import model.Probability;
import model.Result;
import model.Stage;
import model.TeamConnector;
import model.Venue;

public class Service {
	static Datenbankverbindung datenbank;
	static FormelEConnection api;
	private static String[] seasons = { "sr:stage:943661", "sr:stage:621117"/*, "sr:stage:539718","sr:stage:416398"
			,"sr:stage:361171"*/  };

	static void addCompetitors() {
		api = new FormelEConnection("####");
		datenbank = new Datenbankverbindung();
		try {
			Thread.sleep(1000);
			for (String stage : seasons) {
				Competitor c[] = api.getCompetitors(stage);
				for (Competitor comp : c) {
					datenbank.addCompetitor(comp.getId(), comp.getName(), comp.getGender(),
					comp.getNationality(), comp.getCountryCode());
					Thread.sleep(1000);
					api.updateCompetitorDetails(comp);
					datenbank.updateCompetitor(comp);
				}
				Thread.sleep(1000);
			}
			datenbank.closeConnection();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			datenbank.closeConnection();
		}
	}

	static void addVenues() {
		FormelEConnection api = new FormelEConnection("####");
		Datenbankverbindung datenbank = new Datenbankverbindung();
		for (String stage : seasons) {
			Venue v[] = api.getVenues(stage);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Venue ven : v) {
				datenbank.addVenue(ven.getId(), ven.getName(), ven.getCity(), ven.getCountrycode(), ven.getCountry());
			}
		}
	}

	static void addStages() throws InterruptedException {
		FormelEConnection api = new FormelEConnection("####");
		Datenbankverbindung datenbank = new Datenbankverbindung();
		for (String season : seasons) {
			Stage[] stages = api.getChildStages(season);
		//Stage[] stages = api.getChildStages("sr:stage:416398");
			Thread.sleep(1000);
			for (int i = 0; i < stages.length; i++) {
				datenbank.addStage(stages[i]);
				Stage[] races = api.getChildStages("sr:stage:" + stages[i].getId());
				for (Stage race : races) {
					datenbank.addStage(race);
				}
			}
		}
	}

	static void addTeamCompetitors() throws InterruptedException {
		FormelEConnection api = new FormelEConnection("####");
		Datenbankverbindung datenbank = new Datenbankverbindung();
		for (String stage : seasons) {
			TeamConnector[] teams = api.getTeams(stage);
			Thread.sleep(1000);
			for (int i = 0; i < teams.length; i++) {
				/*datenbank.addTeam(teams[i].getTeam().getId(), teams[i].getTeam().getName(),
						teams[i].getTeam().getGender(), teams[i].getTeam().getCountrycode(),
						teams[i].getTeam().getCountry());*/
				datenbank.addTeamCompetitor(teams[i].getCompetitorId1(), teams[i].getCompetitorId2(),
						teams[i].getTeam().getId(), teams[i].getStageId());
			}
		}
	}

	static void addResult() throws Exception{
		FormelEConnection api = new FormelEConnection("####");

		for(int a = 0; a<seasons.length;a++)
		{
			Thread.sleep(1000);
			Result[] r = api.getResults(seasons[a]);
			addResultsToDB(r);
			Thread.sleep(1000);
			String[] race = api.getChildStageIds(seasons[a]);	
			for(int b = 0; b<race.length;b++) {
				Thread.sleep(1000);
				r = api.getResults(race[b]);
				addResultsToDB(r);
				Thread.sleep(1000);
				String[] run = api.getChildStageIds(race[b]);
				for(int c = 0;c<run.length;c++){
					Thread.sleep(1000);
					r = api.getResults(run[c]);
					addResultsToDB(r);
				}
			}
		}
	}
	
	static void addResultsToDB(Result[] arr) {
		Datenbankverbindung datenbank = new Datenbankverbindung();
		if(!(arr==null)) {
		if(!(arr[0]==null)) {
			if(!(arr[0].getCompetitor_id()==null))
		for (int i = 0; i < arr.length; i++) {
			datenbank.addResults(arr[i]);
		}
	}
		}
		datenbank.closeConnection();
	}
	
	static void addprobs() throws Exception{
		FormelEConnection api = new FormelEConnection("####");
		Datenbankverbindung datenbank = new Datenbankverbindung();
		for (String stage : seasons) {
			Probability[] probs = api.getProbabilities(stage);
			Thread.sleep(1000);
			for (int i = 0; i < probs.length; i++) {
				datenbank.addSeasonPropabilities(probs[i].getId(),stage.replace("sr:stage:", ""),probs[i].getPropType() , probs[i].getDesc(),probs[i].getTeamId() , probs[i].getTeamProp(), probs[i].getCompId(), probs[i].getCompProp());
			}
		}
	}
	
	public static void main(String... args) {
		try {
			addCompetitors();
			addVenues();
			addStages();
			addTeamCompetitors();
			addResult();
			addprobs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
