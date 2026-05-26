# FIX Protocol Automation Framework

## Objective

Develop an enterprise-grade FIX Protocol automation framework using:

- Java
- BDD
- Cucumber
- Socket Programming
- Config-Driven FIX Templates

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

Framework establishes socket connection with AUT from sender/client side.

Example:

```text
Client → AUT
```
---

## 3. Receiver Session Connection

Framework establishes socket connection with receiver/exchange side to capture outgoing FIX messages.

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
      │     ├── stepdefinitions
      │     └── utils
      │
      └── resources
            ├── config
            └── features
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
FixMessageBuilder
    ↓
FixMessageConverter
    ↓
ClientSessionManager
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

## 4. FixMessageBuilder

Creates dynamic FIX messages using:
- config templates
- runtime test data
- placeholder replacement

---

## 5. FixMessageConverter

Converts FIX template into final FIX string format.

Maintains proper FIX tag sequence.

---

## 6. ClientSessionManager

Establishes socket connection with AUT.

Responsibilities:
- send FIX requests
- receive execution reports

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
- end-to-end validation
- exchange-side validation
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
| Communication | Socket Programming |
| Validation | JUnit Assert |
| Build Tool | Maven |

---

# Current Enterprise-Level Features

Implemented:

- Dynamic FIX templates
- Config-driven framework
- Sender-side validation
- Receiver-side validation
- Dynamic FIX parsing
- Data-driven validation
- Socket-level communication
- Runtime placeholder replacement

---

# Recommended Future Enhancements

- Session management
- Sequence numbers
- Logon/Logout
- Heartbeats
- FIX dictionary validation
- FIX Checksum support
- Reporting Integration
- Multi-Exchange Support through external FIX template
---

# Conclusion

This framework provides a scalable enterprise-grade foundation for:

- FIX Certification Testing
- OMS Testing
- EMS Testing
- Exchange Connectivity Validation
- Broker Integration Testing

using dynamic and maintainable automation architecture.