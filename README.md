#### Welcome to setup Spring JPA Data
- Step by step to run docker postgres
- - `docker run -d -p 5432:5432 postgres:latest`
- - `docker exec -it [comtainer_image] bash`
- - `psql -U postgres`
- - `CREATE NEW USER [user] WITH PASSWORD [password]`
- - `CREATE DATABASE [db] OWNER [user]`
- - `GRANT ALL PRIVILEGES on DATABASE [db] to [user]`
- - `psql -U [user]`