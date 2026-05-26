# FIX Protocol Automation Framework Prompt Collection

## Objective

This document contains all major prompts and requirement refinements used to design and enhance the FIX Protocol Automation Framework.

The framework was developed iteratively using prompt-driven architecture refinement.

---

# Initial Framework Requirement

## Base Prompt

Your role : Test Automation Developer

Framework : BDD, Cucumber

Framework files :
- feature
- config
- step definition
- implementation class files
- .md documentation file

Scripting language : Java

Domain : Exchange Trading

Product : FIX Protocol Engine

Requirement :

Develop a test automation tool to test this FIX Protocol engine at both:
- Client End
- Server End

Consider following test case and generate the code:

1. Start FIX protocol engine
2. Connect session
3. Place order
4. Modify order
5. Cancel order

Take real-time data for above case.

---

# Feature File Data-Driven Enhancement

## Prompt

Modify the implementation with accepting data from feature file instead of hard-coded values inside methods.

---

# Socket Communication Enhancement

## Prompt

Replace Java code of Socket class to establish connection in place of "Actual session connection logic" comment of SessionManager.java class.

Also update feature file with supported data in tabular format.

---

# Receiver Session Validation Enhancement

## Prompt

Connect one more session at sender's end so we can read what AUT sends to other receiver.

Modify the case to verify data at both ends.

---

# Dynamic FIX Template Enhancement

## Prompt

Need to work on FixMessageBuilder.java class.

Avoid hard-coding values.

Consider base FIX tags for each type of transaction in:
- HashMap
- Similar object

Read base data from config file.

Once transaction function gets called:
- replace base data with runtime parameter values
- call converter method
- convert object into FIX instruction string
- return FIX message

---

# Framework Architecture Correction

## Prompt

Fix converter is ok.

Do not change other framework layers.

OrderManager should remain same.

Transaction methods should pass test data in String parameter format only instead of HashMap.

---

# Validation Logic Enhancement

## Prompt

OrderManager does not contain all methods in ZIP file.

Replace:

```java
// Actual validation logic