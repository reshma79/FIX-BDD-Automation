Feature: FIX Protocol Complete End-To-End Validation

  Background:
    Given FIX application is started 
    And Client FIX session is started using config "src/test/resources/config/quickfixj_Client.cfg"
    And Receiver FIX session is started using config "src/test/resources/config/quickfixj_Receiver.cfg"

  Scenario: Validate FIX Order Lifecycle At Both Ends

    When user places FIX order:
      | symbol   | quantity | price | side |
      | RELIANCE | 100      | 2500  | BUY  |

    Then client execution report should contain:
      | 35 | 39 | 150 |
      | 8  | 0  | 0   |

    And receiver FIX message should contain:
      | 35 | 55       | 38  | 44   |
      | D  | RELIANCE | 100 | 2500 |

    When user modifies FIX order:
      | quantity | price |
      | 150      | 2550  |

    Then client execution report should contain:
      | 35 | 39 | 150 |
      | 8  | 5  | 5   |

    And receiver FIX message should contain:
      | 35 | 38  | 44   |
      | G  | 150 | 2550 |

    When user cancels the FIX order

    Then client execution report should contain:
      | 35 | 39 | 150 |
      | 8  | 4  | 4   |

    And receiver FIX message should contain:
      | 35 |
      | F  |

  Scenario: Validate FIX Order Cancellation Lifecycle At Both Ends

    When user places FIX order:
      | symbol | quantity | price | side |
      | TCS    | 500      | 5500  | SELL |

    Then client execution report should contain:
      | 35 | 39 | 150 |
      | 8  | 0  | 0   |

    And receiver FIX message should contain:
      | 35 | 55  | 38  | 44   |
      | D  | TCS | 500 | 5500 |

    When user cancels the FIX order

    Then client execution report should contain:
      | 35 | 39 | 150 |
      | 8  | 4  | 4   |

    And receiver FIX message should contain:
      | 35 |
      | F  |
      