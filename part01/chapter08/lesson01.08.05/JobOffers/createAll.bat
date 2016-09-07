psql --username=postgres -f createDatabase.sql
psql --username=postgres -f createDatabaseTables.sql
psql --username=postgres -f createQartzTables.sql