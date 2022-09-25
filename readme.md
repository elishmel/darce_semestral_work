# Projekt Dárce

**Dárce je portál pro lidi. Pro všechny. Bez rozdílu.**

## Téma práce

Pro toto téma semestrální práce jsem se rozhodl v *(částečné)* návaznosti na téma mé semestrální práce v předmětu BI-DBS, kde jsem tvořil databázi pro fiktivní restauraci **Ahmed kebab**. Nejprve jsem chtěl plně využít tuto již existující databázi a vytvořit k ním systém na objednávky jídel. Ovšem, v rozsahu předmětu se mi toto rozhodnutí zdálo jako příliš strohé. Dospěl jsem tedy k závěru, že je potřeba se nad tématem znovu zamyslet a nakonec jsem zvolil portál **Dárce**. Tento portál slouží fiktivní neziskové organizaci  Dárce, která spojuje lidi v nouzi s lidmi s přebytkem a dobrým srdcem. Tato platforma uživatelům umožnuje vystavit nabídku na nějaký předmět či potravinu, pro kterou již nemají užití, nebo kterou se rozhodli zakoupit a darovat ostatním. Uživatelé, kteři nějakou formu materiální pomoci potřebují, mohou prohledávat seznam nabízeného zboží a vybrat si, co potřebují. Případně, pokud se předmět v aktuální nabídce nenachází, uživatel si může vytvořit "žádost" o specifickou položku.

## Konceptuální model

<img src="https://gitlab.fit.cvut.cz/nebesluk/tjv_semestral_work/-/raw/media/images/dbs_scheme.jpg?raw=true" alt="Database scheme" Width="450" Heihght="300">

## Návrh systému

Systém jsem se rozhodl navrhnout tak, že pokud ještě neexistuje nabídka na zboží, které se uživatel chystá nabízet, vytvoří zcela novou nabídku. Pokud ovšem již zboží, které se uživatel chystá nabídnout exituje, může u něho zakliknout tlačítko "Také nabízím" a přidá se do seznamu uživatelů, kteří danou položku nabízí. V případě poptávky po zboží tento proces funguje obdobně. Více uživatelů tedy může nabízet více zboží a stejně tak více zboží poptávat.

## Komplexní dotaz

Komplexní dotaz nad databází bude mít podobu "Všechny nabídky od uživatele _____ s tagem _____" .

----

***Disclaimer:** Projekt Dárce je pracovní název, není finální a v průběhu vývoje se očekávají změny.<br> Projekt Dárce není chráněný žádnou ochranou známkou. Schoda s libovolnou existující fyzickou či právní entitou je čistě náhodná.*
