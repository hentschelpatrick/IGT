#!/usr/bin/bash
mkdir ~/data
docker run --name my_mongodb -d -p 27017:27017 -v ~/data:/data/db mongo