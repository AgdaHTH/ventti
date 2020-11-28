Feature: A user can add a tip in the application   

    Scenario: a tip can be added
        Given command lisaa is selected
        When a new tip with title "Opus" and type "Kirja" is added
        Then system will respond with "Vinkki lisatty onnistuneesti!" 