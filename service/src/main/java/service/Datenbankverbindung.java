package service;

import java.io.FileWriter;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import model.Competitor;
import model.Result;
import model.Stage;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import java.io.IOException;

public class Datenbankverbindung {
	private static String url = "jdbc:mysql://####.mysql.database.azure.com:3306/####?useSSL=true";
	private Connection con;

	public Datenbankverbindung() {

		try {
			con = DriverManager.getConnection(url, "####", "####");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCompetitor(String id, String name, String gender, String nationality, String countycode) {
		String query = "INSERT INTO formele.competitor" + "(id,name,gender,country_of_residence_code,nationality)"
				+ "VALUES('" + id + "','" + name + "','" + gender + "','" + countycode + "','" + nationality
				+ "')ON DUPLICATE KEY UPDATE `id` = `id`;";

		System.out.println(query);
		Statement st;
		try {
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCompetitor(Competitor c) {
		String query = "UPDATE formele.competitor SET " + "country_of_birth_code =" + parse(c.getBirthcode())
				+ ",date_of_birth =" + parse(c.getDateOfBirth()) + ",salary=" + parse(c.getSalary()) + ",height="
				+ parse(c.getHeight()) + ",weight=" + parse(c.getWeight()) + ",debut=" + parse(c.getDebut())
				+ ",firstpointsdate=" + parse(c.getFirstPointsDate()) + ",firstpoledate=" + parse(c.getFirstPoleDate())
				+ ",firstvictorydate=" + parse(c.getFirstVictoryDate()) + ",placeofbirth=" + parse(c.getPlaceOfBirth())
				+ ",vehicleNickname=" + parse(c.getVehicleNickname()) + ",wcswon=" + parse(c.getwCSWon())
				+ " WHERE id = " + c.getId() + ";";
		System.out.println(query);
		try {
			Statement st;
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addTeam(String id, String name, String gender, String countrycode, String country) {
		String query = "INSERT INTO formele.team" + "(id,name,gender,country_code,country)" + "VALUES('" + id + "','"
				+ name + "','" + gender + "','" + countrycode + "','" + country
				+ "')ON DUPLICATE KEY UPDATE `id` = `id`;";
		System.out.println(query);
		try {
			Statement st;
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addVenue(String id, String name, String city, String countrycode, String country) {
		if (!id.equals("0")) {
			String query = "INSERT INTO formele.venue" + "(id,name,city,country_code,country)" + "VALUES('" + id + "','"
					+ name + "','" + city + "','" + countrycode + "','" + country
					+ "')ON DUPLICATE KEY UPDATE `id` = `id`;";
			System.out.println(query);

			try {
				Statement st;
				st = con.createStatement();
				st.execute(query);
			} catch (Exception e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void addTeamCompetitor(String competitor1Id, String competitor2Id, String teamId, String SeasonId) {
		String query = "INSERT INTO formele.team_competitor"
				+ "(team_id,competitor1_id,competitor2_id,stage_id)VALUES('" + teamId + "'," + competitor1Id + ","
				+ competitor2Id + ",'" + SeasonId + "')ON DUPLICATE KEY UPDATE `team_id` = `team_id`;";
		System.out.println(query);
		try {
			Statement st;
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addSeasonPropabilities(String pId,String pSeasonId, String pPropType, String pDesc, String pTeamId, String pTeamProp, String pCompId, String pCompProp) {
		String query = "INSERT INTO formele.season_propabilities"
				+ "(season_id,propabilities_type,description,outcomes_team_id,outcomes_team_propability,outcomes_competitor_id,outcomes_competitor_propability,id)VALUES" +
				"(" + pSeasonId + ",'" + pPropType + "','"+ pDesc + "'," +pTeamId + "," +pTeamProp + "," +pCompId + "," +pCompProp  +"," +pId +")ON DUPLICATE KEY UPDATE id = id;";
		System.out.println(query);
		try {
			Statement st;
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	public void addStage(Stage pStage) {
		String query = "INSERT INTO	formele.stage(id,description,scheduled,scheduled_end,stage_type,status,single_event,venue_id,parent_stage,laps)VALUES("
				+ parse(pStage.getId()) + "," + parse(pStage.getDescription()) + "," + parse(pStage.getScheduled())
				+ "," + parse(pStage.getScheduled_end()) + "," + parse(pStage.getStage_type()) + ","
				+ parse(pStage.getStatus()) + "," + parse(pStage.getSingle_event()) + "," + parse(pStage.getVenue_id())
				+ "," + parse(pStage.getParent_stage()) + "," + parse(pStage.getLaps())
				+ ")ON DUPLICATE KEY UPDATE `id` = `id`;";
		System.out.println(query);

		try {
			Statement st;
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addResults(Result pResult) {
		String query = "INSERT INTO formele.team_competitor_stage(stage_id,competitor_id,result_points,result_position,result_car_number,result_laps,result_fastest_lap_time,result_status,result_grid,result_fanboost,result_time_total,result_victories,result_races,result_races_with_points,result_pole_positions,result_podiums,result_fastest_laps,result_victory_pole_and_fastest_lap) VALUES ("
		+ parse(pResult.getStage_id())+ "," + parse(pResult.getCompetitor_id())+ ","+ parse(pResult.getResult_points())+ ","+ parse(pResult.getResult_position())+ ","+ parse(pResult.getResult_car_number())+ ","+ parse(pResult.getResult_laps())+ ","+ parse(pResult.getResult_fastest_lap_time())+ ","+ parse(pResult.getResult_status())+ ","+ parse(pResult.getResult_grid())+ ","+ parse(pResult.getResult_fanboost())+ ","+ parse(pResult.getResult_time_total())+ ","+ parse(pResult.getResult_victories())+ ","+ parse(pResult.getResult_races())+ ","+ parse(pResult.getResult_races_with_points())+ ","+ parse(pResult.getResult_pole_positions())+ "," + parse(pResult.getResult_podiums())+ ","+ parse(pResult.getResult_fastest_laps())+ ","+ parse(pResult.getResult_victory_pole_and_fastest_lap())+
		")ON DUPLICATE KEY UPDATE result_fastest_lap_time = "+ parse(pResult.getResult_fastest_lap_time())+ ", result_time_total =" + parse(pResult.getResult_time_total());
		System.out.println(pResult.getStage_id()+"FLT"+pResult.getResult_fastest_lap_time()+"Time"+pResult.getResult_time_total());

		try {
			Statement st;
			st = con.createStatement();
			st.execute(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String parse(String wert) {
		if (wert == null)
			return null;
		if (wert.isEmpty())
			return null;
		if (wert.equals("null"))
			return null;
		return "'" + wert + "'";
	}
}
