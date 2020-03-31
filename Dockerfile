FROM openjdk:11
ADD target/kick-scooter-vehicle.jar /kick-scooter-vehicle.jar
ENTRYPOINT ["java", "-jar", "kick-scooter-vehicle.jar"]