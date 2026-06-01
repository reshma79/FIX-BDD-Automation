Feature: FIX Protocol Complete End-To-End Validation

  Scenario: Validate FIX Order Lifecycle At Both Ends

    Given FIX application is started

    And client FIX session is connected with:
      | host | 127.0.0.1 |
      | port | 5000      |

    And receiver FIX session is connected with:
      | host | 127.0.0.1 |
      | port | 6000      |

    When user places FIX order:
      | symbol   | RELIANCE |
      | quantity | 100      |
      | price    | 2500     |
      | side     | BUY      |

    Then client execution report should contain:
      | 35 | 8 |
      | 39 | 0 |
      | 150| 0 |

    And receiver FIX message should contain:
      | 35 | D         |
      | 55 | RELIANCE  |
      | 38 | 100       |
      | 44 | 2500      |

    When user modifies FIX order:
      | quantity | 150  |
      | price    | 2550 |

    Then client execution report should contain:
      | 35 | 8 |
      | 39 | 5 |
      | 150| 5 |

    And receiver FIX message should contain:
      | 35 | G    |
      | 38 | 150  |
      | 44 | 2550 |

    When user cancels the FIX order

    Then client execution report should contain:
      | 35 | 8 |
      | 39 | 4 |
      | 150| 4 |

    And receiver FIX message should contain:
      | 35 | F |

  Scenario: Validate FIX Order Cancellation Lifecycle At Both Ends

    Given FIX application is started

    And client FIX session is connected with:
      | host | 127.0.0.1 |
      | port | 5000      |

    And receiver FIX session is connected with:
      | host | 127.0.0.1 |
      | port | 6000      |

    When user places FIX order:
      | symbol   | TCS 		|
      | quantity | 500      |
      | price    | 5500     |
      | side     | SELL     |

    Then client execution report should contain:
      | 35 | 8 |
      | 39 | 0 |
      | 150| 0 |

    And receiver FIX message should contain:
      | 35 | D         |
      | 55 | TCS       |
      | 38 | 500       |
      | 44 | 5500      |

    When user cancels the FIX order

    Then client execution report should contain:
      | 35 | 8 |
      | 39 | 4 |
      | 150| 4 |

    And receiver FIX message should contain:
      | 35 | F |
