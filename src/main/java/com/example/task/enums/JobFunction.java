package com.example.task.enums;

public enum JobFunction {

    ACCOUNTING_AND_FINANCE("Accounting & Finance", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIkFjY291bnRpbmcgJiBGaW5hbmNlIl19"),
    ADMINISTRATION("Administration", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIkFkbWluaXN0cmF0aW9uIl19"),
    CUSTOMER_SERVICE("Customer Service", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIkN1c3RvbWVyIFNlcnZpY2UiXX0%3D"),
    DATA_SCIENCE("Data Science", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIkRhdGEgU2NpZW5jZSJdfQ%3D%3D"),
    DESIGN("Design", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIkRlc2lnbiJdfQ%3D%3D"),
    IT("IT", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIklUIl19"),
    LEGAL("Legal", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIkxlZ2FsIl19"),
    MARKETING_AND_COMMUNICATIONS("Marketing & Communications", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIk1hcmtldGluZyAmIENvbW11bmljYXRpb25zIl19"),
    OPERATIONS("Operations", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIk9wZXJhdGlvbnMiXX0%3D"),
    OTHER_ENGINEERING("Other Engineering", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIk90aGVyIEVuZ2luZWVyaW5nIl19"),
    PEOPLE_AND_HR("People & HR", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIlBlb3BsZSAmIEhSIl19"),
    PRODUCT("Product", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIlByb2R1Y3QiXX0%3D"),
    QUALITY_ASSURANCE("Quality Assurance", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIlF1YWxpdHkgQXNzdXJhbmNlIl19"),
    SALES_AND_BUSINESS_DEVELOPMENT("Sales and Business Development", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIlNhbGVzICYgQnVzaW5lc3MgRGV2ZWxvcG1lbnQiXX0%3D"),
    SOFTWARE_ENGINEERING("Software Engineering", "https://jobs.techstars.com/jobs?filter=eyJqb2JfZnVuY3Rpb25zIjpbIlNvZnR3YXJlIEVuZ2luZWVyaW5nIl19");

    private String jobName;

    private String jobLink;

    JobFunction(String jobName, String jobLink) {
        this.jobName = jobName;
        this.jobLink = jobLink;
    }


}
