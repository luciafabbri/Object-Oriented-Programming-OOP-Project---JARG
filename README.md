# OOP-Project

## JARG
Il gruppo si pone come obiettivo quello di realizzare un gioco Rouge-like (https://it.wikipedia.org/wiki/Roguelike) vecchio stile, chiamato Just Another Rouge-like Game (J.A.R.G.).

## NOTES
All'interno della classe LoadNatives è presente il metodo disableAccessWarning, utilizzato per bloccare un warning "illegal reflective access" causato dalla libreria LWJGL. Non siamo riusciti a trovare soluzioni alternative a causa del fatto che questa versione della libreria (2.9.3) è stata discontinuata, e viene eseguita dalle versioni 9 di Java o dopo con dei warning, i quali vengono gestiti dalla versione 3.1.1, quest'ultima però non è compatibile con la libreria Slick2D. Consapevoli del fatto che il warning non causi alcun tipo di problema al corretto funzionamento del software, e non essendo riusciti a rimuoverlo, abbiamo deciso di sopprimerlo, pur riconoscendo che tale soluzione non sia quella ottimale.
