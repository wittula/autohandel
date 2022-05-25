# Autohandel 
Treść zadania - link do źródła: [docs.google.com](https://docs.google.com/document/d/1-oZzFmFDqNEF8U1cQiI6zizMwf2sdXHuasmUoJcUy-k/edit#heading=h.kpjw13xhmrxb)

## Ocena
Zadanie powinno być wykonane jako nowy projekt w IntelliJ i osobne repozytorium na github. Częściowe wykonanie zadania umożliwia otrzymanie części punktów, ale oceniane będą tylko działające funkcjonalności. Wykonanie zadania podstawowego umożliwia uzyskanie 70% maksymalnej liczby punktów. Kolejne 30% można zdobyć realizując funkcjonalności dodatkowe.

## Opis zadania - część podstawowa

Napisz program w formie gry tekstowej symulującej prace właściciela komisu samochodowego.

Zaczynasz posiadając określoną ilość gotówki, nie posiadasz żadnych samochodów, ale masz dostęp do bazy kilkunastu aut, które możesz kupić. Baza aut do kupienia powinna być generowana losowo. Możesz przygotować wcześniej większą bazę pojazdów, z których część będzie losowo wybierana, lub napisać generator pojazdów. Baza jest uzupełniana nowymi samochodami po każdym zakupie.
Każde auto ma określoną:
* wartość, 
* markę, 
* przebieg, 
* kolor,
* segment (premium/standard/budget)

 i zestaw 5 elementów, które mogą być sprawne lub wymagać naprawy.

Naprawa każdego z elementów kosztuje, ale też podnosi wartość samochodu. 
* Hamulce - podnoszą wartość auta o 10%
* Zawieszenie - podnosi wartość auta o 20%
* Silnik - podnosi wartość auta o 100%
* Karoseria - podnosi wartość auta o 50%
* Skrzynia biegów - 50%

Dodatkowo, część samochodów to samochody dostawcze dla których istotny jest rozmiar przestrzeni ładunkowej.

Naprawę każdego z elementów możesz zlecić 3 różnym mechanikom. 
* Janusz - ma najdroższe ceny ale 100% gwarancję
* Marian - bierze zdecydowanie mniej niż Janusz, ale masz 10% szans, że nie uda mu się naprawić samochodu i konieczna będzie interwencja Janusza
* Adrian - jest najtańszy, ale masz 20% szans, że nie uda mu się naprawić i 2% szans, że zepsuje coś innego podczas naprawy

Koszty napraw powinny zależeć od marki pojazdu i części, która ma zostać naprawiona.
	
Dodatkowo każdy samochód musisz umyć i zapłacić 2% podatku od wartości przy zakupie i przy sprzedaży.

W grze dostępna jest baza potencjalnych klientów. Początkowo jest to kilka osób, ale możesz wydawać pieniądze na kampanię marketingową, aby powiększyć ich ilość. Wykonanie jednej udanej transakcji generuje 2 potencjalnych klientów bez dodatkowych wydatków. Ogłoszenie w lokalnej gazecie powoduje dopływ losowej grupy kilku nowych klientów, ale kosztuje. Możesz też zainwestować w reklamę w internecie, która jest tańsza od gazety, ale przynosi jednego nowego potencjalnego klienta. 

Niektórych klientów interesują samochody osobowe a innych dostawcze. Każdy klient posiada określoną ilość gotówki, dwie marki pojazdów, które go interesują, niewielka grupa akceptuje zakup uszkodzonego pojazdu, część zgodzi się na niesprawne zawieszenie, ale większość z nich kupi tylko w pełni sprawne auto. Możesz przygotować wcześniej większą listę potencjalnych klientów lub napisać generator.

## Opcje dostępne dla użytkownika
* przeglądaj bazę samochodów do kupienia 
* kup samochód
* przeglądaj bazę posiadanych samochodów
* napraw samochód
* przejrzyj potencjalnych klientów
* sprzedaj samochód za określoną cenę potencjalnemu klientowi
* kup reklamę
* sprawdź stan konta
* sprawdź historię transakcji
* sprawdź historię napraw każdego pojazdu
* sprawdź sumę kosztów napraw i mycia dla każdego z posiadanych pojazdów

## Cel gry
Podwoić stan konta w jak najmniejszej liczbie ruchów. Jeden ruch to zakup auta/sprzedaż auta/naprawienie jednego elementu/dodanie jednej reklamy. Przeglądanie stanu konta, historii transakcji, baz klientów, posiadanych pojazdów i pojazdów dostępnych do kupienia nie oznacza wykorzystania ruchu.

## Funkcjonalności dodatkowe
1. Dodaj obsługę sprzedaży motocykli (10%) - tańsze naprawy ale mniej chętnych klientów niż na samochody osobowe i dostawcze.
2. Dodaj obsługę sprzedaży pojazdów na raty (10%) - klient może kupić droższy samochód, ale pieniądze dostajesz w 10 ratach, po każdym ruchu.
3. Dodaj możliwość gry na dwóch graczy, grających na zmianę (10%) - wasze komisy są obok siebie więc baza dostępnych pojazdów i dostępnych klientów jest wspólna, co gorsza wydatki na reklamę ponoszone przez jednego gracza dodają potencjalnych klientów do wspólnej puli. Gra powinna wskazywać zwycięzcę.
4. \* dla chętnych - dodaj możliwość gry dla dowolnej liczby graczy - na początku rozgrywki podajemy liczbę graczy i imiona każdego z nich.
