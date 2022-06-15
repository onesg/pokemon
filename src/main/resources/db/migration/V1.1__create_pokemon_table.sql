
--  V1.1__create_pokemon_table.sql  --

CREATE TABLE IF NOT EXISTS pokemon (
  id_pokemon    BIGINT(20)  NOT NULL AUTO_INCREMENT,
  nome_pokemon  VARCHAR(64) NOT NULL,
  tipo_pokemon  VARCHAR(64) NOT NULL,
  treinador_id  BIGINT(20)  NOT NULL,
  PRIMARY KEY (id_pokemon),
  FOREIGN KEY (treinador_id) REFERENCES treinador(id_treinador)
);
