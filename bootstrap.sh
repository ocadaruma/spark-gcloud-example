#!/bin/bash

cd ~
wget https://storage.googleapis.com/hadoop-lib/gcs/gcs-connector-latest-hadoop2.jar
aws s3 cp s3://<your-bucket>/path/to/credentials.p12 .
