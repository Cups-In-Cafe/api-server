DROP TABLE IF EXISTS t_err_log CASCADE;
DROP SEQUENCE IF EXISTS seq_err_log;
CREATE SEQUENCE seq_err_log;
CREATE TABLE IF NOT EXISTS t_err_log (
    idx              INT NOT NULL DEFAULT nextval('seq_err_log'),
    type          TEXT NOT NULL,
    content          TEXT NOT NULL,
    ins_timestamp TIMESTAMP DEFAULT now(),
    PRIMARY KEY (idx)
);

COMMENT ON table t_err_log IS 'System Error Log';
COMMENT ON COLUMN t_err_log.type IS 'Log Type';
COMMENT ON COLUMN t_err_log.content IS 'Log Content';
