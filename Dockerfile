FROM openjdk:21
EXPOSE 8080
COPY build/libs/msa-invoices-0.0.1-SNAPSHOT.jar invoices.jar
ENTRYPOINT ["java","-jar","/invoices.jar"]
