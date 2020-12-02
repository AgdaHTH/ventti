Feature: A user can add a tip in the application   

    Scenario: a booktip can be added
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And program is terminated
        Then system will respond with "Uusi kirjavinkki lisatty onnistuneesti!" 

    Scenario: a booktip can be added and is printed
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And program is terminated
        Then system will respond with "Uusi kirjavinkki lisatty onnistuneesti!"
        And output will contain text "Opus"




