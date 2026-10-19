INSERT INTO roolit (nimi) 
VALUES 
('pelaaja'),
('valmentaja');

INSERT INTO tapahtumat (nimi, kuvaus, pvm) 
VALUES 
('Lajitreenit', 'ilman varusteita', '2026-11-19'), 
('Kotipeli vs. Turku', 'Pelialkaa klo 12. Ollaan paikalla viimeistään klo 10', '2026-11-28'),
('koiranäyttely', 'paljon koiria', '2029-11-19');

INSERT INTO kayttajat (nimi, kayttajanimi, salasana, rooli_id) 
VALUES 
('Maija Meikäläinen', 'maijam', '$2a$10$FQ8ExhLktfLqU4TqSYSH8eU3glBelLQffTIqy2OUOYsnPZrp4gDqG', 1),
('Pekka Puupää', 'pekkap', '$2a$10$9Txhhz1DRyb0hD7ZY5umFesXzzL3xOnVbQg0fczEy9je/FnT1tBBq', 1),
('Vilma Valmentaja', 'vilmav', '$2a$10$U5FeSoNMPK6XqMTkdd1V6uRN7imPiYUcZ70n/o3mfafmnERQcSR06', 2);

INSERT INTO tapahtuma_osallistujat(kayttaja_id, tapahtuma_id, ilmoittautuminen, ilmoittautumisaika)
VALUES
(1, 1, 'IN', '2026-10-31 14:54:15'),
(2, 1, 'OUT', '2026-10-28 18:41:19'),
(2, 2, 'IN', '2026-10-28 18:42:30'),
(3, 1, 'IN', '2026-11-11 14:54:15');