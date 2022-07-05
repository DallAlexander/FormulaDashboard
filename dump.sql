CREATE TABLE country (
	name varchar(30),
	country_code varchar(4) NOT NULL,
	PRIMARY KEY (country_code)
);

CREATE TABLE team (
	id int NOT NULL,
	name varchar(30),
	gender varchar(5),
	country_code varchar(4),
	PRIMARY KEY (id),
	FOREIGN KEY (country_code) REFERENCES country(country_code)
);

CREATE TABLE competitor (
	id int NOT NULL,
	name varchar(30),
	gender varchar(5),
	country_of_birth_code varchar(4),
	date_of_birth date,
	country_of_residence_code varchar(4),
	salary int,
	PRIMARY KEY (id),
	FOREIGN KEY (country_of_birth_code) REFERENCES country(country_code),
	FOREIGN KEY (country_of_residence_code) REFERENCES country(country_code)
);

CREATE TABLE team_competitor (
	team_id int NOT NULL,
	competitor_id int NOT NULL,
	FOREIGN KEY (team_id) REFERENCES team(id),
	FOREIGN KEY (competitor_id) REFERENCES competitor(id)
);

CREATE TABLE venue (
	id int NOT NULL,
	name varchar(20),
	city varchar(20),
	country_code varchar(4),
	PRIMARY KEY (id),
	FOREIGN KEY (country_code) REFERENCES country(country_code)
);

CREATE TABLE stage (
	id int NOT NULL,
	description varchar(50),
	scheduled datetime,
	scheduled_end datetime,
	stage_type varchar(20),
	status varchar(10),
	single_event boolean,
	venue_id int,
	PRIMARY KEY (id),
	FOREIGN KEY (venue_id) REFERENCES venue(id)
);

CREATE TABLE mapping (
	parent_stage_id int NOT NULL,
	child_stage_id int NOT NULL,
	FOREIGN KEY (parent_stage_id) REFERENCES stage(id),
	FOREIGN KEY (child_stage_id) REFERENCES stage(id)
); -- PRIMARY KEY aus den beiden stage_ids

CREATE TABLE season_propabilities (
	season_id int NOT NULL,
	propabilities_type varchar(4), -- team oder comp
	description varchar(30),
	outcomes_team_id int,
	outcomes_team_propability decimal(4,2),
	outcomes_competitor_id int,
	outcomes_competitor_propability decimal(4,2),
	FOREIGN KEY (season_id) REFERENCES stage(id),
	FOREIGN KEY (outcomes_team_id) REFERENCES team(id),
	FOREIGN KEY (outcomes_competitor_id) REFERENCES competitor(id)
); -- PRIMARY KEY aus stage_id und competitor_id oder team_id

CREATE TABLE team_competitor_stage (
	stage_id int NOT NULL,
	team_id int,
	competitor_id int,
	result_points int,
	result_position int,
	result_car_number int,
	result_laps int,
	result_fastest_lap_time time,
	result_status varchar(15),
	result_grid int,
	result_fanboost boolean,
	result_time_total time,
	result_victories int,
	result_races int,
	result_races_with_points int,
	result_pole_positions int,
	result_podiums int,
	result_fastest_laps int,
	result_victory_pole_and_fastest_lap int,
	FOREIGN KEY (stage_id) REFERENCES stage(id),
	FOREIGN KEY (team_id) REFERENCES team(id),
	FOREIGN KEY (competitor_id) REFERENCES competitor(id)
); -- PRIMARY KEY aus stage_id und competitor_id oder team_id