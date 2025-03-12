curl -s -X POST 'http://localhost:8081/api/users' \
        -H 'Content-Type: application/json' \
        -d '{
        "username": "yoman",
        "password": "five"
}'

curl -s -X POST 'http://localhost:8081/api/users/login' \
        -H 'Content-Type: application/json' \
        -d '{
        "username": "yoman",
        "password": "five"
}'


# verifier

