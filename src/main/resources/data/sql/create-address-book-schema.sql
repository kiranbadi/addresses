DO $$
BEGIN
IF NOT EXISTS (
SELECT schema_name
FROM information_schema.schemata
WHERE schema_name = 'address_book') THEN
 CREATE SCHEMA address_book;
 END IF;
 END
$$;

REVOKE ALL ON SCHEMA address_book FROM PUBLIC;
GRANT ALL PRIVILEGES ON SCHEMA address_book TO kiran;

