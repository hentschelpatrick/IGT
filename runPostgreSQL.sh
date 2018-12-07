#!/usr/bin/bash
docker run --name my_postgres -e POSTGRES_PASSWORD=root -d -p 5432:5432 postgres