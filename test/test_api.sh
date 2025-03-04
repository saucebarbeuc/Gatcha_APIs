#!/bin/bash

if [ "$#" -ne 2 ]; then
	echo "Usage: $0 <username> <password>"
	exit 1
fi

username=$1
password=$2

curl -s -X POST 'http://localhost:8080/api/users' \
	-H 'Content-Type: application/json' \
  	-d '{
	"username": "'$username'",
	"password": "'$password'"
}' | jq

token=$(curl -s -X POST 'http://localhost:8080/api/users/login' \
	-H 'Content-Type: application/json' \
  	-d '{
	"username": "'$username'",
	"password": "'$password'"
}' | jq .token | tr -d \")

echo $token

curl -s -X POST http://localhost:8080/api/monstres \
  -H 'Content-Type: application/json' \
  -H "Authorization: $token" \
  -d '{
  "nom": "monstre",
  "description": "ceci est un gros monstresssssss"
}' | jq



