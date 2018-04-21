echo "admin task executed.."
call resource/apache-maven-3.5.2-bin/apache-maven-3.5.2/bin/mvn clean install
call resource/apache-maven-3.5.2-bin/apache-maven-3.5.2/bin/mvn spring-boot:run
echo "admin task running now.."
