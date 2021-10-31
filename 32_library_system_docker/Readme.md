### Docker lesson

#### Run order

Note: commands for linux!

1. build jar with app</br>
   ```sh ./gradlew jar```</br>

2. build docker image with app</br>
   ```docker build -t hw-32 .```</br>

3. run app by docker-compose</br>
   ```docker-compose up -d```

or
```sh ./gradlew jar && docker build -t hw-32 . && docker-compose up -d```