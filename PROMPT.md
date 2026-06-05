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
```

# QuickFIX/J Migration and Framework Refactoring

## Prompt

What changes do I have to do in framework if I implement QuickFIX/J?

Requirement:

- Identify impacted classes.
- Keep BDD framework structure unchanged.
- Replace socket-based FIX session management with QuickFIX/J.
- Preserve Feature Files, Step Definitions and OrderManager flow where possible.

---

# QuickFIX/J Session Layer Implementation

## Prompt

Can you create QuickFixSessionManager.java and QuickFixApplication.java files for my project?

Requirement:

Create reusable QuickFIX/J wrapper classes for:

- Session startup
- Session shutdown
- Message transmission
- Incoming message capture
- Outgoing message capture

---

# QuickFIX/J Dependency Integration

## Prompt

Show me required Maven dependencies.

Requirement:

Add QuickFIX/J dependencies to pom.xml.

Required libraries:

- quickfixj-core
- quickfixj-messages-fix44

Optional:

- slf4j logging

---

# QuickFIX/J Message Creation

## Prompt

Replace tag-based setString() calls with QuickFIX/J field objects.

Requirement:

Replace:

- setString(11,...)
- setString(55,...)
- setString(38,...)
- setString(44,...)

with:

- ClOrdID
- Symbol
- OrderQty
- Price
- Side

---

# Feature File Refactoring

## Prompt

Modify Feature File for following:

1. Add Background before all scenarios.
2. Connect Client and Receiver sessions using cfg file paths.
3. Represent scenario data in horizontal format.
4. Add provision for Excel or external datasheet integration.
5. Provide framework-level mechanism to close sessions after execution.

---

# Horizontal Data Table Conversion

## Prompt

Convert feature file data into horizontal format.

Requirement:

Replace vertical key-value tables with:

| ClOrdID | Symbol | Quantity | Price | Side |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| ORD1001 | RELIANCE | 100 | 2500 | BUY |

style tables.

---

# Step Definition Refactoring

## Prompt

Suggest changes in step definition function.

Requirement:

Support horizontal DataTable structure.

Use:

table.asMaps(String.class, String.class).get(0)

instead of:

table.asMap(String.class, String.class)

---

# QuickFIX/J Validation Refactoring

## Prompt

How will MessageValidator.java change for QuickFIX/J implementation?

Requirement:

- Remove FIX string parsing dependency.
- Remove String → Map conversion dependency.
- Validate directly using QuickFIX/J Message objects.
- Support generic FIX tag validation.

---

# Receiver Side Validation Refactoring

## Prompt

What about validateReceiverSideMessage()?

Requirement:

- Support receiver-side validation using QuickFIX/J.
- Reuse MessageValidator.
- Validate messages captured from ReceiverSessionManager.

---

# Final Framework Architecture Refactoring

## Prompt

Modify project as per below:

1. QuickFixSessionManager acts as generic QuickFIX/J wrapper.
2. QuickFixApplication stores incoming and outgoing messages.
3. ClientSessionManager creates and manages Client FIX session.
4. ReceiverSessionManager creates and manages Receiver FIX session.
5. OrderManager interacts only with ClientSessionManager.
6. MessageValidator validates Client and Receiver messages.
7. FixStepDefinitions interacts with ClientSessionManager and ReceiverSessionManager.

Requirement:

Maintain layered architecture:

FixStepDefinitions
→ OrderManager
→ ClientSessionManager
→ QuickFixSessionManager
→ QuickFIX/J

and

MessageValidator
→ ClientSessionManager
→ ReceiverSessionManager

---

# Documentation Update

## Prompt

Update README.md and PROMPT.md to reflect final QuickFIX/J implementation.

Requirement:

Document:

- New architecture
- Session management flow
- QuickFIX/J integration
- Feature file changes
- Validation flow
- Runtime execution flow
- Future enhancement roadmap

