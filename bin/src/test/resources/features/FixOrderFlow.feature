Feature: FIX Protocol Complete End-To-End Validation

  Scenario: Validate FIX Order Lifecycle At Both Ends

    Given FIX engine is started

    And client FIX session is connected with:
      | host | 127.0.0.1 |
      | port | 9876      |

    And receiver FIX session is connected with:
      | host | 127.0.0.1 |
      | port | 9999      |

    When user places FIX order:
      | symbol   | RELIANCE |
      | quantity | 100      |
      | price    | 2500     |
      | side     | BUY      |

    Then order should be validated at client end
    And order should be validated at receiver end