package model;

public class Result {
	String stage_id, competitor_id, result_points, result_position, result_car_number, result_laps,
			result_fastest_lap_time, result_status, result_grid, result_fanboost, result_time_total, result_victories,
			result_races, result_races_with_points, result_pole_positions, result_podiums, result_fastest_laps,
			result_victory_pole_and_fastest_lap;

	public String getStage_id() {
		return stage_id.replace("sr:stage:", "");
	}

	public void setStage_id(String stage_id) {
		this.stage_id = stage_id;
	}

	public String getCompetitor_id() {
		return competitor_id.replace("sr:competitor:", "");
	}

	public void setCompetitor_id(String competitor_id) {
		this.competitor_id = competitor_id;
	}

	public String getResult_points() {
		return result_points;
	}

	public void setResult_points(String result_points) {
		this.result_points = result_points;
	}

	public String getResult_position() {
		return result_position;
	}

	public void setResult_position(String result_position) {
		this.result_position = result_position;
	}

	public String getResult_car_number() {
		return result_car_number;
	}

	public void setResult_car_number(String result_car_number) {
		this.result_car_number = result_car_number;
	}

	public String getResult_laps() {
		return result_laps;
	}

	public void setResult_laps(String result_laps) {
		this.result_laps = result_laps;
	}

	public String getResult_fastest_lap_time() {
		return result_fastest_lap_time;
	}

	public void setResult_fastest_lap_time(String result_fastest_lap_time) {
		this.result_fastest_lap_time = result_fastest_lap_time;
	}

	public String getResult_status() {
		return result_status;
	}

	public void setResult_status(String result_status) {
		this.result_status = result_status;
	}

	public String getResult_grid() {
		return result_grid;
	}

	public void setResult_grid(String result_grid) {
		this.result_grid = result_grid;
	}

	public String getResult_fanboost() {
		return result_fanboost == "true"?"1":"0";
	}

	public void setResult_fanboost(String result_fanboost) {
		this.result_fanboost = result_fanboost;
	}

	public String getResult_time_total() {
		return result_time_total;
	}

	public void setResult_time_total(String result_time_total) {
		this.result_time_total = result_time_total;
	}

	public String getResult_victories() {
		return result_victories;
	}

	public void setResult_victories(String result_victories) {
		this.result_victories = result_victories;
	}

	public String getResult_races() {
		return result_races;
	}

	public void setResult_races(String result_races) {
		this.result_races = result_races;
	}

	public String getResult_races_with_points() {
		return result_races_with_points;
	}

	public void setResult_races_with_points(String result_races_with_points) {
		this.result_races_with_points = result_races_with_points;
	}

	public String getResult_pole_positions() {
		return result_pole_positions;
	}

	public void setResult_pole_positions(String result_pole_positions) {
		this.result_pole_positions = result_pole_positions;
	}

	public String getResult_podiums() {
		return result_podiums;
	}

	public void setResult_podiums(String result_podiums) {
		this.result_podiums = result_podiums;
	}

	public String getResult_fastest_laps() {
		return result_fastest_laps;
	}

	public void setResult_fastest_laps(String result_fastest_laps) {
		this.result_fastest_laps = result_fastest_laps;
	}

	public String getResult_victory_pole_and_fastest_lap() {
		return result_victory_pole_and_fastest_lap;
	}

	public void setResult_victory_pole_and_fastest_lap(String result_victory_pole_and_fastest_lap) {
		this.result_victory_pole_and_fastest_lap = result_victory_pole_and_fastest_lap;
	}
	
	
}
