1- push the docker images into  ( docker hub )

docker push alaaward/mmv-currency-exchange-service:0.0.11-SNAPSHOT
docker push alaaward/mmv-currency-conversion-service:0.0.11-SNAPSHOT

2- create deployment then expose

kubectl create deployment currency-exchange --image=alaaward/mmv-currency-exchange-service:0.0.11-SNAPSHOT
kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000

kubectl create deployment currency-conversion --image=alaaward/mmv-currency-conversion-service:0.0.11-SNAPSHOT
kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100

3- URLs Examples :

Currency Exchange Service - http://EXTERNAL-IP:8000/currency-exchange/from/USD/to/EUR
Currency Conversion Service - http://EXTERNAL-IP:8100/currency-conversion-feign/from/USD/to/EUR/quantity/5

4- Creating Environment Variables to enable Microservice Communication
kubectl create configmap alaaconfigmap --from-literal=CURRENCY_EXCHANGE_HOST=http://currency-exchange
