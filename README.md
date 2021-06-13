# Wallet_API

Developed On Spring Boot
Database - H2


Player
  Create player
  Find player
  Retrieve All players


Wallet
  Debit transaction
  Credit transaction
  Transactin History By Player
  
  
For-Production ready
  Use authorization
  Pass userId into the request 
  There can be duplicated player names in the system we should allow it but different identified ID.
  Store more information of players
  Use relational database like Mysql or Postgre
  Logging every request/response to the file since it contains transactions.
  Unit/Automatic Test
  Handle Exception and Response messages
  Currency code better populated from Database or Enum values
  API documentation for developers
  Using GlobalValidatorService to validate incoming requests
