package model;

public class TeamConnector {
	Team team;
	String stageId,competitorId1,competitorId2;
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public String getStageId() {
		return stageId.replace("sr:stage:", "");
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public String getCompetitorId1() {
		if(competitorId1 == null)return "null"; 
		return competitorId1.replace("sr:competitor:", "");
	}
	public void setCompetitorId1(String competitorId1) {
		this.competitorId1 = competitorId1;
	}
	public String getCompetitorId2() {
		if(competitorId2 == null)return "null"; 
		return competitorId2.replace("sr:competitor:", "");
	}
	public void setCompetitorId2(String competitorId2) {
		this.competitorId2 = competitorId2;
	}
	
}
