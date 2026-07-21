USE Chips4Cheap;

-- =========================
-- ACCOUNT1
-- =========================
INSERT INTO account1(email, username, Password1, Via, Cap, NumeroCivico, Amministratore)
VALUES
('mario.rossi@gmail.com', 'MarioR', SHA2('password123',512), 'Via Roma', '00100', 12, false),
('luigi.verdi@gmail.com', 'LuigiV', SHA2('ciao123',512), 'Via Milano', '20100', 25, false),
('anna.bianchi@gmail.com', 'AnnaB', SHA2('anna2024',512), 'Corso Italia', '80100', 8, false),
('admin@chips4cheap.it', 'Admin', SHA2('admin123',512), 'Via Napoli', '80121', 1, true),
('giulia.neri@gmail.com', 'GiuliaN', SHA2('giulia99',512), 'Via Firenze', '50100', 45, false);

-- =========================
-- PRODOTTO
-- =========================
INSERT INTO prodotto
(NomeModello, Produttore, Prezzo, Descrizione, Sconto, Tipo, Quantità, Image, MimeType)
VALUES
('Ryzen 7 7800X3D', 'AMD', 389.99,
'Processore AMD Ryzen 7 con tecnologia 3D V-Cache.', 10,
'CPU', 20, '7800x3d.jpg', 'image/jpeg'),

('Core i7-14700K', 'Intel', 429.99,
'Processore Intel Core i7 di quattordicesima generazione.', 5,
'CPU', 15, '14700k.jpg', 'image/jpeg'),

('RTX 5070', 'NVIDIA', 699.99,
'Scheda video NVIDIA RTX serie 5000.', 0,
'GPU', 10, 'rtx5070.jpg', 'image/jpeg'),

('B650 Aorus Elite', 'Gigabyte', 209.99,
'Scheda madre AM5 con chipset B650.', 15,
'Scheda Madre', 18, 'b650.jpg', 'image/jpeg'),

('DDR5 32GB 6000MHz', 'Corsair', 149.99,
'Kit RAM DDR5 32GB ad alte prestazioni.', 20,
'RAM', 30, 'ddr5.jpg', 'image/jpeg');

-- =========================
-- ANNUNCIO
-- =========================
INSERT INTO Annuncio(Titolo, Data_Pubblicazione, Descrizione)
VALUES
('Saldi Estivi', '2026-07-01', 'Sconti fino al 30% sui processori AMD.'),
('Nuove GPU', '2026-07-03', 'Sono arrivate le nuove schede video NVIDIA.'),
('Spedizione Gratis', '2026-07-05', 'Spedizione gratuita per ordini superiori a 100 euro.'),
('Nuove RAM DDR5', '2026-07-08', 'Disponibili nuovi kit DDR5 ad alta velocità.'),
('Promo Weekend', '2026-07-10', 'Solo per il weekend sconto del 15% su tutti i prodotti.');

-- =========================
-- RICEVUTA FISCALE
-- =========================
INSERT INTO RicevutaFiscale
(email, metodoPagamento, DataEmissione, NumeroCivico, via, Cap)
VALUES
('mario.rossi@gmail.com', 'Carta di Credito', '2026-07-02', 12, 'Via Roma', '00100'),
('luigi.verdi@gmail.com', 'PayPal', '2026-07-03', 25, 'Via Milano', '20100'),
('anna.bianchi@gmail.com', 'Bonifico', '2026-07-04', 8, 'Corso Italia', '80100'),
('giulia.neri@gmail.com', 'Carta di Credito', '2026-07-05', 45, 'Via Firenze', '50100'),
('admin@chips4cheap.it', 'Carta di Debito', '2026-07-06', 1, 'Via Napoli', '80121');

-- =========================
-- PRODOTTO RICEVUTA
-- =========================
INSERT INTO ProdottoRicevuta
(Prezzo, Quantità, Produttore, IDRicevutaFiscale, email,
 NomeModello, image, MimeType, tipo)
VALUES
(389.99, 1, 'AMD', 1, 'mario.rossi@gmail.com',
'Ryzen 7 7800X3D', '7800x3d.jpg', 'image/jpeg', 'CPU'),

(699.99, 1, 'NVIDIA', 2, 'luigi.verdi@gmail.com',
'RTX 5070', 'rtx5070.jpg', 'image/jpeg', 'GPU'),

(149.99, 2, 'Corsair', 3, 'anna.bianchi@gmail.com',
'DDR5 32GB 6000MHz', 'ddr5.jpg', 'image/jpeg', 'RAM'),

(209.99, 1, 'Gigabyte', 4, 'giulia.neri@gmail.com',
'B650 Aorus Elite', 'b650.jpg', 'image/jpeg', 'Scheda Madre'),

(429.99, 1, 'Intel', 5, 'admin@chips4cheap.it',
'Core i7-14700K', '14700k.jpg', 'image/jpeg', 'CPU');