# DataOxScrapingTask

Dataox Scraping Project is a web scraping application built in Java that extracts job listings from various job boards and websites. The project aims to collect relevant job information, such as job titles, employers, locations, posted dates, and tags, from multiple sources and store them in a centralized database.

## Table of Contests

- **Installation**
- **Usage Example**
- **Technologies**
- **Endpoints**
- *Notice*

## Installation

Clone the repository to your local machine:

```git
git clone https://github.com/your-username/dataoxscrapingproject.git
```

Navigate to the project directory:

```cmd
cd dataoxscrapingproject
```

Build the application

```cmd
./mvnw clean package -DskipTests
```

Run the application in a multiple Docker containers:

```docker
docker-compose up -d --build
```

The application will be launched in multiple Docker containers: one container for the Spring Boot application and one for PostgreSQL database.

After the application starts, it will be available to receiveing messages http://localhost:8080.


## Usage Example

Given application oriented to parse vacancies from job-site `job.techstars.com`. Here we have a two endpoints with two different meaning: Minimized - to receive only a general info
about vacancions, Detailed - to receive a compete data from vacancy that include all field reliable to given vacancy.

For example, make a GET request to localhost:8080/minimized-jobs?function=DataSciece. Down here what we'll receive:

```json
{
    "totalJobsCounter": 197,
    "previewJobList": [
        {
            "previewJobId": 1,
            "previewJobTitle": "Senior Data Analyst",
            "previewJobEmployer": "Bench",
            "previewJobAddress": "Canada; Remote ·",
            "previewJobPostedTime": "2023-07-21",
            "previewJobTags": [
                "Finance",
                "Software",
                "201 - 1000",
                "Series C+"
            ]
        },...
    }
```

For example, make a GET request to localhost:8080/detailed-jobs?function=DataSciece. Down here what we'll receive:

```json
{
    "jobList": [
        {
            "jobId": 1,
            "jobName": "Senior Data Analyst",
            "jobUrlToEmployer": "https://bench.co/careers/job/?gh_jid=5203364&utm_source=Techstars+job+board&utm_medium=getro.com&gh_src=Techstars+job+board",
            "jobLogoLink": "https://cdn.filestackcontent.com/C4tkMeQiQ8uXRlACkmKS",
            "jobOrganizationTitle": "Bench",
            "jobLaborFunction": "",
            "jobAddress": "",
            "jobPostedDate": "2023-07-21",
            "jobDescription": "What’s Bench?..."
        },...
  }
```


## Technologies

DataOxScrapingProject was created using the following technologies:
  - Java
  - Spring Boot (Data, JPA, Security)
  -  Liquibase
  -  PostgreSQL
  -  H2
  -  Docker
  -  Docker Compose

## Endpoints

The application provides the following endpoints:

  - `GET /detailed-jobs?function=text` - retrieves detailed job info list.
  - `GET /minimized-jobs?function=text` - retrieves minimized jobs info in list.

## Notice ♻️

Also, if you want to see the results of the program, you can follow this [link](https://drive.google.com/file/d/1i5r4LOZPwUJX7ddUv3MnGKsJFBJX9bud/view?usp=sharing) to google drive and download a backup with already parsed data. After that, you can copy the backup data to your database through PgAdmin
