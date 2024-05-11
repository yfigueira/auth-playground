# *Authentication With JSON Web Token*
<span>
<img src="https://seeklogo.com/images/S/spring-logo-9A2BC78AAF-seeklogo.com.png" alt="Spring logo" style="margin: 20px" width=75>
<img src="https://seeklogo.com/images/S/svelte-logo-E3497608CB-seeklogo.com.png" alt="SvelteKit logo" style="margin: 20px" width=75>
<img src="https://seeklogo.com/images/D/docker-logo-E3A71BA745-seeklogo.com.png" alt="Docker logo" style="margin: 20px" width=100>
<img src="https://seeklogo.com/images/P/postgresql-logo-5309879B58-seeklogo.com.png" alt="PostgreSQL logo" style="margin: 20px" width=85>
</span>

## *Description*
This project is an implementation of a registration and authentication process using JSON Web Tokens.

Technologies I used:
- Database: *PostgreSQL*
- Database migration: *Liquibase*
- Server side: *Java & SpringBoot*
- Integration tests: *Testcontainers*
- Client side: *TypeScript & SvelteKit*
- Containers: *Docker & Docker Compose*

## *Goal*
My goals for this project were:
1. Get acquainted with the **Spring Security architecture** and configuration process
2. Learn how **JSON Web Tokens** work and how to use them in a SpringBoot environment
3. Build the server side according to the **hexagonal architecture pattern**

## *Running the application*
### *Requirements*
In order to run this project you will need to have Docker installed on your computer.
You can run this project on Linux or using WSL on Windows.

### *Running the app*
To run the project you will need to:

- Clone this repository: `git clone https://github.com/yfigueira/auth-playground.git`
- The server uses RSA keys to generate the JWT tokens, which you will have to create:
  - Navigate to the resources directory: `cd auth-playground/backend/src/main/resources`
  - Create a *certs* directory and navigate to it: `mkdir certs && cd certs`
  - Run the following commands:
      ```
      # create rsa key pair
      openssl genrsa -out keypair.pem 2048

      # extract public key
      openssl rsa -in keypair.pem -pubout -out public.pem

      # create private key in PKCS#8 format
      openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
      ```
  - Once the public and private keys are created, you won't need the *keypair.pem* file anymore, so remove it: `rm keypair.pem`
  - You should now have two files in the *certs* directory - *public.pem* and *private.pem*
- Navigate back to the project root directory: `cd ../../../../../`
- (If you're using WSL on Windows, make sure Docker desktop is running!)
- Run the database and the server: `docker-compose up --build -d`
- Navigate to the *frontend* directory: `cd frontend`
- Run the client application: `npm run dev`
- In your browser, navigate to `http://localhost:5173`
  - Since you won't be authenticated yet, you will be automatically redirected to the login page at `http://localhost:5173/login`