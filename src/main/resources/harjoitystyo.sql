-- Poistetaan taulut oikeassa järjestyksessä riippuvuuksien takia
DROP TABLE IF EXISTS tapahtuma_osallistujat;
DROP TABLE IF EXISTS tapahtumat;
DROP TABLE IF EXISTS kayttajat;
DROP TABLE IF EXISTS roolit;

-- roolit-taulu
CREATE TABLE roolit (
    rooli_id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(30) NOT NULL
);

INSERT INTO roolit (nimi) 
VALUES 
('pelaaja'),
('valmentaja');

-- tapahtumat-taulu
CREATE TABLE tapahtumat (
    tapahtuma_id BIGSERIAL PRIMARY KEY,
    nimi VARCHAR(50) NOT NULL,
    kuvaus VARCHAR(500),
    pvm DATE NOT NULL
);

INSERT INTO tapahtumat (nimi, kuvaus, pvm) 
VALUES 
('Lajitreenit', 'ilman varusteita', '2026-11-19'), 
('Kotipeli vs. Turku', 'Pelialkaa klo 12. Ollaan paikalla viimeistään klo 10', '2026-11-28');

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
    salasana VARCHAR(250) NOT NULL,
    paivita_salasana BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO kayttajat (nimi, kayttajanimi, salasana, rooli_id, paivita_salasana) 
VALUES 
('Testi Käyttäjä', 'user', '$2a$10$XnG4PI6qE6qBipYWMXo41e5AgsjBCkP9c0fKMbT6ApxGBxYRU3xlq', 1, FALSE),
('Testi Admin', 'admin', '$2a$10$qtQP/4qm/HexG7D9sin/qOfMR6S1y5KTquWDvTpCDpTEGjWBOrQ6W', 2, FALSE),
('Maija Meikäläinen', 'maijam', '$2a$10$FQ8ExhLktfLqU4TqSYSH8eU3glBelLQffTIqy2OUOYsnPZrp4gDqG', 1, FALSE),
('Pekka Puupää', 'pekkap', '$2a$10$9Txhhz1DRyb0hD7ZY5umFesXzzL3xOnVbQg0fczEy9je/FnT1tBBq', 1, FALSE),
('Vilma Valmentaja', 'vilmav', '$2a$10$U5FeSoNMPK6XqMTkdd1V6uRN7imPiYUcZ70n/o3mfafmnERQcSR06', 2, FALSE);

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

INSERT INTO tapahtuma_osallistujat(kayttaja_id, tapahtuma_id, ilmoittautuminen, ilmoittautumisaika)
VALUES
(1, 1, 'IN', '2026-10-31 14:54:15'),
(2, 1, 'OUT', '2026-10-28 18:41:19'),
(2, 2, 'IN', '2026-10-28 18:42:30'),
(3, 1, 'IN', '2026-11-11 14:54:15'),
(4, 1, 'IN', '2026-10-31 14:54:15');

