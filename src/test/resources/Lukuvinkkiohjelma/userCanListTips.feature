Feature: A user can list all tips

    Scenario: added tips are listed
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And command lisaa blogi is selected
        And a new blogtip with title "Kivablogi", author "Bloggaaja" and url "www.blogi.org" is added
        And command listaa vinkit is selected
        And program is terminated
        Then output will contain text "Opus"
        And output will contain text "Kivablogi"