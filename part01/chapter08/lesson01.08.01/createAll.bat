psql --username=postgres -f createDatabase.sql
psql --username=postgres -f createTables.sql
psql --username=postgres -f fillTables.sql