Feature: A user can delete an existing a tip

    Scenario: a booktip can be deleted
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And confirmation "y" is entered
        And program is terminated
        Then output will contain text "Vinkki poistettu onnistuneesti!"

    Scenario: a podcasttip can be deleted
        Given command lisaa podcast is selected
        When a new podcasttip with title "Hauska podcast" and url "www.podcast.org" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And confirmation "y" is entered
        And program is terminated
        Then output will contain text "Vinkki poistettu onnistuneesti!"

    Scenario: a blogtip can be deleted
        Given command lisaa blogi is selected
        When a new blogtip with title "Kivablogi", author "Bloggaaja" and url "www.blogi.org" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And confirmation "y" is entered
        And program is terminated
        Then output will contain text "Vinkki poistettu onnistuneesti!"

    Scenario: deletion can be cancelled
        Given command lisaa blogi is selected
        When a new blogtip with title "Blogi", author "Bloggaaja" and url "blogiurl" is added
        And command poista vinkki is selected
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"

    Scenario: specific deletion can be cancelled
        Given command lisaa blogi is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "12345" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And input "n" is entered
        And program is terminated
        Then output will contain text "Ei poistettu"