DROP TABLE IF EXISTS konto;

CREATE TABLE konto(
  konto_nummer INTEGER NOT NULL,
  lock_field INTEGER NOT NULL,
  eier VARCHAR(50) NOT NULL,
  saldo DOUBLE NOT NULL,
  PRIMARY KEY(konto_nummer)
);