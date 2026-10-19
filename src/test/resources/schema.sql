-- roolit-taulu
CREATE TABLE roolit (
    rooli_id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(30) NOT NULL
);

-- tapahtumat-taulu
CREATE TABLE tapahtumat (
    tapahtuma_id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(50) NOT NULL,
    kuvaus VARCHAR(500),
    pvm DATE NOT NULL
);

-- kayttajat-taulu
CREATE TABLE kayttajat (
    kayttaja_id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(100) NOT NULL,
    email VARCHAR(255),
    puhelin VARCHAR(20),
    syntymapaiva DATE,
    rooli_id BIGINT,
    FOREIGN KEY (rooli_id) REFERENCES roolit(rooli_id),
    kayttajanimi VARCHAR(250) UNIQUE NOT NULL,
    salasana VARCHAR(250) NOT NULL
);

--tapahtuma_osallistujat-taulu
CREATE TABLE tapahtuma_osallistujat(
    id BIGSERIAL PRIMARY KEY,
    kayttaja_id BIGINT NOT NULL, 
    FOREIGN KEY (kayttaja_id) REFERENCES kayttajat(kayttaja_id),
    tapahtuma_id BIGINT NOT NULL,
    FOREIGN KEY (tapahtuma_id) REFERENCES tapahtumat(tapahtuma_id),
    ilmoittautuminen VARCHAR(10) NOT NULL,
    ilmoittautumisaika TIMESTAMP
);