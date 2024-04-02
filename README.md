# eBank Management Application

This is a web application for bank management, developed as a mini-project for the course "Enterprise Component Architecture". The application utilizes Spring Boot for the backend and Angular for the frontend, with GraphQL for communication and PostgreSQL for the database.

## Table of Contents
- [Introduction](#introduction)
- [Objectives](#objectives)
- [Technical Requirements and Design Patterns](#technical-requirements-and-design-patterns)
- [Functional Specifications](#functional-specifications)

## Introduction

This project aims to create an application for managing a bank's operations, including authentication, client management, account creation, and transaction handling. It's developed using modern technologies and adheres to best practices in software development.

## Objectives

- Develop a web application for bank management.
- Utilize Spring Boot for the backend and Angular for the frontend.
- Implement authentication using JWT and Spring Security.
- Secure application functionalities.
- Follow best practices including IOC, AOP, and DTO.

## Technical Requirements and Design Patterns

- Communication between frontend and backend via GraphQL.
- Implementation of Inversion of Control (IOC), Aspect-Oriented Programming (AOP), and Data Transfer Object (DTO) patterns.
- Use of Spring Boot, Spring Security, JJWT, Spring Data JPA, and PostgreSQL for the backend.
- Use of Angular for the frontend.

## Functional Specifications

The application must implement the following use cases:
1. Authentication: Users can authenticate as CLIENT or AGENT_GUICHET.
2. Adding a new client: Admins can add new clients with required information.
3. Creating a new bank account: Admins can create a new bank account for a client.
4. Viewing client dashboard: Clients can view their account details and recent transactions.
5. Performing a new transfer: Clients can initiate a new transfer to another account.
