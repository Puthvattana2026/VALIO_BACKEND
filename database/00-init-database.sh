#!/bin/bash
set -e

# -------------------------------------------
# + Access for init tables (created by services)
# + For future table creation (Hibernate ddl-auto)
# -------------------------------------------
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    GRANT USAGE, CREATE ON SCHEMA public TO ${POSTGRES_USER};
    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ${POSTGRES_USER};
    GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO ${POSTGRES_USER};

    ALTER DEFAULT PRIVILEGES FOR ROLE ${POSTGRES_USER} IN SCHEMA public
        GRANT ALL PRIVILEGES ON TABLES TO ${POSTGRES_USER};
    ALTER DEFAULT PRIVILEGES FOR ROLE ${POSTGRES_USER} IN SCHEMA public
        GRANT ALL PRIVILEGES ON SEQUENCES TO ${POSTGRES_USER};
EOSQL
