# 🏦 eBank Microservices Application

Une application bancaire complète basée sur une architecture microservices moderne avec Spring Boot et Angular. Ce projet démontre les meilleures pratiques de conception distribuée, l'intégration d'IA, et le développement full-stack.

## 📋 Table des matières

- [Vue d'ensemble](#vue-densemble)
- [Architecture](#architecture)
- [Prérequis](#prérequis)
- [Installation et Configuration](#installation-et-configuration)
- [Structure du Projet](#structure-du-projet)
- [Modules](#modules)
- [Démarrage du Projet](#démarrage-du-projet)
- [API Documentation](#api-documentation)
- [Technologie](#technologie)
- [Tests](#tests)
- [Contribution](#contribution)

## 🎯 Vue d'ensemble

**eBank** est une plateforme bancaire moderne qui fournit :

- ✅ Gestion des comptes clients
- ✅ Services de transactions bancaires
- ✅ Service de découverte (Service Registry)
- ✅ API Gateway pour le routage
- ✅ Chatbot IA pour support client
- ✅ Interface web Angular responsive
- ✅ Architecture cloud-native avec Spring Cloud

## 🏗️ Architecture

### Architecture Microservices

```
┌─────────────────────────────────────────────────────────┐
│                    Frontend (Angular)                    │
│              (Port 4200 - Développement)                 │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│              API Gateway Service                         │
│           (Port 8888 - Routage & Équilibrage)           │
└──┬────────────┬──────────────┬──────────────┬───────────┘
   │            │              │              │
   ▼            ▼              ▼              ▼
┌────────┐ ┌────────┐    ┌──────────┐  ┌─────────────┐
│Customer│ │eBank   │    │Discovery │  │  eBank Bot  │
│Service │ │Service │    │ Service  │  │   (AI)      │
│        │ │        │    │(Eureka)  │  │             │
│Port    │ │Port    │    │Port 8761 │  │Port 8080    │
│8081    │ │8082    │    │          │  │             │
└────────┘ └────────┘    └──────────┘  └─────────────┘
   │ H2       │ H2
   │ DB       │ DB
```

### Components

- **Frontend** : Interface utilisateur Angular 21
- **API Gateway** : Point d'entrée unique pour tous les services
- **Discovery Service** : Eureka pour le service registry
- **Customer Service** : Gestion des clients et authentification
- **eBank Service** : Opérations bancaires principales
- **eBank Bot** : Chatbot IA (Spring AI)

## 📦 Prérequis

### Logiciels Requis

- **Java** : JDK 21 ou supérieur
- **Maven** : 3.8.1 ou supérieur
- **Node.js** : 18.x ou supérieur
- **npm** : 11.6.2 ou supérieur
- **Git** : Pour cloner le projet

### Versions Clés

| Composant | Version |
|-----------|---------|
| Spring Boot | 3.5.14 |
| Spring Cloud | 2025.0.2 |
| Java | 21 |
| Angular | 21.1.0 |
| Spring AI | 1.1.5 |

## 🚀 Installation et Configuration

### 1. Cloner le Repository

```bash
git clone https://github.com/your-repo/ebank-ms-app.git
cd ebank-ms-app
```

### 2. Configurer l'Environnement Java

```bash
# Vérifier la version de Java
java -version

# Vérifier Maven
mvn -version
```

### 3. Construire tous les modules Backend

```bash
# Du répertoire racine
mvn clean install

# Ou avec skip tests (plus rapide)
mvn clean install -DskipTests
```

### 4. Configurer le Frontend

```bash
cd frontend

# Installer les dépendances
npm install

# Retour au répertoire racine
cd ..
```

### 5. Configuration des Services

Chaque service dispose d'un fichier de configuration :

- `customer-service/src/main/resources/application.properties`
- `ebank-service/src/main/resources/application.properties`
- `discovery-service/src/main/resources/application.properties`
- `gateway-service/src/main/resources/application.yml`
- `ebank-bot/src/main/resources/application.yml`

**Exemple de configuration Spring Cloud** :
```properties
# Service Name
spring.application.name=customer-service

# Eureka Client
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
```

## 📁 Structure du Projet

```
ebank-ms-app/
├── frontend/                          # Application Angular
│   ├── src/
│   │   ├── app/
│   │   │   ├── accounts/             # Gestion des comptes
│   │   │   ├── bot-ui/               # Interface Chatbot
│   │   │   ├── interceptors/         # Intercepteurs HTTP
│   │   │   ├── model/                # Modèles de données
│   │   │   └── services/             # Services API
│   │   ├── index.html
│   │   ├── main.ts
│   │   └── styles.css
│   ├── package.json
│   ├── angular.json
│   └── tsconfig.json
│
├── customer-service/                  # Microservice clients
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/camoutech/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── ebank-service/                     # Microservice opérations bancaires
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/camoutech/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── discovery-service/                 # Service Registry (Eureka)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/camoutech/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── gateway-service/                   # API Gateway
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/camoutech/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── ebank-bot/                         # Chatbot avec IA
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/camoutech/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── pom.xml                            # POM parent
├── mvnw                               # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                           # Maven Wrapper (Windows)
└── README.md                          # Ce fichier
```

## 🔧 Modules

### Discovery Service (Service Registry)
- **Port** : 8761
- **Rôle** : Eureka Server pour l'enregistrement et la découverte des services
- **Accès** : http://localhost:8761

### Gateway Service (API Gateway)
- **Port** : 8888
- **Rôle** : Routage et équilibrage de charge
- **Accès** : http://localhost:8888

### Customer Service
- **Port** : 8081
- **Rôle** : Gestion des clients, authentification, profils utilisateur
- **Base de données** : H2 (en mémoire)
- **Dépendances clés** : Spring Data JPA, Spring AI

### eBank Service
- **Port** : 8082
- **Rôle** : Opérations bancaires (comptes, transactions, virements)
- **Base de données** : H2 (en mémoire)
- **Dépendances clés** : Spring Data JPA, OpenAPI/Swagger

### eBank Bot
- **Port** : 8080
- **Rôle** : Chatbot IA pour support client
- **Dépendances clés** : Spring AI, Spring Web

### Frontend
- **Port** : 4200 (développement)
- **Framework** : Angular 21
- **Rôle** : Interface utilisateur pour les clients
- **Modules principaux** :
  - `accounts` : Affichage et gestion des comptes
  - `bot-ui` : Interface du chatbot
  - `services` : Communication API
  - `interceptors` : Gestion des requêtes HTTP

## ▶️ Démarrage du Projet

### Démarrage en Mode Développement (tous les services)

#### Terminal 1 - Discovery Service (Démarrer en premier)
```bash
cd discovery-service
mvn spring-boot:run
# Attend : "Tomcat started on port(s): 8761"
```

#### Terminal 2 - Gateway Service
```bash
cd gateway-service
mvn spring-boot:run
# Attend : "Tomcat started on port(s): 8888"
```

#### Terminal 3 - Customer Service
```bash
cd customer-service
mvn spring-boot:run
# Attend : "Tomcat started on port(s): 8081"
```

#### Terminal 4 - eBank Service
```bash
cd ebank-service
mvn spring-boot:run
# Attend : "Tomcat started on port(s): 8082"
```

#### Terminal 5 - eBank Bot
```bash
cd ebank-bot
mvn spring-boot:run
# Attend : "Tomcat started on port(s): 8080"
```

#### Terminal 6 - Frontend
```bash
cd frontend
npm start
# Attend : "Application bundle generation complete"
# Ouvrir : http://localhost:4200
```

### Démarrage Rapide avec Maven

```bash
# Construire tous les modules
mvn clean install

# Démarrer tous les services en parallèle (dans des terminaux séparés)
mvn -pl discovery-service spring-boot:run
mvn -pl gateway-service spring-boot:run
mvn -pl customer-service spring-boot:run
mvn -pl ebank-service spring-boot:run
mvn -pl ebank-bot spring-boot:run
```

### Ordre de Démarrage Recommandé

1. **Discovery Service** (8761) - Service Registry
2. **Gateway Service** (8888) - Point d'entrée API
3. **Customer Service** (8081) - Clients
4. **eBank Service** (8082) - Opérations bancaires
5. **eBank Bot** (8080) - Chatbot IA
6. **Frontend** (4200) - Interface utilisateur

## 📚 API Documentation

### Accès Eureka Dashboard
```
http://localhost:8761
```

### Endpoints Principaux via Gateway

#### Health Check
```bash
# Vérifier l'état d'un service
curl http://localhost:8888/customer-service/actuator/health
curl http://localhost:8888/ebank-service/actuator/health
```

#### Customer Service API
```bash
# Lister les clients
GET /customer-service/api/customers

# Créer un client
POST /customer-service/api/customers

# Obtenir un client
GET /customer-service/api/customers/{id}
```

#### eBank Service API
```bash
# Lister les comptes
GET /ebank-service/api/accounts

# Créer un compte
POST /ebank-service/api/accounts

# Lister les transactions
GET /ebank-service/api/transactions
```

#### Swagger/OpenAPI
```
http://localhost:8082/swagger-ui.html  # eBank Service
```

## 💻 Technologie

### Backend
- **Spring Boot** 3.5.14 - Framework web Java
- **Spring Cloud** - Microservices (Eureka, Gateway, Config)
- **Spring Data JPA** - ORM et accès aux données
- **Spring AI** - Intégration IA
- **H2 Database** - Base de données en mémoire
- **Maven** - Gestion de dépendances

### Frontend
- **Angular** 21.1.0 - Framework SPA
- **TypeScript** - Langage de programmation
- **RxJS** - Programmation réactive
- **Node.js/npm** - Environnement

### Infrastructure
- **Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **Actuator** - Monitoring et santé des services

## 🧪 Tests

### Tester les Services Backend

```bash
# Exécuter les tests unitaires
mvn test

# Exécuter les tests avec couverture de code
mvn clean test jacoco:report

# Tester un module spécifique
mvn -pl customer-service test
```

### Tester le Frontend

```bash
cd frontend

# Exécuter les tests Angular
npm test

# Exécuter les tests avec couverture
npm test -- --code-coverage

# Build de production
npm run build
```

## 🔐 Sécurité

- Utilisez des **variables d'environnement** pour les données sensibles
- Mettez à jour les dépendances régulièrement : `mvn dependency:tree`
- Activez HTTPS en production
- Implémentez l'authentification OAuth2/JWT

## 📝 Logging

Les logs sont configurés via Spring Boot Actuator et peuvent être consultés :

```bash
# Logs en temps réel
curl http://localhost:8761/actuator/loggers

# Changer le niveau de log
curl -X POST http://localhost:8761/actuator/loggers/com.camoutech \
  -H "Content-Type: application/json" \
  -d '{"configuredLevel":"DEBUG"}'
```

## 📦 Build et Déploiement

### Build en Production

```bash
# Frontend
cd frontend
npm run build
# Sortie dans : dist/

# Backend (tous les modules)
mvn clean install -Pprod -DskipTests
```

### Créer des Docker Images

```bash
# Pour chaque service
mvn spring-boot:build-image

# Exemple
cd customer-service
mvn spring-boot:build-image
```

## 🐛 Troubleshooting

### Port déjà utilisé
```bash
# Linux/Mac - Tuer le processus
lsof -ti:8761 | xargs kill -9

# Windows
netstat -ano | findstr :8761
taskkill /PID <PID> /F
```

### Maven issues
```bash
# Nettoyer le cache local
mvn clean
rm -rf ~/.m2/repository

# Réinstaller
mvn install
```

### Service non détecté par Eureka
```bash
# Vérifier la configuration
curl http://localhost:8761/eureka/apps/

# Vérifier les logs du service
# Chercher "Registering application" dans les logs
```

### Frontend ne communique pas avec le backend
```bash
# Vérifier la configuration de proxy dans angular.json
# Vérifier CORS dans les services
# Consulter les erreurs dans la console du navigateur
```

## 📞 Support

Pour des questions ou issues :
1. Consulter la section [Troubleshooting](#troubleshooting)
2. Vérifier les logs des services
3. Valider que tous les services sont bien enregistrés dans Eureka
4. Créer une issue sur GitHub avec les logs d'erreur

## 📄 Licence

Ce projet est distribué sous la licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👥 Auteur

**Camoutech**

## 🔄 Contribution

Les contributions sont les bienvenues ! 

1. Forker le projet
2. Créer une branche feature (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📚 Ressources Utiles

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Cloud Documentation](https://spring.io/cloud)
- [Angular Documentation](https://angular.io/docs)
- [Eureka Service Discovery](https://github.com/Netflix/eureka)
- [Spring AI Documentation](https://docs.spring.io/spring-ai/docs/current/reference/html/)

---

**Dernière mise à jour** : 2025-01-09  
**Version** : 0.0.1-SNAPSHOT
