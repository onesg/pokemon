
--  V1.0__create_treinador_table.sql  --

CREATE TABLE IF NOT EXISTS treinador (
  id_treinador      BIGINT(20)  NOT NULL AUTO_INCREMENT,
  nome_treinador    VARCHAR(64) NOT NULL,
  idade_treinador   INTEGER(2)  NOT NULL,
  PRIMARY KEY (id_treinador)
);
