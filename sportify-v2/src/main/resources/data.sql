CREATE TABLE IF NOT EXISTS Leagues (
	`id`				INTEGER PRIMARY KEY,
	`division`			VARCHAR(5) NOT NULL,
	`name`				VARCHAR(50) NOT NULL,
	`season_start`		DATE NOT NULL,
	`season_end`		DATE 	NOT NULL
);

CREATE TABLE IF NOT EXISTS Teams (
	`id`				INTEGER PRIMARY KEY,
	`division`			VARCHAR(5) NOT NULL,
	`name`				VARCHAR(50) NOT NULL,
	`alias`				VARCHAR(50) NOT NULL,
	`stadium`			VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Statistics (
	`id`				INTEGER PRIMARY KEY,
	`Div`				VARCHAR(5) NOT NULL,
	`Date`				VARCHAR(10) NOT NULL,
	`HomeTeam`			VARCHAR(50) NOT NULL,
	`AwayTeam`			VARCHAR(50) NOT NULL,
	`FTHG`				INTEGER NOT NULL,
	`FTAG`				INTEGER NOT NULL,
	`FTR`				CHARACTER(1) NOT NULL,
	`HTHG`				INTEGER NOT NULL,
	`HTAG`				INTEGER NOT NULL,
	`HTR`				CHARACTER(1) NOT NULL,
	`Referee`			VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Standings (
	`id`				INTEGER PRIMARY KEY,
	`division`			VARCHAR(5) NOT NULL,
	`name`				VARCHAR(50) NOT NULL,
	`position`			INTEGER NULL,
	`P`					INTEGER NOT NULL,
	`W`					INTEGER NOT NULL,
	`D`					INTEGER NOT NULL,
	`L`					INTEGER NOT NULL,
	`GF`				INTEGER NOT NULL,
	`GA`				INTEGER NOT NULL,
	`GD`				INTEGER NOT NULL,
	`points`			INTEGER NOT NULL,
	`pastPoints`		INTEGER NOT NULL,
	`movement`			INTEGER NULL
);

CREATE TABLE IF NOT EXISTS Fixtures (
	`id`				INTEGER PRIMARY KEY,
	`division`			VARCHAR(5) NOT NULL,
	`date`				VARCHAR(50) NOT NULL,
	`HomeTeam`			VARCHAR(50) NOT NULL,
	`AwayTeam`			VARCHAR(50) NOT NULL,
	`B365H`				REAL NOT NULL,
	`B365D`				REAL NOT NULL,
	`B365A`				REAL NOT NULL
);

INSERT INTO Leagues (`division`, `name`, `season_start`, `season_end`)
VALUES 
('E0', 'English Premier League', '2017-08-11', '2018-05-13'),
('E1', 'English Championship', '2017-08-04', '2018-05-06'),
('E2', 'English League One', '2017-08-05', '2018-05-05'),
('E3', 'English League Two', '2017-08-05', '2018-05-05'),
('F1', 'France Ligue 1', '2017-08-04', '2018-05-19'),
('D1', 'German Bundesliga', '2017-08-18', '2018-05-12'),
('I1', 'Italy Serie A', '2017-08-19', '2018-05-20'),
('SP1', 'Spanish La Liga', '2017-08-18', '2018-05-20'),
('N1', 'Holland Eredivisie', '2017-08-11', '2018-05-06'),
('B1', 'Belgium First Division A', '2017-07-28', '2018-06-30'),
('P1', 'Portugal Primeira Liga', '2017-08-06', '2018-05-12'),
('T1', 'Turkish Süper Lig', '2017-08-11', '2018-05-20'),
('SC0', 'Scotland Premiership', '2017-08-05', '2018-04-07');

INSERT INTO Teams (`division`, `name`, `alias`, `stadium`)
VALUES
('E0', "AFC Bournemouth", "Bournemouth", "Dean Court"),
('E0', "Arsenal", "Arsenal", "Emirates Stadium"),
('E0', "Brighton & Hove Albion", "Brighton", "Falmer Stadium"),
('E0', "Burnley", "Burnley", "Turf Moor"),
('E0', "Chelsea", "Chelsea", "Stamford Bridge"),
('E0', "Crystal Palace", "Crystal Palace", "Selhurst Park"),
('E0', "Everton", "Everton", "Goodison Park"),
('E0', "Huddersfield Town", "Huddersfield", "John Smith's Stadium"),
('E0', "Leicester City", "Leicester",  "King Power Stadium"),
('E0', "Liverpool", "Liverpool", "Anfield"),
('E0', "Manchester City", "Man City", "Etihad Stadium"),
('E0', "Manchester United", "Man United", "Old Trafford"),
('E0', "Newcastle United", "Newcastle", "St James' Park"),
('E0', "Southampton", "Southampton", "St Mary's Stadium"),
('E0', "Stoke City", "Stoke", "bet365 Stadium"),
('E0', "Swansea City", "Swansea", "Liberty Stadium"),
('E0', "Tottenham Hotspur", "Tottenham", "Wembley Stadium"),
('E0', "Watford", "Watford", "Vicarage Road"),
('E0', "West Bromwich Albion", "West Brom", "The Hawthorns"),
('E0', "West Ham United", "West Ham", "London Stadium"),
('E1', "Aston Villa", "Aston Villa", "Villa Park"),
('E1', "Barnsley", "Barnsley", "Oakwell"),
('E1', "Birmingham City", "Birmingham", "St Andrew's"),
('E1', "Bolton Wanderers", "Bolton", "Macron Stadium"),
('E1', "Brentford", "Brentford", "Griffin Park"),
('E1', "Bristol City", "Bristol City", "Ashton Gate"),
('E1', "Burton Albion", "Burton", "Pirelli Stadium"),
('E1', "Cardiff City", "Cardiff", "Cardiff City Stadium"),
('E1', "Derby County", "Derby", "Pride Park Stadium"),
('E1', "Fulham", "Fulham", "Craven Cottage"),
('E1', "Hull City", "Hull", "KCOM Stadium"),
('E1', "Ipswich Town", "Ipswich", "Portman Road"),
('E1', "Leeds United", "Leeds", "Elland Road"),
('E1', "Middlesbrough", "Middlesbrough", "Riverside Stadium"),
('E1', "Millwall", "Millwall", "The Den"),
('E1', "Norwich City", "Norwich", "Carrow Road"),
('E1', "Nottingham Forest", "Nott'm Forest", "City Ground"),
('E1', "Preston North End", "Preston", "Deepdale"),
('E1', "Queens Park Rangers", "QPR", "Loftus Road"),
('E1', "Reading", "Reading", "Madejski Stadium"),
('E1', "Sheffield United", "Sheffield United", "Bramall Lane"),
('E1', "Sheffield Wednesday", "Sheffield Weds", "Hillsborough Stadium"),
('E1', "Sunderland", "Sunderland", "Stadium of Light"),
('E1', "Wolverhampton Wanderers", "Wolves", "Molineux"),
('E2', "AFC Wimbledon", "AFC Wimbledon", "Kingsmeadow"),
('E2', "Blackburn Rovers", "Blackburn", "Ewood Park"),
('E2', "Blackpool", "Blackpool", "Bloomfield Road"),
('E2', "Bradford City", "Bradford", "Valley Parade"),
('E2', "Bristol Rovers", "Bristol Rvs", "Memorial Stadium"),
('E2', "Bury", "Bury", "Gigg Lane"),
('E2', "Charlton Athletic", "Charlton", "The Valley"),
('E2', "Doncaster Rovers", "Doncaster", "Keepmoat Stadium"),
('E2', "Fleetwood Town", "Fleetwood Town", "Highbury Stadium"),
('E2', "Gillingham", "Gillingham", "Priestfield Stadium"),
('E2', "Milton Keynes Dons", "Milton Keynes Dons", "Stadium:mk"),
('E2', "Northampton Town", "Northampton", "Sixfields Stadium"),
('E2', "Oldham Athletic", "Oldham", "Boundary Park"),
('E2', "Oxford United", "Oxford", "Kassam Stadium"),
('E2', "Peterborough United", "Peterboro", "London Road"),
('E2', "Plymouth Argyle", "Plymouth", "Home Park"),
('E2', "Portsmouth", "Portsmouth", "Fratton Park"),
('E2', "Rochdale", "Rochdale", "Spotland Stadium"),
('E2', "Rotherham United", "Rotherham", "New York Stadium"),
('E2', "Scunthorpe United", "Scunthorpe", "Glanford Park"),
('E2', "Shrewsbury Town", "Shrewsbury", "New Meadow"),
('E2', "Southend United", "Southend", "Roots Hall"),
('E2', "Walsall", "Walsall", "Bescot Stadium"),
('E2', "Wigan Athletic", "Wigan", "DW Stadium"),
('E3', "Accrington Stanley", "Accrington", "Crown Ground"),
('E3', "Barnet", "Barnet", "The Hive Stadium"),
('E3', "Cambridge United", "Cambridge", "Abbey Stadium"),
('E3', "Carlisle United", "Carlisle", "Brunton Park"),
('E3', "Cheltenham Town", "Cheltenham", "Whaddon Road"),
('E3', "Chesterfield", "Chesterfield", "Proact Stadium"),
('E3', "Colchester United", "Colchester", "Colchester Community Stadium"),
('E3', "Coventry City", "Coventry", "Ricoh Arena"),
('E3', "Crawley Town", "Crawley Town", "Broadfield Stadium"),
('E3', "Crewe Alexandra", "Crewe", "Gresty Road"),
('E3', "Exeter City", "Exeter", "St James Park"),
('E3', "Forest Green Rovers", "Forest Green", "The New Lawn"),
('E3', "Grimsby Town", "Grimsby", "Blundell Park"),
('E3', "Lincoln City", "Lincoln", "Sincil Bank"),
('E3', "Luton Town", "Luton", "Kenilworth Road"),
('E3', "Mansfield Town", "Mansfield", "Field Mill"),
('E3', "Morecambe", "Morecambe", "Globe Arena"),
('E3', "Newport County", "Newport County", "Rodney Parade"),
('E3', "Notts County", "Notts County", "Meadow Lane"),
('E3', "Port Vale", "Port Vale", "Vale Park"),
('E3', "Stevenage", "Stevenage", "Broadhall Way"),
('E3', "Swindon Town", "Swindon", "County Ground"),
('E3', "Wycombe Wanderers", "Wycombe", "Adams Park"),
('E3', "Yeovil Town", "Yeovil", "Huish Park"),
('F1', "Amiens", "Amiens", "Stade de la Licorne"),
('F1', "Angers", "Angers", "Stade Raymond Kopa"),
('F1', "Bordeaux", "Bordeaux", "Matmut Atlantique"),
('F1', "Caen", "Caen", "Stade Michel d'Ornano"),
('F1', "Dijon", "Dijon", "Stade Gaston Gérard"),
('F1', "Guingamp", "Guingamp", "Stade du Roudourou"),
('F1', "Lille", "Lille", "Stade Pierre-Mauroy"),
('F1', "Lyon", "Lyon", "Parc OL"),
('F1', "Marseille", "Marseille", "Stade Vélodrome"),
('F1', "Metz", "Metz", "Stade Saint-Symphorien"),
('F1', "Monaco", "Monaco", "Stade Louis II"),
('F1', "Montpellier", "Montpellier", "Stade de la Mosson"),
('F1', "Nantes", "Nantes", "Stade de la Beaujoire"),
('F1', "Nice", "Nice", "Allianz Riviera"),
('F1', "Paris Saint-Germain", "Paris SG", "Parc des Princes"),
('F1', "Rennes", "Rennes", "Roazhon Park"),
('F1', "Saint-Étienne", "St Etienne", "Stade Geoffroy-Guichard"),
('F1', "Strasbourg", "Strasbourg", "Stade de la Meinau"),
('F1', "Toulouse", "Toulouse", "Stadium Municipal"),
('F1', "Troyes", "Troyes", "Stade de l'Aube"),
('D1', "FC Augsburg", "Augsburg", "WWK Arena"),
('D1', "Hertha BSC", "Hertha", "Olympiastadion"),
('D1', "Werder Bremen", "Werder Bremen", "Weser-Stadion"),
('D1', "Borussia Dortmund", "Dortmund", "Signal Iduna Park"),
('D1', "Eintracht Frankfurt", "Ein Frankfurt", "Commerzbank-Arena"),
('D1', "SC Freiburg", "Freiburg", "Schwarzwald-Stadion"),
('D1', "Hamburger SV", "Hamburg", "Volksparkstadion"),
('D1', "Hannover 96", "Hannover", "HDI-Arena"),
('D1', "1899 Hoffenheim", "Hoffenheim", "Wirsol Rhein-Neckar-Arena"),
('D1', "1. FC Köln", "FC Koln", "RheinEnergieStadion"),
('D1', "RB Leipzig", "RB Leipzig", "Red Bull Arena"),
('D1', "Bayer Leverkusen", "Leverkusen", "BayArena"),
('D1', "Mainz 05", "Mainz", "Opel Arena"),
('D1', "Borussia Mönchengladbach", "M'gladbach", "Borussia-Park"),
('D1', "Bayern Munich", "Bayern Munich", "Allianz Arena"),
('D1', "Schalke 04", "Schalke 04", "Veltins-Arena"),
('D1', "VfB Stuttgart", "Stuttgart", "Mercedes-Benz Arena"),
('D1', "VfL Wolfsburg", "Wolfsburg", "Volkswagen Arena"),
('I1', "Atalanta", "Atalanta", "Stadio Atleti Azzurri d'Italia"),
('I1', "Benevento", "Benevento", "Stadio Ciro Vigorito"),
('I1', "Bologna", "Bologna", "Stadio Renato Dall'Ara"),
('I1', "Cagliari", "Cagliari", "Sardegna Arena"),
('I1', "Chievo", "Chievo", "Stadio Marc'Antonio Bentegodi"),
('I1', "Crotone", "Crotone", "Stadio Ezio Scida"),
('I1', "Fiorentina", "Fiorentina", "Stadio Artemio Franchi"),
('I1', "Genoa", "Genoa", "Stadio Luigi Ferraris"),
('I1', "Hellas Verona", "Verona", "Stadio Marc'Antonio Bentegodi"),
('I1', "Internazionale", "Inter", "San Siro"),
('I1', "Juventus", "Juventus", "Allianz Stadium"),
('I1', "Lazio", "Lazio", "Stadio Olimpico"),
('I1', "Milan", "Milan", "San Siro"),
('I1', "Napoli", "Napoli", "Stadio San Paolo"),
('I1', "Roma", "Roma", "Stadio Olimpico"),
('I1', "Sampdoria", "Sampdoria", "Stadio Luigi Ferraris"),
('I1', "Sassuolo", "Sassuolo", "Mapei Stadium – Città del Tricolore "),
('I1', "SPAL", "Spal", "Paolo Mazza"),
('I1', "Torino", "Torino", "Stadio Olimpico Grande Torino"),
('I1', "Udinese", "Udinese", "Stadio Friuli-Dacia Arena"),
('SP1', "Alavés", "Alaves", "Mendizorrotza"),
('SP1', "Athletic Bilbao", "Ath Bilbao", "San Mamés"),
('SP1', "Atlético Madrid", "Ath Madrid", "Vicente Calderón"),
('SP1', "Barcelona", "Barcelona", "Camp Nou"),
('SP1', "Celta Vigo", "Celta", "Balaídos"),
('SP1', "Deportivo La Coruña", "La Coruna", "Riazor"),
('SP1', "Eibar", "Eibar", "Ipurua"),
('SP1', "Espanyol", "Espanol", "RCDE Stadium"),
('SP1', "Getafe", "Getafe", "Coliseum Alfonso Pérez"),
('SP1', "Girona", "Girona", "Montilivi"),
('SP1', "Las Palmas", "Las Palmas", "Gran Canaria"),
('SP1', "Leganés", "Leganes", "Butarque"),
('SP1', "Levante", "Levante", "Ciutat de València"),
('SP1', "Málaga", "Malaga", "La Rosaleda"),
('SP1', "Real Betis", "Betis", "Benito Villamarín"),
('SP1', "Real Madrid", "Real Madrid", "Santiago Bernabéu"),
('SP1', "Real Sociedad", "Sociedad", "Anoeta"),
('SP1', "Sevilla", "Sevilla", "Ramón Sánchez Pizjuán"),
('SP1', "Valencia", "Valencia", "Mestalla"),
('SP1', "Villarreal", "Villarreal", "Estadio de la Cerámica"),
('N1', "ADO Den Haag", "Den Haag", "Cars Jeans Stadion"),
('N1', "Ajax", "Ajax", "Amsterdam ArenA"),
('N1', "AZ", "AZ Alkmaar", "AFAS Stadion"),
('N1', "Excelsior", "Excelsior", "Van Donge & De Roo Stadion"),
('N1', "Feyenoord", "Feyenoord", "Stadion Feijenoord"),
('N1', "FC Groningen", "Groningen", "Noordlease Stadion"),
('N1', "sc Heerenveen", "Heerenveen", "Abe Lenstra Stadion"),
('N1', "Heracles Almelo", "Heracles", "Polman Stadion"),
('N1', "NAC Breda", "NAC Breda", "Rat Verlegh Stadion"),
('N1', "PEC Zwolle", "Zwolle", "MAC³PARK stadion"),
('N1', "PSV", "PSV Eindhoven", "Philips Stadion"),
('N1', "Roda JC Kerkrade", "Roda", "Parkstad Limburg Stadion"),
('N1', "Sparta Rotterdam", "Sparta Rotterdam", "Het Kasteel"),
('N1', "FC Twente", "Twente", "De Grolsch Veste"),
('N1', "FC Utrecht", "Utrecht", "Stadion Galgenwaard"),
('N1', "Vitesse", "Vitesse", "GelreDome"),
('N1', "VVV-Venlo", "VVV Venlo", "Seacon Stadion - De Koel -"),
('N1', "Willem II", "Willem II", "Koning Willem II-stadion"),
('B1', "Anderlecht", "Anderlecht", "Constant Vanden Stock Stadium"),
('B1', "Antwerp", "Antwerp", "Bosuilstadion"),
('B1', "Charleroi", "Charleroi", "Stade du Pays de Charleroi"),
('B1', "Club", "Club Brugge", "Jan Breydel Stadium"),
('B1', "Eupen", "Eupen", "Kehrweg Stadion"),
('B1', "Genk", "Genk", "Luminus Arena"),
('B1', "Gent", "Gent", "Ghelamco Arena"),
('B1', "Kortrijk", "Kortrijk", "Guldensporen Stadion"),
('B1', "Lokeren", "Lokeren", "Daknamstadion"),
('B1', "Mechelen", "Mechelen", "AFAS-stadion Achter de Kazerne"),
('B1', "Mouscron", "Mouscron", "Stade Le Canonnier"),
('B1', "Oostende", "Oostende", "Versluys Arena"),
('B1', "Sint-Truiden", "St Truiden", "Stayen"),
('B1', "Standard", "Standard", "Stade Maurice Dufrasne"),
('B1', "Waasland", "Waasland-Beveren", "Freethiel Stadion"),
('B1', "Zulte", "Waregem", "Regenboogstadion"),
('P1', "Belenenses", "Belenenses", "Estádio do Restelo"),
('P1', "Benfica", "Benfica", "Estádio da Luz"),
('P1', "Boavista", "Boavista", "Estádio do Bessa"),
('P1', "Braga", "Sp Braga", "Estádio Municipal de Braga"),
('P1', "Chaves", "Chaves", "Estádio Municipal Eng. Manuel Branco Teixeira"),
('P1', "Desportivo das Aves", "Aves", "Estádio do CD Aves"),
('P1', "Estoril", "Estoril", "Estádio António Coimbra da Mota"),
('P1', "Feirense", "Feirense", "Estádio Marcolino de Castro"),
('P1', "Marítimo", "Maritimo", "Estádio do Marítimo"),
('P1', "Moreirense", "Moreirense", "Parque de Jogos Comendador Joaquim de Almeida Freitas"),
('P1', "Paços de Ferreira", "Pacos Ferreira", "Estádio Capital do Móvel"),
('P1', "Porto", "Porto", "Estádio do Dragão"),
('P1', "Portimonense", "Portimonense", "Estádio Municipal de Portimão"),
('P1', "Rio Ave", "Rio Ave", "Estádio dos Arcos"),
('P1', "Sporting CP", "Sp Lisbon", "Estádio José Alvalade"),
('P1', "Tondela", "Tondela", "Estádio João Cardoso"),
('P1', "Vitória de Guimarães", "Guimaraes", "Estádio D. Afonso Henriques"),
('P1', "Vitória de Setúbal", "Setubal", "Estádio do Bonfim"),
('T1', "Akhisar Belediyespor", "Akhisar Belediyespor", "Manisa 19 Mayıs Stadium"),
('T1', "Alanyaspor", "Alanyaspor", "Alanya Oba Stadium"),
('T1', "Antalyaspor", "Antalyaspor", "Antalya Stadium"),
('T1', "Beşiktaş", "Besiktas", "Vodafone Park"),
('T1', "Bursaspor", "Bursaspor", "Timsah Arena"),
('T1', "Fenerbahçe", "Fenerbahce", "Ülker Stadium"),
('T1', "Galatasaray", "Galatasaray", "Türk Telekom Stadium"),
('T1', "Gençlerbirliği", "Genclerbirligi", "Ankara 19 Mayıs Stadium"),
('T1', "Göztepe", "Goztep", "Bornova Stadium"),
('T1', "İstanbul Başakşehir", "Buyuksehyr", "Başakşehir Fatih Terim Stadium"),
('T1', "Kardemir Karabükspor", "Karabukspor", "Dr. Necmettin Şeyhoğlu Stadium"),
('T1', "Kasımpaşa", "Kasimpasa", "Recep Tayyip Erdoğan Stadium"),
('T1', "Kayserispor", "Kayserispor", "Kadir Has Stadium"),
('T1', "Konyaspor", "Konyaspor", "Konya Büyükşehir Stadium"),
('T1', "Osmanlıspor", "Osmanlispor", "Osmanlı Stadyumu"),
('T1', "Sivasspor", "Sivasspor", "Sivas Arena"),
('T1', "Trabzonspor", "Trabzonspor", "Şenol Güneş Stadium"),
('T1', "Yeni Malatyaspor", "Yeni Malatyaspor", "Malatya İnönü Stadium"),
('SC0', "Aberdeen", "Aberdeen", "Pittodrie Stadium"),
('SC0', "Celtic", "Celtic", "Celtic Park"),
('SC0', "Dundee", "Dundee", "Dens Park"),
('SC0', "Hamilton Academical", "Hamilton", "New Douglas Park"),
('SC0', "Heart of Midlothian", "Hearts", "Tynecastle Stadium"),
('SC0', "Hibernian", "Hibernian", "Easter Road"),
('SC0', "Kilmarnock", "Kilmarnock", "Rugby Park"),
('SC0', "Motherwell", "Motherwell", "Fir Park"),
('SC0', "Partick Thistle", "Partick", "Firhill Stadium"),
('SC0', "Rangers", "Rangers", "Ibrox Stadium"),
('SC0', "Ross County", "Ross County", "Victoria Park"),
('SC0', "St Johnstone", "St Johnstone", "McDiarmid Park");