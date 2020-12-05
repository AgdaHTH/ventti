Feature: A user can add a tip in the application   

    Scenario: a booktip can be added
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And program is terminated
        Then system will respond with "Uusi kirjavinkki lisatty onnistuneesti!" 
        And output will contain text "Opus"

    Scenario: a blogtip can be added
        Given command lisaa blogi is selected
        When a new blogtip with title "Kivablogi", author "Bloggaaja" and url "www.blogi.org" is added
        And program is terminated
        Then system will respond with "Uusi blogivinkki lisatty onnistuneesti!" 

    Scenario: a booktip can be deleted
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And program is terminated
        And output will contain text "Vinkki poistettu onnistuneesti!"

