package model;

public class Stage {
	private String id,description,scheduled,scheduled_end,stage_type,status,single_event,venue_id,parent_stage,laps;

	public Stage (String pId) {
		this.id = pId;
	}
	public String getId() {
		return id.replace("sr:stage:", "");
	}

	public String getLaps() {
		return laps;
	}
	public void setLaps(String laps) {
		this.laps = laps;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScheduled() {
		return parseDate(scheduled);
	}

	public void setScheduled(String scheduled) {
		this.scheduled = scheduled;
	}

	public String getScheduled_end() {
		return parseDate(scheduled_end);
	}

	public void setScheduled_end(String scheduled_end) {
		this.scheduled_end = scheduled_end;
	}

	public String getStage_type() {
		return stage_type;
	}

	public void setStage_type(String stage_type) {
		this.stage_type = stage_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSingle_event() {
		return (single_event=="true")? "1" : "0";
	}

	public void setSingle_event(String single_event) {
		this.single_event = single_event;
	}

	public String getVenue_id() {
		if(venue_id==null)return null;
		return venue_id.replace("sr:venue:", "");
	}

	public void setVenue_id(String venue_id) {
		this.venue_id = venue_id;
	}

	public String getParent_stage() {
		if(parent_stage == null)return null;
		return parent_stage.replace("sr:stage:", "");
	}

	public void setParent_stage(String parent_stage) {
		this.parent_stage = parent_stage;
	} 
	
	private String parseDate(String pDatum) {
		return pDatum.replace("T", " ").substring(0, 19);
	}
}
