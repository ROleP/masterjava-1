#!/bin/bash
LIQUIBASE_HOME=/home/rolep/java/liquibase
${LIQUIBASE_HOME}/liquibase --driver=org.postgresql.Driver \
--classpath=${LIQUIBASE_HOME}/lib \
--changeLogFile=databaseChangeLog.sql \
--url="jdbc:postgresql://localhost:5432/masterjava" \
--username=user \
--password=password \
migrate