#!/usr/bin/bash
docker run -d -it -p 8080:8080 -p 11222:11222 --rm --name=my_infinispan jboss/infinispan-server