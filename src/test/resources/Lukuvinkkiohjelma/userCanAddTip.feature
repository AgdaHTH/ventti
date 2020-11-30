Feature: A user can add a tip in the application 

    Scenario: a tip can be added
        Given command lisaa is selected
        When a new tip with title "Opus" and type "Kirja" is added
        Then system will respond with "Vinkki lisatty onnistuneesti!" 

    Scenario: a booktip can be added
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And program is terminated
        Then system will respond with "Uusi kirjavinkki lisatty onnistuneesti!" 

