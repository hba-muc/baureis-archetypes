# Baureis Maven Archetypes

Archetypes for Baureis sample applications.

## Local archetype installation

1. Download and unzip this project from GitHub. 
2. Install the archetypes to your local system:

```cmd
cd baureis-archetypes
mvn clean install
```

## Create a new project from command line

Run 

```cmd
mvn archetype:generate -DarchetypeCatalog=local
```

on your command line and select an archetype.

## Create a new project using Eclipse

Create Maven project from archetype: **File -> New -> Maven -> Maven Project**
