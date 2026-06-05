# FIX Protocol Automation Framework

## Objective

Develop an enterprise-grade FIX Protocol automation framework using:

- Java
- Cucumber BDD
- Maven
- JUnit
- QuickFIX/J
- Allure Reporting

The framework validates FIX message flow at both:

1. Client Side
2. Receiver Side

through the Application Under Test (AUT).

---

# High-Level Architecture

Client Session  →  AUT (FIX Engine)  →  Receiver Session

The framework acts as both:

- FIX Client Simulator
- FIX Receiver Simulator

to validate end-to-end FIX communication.

---

# Supported Business Flows

## 1. FIX Engine Startup

Framework starts and validates FIX engine availability.

---

## 2. Client Session Connection

Framework establishes QuickFix/J connection with AUT from sender/client side.

Example:

```text
Client → AUT
```
---

## 3. Receiver Session Connection

Framework establishes QuickFix/J connection with receiver/exchange side to capture outgoing FIX messages.

Example:

```text
AUT → Receiver
```
---

# Framework Folder Structure

```text
src
 └── test
      ├── java
      │     ├── implementation
      │		│     ├── QuickFixSessionManager.java 	 
      │		│     ├── QuickFixApplication.java 	 
      │		│     ├── ClientSessionManager.java 	 
      │		│     ├── ReceiverSessionManager.java 	 
      │		│     ├── OrderManager.java 	 
      │		│     ├── MessageValidator.java 	 
      │		│     ├── FixMessageBuilder.java 	 
      │		│     └── EngineManager.java 	 
      │     ├── runner
      │		│     └── TestRunner.java 	 
      │     └── stepdefinitions
      │		      └── FixStepDefinitions.java 	 
      └── resources
            ├── config
       		│     ├── quickfixj_Client.cfg 	 
       		│     └── quickfixj_Receiver.cfg 	 
            └── features
       		      └── FixOrderFlow.feature 	 
            
```

---

# Final Runtime Flow

```text
Feature File
    ↓
Step Definitions
    ↓
OrderManager
    ↓
ClientSessionManager
    ↓
QuickFixSessionManager
    ↓
QuickFixApplication
    ↓
QuickFIX/J Session
    ↓
AUT (FIX Engine)
    ↓
ReceiverSessionManager
    ↓
MessageValidator
```

---

# Runtime Flow Explanation

## 1. Feature File

Contains:
- test scenarios
- FIX order data
- expected validation data

This layer is fully data-driven.

---

## 2. Step Definitions

Reads data from feature file and invokes framework business methods.

Example:
- establishing connections
- place order
- modify order
- cancel order
- validations

---

## 3. OrderManager

Handles business workflows.

Responsibilities:
- prepare transaction flow
- maintain order ids
- invoke FIX builder methods

This layer does not contain FIX formatting logic.

---

## 4. ClientSessionManager

Establishes QuickFIX/J connection with AUT.

Responsibilities:
- Manage Client FIX session
- Send FIX messages
- Retrieve Client responses

---

## 5. QuickFixSessionManager

Responsibilities:
- Load cfg file
- Start FIX session
- Stop FIX session
- Send FIX messages

---

## 6. QuickFixApplication

Responsibilities:
- Receive incoming FIX messages
- Capture outgoing FIX messages
- Handle FIX callbacks

---

## 7. AUT (FIX Engine)

Application Under Test.

Processes:
- new orders
- modifications
- cancellations

and forwards FIX messages to receiver side.

---

## 8. ReceiverSessionManager

Captures outgoing FIX messages sent by AUT.

Used for:
- Manage Receiver FIX session
- Retrieve Receiver-side messages
- outgoing FIX verification

---

## 9. MessageValidator

Validates:
- execution reports
- outgoing FIX messages

using:
- parsed FIX tags
- feature-file expected values
- JUnit assertions

---

# Current Technology Stack

| Component | Technology |
|---|---|
| Language | Java |
| Framework | Cucumber BDD |
| Test Style | Data Driven |
| Communication | QuickFIX/J |
| Validation | JUnit Assert |
| Build Tool | Maven |
| Reporting Tool | Allure Report |

---

# Current Implemented Enterprise-Level Features

## Session Management
- Client FIX Session Startup
- Receiver FIX Session Startup
- Session Shutdown
- QuickFIX/J Integration

## Order Lifecycle
- New Order
- Order Modification
- Order Cancellation

## Message Validation

Validation at:
- Client Response Side
- Receiver Message Side

## Reporting
- Allure Reports
- Cucumber Reports

---

# Recommended Future Enhancements

- Excel Driven Testing
- Multiple FIX Sessions
- FIX Dictionary Validation
- Multi Engine E2E Testing
- Order Book Validation
- Jenkins Integration
- Docker Execution

---

# Conclusion

This framework provides a scalable enterprise-grade foundation for:

- FIX Certification Testing
- OMS Testing
- EMS Testing
- Exchange Connectivity Validation
- Broker Integration Testing

using dynamic and maintainable automation architecture.