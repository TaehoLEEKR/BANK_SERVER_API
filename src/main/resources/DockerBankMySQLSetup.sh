docker run -d \
  --name bank-mysql \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=0000 \
  -e MYSQL_DATABASE=bank \
  -v mysql-data:/var/lib/mysql \
  --restart unless-stopped \
  mysql:8.0