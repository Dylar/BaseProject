# SVN Repository Checkout
`svn checkout https://ci@svn.bornholdtlee.de/svn/KlimaTraxSmartphone/` 

# Authors File generieren
`svn log --xml | grep author | sort -u | perl -pe 's/.*>(.*?)<.*/$1 = /' > authors.txt`

# authors.txt bearbeiten
In der authors.txt müssen die entsprechenden Git benutzer eingetragen werden damit der import funktioniert. Die Datei hat folgendes Format:

    ce = 
    daniel.scheibe = 
    jr =
    justus.klawisch = 
    nb = 
    sebastian.bassen = 

Die eingetragenen E-Mail Adressen müssen das format `Voller Name <email.adress@host.de>` haben. Ansonsten nimmt der import befehl sie nicht an.

# Repo aus SVN in Git importieren
`git svn clone https://ci@svn.bornholdtlee.de/svn/KlimaTraxSmartphone/ -TAndroid/trunk --authors-file=authors.txt --no-metadata -s KlimaTraxSmartphoneGit`

Mit diesem Befehl wird wird ein Ordner erstellt in dem das SVN Repository als Git Repository abgelegt wurde.