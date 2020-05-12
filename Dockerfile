  
FROM openjdk:8
EXPOSE 8080
ADD target/demochat.jar demochat.jar 
ENTRYPOINT ["java","-jar","/demochat.jar"]