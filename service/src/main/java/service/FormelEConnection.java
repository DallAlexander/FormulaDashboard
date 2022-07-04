package service;

import java.text.Format;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TimeZone;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.Competitor;
import model.Probability;
import model.Result;
import model.Stage;
import model.Team;
import model.TeamConnector;
import model.Venue;

public class FormelEConnection {
	private String baseUrl, token;
	static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("mm:ss.SSS");

	public FormelEConnection(String pToken) {
		this.baseUrl = "https://api.sportradar.us/formulae/trial/v2";
		this.token = pToken;

		try {
			HttpResponse<JsonNode> response = Unirest
					.get(this.baseUrl + "/en/competitors/sr:competitor:422427/profile.json?api_key=" + token).asJson();
			System.out.println(response.getStatus());

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// works
	public Competitor[] getCompetitors(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/summary.json?api_key=" + token;
		System.out.println(url);
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			System.out.println(response.getBody());

			JSONObject stage = response.getBody().getObject().getJSONObject("stage");
			JSONArray competitors = stage.getJSONArray("competitors");

			Competitor c[] = new Competitor[competitors.length()];

			for (int i = 0; i < competitors.length(); i++) {
				c[i] = new Competitor((competitors.getJSONObject(i).getString("id")));
				c[i].setName(competitors.getJSONObject(i).getString("name"));
				c[i].setGender(competitors.getJSONObject(i).getString("gender"));
				c[i].setNationality(competitors.getJSONObject(i).getString("nationality"));
				c[i].setCountryCode(competitors.getJSONObject(i).getString("country_code"));
			}
			return c;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Probability[] getProbabilities(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/probabilities.json?api_key=" + token;
		
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			System.out.println(response.getBody());

			JSONObject stage = response.getBody().getObject().getJSONObject("probabilities");
			JSONArray markets = stage.getJSONArray("markets");
			JSONObject team = markets.getJSONObject(0);
			JSONArray teamOutcomes = team.getJSONArray("outcomes");
			JSONObject competitor = markets.getJSONObject(1);
			JSONArray competitorOutcomes = competitor.getJSONArray("outcomes");
			

			Probability p[] = new  Probability[teamOutcomes.length()+competitorOutcomes.length()];
			int i;
			
			for (i = 0; i < teamOutcomes.length(); i++) {
				p[i] = new Probability(teamOutcomes.getJSONObject(i).getString("id"));
				p[i].setDesc(team.getString("description"));
				p[i].setPropType(team.getString("type"));
				p[i].setTeamId((teamOutcomes.getJSONObject(i).getJSONObject("team").getString("id")));
				p[i].setTeamProp(Double.toString(teamOutcomes.getJSONObject(i).getDouble("probability")));
			}
			for (int j = 0; j < competitorOutcomes.length(); j++) {
				p[i+j] = new Probability(competitorOutcomes.getJSONObject(j).getString("id"));
				p[i+j].setDesc(competitor.getString("description"));
				p[i+j].setPropType(competitor.getString("type"));
				p[i+j].setCompId ((competitorOutcomes.getJSONObject(j).getJSONObject("competitor").getString("id")));
				p[i+j].setCompProp(Double.toString(competitorOutcomes.getJSONObject(j).getDouble("probability")));
			}
			return p;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public TeamConnector[] getTeams(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/summary.json?api_key=" + token;
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			System.out.println(response.getBody());

			JSONObject stage = response.getBody().getObject().getJSONObject("stage");
			JSONArray teams = stage.getJSONArray("teams");

			TeamConnector t[] = new TeamConnector[teams.length()];

			for (int i = 0; i < teams.length(); i++) {
				t[i] = new TeamConnector();
				Team team = new Team((teams.getJSONObject(i).getString("id")));
				team.setName(teams.getJSONObject(i).getString("name"));
				team.setGender(teams.getJSONObject(i).getString("gender"));
				team.setCountry(teams.getJSONObject(i).getString("nationality"));
				team.setCountrycode(teams.getJSONObject(i).getString("country_code"));
				t[i].setTeam(team);
				t[i].setStageId(pStage);
				if (teams.getJSONObject(i).has("competitors")) {
					JSONArray arr = teams.getJSONObject(i).getJSONArray("competitors");

					if (arr.length() > 0)
						t[i].setCompetitorId1(arr.getJSONObject(0).getString("id"));
					if (arr.length() == 2)
						t[i].setCompetitorId2(arr.getJSONObject(1).getString("id"));
				}

			}
			return t;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Result[] getResults(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/summary.json?api_key=" + token;
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();

			JSONObject stage = response.getBody().getObject().getJSONObject("stage");
			if (stage.has("competitors")) {
				JSONArray competitors = stage.getJSONArray("competitors");
				Result[] rueckgabe = new Result[competitors.length()];
				JSONObject result;
				String fastestlaptime = "";
				String time = "";

				for (int m = 0; m < competitors.length(); m++) {
					result = competitors.getJSONObject(m).optJSONObject("result");

					if (result.has("fastest_lap_time")) {
						String flt = String.valueOf(result.get("fastest_lap_time"));
						if (!flt.contains("+")) {
							fastestlaptime = flt;
						}
					}
					if (result.has("time")) {
						String t = String.valueOf(result.opt("time"));
						if (!t.contains("+") && !t.contains("l")) {
							time = t;
						}
					}
				}
				for (int i = 0; i < competitors.length(); i++) {
					Result r = new Result();
					result = competitors.getJSONObject(i).optJSONObject("result");

					if (!(result == null)) {
						r.setStage_id(pStage);
						r.setCompetitor_id(competitors.getJSONObject(i).getString("id"));
						r.setResult_points(String.valueOf(result.opt("points")));
						r.setResult_car_number(String.valueOf(result.opt("car_number")));
						r.setResult_position(String.valueOf(result.opt("position")));
						r.setResult_victories(String.valueOf(result.opt("victories")));
						r.setResult_races(String.valueOf(result.opt("races")));
						r.setResult_races_with_points(String.valueOf(result.opt("races_with_points")));
						r.setResult_pole_positions(String.valueOf(result.opt("pole_positions")));
						r.setResult_podiums(String.valueOf(result.opt("podiums")));
						r.setResult_fastest_laps(String.valueOf(result.opt("fastest_laps")));
						r.setResult_status(String.valueOf(result.opt("status")));
						r.setResult_grid(String.valueOf(result.opt("grid")));
						r.setResult_fanboost(String.valueOf((result.opt("fanboost"))));

						if (fastestlaptime.length() > 1) {
							if (result.has("fastest_lap_time") && result.getString("fastest_lap_time").contains("+")) {
								r.setResult_fastest_lap_time(
										calculateTime(fastestlaptime, String.valueOf(result.opt("fastest_lap_time"))));
							} else if (result.has("fastest_lap_time")) {
								r.setResult_fastest_lap_time(
										calculateTime(result.getString("fastest_lap_time"), "+0.000"));
							}
						}
						if (time.length() > 1) {
							if (time.matches((".*:.*:.*:.*"))&&result.has("time")) {
								r.setResult_time_total(calculateTotalTime2018(time, result.getString("time")));
							} else if (result.has("time") && result.getString("time").contains("+")) {
								r.setResult_time_total(calculateTime(time, String.valueOf(result.opt("time"))));
							} else if (result.has("time")) {
								r.setResult_time_total(calculateTime(result.getString("time"), "+0.000"));
							}
						}

					}
					rueckgabe[i] = r;
				}

				return rueckgabe;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String calculateTime(String pTime, String pDiff) {
		long mil1, min1, sec1, mil2, min2, sec2;
		Duration dGesamt;
		mil1 = Long.valueOf(pTime.substring(pTime.lastIndexOf(".") + 1, pTime.length()));
		if (pTime.contains(":")) {
			sec1 = Long.valueOf(pTime.substring(pTime.lastIndexOf(":") + 1, pTime.lastIndexOf(".")));
		} else {
			sec1 = Long.valueOf(pTime.substring(0, pTime.lastIndexOf(".")));
		}

		if (pTime.contains(":")) {
			min1 = Long.valueOf(pTime.substring(0, pTime.lastIndexOf(":")));
		} else {
			min1 = 0;
		}
		if (!(pDiff.contains("L") || pDiff.contains("l"))) {
			mil2 = Long.valueOf(pDiff.substring(pDiff.lastIndexOf(".") + 1, pDiff.length()));
			if (pDiff.contains(":")) {
				sec2 = Long.valueOf(pDiff.substring(pDiff.lastIndexOf(":") + 1, pDiff.lastIndexOf(".") - 1));
				min2 = Long.valueOf(pDiff.substring(0, pDiff.lastIndexOf(":")));
			} else {
				min2 = 0;
				sec2 = Long.valueOf(pDiff.substring(1, pDiff.lastIndexOf(".")));
			}
		} else {
			mil2 = sec2 = min2 = 1;
		}
		Duration d = Duration.ZERO;
		if (!pTime.equals(pDiff)) {
			dGesamt = d.plusMinutes(min2).plusMinutes(min1).plusSeconds(sec2).plusSeconds(sec1).plusMillis(mil2)
					.plusMillis(mil1);
		} else {
			dGesamt = d.plusMinutes(min1).plusSeconds(sec1).plusMillis(mil1);
		}

		return String.format("%02d:%02d:%02d.%03d", dGesamt.toHours(), dGesamt.toMinutesPart(), dGesamt.toSecondsPart(),
				dGesamt.toMillisPart());
	}

	public Stage[] getChildStages(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/summary.json?api_key=" + token;
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			JSONObject stage = response.getBody().getObject().getJSONObject("stage");
			JSONArray stages = stage.getJSONArray("stages");
			String parent = stage.getString("id");

			Stage rueckgabe[] = new Stage[stages.length() + 1];
			JSONObject venue = stage.optJSONObject("venue");

			rueckgabe[0] = new Stage(((String) stage.opt("id")));
			rueckgabe[0].setDescription((String) stage.opt("description"));
			rueckgabe[0].setScheduled((String) stage.opt("scheduled"));
			rueckgabe[0].setScheduled_end((String) stage.opt("scheduled_end"));
			rueckgabe[0].setSingle_event(String.valueOf(stage.optBoolean("single_event")));
			rueckgabe[0].setStatus((String) stage.opt("status"));
			if (!(venue == null))
				rueckgabe[0].setVenue_id((String) venue.opt("id"));
			rueckgabe[0].setStage_type((String) stage.opt("type"));
			rueckgabe[0].setLaps(String.valueOf(stage.optInt("laps")));

			for (int i = 0; i < stages.length(); i++) {
				venue = stages.getJSONObject(i).optJSONObject("venue");
				rueckgabe[i + 1] = new Stage(((String) stages.getJSONObject(i).opt("id")));
				rueckgabe[i + 1].setDescription((String) stages.getJSONObject(i).opt("description"));
				rueckgabe[i + 1].setScheduled((String) stages.getJSONObject(i).opt("scheduled"));
				rueckgabe[i + 1].setScheduled_end((String) stages.getJSONObject(i).opt("scheduled_end"));
				rueckgabe[i + 1].setSingle_event(String.valueOf(stages.getJSONObject(i).optBoolean("single_event")));
				rueckgabe[i + 1].setStatus((String) stages.getJSONObject(i).opt("status"));
				if (!(venue == null))
					rueckgabe[i].setVenue_id((String) venue.get("id"));
				rueckgabe[i + 1].setParent_stage(parent);
				rueckgabe[i + 1].setStage_type((String) stages.getJSONObject(i).opt("type"));
				rueckgabe[i + 1].setLaps(String.valueOf(stages.getJSONObject(i).optInt("laps")));
			}
			return rueckgabe;

		} catch (UnirestException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String[] getChildStageIds(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/summary.json?api_key=" + token;
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			JSONObject stage = response.getBody().getObject().getJSONObject("stage");
			JSONArray stages = stage.getJSONArray("stages");
			String[] ids = new String[stages.length()];
			for (int i = 0; i < stages.length(); i++) {
				ids[i] = stages.getJSONObject(i).getString("id");
			}
			return ids;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Venue[] getVenues(String pStage) {
		String url = this.baseUrl + "/en/sport_events/" + pStage + "/summary.json?api_key=" + token;
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			JSONObject stage = response.getBody().getObject().getJSONObject("stage");
			JSONArray stages = stage.getJSONArray("stages");
			Venue venues[] = new Venue[stages.length()];

			for (int i = 0; i < stages.length(); i++) {
				if (stages.getJSONObject(i).has("venue")) {
					JSONObject temp = stages.getJSONObject(i).getJSONObject("venue");
					venues[i] = new Venue(temp.getString("id"));
					venues[i].setName((String) temp.opt("name"));
					venues[i].setCity((String) temp.opt("city"));
					venues[i].setCountrycode((String) temp.opt("country_code"));
					venues[i].setCountry((String) temp.opt("country"));
				} else {
					venues[i] = new Venue("0");
				}
			}
			return venues;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String calculateTotalTime2018(String pTime, String pDiff) {
		long h1, mil1, min1, sec1, mil2, min2, sec2;
		Duration dGesamt;
		String temp = pTime;
		h1 = Long.valueOf(pTime.substring(0, pTime.indexOf(":")));
		temp = pTime.substring(pTime.indexOf(":") + 1, pTime.length());
		min1 = Long.valueOf(temp.substring(0, temp.indexOf(":")));
		temp = temp.substring(temp.indexOf(":") + 1, temp.length());
		sec1 = Long.valueOf(temp.substring(0, temp.indexOf(":")));
		temp = temp.substring(temp.indexOf(":") + 1, temp.length());
		mil1 = Long.valueOf(temp);

		if(pTime == pDiff) {
			mil2=min2=sec2=0;
		}
		else if (!pDiff.contains("l")) {
			mil2 = Long.valueOf(pDiff.substring(pDiff.lastIndexOf(".") + 1, pDiff.length()));
			if (pDiff.contains(":")) {
				sec2 = Long.valueOf(pDiff.substring(pDiff.lastIndexOf(":") + 1, pDiff.lastIndexOf(".") - 1));
				min2 = Long.valueOf(pDiff.substring(0, pDiff.lastIndexOf(":")));
			} else {
				min2 = 0;
				sec2 = Long.valueOf(pDiff.substring(0, pDiff.lastIndexOf(".")));
			}
		} else {
			mil2 = sec2 = min2 = 1;
		}
		Duration d = Duration.ZERO;
		if (!pTime.equals(pDiff)) {
			dGesamt = d.plusMinutes(min2).plusMinutes(min1).plusSeconds(sec2).plusSeconds(sec1).plusMillis(mil2)
					.plusMillis(mil1).plusHours(h1);
		} else {
			dGesamt = d.plusMinutes(min1).plusSeconds(sec1).plusMillis(mil1).plusHours(h1);
		}

		return String.format("%02d:%02d:%02d.%03d", dGesamt.toHours(), dGesamt.toMinutesPart(), dGesamt.toSecondsPart(),
				dGesamt.toMillisPart());
	}

	public void updateCompetitorDetails(Competitor comp) throws Exception {
		String url = this.baseUrl + "/en/competitors/sr:competitor:" + comp.getId() + "/profile.json?api_key=" + token;
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();

		// JSONObject competitorjson =
		// response.getBody().getObject().getJSONObject("competitor"); FEHLER IN API
		if (response.getBody().getObject().has("info")) {
			JSONObject infojson = response.getBody().getObject().getJSONObject("info");

			/*
			 * c.setName(competitorjson.getString("name"));
			 * c.setGender(competitorjson.getString("gender"));
			 * c.setNationality(competitorjson.getString("nationality"));
			 * c.setCountryCode(competitorjson.getString("country_code"));
			 */

			comp.setWeight((String) infojson.opt("weight"));
			comp.setDateOfBirth((String) infojson.opt("date_of_birth"));
			comp.setCountry((String) infojson.opt("country_of_residence"));
			comp.setCountryCode((String) infojson.opt("country_code_of_residence"));
			comp.setHeight((String) infojson.opt("height"));
			comp.setSalary((String) infojson.opt("salary"));
			comp.setPlaceOfBirth((String) infojson.opt("place_of_birth"));
			comp.setDebut((String) infojson.opt("debut"));
			comp.setFirstPointsDate((String) infojson.opt("first_points"));
			comp.setFirstPoleDate((String) infojson.opt(""));
			comp.setFirstVictoryDate((String) infojson.opt(""));
			comp.setVehicleNickname((String) infojson.opt("vehicle_nickname"));
		}
	}

}
