###Meal
#####Get All
curl --location --request GET 'http://localhost:8080/topjava/rest/meals'
#####Get
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/100002'
#####Create
curl --location --request POST 'http://localhost:8080/topjava/rest/meals' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": null,
    "dateTime": "2020-02-01T18:00:00",
    "description": "Created dinner",
    "calories": 300,
    "user": null
}'
#####Update
curl --location --request PUT 'http://localhost:8080/topjava/rest/meals/100002'
#####Delete
curl --location --request DELETE 'http://localhost:8080/topjava/rest/meals/100002'
#####Filter
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&endDate=2020-01-30&startTime=00:00:00&endTime=23:59:00'
####Filter with null values
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/filter'

