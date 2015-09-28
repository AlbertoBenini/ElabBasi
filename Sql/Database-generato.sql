CREATE TABLE Divisione Ospedaliera (
  Nome univoco VARCHAR NOT NULL,
  Persona_Matricola univoca VARCHAR NOT NULL,
  Numero dipendenti INTEGER UNSIGNED NULL,
  Numero posti letto INTEGER UNSIGNED NULL,
  Breve descrizione VARCHAR NULL,
  PRIMARY KEY(Nome univoco, Persona_Matricola univoca),
  INDEX Divisione Ospedaliera_FKIndex1(Persona_Matricola univoca)
);

CREATE TABLE In carico (
  Strumento_Codice univoco VARCHAR NOT NULL,
  Divisione Ospedaliera_Nome univoco VARCHAR NOT NULL,
  Divisione Ospedaliera_Persona_Matricola univoca VARCHAR NOT NULL,
  PRIMARY KEY(Strumento_Codice univoco, Divisione Ospedaliera_Nome univoco, Divisione Ospedaliera_Persona_Matricola univoca),
  INDEX Strumento_has_Divisione Ospedaliera_FKIndex1(Strumento_Codice univoco),
  INDEX Strumento_has_Divisione Ospedaliera_FKIndex2(Divisione Ospedaliera_Nome univoco, Divisione Ospedaliera_Persona_Matricola univoca)
);

CREATE TABLE Manutenzione (
  Giorno DATE NOT NULL AUTO_INCREMENT,
  Strumento_Codice univoco VARCHAR NOT NULL,
  Durata intervento TIME NULL,
  Numero operatori INTEGER UNSIGNED NULL,
  Ditta VARCHAR NULL,
  PRIMARY KEY(Giorno, Strumento_Codice univoco),
  INDEX Manutenzione_FKIndex1(Strumento_Codice univoco)
);

CREATE TABLE Ordinaria (
  Manutenzione_Giorno DATE NOT NULL,
  Manutenzione_Strumento_Codice univoco VARCHAR NOT NULL,
  PRIMARY KEY(Manutenzione_Giorno, Manutenzione_Strumento_Codice univoco),
  INDEX Ordinaria_FKIndex1(Manutenzione_Giorno, Manutenzione_Strumento_Codice univoco)
);

CREATE TABLE Persona (
  Matricola univoca VARCHAR NOT NULL AUTO_INCREMENT,
  Nome VARCHAR NULL,
  Cognome VARCHAR NULL,
  Data assunzione  DATE NULL,
  Password_2 VARCHAR NULL,
  PRIMARY KEY(Matricola univoca)
);

CREATE TABLE Straordinaria (
  Urgenza INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Manutenzione_Giorno DATE NOT NULL,
  Manutenzione_Strumento_Codice univoco VARCHAR NOT NULL,
  Costo FLOAT NULL,
  PRIMARY KEY(Urgenza, Manutenzione_Giorno, Manutenzione_Strumento_Codice univoco),
  INDEX Straordinaria_FKIndex1(Manutenzione_Giorno, Manutenzione_Strumento_Codice univoco)
);

CREATE TABLE Strumento (
  Codice univoco VARCHAR NOT NULL AUTO_INCREMENT,
  Nome VARCHAR NULL,
  Potenza watt FLOAT NULL,
  Ditta produttrice VARCHAR NULL,
  Modello VARCHAR NULL,
  Costo FLOAT NULL,
  Anno acquisto INTEGER UNSIGNED NULL,
  PRIMARY KEY(Codice univoco)
);

CREATE TABLE Utilizzo (
  Data ora inizio VARCHAR NOT NULL AUTO_INCREMENT,
  Divisione Ospedaliera_Nome univoco VARCHAR NOT NULL,
  Data ora fine VARCHAR NOT NULL,
  Strumento_Codice univoco VARCHAR NOT NULL,
  Persona_Matricola univoca VARCHAR NOT NULL,
  Divisione Ospedaliera_Persona_Matricola univoca VARCHAR NOT NULL,
  Motivo  VARCHAR NULL,
  Responsabile Utilizzo VARCHAR NULL,
  PRIMARY KEY(Data ora inizio, Divisione Ospedaliera_Nome univoco, Data ora fine, Strumento_Codice univoco, Persona_Matricola univoca, Divisione Ospedaliera_Persona_Matricola univoca),
  INDEX Utilizzo_FKIndex2(Divisione Ospedaliera_Nome univoco, Divisione Ospedaliera_Persona_Matricola univoca),
  INDEX Utilizzo_FKIndex3(Strumento_Codice univoco),
  INDEX Utilizzo_FKIndex3(Persona_Matricola univoca)
);


