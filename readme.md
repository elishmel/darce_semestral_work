# Projekt Dárce

**Dárce je portál pro lidi. Pro všechny. Bez rozdílu.**

## Téma práce

Pro toto téma semestrální práce jsem se rozhodl v *(částečné)* návaznosti na téma mé semestrální práce v předmětu BI-DBS, kde jsem tvořil databázi pro fiktivní restauraci **Ahmed kebab**. Nejprve jsem chtěl plně využít tuto již existující databázi a vytvořit k ním systém na objednávky jídel. Ovšem, v rozsahu předmětu se mi toto rozhodnutí zdálo jako příliš strohé. Dospěl jsem tedy k závěru, že je potřeba se nad tématem znovu zamyslet a nakonec jsem zvolil portál **Dárce**. Tento portál slouží fiktivní neziskové organizaci  Dárce, která spojuje lidi v nouzi s lidmi s přebytkem a dobrým srdcem. Tato platforma uživatelům umožnuje vystavit nabídku na nějaký předmět či potravinu, pro kterou již nemají užití, nebo kterou se rozhodli zakoupit a darovat ostatním. Uživatelé, kteři nějakou formu materiální pomoci potřebují, mohou prohledávat seznam nabízeného zboží a vybrat si, co potřebují. Případně, pokud se předmět v aktuální nabídce nenachází, uživatel si může vytvořit "žádost" o specifickou položku.

## Konceptuální model

<img src="https://gitlab.fit.cvut.cz/nebesluk/tjv_semestral_work/-/raw/media/images/dbs_scheme.jpg?raw=true" alt="Database scheme" Width="450" Heihght="300">

## Návrh systému

Systém jsem se rozhodl navrhnout tak, že uživatelé mohou vytvořit nabídku na zboží, které nabízí. Stejně tak uživatelé mohou, pokud zrovna žádná nabídka tohoto typu neexistuje, o nějaké zboží požádat a jiní uživatelé jim pak v této žádosti mohou vyhovět. Platí pak vztah, že jeden uživatel může nabízet/poptávat více zboží.

## Business operace

**Operace přihlášení uživatele:**
- Uživatel vyplní své udáje
- Klikne na tlačítko "Přihlásit"
- Systém se podívá, zda již uživatel s uživatelským jménem existuje **(R)**
- Pokud uživatel neexistuje, systém oznámí neplatnost jména uživateli
- Pokud uživatel existuje, systém ověří hash jeho hesla **(R)**
- Pokud se heslo neshoduje, systém oznámí uživateli, že zadal nesprávného heslo
- Pokud se heslo shoduje, uživatel je úspěšně přihlášen (možno třeba zanést do databáze časové razítko posledního přihlášení) **(U)**

**Operace vytvoření nabídky**
- Uživatel klikne na tlačítko "Nabídnout"
- Systém mu zobrazí formulář pro vytvoření nabídky
- Po vyplnění formuláře se systém podívá
    - Zda je uživatel přihlášený **(R)**
    - Pokud uživatel není přihlášený, systém ho vyzve k přihlášení
    - Zda existují přiložené obrázky **(R)**
    - Pokud přiložené obrázky neexistují, systém oznámí chybu
    - Zda existují zvolené tagy **(R)**
    - Pokud zvolené tagy neexistují, systém oznámí chybu
- Projde li vše v pořádku, systém zavede nabídku do databáze se stavem isOffer=true (pokud by se jednalo o poptávu, isOffer by bylo nastaveno na false). **(C)**

**Operace odstranění nabídky**
- Uživatel klikne ve svém seznamu položek na položku, kterou si přeje spravovat 
- Systém ověří, zda je uživatel přihlášený
- Pokud není, systém ohlásí chybu
- Pokud ano, systém ověří, zda nabídka skutečně patří aktuálně přihlášenému uživateli **(R)**
- Pokud nepatří, systém ohlásí chybu
- Pokud patří, systém se uživatele zeptá, zda si přeje nabídku trvale odstranit, či pouze "deaktivovat"
- Pokud si uživatel přeje nabídku deaktivovat, systém nastaví stav isActive na false **(U)**
- Pokud si uživatel přeje nabídku trvale smazat, systém odstraní záznam z databáze **(D)**



## Komplexní dotaz

Komplexní dotaz nad databází bude mít podobu "Všechny nabídky od uživatele _____ s tagem _____" .

----

***Disclaimer:** Projekt Dárce je pracovní název, není finální a v průběhu vývoje se očekávají změny.<br> Projekt Dárce není chráněný žádnou ochranou známkou. Schoda s libovolnou existující fyzickou či právní entitou je čistě náhodná.*


---

## Popis odevzdané práce

Projekt se, po vzoru práce ze semestru, skládá ze dvou částí:
- Backendu - Ten lze nalézt v aktuálním repozitáři
- Frontendu - [Nachází se v tomto repozitáři](https://gitlab.fit.cvut.cz/nebesluk/tjv_semestral_client)

Backend je nakonfigurovaný k použití s PostgreSQL databází v Dockeru, tu lze spustit příkazem `docker compose up`. Ve zdrojích aplikace _(src/main/resources)_ lze najít ukázkový soubor s předvytvořenými daty. Jsou zde připraveni dva uživatelé, Lumir a Esmeralda s předpřiravenými nabídkami a požadavky. Oba tito uživatelé mají totožné heslo, a to "1234". Kvůli kompatabilitě s testy je ovšem potřeba SQL soubor znefunkčnit, má tedy umělou příponu _.old_.

Oba projekty jsou dále implementovány s využitím Gradle a lze je tedy spustit sekvencí příkazů `./gradle build` a `./gradle bootRun`. Dále lze na backendu spustit testy pomocí příkazu `./gradle test`.

K implementaci frontendu je užito kombinace Jersey, Thymeleaf, BootStrap, SpringBoot a Spring Security. V projektu je realizovaná autentikace uživatelů za pomocí Spring Security a HttpBasic hlaviček u vybraný REST požadavků.

Ověření uživatele v klientské části probíhá REST požadavkem na server, který vrátí, zda je uživatel existující entita, či nikoli. Po úspěšném ověrění klientská aplikace vytvoří session s danými přihlašovacímí údaji, které jsou dále užity v ostatních REST požadavcích.

Tagy _(počeštile značky)_ jsem se rozhodl implementovat _staticky_, tedy tak, že uživatelé nemohou přidávat značky vlastní, ale musí užít značky poskytnuté systémem. Standardní CRUD operace jsou pro ně však připraveny.

V samotné aplikaci si uživatel bez přihlášení může:
- Prohlížet nabídky
- Vytvořit účet
- Zobrazit veřejný profil libovolného uživatele

Přihlášený uživatel pak dále může:
- Vytvářet nabídky/poptávky
- Přijímat/poskytovat _(tedy odpovídat)_ na existující nabídky/poptávky
- Upravovat své položky
- Upravovat svůj účet

---

Ikona chybějícího obrázku pochází z:
<a href="https://www.flaticon.com/free-icons/no-photo" title="no photo icons">No photo icons created by Icon.doit - Flaticon</a><br>
Logo webu bylo poskytnuto kamarádem grafikem:
Simon Chaloupka @ https://instagram.com/simonhytte?igshid=YmMyMTA2M2Y=