docker mysql

docker run -p 3306:3306 --name medicalHistory-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql/mysql-server:latest