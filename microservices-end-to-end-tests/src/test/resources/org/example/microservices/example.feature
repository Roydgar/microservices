Feature: Order

  Scenario: Successful order creation
    Given a new customer is created
    When a new order is created for the created customer
    Then order is in "CONFIRMED" status
    And cancel reason of the order is empty

  Scenario: Order creation failed due to non-existing customer
    When order is created for a non-existing customer
    Then order is in "CANCELLED" status
    And cancel reason of the order is "CUSTOMER_VERIFICATION_FAILED"