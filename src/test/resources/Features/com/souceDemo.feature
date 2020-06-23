Feature:Testing SouceDemo Page

  Background: the user goes to page
    Given the user navigate to page url
    When the user validate title "Swag Labs" and url "https://www.saucedemo.com/inventory.html"



  Scenario:Sorting and shopping cart validation
    When the user sort items name A to Z "Name (A to Z)"
    Then the user add 3 items to the shopping cart
    Then the user click shopping cart
    And Validate the items that you added

    @case2
    Scenario:Removing and Re add items
      When the user add 3 items to the shopping cart
      Then the user click shopping cart
      Then the user remove one item and continue shopping
      Then  add another item
      Then the user click shopping cart
      And the user click checkout and continue with correct information
      And the user Assert you are purchasing the correct items
      And the user Assert the total price
      And the user validate final message "THANK YOU FOR YOUR ORDER"










