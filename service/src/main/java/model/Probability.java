package model;

public class Probability {
	  String id,  propType,  desc,  teamId,  teamProp,  compId,  compProp;
	  
	  public Probability(String pId) {
		  this.id = pId;
		  propType = desc = teamId = teamProp = compId = compProp = "null";
	  }

	public String getId() {
		return id.replace("sr:competitor:", "");
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTeamId() {
		return teamId.replace("sr:competitor:", "");
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamProp() {
		return teamProp;
	}

	public void setTeamProp(String teamProp) {
		this.teamProp = teamProp;
	}

	public String getCompId() {
		return compId.replace("sr:competitor:", "");
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompProp() {
		return compProp;
	}

	public void setCompProp(String compProp) {
		this.compProp = compProp;
	}
	  
}